package com.mko.chem_ques_gen.word;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xwpf.usermodel.LineSpacingRule;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTabStop;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTabJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

public class WordHelper {
	
	
	public void formatTitleTable(XWPFDocument document,  Integer tableWidth, List<String> stringList) {
		
		// Create table with specific row and column
		XWPFTable table = this.createBorderlessRowColumnWidthTable(document, 1, 3, 10800);
				
		// Access the first row
		XWPFTableRow row = table.getRow(0);

		// Calculate equal width for each cell
		int cellWidth = tableWidth / 3; // Divide table width (9360 - for default) equally by 3
		
		// Cell : Set equal width

		XWPFTableCell[] cells = {row.getCell(0), row.getCell(1), row.getCell(2)};
		ParagraphAlignment[] alignments = {ParagraphAlignment.LEFT, ParagraphAlignment.CENTER, ParagraphAlignment.RIGHT};

		for (int i = 0; i < cells.length; i++) {
		    XWPFTableCell cell = cells[i];
		    // Set cell width
		    cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(cellWidth));
		    
		    // Remove default paragraph
		    cell.removeParagraph(0);

		    // Create paragraph and set alignment
		    XWPFParagraph paragraph = cell.addParagraph();
		    paragraph.setAlignment(alignments[i]);
		    paragraph.setSpacingAfter(0);

		    // Create run and set properties
		    XWPFRun run = paragraph.createRun();
		    run.setText(stringList.get(i));
		    run.setFontFamily("Times New Roman");
		    run.setBold(true);
		    run.setFontSize(12);
		}
	
	};
	
	//---------------helper methods
	public void setPageIndent(XWPFDocument document, Integer twips) {
    	CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
    	CTPageMar pageMar = sectPr.addNewPgMar();
    	pageMar.setTop(BigInteger.valueOf(720));    // 0.5 inch
    	pageMar.setBottom(BigInteger.valueOf(720)); // 0.5 inch
    	pageMar.setLeft(BigInteger.valueOf(720));   // 0.5 inch
    	pageMar.setRight(BigInteger.valueOf(720));  // 0.5 inch
	}
	
	public void setLeftHangingIndentation(XWPFParagraph paragraph, Integer indent) {
		paragraph.setIndentationLeft(indent);
		paragraph.setIndentationHanging(indent); // 0.5 inches in twips
		
	}

	public XWPFTable createBorderlessRowColumnWidthTable(XWPFDocument document, Integer row, Integer col, Integer tableWidth) {
		
		XWPFTable table = document.createTable(row,  col);
		
        // Set table borders (optional, modifies appearance)
        table.setInsideHBorder(XWPFTable.XWPFBorderType.NONE, 0, 0, "000000"); // Horizontal inner borders
        table.setInsideVBorder(XWPFTable.XWPFBorderType.NONE, 0, 0, "000000"); // Vertical inner borders
        table.setTopBorder(XWPFTable.XWPFBorderType.NONE, 0, 0, "000000");
        table.setBottomBorder(XWPFTable.XWPFBorderType.NONE, 0, 0, "000000");
        table.setLeftBorder(XWPFTable.XWPFBorderType.NONE, 0, 0, "000000");
        table.setRightBorder(XWPFTable.XWPFBorderType.NONE, 0, 0, "000000");
        
		// Set the table width to full page width
		CTTbl ctTbl = table.getCTTbl();
		CTTblPr pr = ctTbl.getTblPr();
		if (pr == null) pr = ctTbl.addNewTblPr();
		CTTblWidth tblWidth = pr.addNewTblW();
		tblWidth.setW(BigInteger.valueOf(tableWidth)); // 10800 for TRBL 0.5" - 100% width
		tblWidth.setType(STTblWidth.DXA);
        return table;
	}

	public void runEmptyOnePxLine(XWPFDocument document) {
		XWPFParagraph paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.BOTH);
		paragraph.setSpacingBefore(0);
		paragraph.setSpacingAfter(0);
		paragraph.setSpacingBetween(.7, LineSpacingRule.EXACT);
		
		
		// Set a 1 px line with no text
		XWPFRun run = paragraph.createRun();
		run.setText(" ");
		run.setFontSize(1);

	}

	public void setTab(XWPFParagraph paragraph, Integer twip) {
		CTTabStop tabStop2 = paragraph.getCTP().addNewPPr().addNewTabs().addNewTab();
		tabStop2.setVal(STTabJc.LEFT); // Left-aligned tab
		tabStop2.setPos(BigInteger.valueOf(twip)); // Position in twips
	}

	public List<String> extractSubStrings(String paragraph) {
		
		 List<String> substrings = new ArrayList<>();
	        StringBuilder currentSubstring = new StringBuilder();

	        boolean isSpecialMode = false; // Tracks whether we're inside `_` or `^` sections

	        for (int i = 0; i < paragraph.length(); i++) {
	            char currentChar = paragraph.charAt(i);

	            if (currentChar == '|' || currentChar == '^' || currentChar == '$') {
	                // Add the current substring to the list before starting special mode
	                if (currentSubstring.length() > 0) {
	                    substrings.add(currentSubstring.toString());
	                    currentSubstring.setLength(0); // Clear the buffer
	                }
	                isSpecialMode = true; // Enter special mode
	                currentSubstring.append(currentChar); // Add the marker to the substring
	            } else if (isSpecialMode && currentChar == ':') {
	                // End special mode when `:` is encountered
	                substrings.add(currentSubstring.toString()); // Add the special substring
	                currentSubstring.setLength(0); // Clear the buffer
	                isSpecialMode = false; // Exit special mode
	            } else {
	                // Add characters to the current substring
	                currentSubstring.append(currentChar);
	            }
	        }

	        // Add the remaining substring (if any)
	        if (currentSubstring.length() > 0) {
	            substrings.add(currentSubstring.toString());
	        }

	        return substrings;
	}
}
