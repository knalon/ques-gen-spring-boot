package com.mko.chem_ques_gen.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mko.chem_ques_gen.entities.Paper;
import com.mko.chem_ques_gen.entities.dto.PaperDto;
import com.mko.chem_ques_gen.entities.requirements.ChemPaperRequirement;
import com.mko.chem_ques_gen.entities.wrapper.ChemQuestionWrapper;
import com.mko.chem_ques_gen.service.PaperServiceImpl;

@RestController
@CrossOrigin("http://localhost:3000")
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
	@GetMapping("/find/{paperId}")
	public Paper findPaperById(@PathVariable Integer paperId){
		return paperService.findPaperById(paperId);
	}
	
	@GetMapping("/review/{paperId}")
	public ResponseEntity<List<ChemQuestionWrapper>> getPaperById(@PathVariable Integer paperId){
		
		try {
			return paperService.getPaperById(paperId);
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/review/format/{paperId}")
	public ResponseEntity<String> getFormatPaperById(@PathVariable Integer paperId){
		try {
			return paperService.getFormatPaperById(paperId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>("no question with this id is found",HttpStatus.NOT_FOUND);
		}
	}
	
//----------------------------------------------------delete
	@DeleteMapping("/delete_all")
	public ResponseEntity<String> deleteAllPaper(){
		
		paperService.deleteAllPapers();
		
		return new ResponseEntity<> ("All Paper Deleted", HttpStatus.NO_CONTENT);
	}

//----------------------------------------------------update
	
}
