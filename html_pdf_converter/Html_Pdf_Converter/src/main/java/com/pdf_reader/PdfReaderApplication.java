package com.pdf_reader;

import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thymeleaf.expression.Numbers;
import org.thymeleaf.expression.Strings;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

//@SpringBootApplication
public class PdfReaderApplication {
    public static void main(String[] args) throws FileNotFoundException, IOException {
//        SpringApplication.run(PdfReaderApplication.class, args);
//        HtmlConverter.convertToPdf(new FileInputStream("/Users/gautamraj/Documents/Github-Repo/springboot-solutions/springboot-solutions/html_pdf_converter/Html_Pdf_Converter/src/main/resources/lmg/order_delivered.html"), new FileOutputStream("iText Pdf creator.pdf"));
//        System.out.println("Pdf created");
        int input = 123456789;

        Numbers format = new Numbers(Locale.ENGLISH);
        System.out.println(format.formatDecimal(input, 0, "COMMA", 2, "POINT"));
        System.out.println(format.formatCurrency(input));

        Strings str = new Strings(Locale.ENGLISH);
        System.out.println(str.substring("2022-06-03T04:27:39.148Z", 0, 10));
    }
}