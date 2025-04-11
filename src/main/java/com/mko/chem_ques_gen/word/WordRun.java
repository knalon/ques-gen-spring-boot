package com.mko.chem_ques_gen.word;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.VerticalAlign;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mko.chem_ques_gen.entities.Image;
import com.mko.chem_ques_gen.service.ImageService;

@Component
public class WordRun {

	@Autowired
    private ImageService imageService;
	
    private final WordHelper helper = new WordHelper();

	//----------------run methods
	
	public void runTitle(XWPFDocument document, Integer tableWidth, List<String> titleString) {
		for(int i = 0; i<2; i++) {
			
			XWPFParagraph titleText = document.createParagraph();
			titleText.setAlignment(ParagraphAlignment.CENTER);
			
			XWPFRun titleRun = titleText.createRun();
			titleRun.setText(titleString.get(i));
			titleRun.setBold(true);
			titleRun.setFontFamily("Times New Roman");
			titleRun.setFontSize(12);
		}
		
		
		titleString.remove(0);
		titleString.remove(0);
		helper.formatTitleTable(document, tableWidth, titleString);
		
	}

	public void runMainQues(XWPFDocument document, String para, String mark) {
		// Create table with specific row and column
		XWPFTable table = helper.createBorderlessRowColumnWidthTable(document, 1, 2, 10800);
		
		XWPFTableRow row = table.getRow(0);
		System.out.println(table.getColBandSize());
		
		XWPFTableCell[] cells = {row.getCell(0), row.getCell(1)};
		ParagraphAlignment[] alignments = {ParagraphAlignment.LEFT, ParagraphAlignment.RIGHT};
		Integer[] cellWidths = {9600, 1200};
		String[] contexts = {para, mark};
		
		for (int i = 0; i < cells.length; i++) {
		    XWPFTableCell cell = cells[i];
		    // Set cell width

		    cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(cellWidths[i]));
		    
		    // Remove default paragraph
		    cell.removeParagraph(0);

		    // Create paragraph and set alignment
		    XWPFParagraph paragraph = cell.addParagraph();
		    paragraph.setAlignment(alignments[i]);
		    paragraph.setSpacingBefore(3);
		    paragraph.setSpacingAfter(6);
		    
		    // Create run and set properties
		    XWPFRun run = paragraph.createRun();
		    run.setText(contexts[i]);
		    run.setFontFamily("Times New Roman");
		    run.setBold(true);
		    run.setFontSize(12);
		}
		
	}
	
	public XWPFParagraph runParagraph(XWPFDocument document, String para, String type) throws InvalidFormatException, IOException {
        
		XWPFParagraph paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.BOTH);
		paragraph.setSpacingBefore(3);
		paragraph.setSpacingAfter(3);

		// Define a tab stop at 1/2 of 0.5 inches (360 twips)
		helper.setTab(paragraph, 360);
		
		// Define a tab stop at 0.5 inches (720 twips)
		helper.setTab(paragraph, 720);
		
		if(type.equals("MCQ")) {
			helper.setTab(paragraph, 2880);
			helper.setTab(paragraph, 5040);
			helper.setTab(paragraph, 7200);			
		}
		
		runMultipleSubStrings(paragraph, helper.extractSubStrings(para));		
		return paragraph;
       
	}

	
	
	
	public void runSubScript(XWPFParagraph paragraph, String text) {
		XWPFRun run = paragraph.createRun();
		run.setText(text);
		run.setFontFamily("Times New Roman");
		run.setFontSize(12);
		run.setSubscript(VerticalAlign.SUBSCRIPT); // Set subscript
	}
	public void runSuperScript(XWPFParagraph paragraph, String text) {
		XWPFRun run = paragraph.createRun();
		run.setText(text);
		run.setFontFamily("Times New Roman");
		run.setFontSize(12);
		run.setSubscript(VerticalAlign.SUPERSCRIPT); // Set subscript
	}
	public void runNormal(XWPFParagraph paragraph, String text) {
		XWPFRun run = paragraph.createRun();
		run.setText(text);
		run.setFontFamily("Times New Roman");
		run.setFontSize(12);
	}	
	public void runWithKey(XWPFParagraph paragraph, String text) {
		XWPFRun run = paragraph.createRun();
		if(text.equals("br")) {
			run.addCarriageReturn();
		}else if(text.equals("t")) {
			run.addTab();
		}
	}
	
	public void runWithImage(XWPFParagraph paragraph, String imageName) throws InvalidFormatException, IOException {
	    if (paragraph == null || imageName == null || imageName.isEmpty()) {
	        throw new IllegalArgumentException("Paragraph or image name cannot be null/empty.");
	    }

	    Image image = imageService.getImageByName(imageName);
	    if (image == null || image.getImageData() == null) {
	        throw new IOException("Image not found or image data is null for name: " + imageName);
	    }

	    XWPFRun run = paragraph.createRun();
	    try (InputStream byteInputStream = new ByteArrayInputStream(image.getImageData())) {
//	    	 BufferedImage bufferedImage = ImageIO.read(byteInputStream);
//	    	 
//	        int originalWidthPixels = bufferedImage.getWidth();
//	        int originalHeightPixels = bufferedImage.getHeight();
//
//	            // Convert pixels to EMUs
//	        int widthEMU = Units.pixelToEMU(originalWidthPixels);
//	        int heightEMU = Units.pixelToEMU(originalHeightPixels);
	        int widthEMU = image.getWidthInCm()*360000;
	        int heightEMU = image.getHeightInCm()*360000;
	        System.out.println("widthEMU : "+widthEMU);
	        System.out.println("heightEMU : "+heightEMU);
	        
	        run.addPicture(byteInputStream, XWPFDocument.PICTURE_TYPE_PNG, imageName, widthEMU, heightEMU);
	    }catch(Exception e) {
	    	System.out.println("inside exception");
	    	e.printStackTrace();
	    }
	}

	public void runMultipleSubStrings(XWPFParagraph paragraph, List<String> subStrings) throws InvalidFormatException, IOException {
		for(String s: subStrings) {
			if(s.charAt(0) == '|') {
				//    |sub: is subscript
				this.runSubScript(paragraph, s.substring(1));
			}else if(s.charAt(0) == '^') {
				//    ^sup: is superscript
				this.runSuperScript(paragraph, s.substring(1));
			}else if(s.charAt(0) == '$') {
				//    $t: is tab
				//    $br: is new line
				this.runWithKey(paragraph, s.substring(1));
			}else if(s.charAt(0) == '#'){
				// 	  #image.png: is image
				runWithImage(paragraph, s.substring(1));
			}else {
				this.runNormal(paragraph, s);
			}
		}
	}
}
