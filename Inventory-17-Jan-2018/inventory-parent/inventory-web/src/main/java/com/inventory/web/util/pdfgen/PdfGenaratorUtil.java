package com.inventory.web.util.pdfgen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;
/**
 * 
 * @author ES002
 *
 */

@Component
public class PdfGenaratorUtil {
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private ServletContext servletContext;

	public String createPdf(String templateName, Map<Object, Object> map)
			throws Exception {
		Assert.notNull(templateName, "The templateName can not be null");
		Context ctx = new Context();
		if (map != null) {
			Iterator<Entry<Object, Object>> itMap = map.entrySet().iterator();
			while (itMap.hasNext()) {
				Map.Entry<Object, Object> pair = itMap.next();
				ctx.setVariable(pair.getKey().toString(), pair.getValue());
			}
		}
		String processedHtml = templateEngine.process(templateName, ctx);
		FileOutputStream os = null;
		String fileName = "POLIST ";
		try {
			final File outputFile = File.createTempFile(fileName, ".pdf",new File(servletContext.getRealPath("/WEB-INF/classes/static/purchasepdf/")));
			os = new FileOutputStream(outputFile);
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocumentFromString(processedHtml);
			renderer.layout();
			renderer.createPDF(os, false);
			renderer.finishPDF();
			System.out.println("PDF created successfully");
			return outputFile.toString();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) { 
				}
			}
		}

	}
}
