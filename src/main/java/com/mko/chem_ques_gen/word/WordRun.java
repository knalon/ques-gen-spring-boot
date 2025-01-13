package com.mko.chem_ques_gen.word;

import java.math.BigInteger;
import java.util.List;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.VerticalAlign;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class WordRun {
	
	WordHelper helper = new WordHelper();
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
	
	public XWPFParagraph runParagraph(XWPFDocument document, String para, String type) {
        
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
	
	public void runMultipleSubStrings(XWPFParagraph paragraph, List<String> subStrings) {
		for(String s: subStrings) {
			if(s.charAt(0) == '|') {
				this.runSubScript(paragraph, s.substring(1));
			}else if(s.charAt(0) == '^') {
				this.runSuperScript(paragraph, s.substring(1));
			}else if(s.charAt(0) == '$') {
				this.runWithKey(paragraph, s.substring(1));
			}else {
				this.runNormal(paragraph, s);
			}
		}
	}
}
