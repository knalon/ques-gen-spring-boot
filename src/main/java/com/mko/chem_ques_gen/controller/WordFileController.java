package com.mko.chem_ques_gen.controller;

import java.io.IOException;

import org.apache.xmlbeans.XmlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mko.chem_ques_gen.service.PaperServiceImpl;
import com.mko.chem_ques_gen.service.WordServiceImpl;

@RestController
@RequestMapping("/word")
public class WordFileController {

	@Autowired
	PaperServiceImpl paperService;
	
	@Autowired
	WordServiceImpl wordService;

	@GetMapping("/test")
	public void test(){
		wordService.testSubString();
	}

	
    @GetMapping("/generate/{paperId}")
    public ResponseEntity<String> generateWordFile(@PathVariable Integer paperId) throws XmlException {
    	try {
			return new ResponseEntity<>(wordService.createWord(paperId),HttpStatus.OK);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>(("no paper found with Id "+ paperId), HttpStatus.NOT_FOUND);
    }
}
