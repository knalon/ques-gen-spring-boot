package com.mko.chem_ques_gen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mko.chem_ques_gen.entities.dto.ChemQuestionDto;
import com.mko.chem_ques_gen.service.ChemQuesServiceImpl;

@RestController
@RequestMapping("/chem")
public class ChemQuesController {
	
	@Autowired
	ChemQuesServiceImpl chemQuesService;
	
	@GetMapping("/allQuestions")
	public ResponseEntity<List<ChemQuestionDto>> getAllQuestions() {
		return chemQuesService.getAllQuestions();
	}
	
	
	//similar call exists in paper controller
	//but this will not be saved in the database while the paper controller will save

	@PostMapping("/add")
	public ResponseEntity<String> addQuestion(@RequestBody ChemQuestionDto chemQuesDto) {
		return chemQuesService.addQuestion(chemQuesDto);
	}
	
	@PostMapping("/addMultiple")
	public ResponseEntity<List<String>> addMultipleQuestions(@RequestBody List<ChemQuestionDto> chemQuesDtoList){
		return chemQuesService.addMultipleQuestions(chemQuesDtoList);
	}
	
	@PostMapping("/update")
	public ResponseEntity<String> updateQuestion(@RequestBody ChemQuestionDto chemQuesDto){
		return chemQuesService.updateQuestion(chemQuesDto);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteQuestionById(@PathVariable Integer id){
		return chemQuesService.deleteQuestionById(id);
	}
	
	
}
