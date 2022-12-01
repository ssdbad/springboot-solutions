package com.html_pdf_converter;

import com.lowagie.text.DocumentException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@SpringBootApplication
public class HtmlPdfConverterApplication {

	public static void main(String[] args) throws IOException, DocumentException {
		SpringApplication.run(HtmlPdfConverterApplication.class, args);
		HtmlPdfConverterApplication thymeleaf2Pdf = new HtmlPdfConverterApplication();
		String html = thymeleaf2Pdf.parseThymeleafTemplate();
		thymeleaf2Pdf.generatePdfFromHtml(html);
		System.out.println("**************************PDF Generated**********************");
	}

	public void generatePdfFromHtml(String html) throws IOException, DocumentException {
//		String outputFolder = "//Users/gautamraj/Documents/Github-Repo/springboot-solutions/springboot-solutions/html_pdf_converter/Html_Pdf_Converter/pdf_output/" +System.currentTimeMillis()+"_order_delivered.pdf";
		File fileOutput = new File("outputPdf");
		OutputStream outputStream = new FileOutputStream(fileOutput);

		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocumentFromString(html);
		renderer.layout();
		renderer.createPDF(outputStream);

//		sendgrifd.send(addAtaachment(fileoutput));

		outputStream.close();
	}

	private String parseThymeleafTemplate() {
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);

		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);

		Context context = new Context();
		context.setVariable("to", "PDF Converter");

		return templateEngine.process("/lmg/order_delivered", context);
	}

}
