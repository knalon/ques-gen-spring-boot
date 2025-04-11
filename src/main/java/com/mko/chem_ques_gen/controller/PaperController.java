package com.mko.chem_ques_gen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mko.chem_ques_gen.entities.Paper;
import com.mko.chem_ques_gen.entities.dto.PaperDto;
import com.mko.chem_ques_gen.entities.requirements.ChemPaperRequirement;
import com.mko.chem_ques_gen.entities.requirements.PaperEditRequirement;
import com.mko.chem_ques_gen.service.PaperServiceImpl;

@RestController
@RequestMapping("/paper")
public class PaperController {

	//The assumption here is that, there are enough question types and enough questions stored in the database. 
	//Else, there can be problems since I try to get unique questions using labels.
	
	@Autowired
	PaperServiceImpl paperService;
	
//---------------------------------------------- create
	@PostMapping("/create_omni_paper")
	public ResponseEntity<PaperDto> createOmniPaper
										(@RequestBody ChemPaperRequirement requirement){
		return paperService.createOmniPaper(requirement);
	}
	
	
//---------------------------------------------- retrieve
	@GetMapping("/findAll")
	public ResponseEntity<List<Paper>> findAll(){
		return paperService.findAllPaper();
	}
	
	@GetMapping("/find/{paperId}")
	public Paper findPaperById(@PathVariable Integer paperId){
		return paperService.findPaperById(paperId);
	}

	//------------------------------------------------update
	
	@PutMapping("/edit/{paperId}/{oldQuesId}/{newQuesId}")
	public ResponseEntity<Paper> editSingleQuestionInPaper(@PathVariable Integer paperId, 
															@PathVariable Integer oldQuesId, 
															@PathVariable Integer newQuesId){
	return paperService.updateSingleQuestionInPaper(paperId, oldQuesId, newQuesId);
	}
	
	@PutMapping("/edit/{paperId}")
	public ResponseEntity<Paper> changeQuestionsInPaper(@PathVariable Integer paperId, 
																@RequestBody PaperEditRequirement paperEditReq){
		return paperService.updateMultipleQuestionsInPaper(paperId, 
															paperEditReq.getOldQuestionsIdList(), 
															paperEditReq.getNewQuestionsIdList());
	}
	
//----------------------------------------------------delete
	
	@DeleteMapping("/delete/{paperId}")
	public ResponseEntity<String> deletePaperById(@PathVariable Integer paperId){
		try {
			return paperService.deletePaperById(paperId);
		}catch(Exception e) {
			return new ResponseEntity<String>("Paper Not Found",HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/delete_all")
	public ResponseEntity<String> deleteAllPaper(){
		
		paperService.deleteAllPapers();
		
		return new ResponseEntity<> ("All Paper Deleted", HttpStatus.OK);
	}

//----------------------------------------------------update
	
}
