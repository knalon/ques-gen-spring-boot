package com.mko.chem_ques_gen.service;


import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mko.chem_ques_gen.entities.dto.PaperDto;
import com.mko.chem_ques_gen.entities.requirements.ChemPaperRequirement;
import com.mko.chem_ques_gen.entities.wrapper.ChemQuestionWrapper;


public interface PaperService {
//	@Autowired
//	private ChemQuesServiceImpl chemQuesService;

	
	public ResponseEntity<PaperDto> createOmniPaper(ChemPaperRequirement requirement);

	
	//===============
	public ResponseEntity<List<ChemQuestionWrapper>> getPaperById(Integer paperId);
	public ResponseEntity<String> getFormatPaperById(Integer paperId);
	
	public ResponseEntity<String> deleteAllPapers();




}
