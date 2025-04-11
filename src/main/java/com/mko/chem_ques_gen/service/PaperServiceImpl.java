package com.mko.chem_ques_gen.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mko.chem_ques_gen.entities.ChemQuestion;
import com.mko.chem_ques_gen.entities.Paper;
import com.mko.chem_ques_gen.entities.dto.PaperDto;
import com.mko.chem_ques_gen.entities.requirements.ChemPaperRequirement;
import com.mko.chem_ques_gen.entities.requirements.QuestionRequirement;
import com.mko.chem_ques_gen.enums.EnumFactory;
import com.mko.chem_ques_gen.enums.LabelVerification;
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
						requirement.getYear(),
						requirement.getTimeAllowed());
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
		
		return new ResponseEntity<>(paper.paperToDto(),HttpStatus.OK);
	}

	//=============== CRUD
	//=======retrieve
	public Paper findPaperById(Integer paperId){
		return paperRepo.findById(paperId).get();
	}
	public ResponseEntity<List<Paper>> findAllPaper() {
		try {
			return new ResponseEntity<> (paperRepo.findAll(),HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NOT_FOUND);
		}
	}
	//=======delete
	public ResponseEntity<String> deletePaperById(Integer paperId) {
		try{
			paperRepo.deleteById(paperId);
			return new ResponseEntity<>("Paper Deleted Successfully.",HttpStatus.OK);
		}catch(Exception e) {
			
		}
		return new ResponseEntity<>("Paper Not found",HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<String> deleteAllPapers(){
		paperRepo.deleteAll();
		return new ResponseEntity<> ("All Papers Deleted", HttpStatus.NO_CONTENT);
	}


	//========update
	public ResponseEntity<Paper> updateSingleQuestionInPaper(Integer paperId, Integer oldQuesId,
			Integer newQuesId) {
		try {
			Paper paper = paperRepo.findById(paperId).get();
			for(ChemQuestion question: paper.getQuestions()) {
				if(question.getChemQuesId()!=oldQuesId) {
					continue;
				}else {
					paper.getQuestions().remove(question);
					paper.getQuestions().add(chemQuesService.getQuestionById(newQuesId).getBody().dtoToQuestion());
					paperRepo.save(paper);
					return new ResponseEntity<>(paper,HttpStatus.OK);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(new Paper(), HttpStatus.BAD_REQUEST);

	}

	public ResponseEntity<Paper> updateMultipleQuestionsInPaper(Integer paperId, 
																	List<Integer> oldQuesIDList, 
																	List<Integer> newQuesIdList) {
		
		Paper paper = paperRepo.findById(paperId).get();
		if(oldQuesIDList.size()==newQuesIdList.size()) {
			for(int i = 0; i< oldQuesIDList.size(); i++) {
				for(ChemQuestion question: paper.getQuestions()) {
					if(question.getChemQuesId() != oldQuesIDList.get(i)) {
						continue;
					}else {
						paper.getQuestions().remove(question);
						paper.getQuestions().add(chemQuesService
								.getQuestionById(newQuesIdList.get(i))
								.getBody()
								.dtoToQuestion()
								);
					}
				}			
			}
			paperRepo.save(paper);
			return new ResponseEntity<>(paper, HttpStatus.OK);
		}

		return new ResponseEntity<>(paper, HttpStatus.BAD_REQUEST);
		
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
	
}




