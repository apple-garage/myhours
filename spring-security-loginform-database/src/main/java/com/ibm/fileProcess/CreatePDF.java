package com.ibm.fileProcess;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.RequestBody;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

public class CreatePDF {
	
	private static Font TIME_ROMAN = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.BOLD);
	private static Font TIME_ROMAN_SMALL = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	final static String[] reports= {"Work_details","More_than_forty","Less_than_forty","Without_holidays","More_one_proyect"};
	
	public static Document createPDF(String file, JSONArray array, String filename, int report) {
		Document document = null;
		try {
			document = new Document();
			 PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
		     TableHeader event = new TableHeader();
		     writer.setPageEvent(event);
			document.open();
			addMetaData(document);
			addTitlePage(document, filename);
			switch(report){
			case 1:
			case 2: createDiffThanFortyTable(document, array);
				break;
			case 3: createNoHolidaysTable(document, array);
            	break;
			case 4: createMultipleProjectsTable(document, array);
            	break;
            default: createTable(document, array);
            	break;
			}
			document.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		return document;
	}

	public static void exportPDF(HttpServletResponse response, HttpServletRequest request, @RequestBody String data, int report){
		try{
			final ServletContext servletContext = request.getSession().getServletContext();
		    final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		    final String temperotyFilePath = tempDirectory.getAbsolutePath();

			JSONParser jsonParser=new JSONParser();
			JSONArray array = new JSONArray();
			array = (JSONArray) jsonParser.parse(data);//.substring(0, data.lastIndexOf("]")+1)
		    
		    String fileName = reports[report]+".pdf";
		    response.setContentType("application/pdf");
		    response.setHeader("Content-disposition", "attachment; filename="+ fileName);

	        CreatePDF.createPDF(temperotyFilePath+"\\"+fileName, array, fileName, report);
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        baos = CreatePDF.convertPDFToByteArrayOutputStream(temperotyFilePath+"\\"+fileName);
	        OutputStream os = response.getOutputStream();
	        baos.writeTo(os);
	        os.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static ByteArrayOutputStream convertPDFToByteArrayOutputStream(String fileName) {
		InputStream inputStream = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			inputStream = new FileInputStream(fileName);
			byte[] buffer = new byte[1024];
			baos = new ByteArrayOutputStream();

			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				baos.write(buffer, 0, bytesRead);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return baos;
	}
	
	private static void addMetaData(Document document) {
		document.addTitle("PDF report");
		document.addSubject("Generate PDF report");
	}

	private static void addTitlePage(Document document, String filename) throws DocumentException {
		try{
		Paragraph preface = new Paragraph();
		creteEmptyLine(preface, 1);
		preface.add(new Paragraph(filename, TIME_ROMAN));
        Image image1 = Image.getInstance("//Users/apple02/git/myhours/spring-security-loginform-database/src/main/webapp/images/IBM_logo.png");
        image1.scaleAbsolute(70f, 40f);
        document.add(image1);
//		creteEmptyLine(preface, 1);
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
//		preface.add(new Paragraph("Report created on "
//				+ simpleDateFormat.format(new Date()), TIME_ROMAN_SMALL));
		document.add(preface);
		}catch(Exception ex){ex.printStackTrace();}
	}

	private static void creteEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
	
	private static void createTable(Document document, JSONArray array) throws DocumentException {
		Paragraph paragraph = new Paragraph(); 
		BaseColor color = new BaseColor(192, 192, 192);
		creteEmptyLine(paragraph, 2);
		document.add(paragraph);
		PdfPTable table = new PdfPTable(7);
		table.setWidthPercentage(100);
		
		PdfPCell c1 = new PdfPCell(new Phrase("ID"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(color);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("NAME"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(color);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("COUNRTY"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(color);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("MANAGER"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(color);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("WEEK"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(color);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("PROJECT"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(color);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("HOURS"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(color);
		table.addCell(c1);
		table.setHeaderRows(1);

		JSONObject registry = new JSONObject();
		for(int i = 0;i<array.size();i++){
			registry = (JSONObject) array.get(i);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(registry.get("id").toString());
			table.addCell(registry.get("name").toString());
			table.addCell(registry.get("country").toString());
			table.addCell(registry.get("manager").toString());
			table.addCell(registry.get("week").toString());
			table.addCell(registry.get("project").toString());
			table.addCell(registry.get("hours").toString());
		}
		
        float[] columnWidths = new float[] {12f, 25f, 15f, 20f, 15f, 15f, 12f};
        table.setWidths(columnWidths);
        
		document.add(table);
	}
	
	private static void createMultipleProjectsTable(Document document, JSONArray array)  throws DocumentException {
		Paragraph paragraph = new Paragraph(); 
		BaseColor color = new BaseColor(192, 192, 192);
		creteEmptyLine(paragraph, 2);
		document.add(paragraph);
		PdfPTable table = new PdfPTable(7);
		table.setWidthPercentage(100);
		
		PdfPCell c1 = new PdfPCell(new Phrase("ID"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(color);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("NAME"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(color);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("COUNRTY"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(color);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("MANAGER"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(color);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("WEEK"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(color);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("PROJECT"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(color);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("TOTAL"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(color);
		table.addCell(c1);
		table.setHeaderRows(1);

		JSONObject registry = new JSONObject();
		for(int i = 0;i<array.size();i++){
			registry = (JSONObject) array.get(i);
			JSONArray details = (JSONArray) registry.get("detail");
			JSONObject jsonDetail = new JSONObject();
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			
			for(int j=0;j<details.size();j++){
				table.addCell(registry.get("id").toString());
				table.addCell(registry.get("name").toString());
				table.addCell(registry.get("country").toString());
				table.addCell(registry.get("manager").toString());
				table.addCell(registry.get("week").toString());
				
				jsonDetail = (JSONObject) details.get(j);
				
				table.addCell(jsonDetail.get("assignment").toString());
//				table.addCell(jsonDetail.get("hours").toString());
				
				if(j==0){
					c1 = new PdfPCell(new Phrase(registry.get("totalHours").toString()));
					c1.setHorizontalAlignment(Element.ALIGN_CENTER);
					c1.setRowspan(details.size());
					table.addCell(c1);
				}
			}	
		}
		
		
        float[] columnWidths = new float[] {12f, 25f, 15f, 20f, 15f, 15f, 12f};
        table.setWidths(columnWidths);
        
		document.add(table);
		
	}

	private static void createNoHolidaysTable(Document document, JSONArray array)  throws DocumentException {
		Paragraph paragraph = new Paragraph(); 
		BaseColor color = new BaseColor(192, 192, 192);
		creteEmptyLine(paragraph, 2);
		document.add(paragraph);
		PdfPTable table = new PdfPTable(8);
		table.setWidthPercentage(100);
		
		PdfPCell c1 = new PdfPCell(new Phrase("ID"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(color);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("NAME"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(color);
		table.addCell(c1);

//		c1 = new PdfPCell(new Phrase("COUNRTY"));
//		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//		c1.setBackgroundColor(color);
//		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("MANAGER"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(color);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("WEEK"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(color);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("DESCRIPTION"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(color);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("DATES"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(color);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("ACTUAL HOURS"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(color);
		table.addCell(c1);
		table.setHeaderRows(1);
		
		c1 = new PdfPCell(new Phrase("REQUIRED HOURS"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(color);
		table.addCell(c1);
		table.setHeaderRows(1);

		JSONObject registry = new JSONObject();
		for(int i = 0;i<array.size();i++){
			registry = (JSONObject) array.get(i);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(registry.get("id").toString());
			table.addCell(registry.get("name").toString());
//			table.addCell(registry.get("country").toString());
			table.addCell(registry.get("manager").toString());
			table.addCell(registry.get("week").toString());
			table.addCell(registry.get("holiday").toString());
			table.addCell(registry.get("h_dates").toString());
			table.addCell(registry.get("hours").toString());
			table.addCell(registry.get("h_hours").toString());
		}
		
        float[] columnWidths = new float[] {10f, 18f, 18f, 14f, 18f, 14f, 14f, 14f};
        table.setWidths(columnWidths);
        
		document.add(table);
		
	}

	private static void createDiffThanFortyTable(Document document, JSONArray array)  throws DocumentException {
		Paragraph paragraph = new Paragraph(); 
		BaseColor color = new BaseColor(192, 192, 192);
		creteEmptyLine(paragraph, 2);
		document.add(paragraph);
		PdfPTable table = new PdfPTable(8);
		table.setWidthPercentage(100);
		
		PdfPCell c1 = new PdfPCell(new Phrase("ID"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(color);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("NAME"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(color);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("COUNRTY"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(color);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("MANAGER"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(color);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("WEEK"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(color);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("PROJECT"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(color);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("HOURS"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(color);
		table.addCell(c1);
		table.setHeaderRows(1);
		
		c1 = new PdfPCell(new Phrase("TOTAL"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(color);
		table.addCell(c1);
		table.setHeaderRows(1);
		
		JSONObject registry = new JSONObject();
		for(int i = 0;i<array.size();i++){
			registry = (JSONObject) array.get(i);
			JSONArray details = (JSONArray) registry.get("detail");
			JSONObject jsonDetail = new JSONObject();
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

			for(int j=0;j<details.size();j++){
				table.addCell(registry.get("id").toString());
				table.addCell(registry.get("name").toString());
				table.addCell(registry.get("country").toString());
				table.addCell(registry.get("manager").toString());
				table.addCell(registry.get("week").toString());
				
				jsonDetail = (JSONObject) details.get(j);
				
				table.addCell(jsonDetail.get("assignment").toString());
				table.addCell(jsonDetail.get("hours").toString());
				
				if(j==0){
					c1 = new PdfPCell(new Phrase(registry.get("totalHours").toString()));
					c1.setRowspan(details.size());
					table.addCell(c1);
				}
			}			
		}
		
        float[] columnWidths = new float[] {12f, 20f, 15f, 18f, 18f, 18f, 12f, 10f};
        table.setWidths(columnWidths);
        
		document.add(table);
		
	}

}


class TableHeader extends PdfPageEventHelper {
    /** The header text. */
    String header;
    /** The template with the total number of pages. */
    PdfTemplate total;

    /**
     * Allows us to change the content of the header.
     * @param header The new header String
     */
    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * Creates the PdfTemplate that will hold the total number of pages.
     * @see com.itextpdf.text.pdf.PdfPageEventHelper#onOpenDocument(
     *      com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
     */
    public void onOpenDocument(PdfWriter writer, Document document) {
        total = writer.getDirectContent().createTemplate(30, 16);
    }

    /**
     * Adds a header to every page
     * @see com.itextpdf.text.pdf.PdfPageEventHelper#onEndPage(
     *      com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
     */
    public void onEndPage(PdfWriter writer, Document document) {
        PdfPTable table = new PdfPTable(3);
        try {
            table.setWidths(new int[]{24, 24, 2});
            table.setTotalWidth(570);
            table.setLockedWidth(true);
            table.getDefaultCell().setFixedHeight(20);
            table.getDefaultCell().setBorder(Rectangle.TOP);
            table.addCell(header);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(String.format("Page %d of", writer.getPageNumber()));
            PdfPCell cell = new PdfPCell(Image.getInstance(total));
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
            table.writeSelectedRows(0, -1, 15, 26, writer.getDirectContent());
        }
        catch(DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }

    /**
     * Fills out the total number of pages before the document is closed.
     * @see com.itextpdf.text.pdf.PdfPageEventHelper#onCloseDocument(
     *      com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
     */
    public void onCloseDocument(PdfWriter writer, Document document) {
        ColumnText.showTextAligned(total, Element.ALIGN_LEFT,
                new Phrase(String.valueOf(writer.getPageNumber() - 1)), 2, 2, 0);
    }
}

