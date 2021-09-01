package interview.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.FileUtils;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;

public class FileZipUtil {
    private static final Logger logger = LogManager.getLogger(FileZipUtil.class);
    static SecureRandom srandom = new SecureRandom();
    private static String baseFolder = "tempspace/";

    public static void writeToFile(String filename, String content) {
        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(content);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    static private void processFile(Cipher ci, InputStream in, OutputStream out)
            throws javax.crypto.IllegalBlockSizeException, javax.crypto.BadPaddingException, java.io.IOException {
        byte[] ibuf = new byte[1024];
        int len;
        while ((len = in.read(ibuf)) != -1) {
            byte[] obuf = ci.update(ibuf, 0, len);
            if (obuf != null)
                out.write(obuf);
        }
        byte[] obuf = ci.doFinal();
        if (obuf != null)
            out.write(obuf);
    }

    // static private void processFile(Cipher ci, String inFile, String outFile)
    // throws javax.crypto.IllegalBlockSizeException,
    // javax.crypto.BadPaddingException, java.io.IOException {
    // try (FileInputStream in = new FileInputStream(inFile); FileOutputStream out =
    // new FileOutputStream(outFile)) {
    // processFile(ci, in, out);
    // }
    // }

    static private void doGenkey(String fileBase) throws java.security.NoSuchAlgorithmException, java.io.IOException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        KeyPair kp = kpg.generateKeyPair();
        try (FileOutputStream out = new FileOutputStream(fileBase + ".key")) {
            out.write(kp.getPrivate().getEncoded());
        }
        try (FileOutputStream out = new FileOutputStream(fileBase + ".pub")) {
            out.write(kp.getPublic().getEncoded());
        }
    }

    // static private void doEncrypt(String pvtKeyFile, String inputFile) throws
    // Exception {
    // byte[] bytes = Files.readAllBytes(Paths.get(pvtKeyFile));
    // PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(bytes);
    // KeyFactory kf = KeyFactory.getInstance("RSA");
    // PrivateKey pvt = kf.generatePrivate(ks);

    // Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
    // cipher.init(Cipher.ENCRYPT_MODE, pvt);
    // processFile(cipher, inputFile, inputFile + ".enc");
    // }

    // static private void doDecrypt(String pubKeyFile, String inputFile) throws
    // Exception {
    // byte[] bytes = Files.readAllBytes(Paths.get(pubKeyFile));
    // X509EncodedKeySpec ks = new X509EncodedKeySpec(bytes);
    // KeyFactory kf = KeyFactory.getInstance("RSA");
    // PublicKey pub = kf.generatePublic(ks);

    // Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
    // cipher.init(Cipher.DECRYPT_MODE, pub);
    // processFile(cipher, inputFile, inputFile + ".ver");
    // }

    static public String doEncryptRSAWithAES(String pvtKeyFile, String inputFile) throws Exception {
        byte[] bytes = Files.readAllBytes(Paths.get(pvtKeyFile));
        PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(bytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PrivateKey pvt = kf.generatePrivate(ks);

        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        SecretKey skey = kgen.generateKey();

        byte[] iv = new byte[128 / 8];
        srandom.nextBytes(iv);
        IvParameterSpec ivspec = new IvParameterSpec(iv);

        try (FileOutputStream out = new FileOutputStream(inputFile + ".enc")) {
            {
                Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                cipher.init(Cipher.ENCRYPT_MODE, pvt);
                byte[] b = cipher.doFinal(skey.getEncoded());
                out.write(b);
                System.err.println("AES Key Length: " + b.length);
            }
            out.write(iv);
            System.err.println("IV Length: " + iv.length);

            Cipher ci = Cipher.getInstance("AES/CBC/PKCS5Padding");
            ci.init(Cipher.ENCRYPT_MODE, skey, ivspec);
            try (FileInputStream in = new FileInputStream(inputFile)) {
                processFile(ci, in, out);
            }
        }
        return inputFile + ".enc";
    }

    static public void doDecryptRSAWithAES(String pubKeyFile, String inputFile) throws Exception {
        byte[] bytes = Files.readAllBytes(Paths.get(pubKeyFile));
        X509EncodedKeySpec ks = new X509EncodedKeySpec(bytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PublicKey pub = kf.generatePublic(ks);

        try (FileInputStream in = new FileInputStream(inputFile)) {
            SecretKeySpec skey = null;
            {
                Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                cipher.init(Cipher.DECRYPT_MODE, pub);
                byte[] b = new byte[256];
                in.read(b);
                byte[] keyb = cipher.doFinal(b);
                skey = new SecretKeySpec(keyb, "AES");
            }

            byte[] iv = new byte[128 / 8];
            in.read(iv);
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            Cipher ci = Cipher.getInstance("AES/CBC/PKCS5Padding");
            ci.init(Cipher.DECRYPT_MODE, skey, ivspec);

            try (FileOutputStream out = new FileOutputStream(inputFile + ".json")) {
                processFile(ci, in, out);
            }
        }
    }

    static public void zipPasswordProtect(String password, ArrayList<File> inputFile, String destFileName)
            throws Exception {
        // Creating encryption zipParameters
        // for passward protection
        ZipParameters zipParameters = new ZipParameters();

        // Setting encryption files
        zipParameters.setCompressionMethod(CompressionMethod.DEFLATE);
        zipParameters.setCompressionLevel(CompressionLevel.NORMAL);

        // Setting encryption of files to true
        zipParameters.setEncryptFiles(true);

        // Setting encryption method
        zipParameters.setEncryptionMethod(EncryptionMethod.AES);

        // Set the key strength
        zipParameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);

        // *********CREATE ZIP FILE***************

        // Zip file name

        // Creating ZIP file
        ZipFile zipFile = new ZipFile(destFileName);

        // // Creating list of files to be added to ZIP file
        // ArrayList<File> list = new ArrayList<File>();
        // // Add SPECIFIC files
        // list.add(new File(inputFile));

        // Set the password
        zipFile.setPassword(password.toCharArray());

        // Pass and ZIP parameters
        // for Zip file to be created
        zipFile.addFiles(inputFile, zipParameters);

        // Print the destination in the local directory
        // where ZIP file is created
    }

    public static File[] unzipPasswordProtect(String password, Path sourceFilePath, String destinationPath)
            throws Exception {
        String extractFolderPath = sourceFilePath.toFile().getPath();
        ZipFile zipFile = new ZipFile(extractFolderPath);
        if (zipFile.isEncrypted()) {
            zipFile.setPassword(password.toCharArray());
        }
        zipFile.extractAll(destinationPath);
        File extractFolder = new File(destinationPath);
        return extractFolder.listFiles();
    }

    public static void tempCleanUp() throws Exception {
        long cutOff = System.currentTimeMillis() - (1 * 60 * 60 * 1000);
        Files.list(Paths.get(baseFolder)).filter(path -> {
            try {
                return Files.isDirectory(path) && Files.getLastModifiedTime(path).to(TimeUnit.MILLISECONDS) < cutOff;
            } catch (IOException ex) {
                logger.error("Error occured while temp cleanup" + ex.getMessage());
                return false;
            }
        }).forEach(path -> {
            try {
                FileUtils.deleteDirectory(path.toFile());
                // Files.delete(path);
            } catch (IOException ex) {
                logger.error("Error occured while temp cleanup" + ex.getMessage());
            }
        });
    }
}
