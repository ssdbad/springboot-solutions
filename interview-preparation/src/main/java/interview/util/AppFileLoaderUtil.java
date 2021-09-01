package interview.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class AppFileLoaderUtil {
	public static String getResourceFileAsString(String fileName) {
        InputStream is = getResourceFileAsInputStream(fileName);
        if (is != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            return (String)reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } else {
            throw new RuntimeException("resource not found");
        }
    }
    
    public static InputStream getResourceFileAsInputStream(String fileName) {
        ClassLoader classLoader = AppFileLoaderUtil.class.getClassLoader();
        return classLoader.getResourceAsStream(fileName);
    }
}