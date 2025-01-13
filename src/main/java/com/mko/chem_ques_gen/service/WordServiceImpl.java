package com.mko.chem_ques_gen.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.xmlbeans.XmlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mko.chem_ques_gen.entities.Paper;
import com.mko.chem_ques_gen.entities.wrapper.ChemQuestionWrapper;
import com.mko.chem_ques_gen.enums.QuestionType;
import com.mko.chem_ques_gen.word.WordHelper;
import com.mko.chem_ques_gen.word.WordRun;

@Service
public class WordServiceImpl implements WordService{
	
	@Autowired
	PaperServiceImpl paperService;
	
	WordRun run = new WordRun();
	WordHelper helper = new WordHelper();
	
	@Override
	public String createWord(Integer paperId) throws IOException, XmlException {
		
		Paper paper = paperService.findPaperById(paperId);
		
		// Save the document to a file
		String filePath = "C:\\Users\\mko\\Documents\\workspace-spring-tool-suite-4-4.18.1.RELEASE\\"
							+"chem_ques_gen_sprint_1.2\\word\\"
							+paper.getYear()+" "+paper.getMonth()+" "+paper.getTitle()+".docx";
    	
		List<String> titleString = new ArrayList<>(
    									List.of("UNIQUE Private High School",
    											paper.getTitle(),
    											paper.getGrade().toString(),
    											"CHEMISTRY",
    											"TIME Allowed (45 minutes)"));
		
		try (XWPFDocument document = new XWPFDocument()) {
			
        	// Set margins to 0.5 inches (720 twips)
			helper.setPageIndent(document, paperId);
			
			//creating title
			run.runTitle(document, 10800, titleString);
			helper.runEmptyOnePxLine(document);
			
			// Create a new paragraph
			
			List<ChemQuestionWrapper> wrappers = paperService.paperToWrapperList(paper);
			int questionCount = 1;
			
			for (int i = 0; i < QuestionType.values().length; i++) {
		    	
				List<ChemQuestionWrapper> wrapperList = new ArrayList<>();
				
		        // Filter wrapper by current QuestionType
		        for (ChemQuestionWrapper wrapper: wrappers) {
		            if (QuestionType.values()[i].toString() == wrapper.getChemQuestionType()) {
		                wrapperList.add(wrapper);
		            }
		        }
		        
		        // Skip if no wrapper for the current type
		        if (wrapperList.isEmpty()) {
		            continue;
		        }
		        
		        int subQuestionCount = 1;
		        
		        //assumption here is that TF/FB/MCQ are defined in this order in the enum value.
		        String[] mainQuesList = {".  Write TRUE or FALSE for each of the following statements",
		        							".  Fill in the blanks with the correct word(s), phrase(s), term(s), unit(s), etc.., as necessary",
		        							".  Select the correct word(s), notation(s), term(s), unit(s), etc.., given in the brackets"
		        						};  
		        		        
		        run.runMainQues(document, (questionCount+mainQuesList[i]), "(25 marks)");
		        questionCount++;
		        
		        
		        for(ChemQuestionWrapper wrapper: wrapperList) {
		        	String para = "$t:"+(char)(subQuestionCount+96)+". $t:"+ wrapper.getChemQuestionContent();
		        	String type = QuestionType.values()[i].toString().toUpperCase();
		        	
		        	XWPFParagraph questionContent = run.runParagraph(document, para,type);
		        	helper.setLeftHangingIndentation(questionContent, 720);
		        	subQuestionCount++;
		        }
		    	

			}
			
 
			
            try (FileOutputStream out = new FileOutputStream(filePath)) {
                document.write(out);
            }
			return "Word file created successfully at: " + filePath;
	
	    } catch (IOException e) {
	        e.printStackTrace();
	        return "Error occurred while creating the Word file.";
	    }
	}


	String text = "The formula of ammonia NH_3: is authentic. An example of a positively charged ion is H^2:.";
	public void testSubString(){
		
		// Save the document to a file
		String filePath = "C:\\Users\\mko\\Documents\\workspace-spring-tool-suite-4-4.18.1.RELEASE\\"
							+"chem_ques_gen_sprint_1.2\\word\\test.docx";
		try (XWPFDocument document = new XWPFDocument()) {
			
			run.runParagraph(document, text,"");
			
			
            try (FileOutputStream out = new FileOutputStream(filePath)) {
                document.write(out);
            }
	
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	
	

}
