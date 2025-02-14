package com.mko.chem_ques_gen.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mko.chem_ques_gen.entities.Paper;
import com.mko.chem_ques_gen.entities.dto.PaperDto;
import com.mko.chem_ques_gen.entities.requirements.ChemPaperRequirement;

public interface PaperService {
//	@Autowired
//	private ChemQuesServiceImpl chemQuesService;

	
	public ResponseEntity<PaperDto> createOmniPaper(ChemPaperRequirement requirement);
	
	//===============	
	public Paper findPaperById(Integer paperId);
	public ResponseEntity<List<Paper>> findAllPaper();
	
	
	public ResponseEntity<String> deletePaperById(Integer paperId);
	public ResponseEntity<String> deleteAllPapers();
	
	
	public ResponseEntity<Paper> updateSingleQuestionInPaper(Integer paperId, Integer oldQuesId,
			Integer newQuesId);
	public ResponseEntity<Paper> updateMultipleQuestionsInPaper(Integer paperId, 
			List<Integer> oldQuesIDList, 
			List<Integer> newQuesIdList);




}
