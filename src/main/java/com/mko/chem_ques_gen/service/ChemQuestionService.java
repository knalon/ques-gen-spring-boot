package com.mko.chem_ques_gen.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mko.chem_ques_gen.entities.ChemQuestion;
import com.mko.chem_ques_gen.entities.dto.ChemQuestionDto;
import com.mko.chem_ques_gen.entities.requirements.QuestionRequirement;

public interface ChemQuestionService {
	public ResponseEntity<List<ChemQuestionDto>> getAllQuestions();
	
	//-----------
	//1
//	public ResponseEntity<List<ChemQuestionDto>> getQuestionsByChapter
//													(QuestionRequirement requirement);
	//2
//	public ResponseEntity<List<ChemQuestionDto>> getQuestionsByChapterAndLabel
//													(QuestionRequirement requirement);
	//3
	public ResponseEntity<List<ChemQuestionDto>> getQuestionsByChapterAndTypeAndGrade
													(QuestionRequirement requirement);
	//4
//	public ResponseEntity<List<ChemQuestionDto>> getQuestionsByChapterAndLabelAndType
//													(QuestionRequirement requirement);
	//5
	public ResponseEntity<List<ChemQuestionDto>> getQuestionsByChapterAndTypeAndDifficultyAndGrade
													(QuestionRequirement requirement);
	//6
//	public ResponseEntity<List<ChemQuestionDto>> getQuestionsByChapterAndLabelAndTypeAndDifficulty
//													(QuestionRequirement requirement);
	
	//-----------	
	//7
//	public ResponseEntity<List<ChemQuestionDto>> getANumberOfQuestionsByChapter
//													(QuestionRequirement requirement);	
	//8
//	public ResponseEntity<List<ChemQuestionDto>> getANumberOfQuestionsByChapterAndLabel
//													(QuestionRequirement requirement);	
	//9
	public ResponseEntity<List<ChemQuestion>> getANumberOfQuestionsByChapterAndTypeAndGrade
													(QuestionRequirement requirement);	
	//10
	//unique label
	public ResponseEntity<List<ChemQuestion>> getANumberOfQuestionsByChapterAndUniuqeLabelAndTypeAndGrade
													(QuestionRequirement requirement);	
	//11
//	public ResponseEntity<List<ChemQuestionDto>> getANumberOfQuestionsByChapterAndLabelAndType
//													(QuestionRequirement requirement);
	//12
	//unique label
	public ResponseEntity<List<ChemQuestion>> getANumberOfQuestionsByChapterAndUniqueLabelAndTypeAndDifficultyAndGrade
													(QuestionRequirement requirement);
	//13
	public ResponseEntity<List<ChemQuestion>> getANumberOfQuestionsByChapterAndTypeAndDifficultyAndGrade
													(QuestionRequirement requirement);	

	//-----------
	//15
	public ResponseEntity<String> addQuestion(ChemQuestionDto dto);
	//16
	public ResponseEntity<String> updateQuestion(ChemQuestionDto dto);
	//17
	public ResponseEntity<String> deleteQuestionById(Integer dtoId);

	public ResponseEntity<List<String>> addMultipleQuestions(List<ChemQuestionDto> chemQuesDtoList);
	
	
}
