package com.mko.chem_ques_gen.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mko.chem_ques_gen.entities.ChemQuestion;
import com.mko.chem_ques_gen.entities.Paper;
import com.mko.chem_ques_gen.entities.dto.PaperDto;
import com.mko.chem_ques_gen.entities.requirements.ChemPaperRequirement;
import com.mko.chem_ques_gen.entities.requirements.QuestionRequirement;
import com.mko.chem_ques_gen.entities.wrapper.ChemQuestionWrapper;
import com.mko.chem_ques_gen.enums.EnumFactory;
import com.mko.chem_ques_gen.enums.LabelVerification;
import com.mko.chem_ques_gen.enums.QuestionType;
import com.mko.chem_ques_gen.repository.PaperRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class PaperServiceImpl implements PaperService{
	
	
	@Autowired
	private ChemQuesServiceImpl chemQuesService;
	
	@Autowired
	private PaperRepository paperRepo;
	
	@Override
	public ResponseEntity<PaperDto> createOmniPaper(ChemPaperRequirement requirement) {
		
		Paper paper = new Paper(EnumFactory.generateGrade(requirement.getGrade()),
						requirement.getTitle(), 
						requirement.getMonth(), 
						requirement.getYear());
		System.out.println("paper : "+ paper.toString());
		
		ResponseEntity<List<ChemQuestion>> quesList; 
		
		
		for(QuestionRequirement questionRequirement: requirement.getRequirementList()) {
			
			if(questionRequirement.getDifficulty()==null) {
				
				if(questionRequirement.getLabel().equals(LabelVerification.NO_REPEAT)) {
					quesList = chemQuesService.getANumberOfQuestionsByChapterAndUniuqeLabelAndTypeAndGrade(questionRequirement);					
				}else {
					quesList = chemQuesService.getANumberOfQuestionsByChapterAndTypeAndGrade(questionRequirement);						
				}
				
				
			}else if(questionRequirement.getLabel().equals(LabelVerification.REPEATABLE)) {				
				quesList = chemQuesService.getANumberOfQuestionsByChapterAndTypeAndDifficultyAndGrade(questionRequirement);
			
				
			}else {
				quesList = chemQuesService.getANumberOfQuestionsByChapterAndUniqueLabelAndTypeAndDifficultyAndGrade(questionRequirement);				
			}
	

			paper.addListOfQuestions(quesList.getBody());
			
		}
		System.out.println("before paper save.");
		System.out.println(paper.toString());
		paperRepo.save(paper);
		System.out.println("after paper save.");
		
		return new ResponseEntity<>(paper.paperToDto(),HttpStatus.CREATED);
	}

	//===============
	@Override
	public ResponseEntity<List<ChemQuestionWrapper>> getPaperById(Integer paperId) {
		try {
			
			Paper paper = paperRepo.findById(paperId).get();

			return new ResponseEntity<>(this.paperToWrapperList(paper),HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<String> getFormatPaperById(Integer paperId) {
		Random rand = new Random();
		String formatPaper = new String();
		List<ChemQuestionWrapper> wrappers = this.getPaperById(paperId).getBody();
		
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
	        
	        formatPaper +=QuestionType.values()[i].toString()+"\n";
	        int number = 1;
	        
	        if(QuestionType.values()[i].toString()=="MATCHING") {
	        	
	        	List<Integer> sequenceList = new ArrayList<>();
	        	List<Integer> randList = new ArrayList<>();
	        	
	        	do{
	        		for(int k = 0; k<wrapperList.size(); k++) {
	        			sequenceList.add(k);
	        		}
	        		
		        	int randCount=0;
		        	while(randCount<wrapperList.size()) {
		        		int randomNum = rand.nextInt(wrapperList.size());
		        		if(!randList.contains(randomNum)) {
		        			randList.add(randomNum);
		        			randCount++;
		        		}
		        	} 
	        	}while(this.randomizeMatching(sequenceList, randList));
	        	
	        	for(int j = 0; j<wrapperList.size(); j++) {
	        		formatPaper = formatPaper.concat(wrapperList.get(sequenceList.get(j)).getChemQuestionContent() + " | ")
	        									.concat(wrapperList.get(randList.get(j)).getChemQuestionAnswer()+"\n");
	        	}
	        	
	        }else {
	        	for(ChemQuestionWrapper wrapper: wrapperList) {
	        		formatPaper = formatPaper.concat(number +" " +wrapper.getChemQuestionContent()+"\n");
	        		number++;
	        	}
	        	
	        }
	        
	    }
			
		return new ResponseEntity<>(formatPaper,HttpStatus.OK);
	}	
	
	@Override
	public ResponseEntity<String> deleteAllPapers(){
		paperRepo.deleteAll();
		return new ResponseEntity<> ("All Papers Deleted", HttpStatus.NO_CONTENT);
	}

	public Paper findPaperById(Integer paperId){
		return paperRepo.findById(paperId).get();
	}

	//check equal in matching.
	public Boolean randomizeMatching(List<Integer> list1, List<Integer> list2) {
				
		int count = 0;
		while(count<list1.size()) {
			if(list1.get(count) == list2.get(count)) {
				return true;
			}
			count++;
		}
		return false;
	}
	
	public List<ChemQuestionWrapper> paperToWrapperList(Paper paper){
		List<ChemQuestionWrapper> wrapperList = new ArrayList<>();
		
		for(ChemQuestion question: paper.getChemQuestions()) {
			ChemQuestionWrapper wrapper= 
					new ChemQuestionWrapper(paper.getPaperId(),
											question.getChemQuesId(), 
											question.getChemQuestionContent(),
											question.getChemQuestionAnswer(),
											question.getChemQuestionType().toString());
			wrapperList.add(wrapper);
		}
		return wrapperList;
	}



}
