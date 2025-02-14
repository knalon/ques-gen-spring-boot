package com.mko.chem_ques_gen.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mko.chem_ques_gen.entities.ChemQuestion;
import com.mko.chem_ques_gen.entities.dto.ChemQuestionDto;
import com.mko.chem_ques_gen.entities.requirements.QuestionRequirement;
import com.mko.chem_ques_gen.formatter.Formatter;
import com.mko.chem_ques_gen.repository.ChemQuestionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ChemQuesServiceImpl implements ChemQuestionService{

	@Autowired
	ChemQuestionRepository chemQuesRepo;
	
//-------------------utility methods
	public List<ChemQuestionDto> quesListToDtoList(List<ChemQuestion> quesList){
		
		List<ChemQuestionDto> quesDtoList = new ArrayList<ChemQuestionDto>();
		for(ChemQuestion ques: quesList) {
			quesDtoList.add(ques.questionToDto());
		}
		return quesDtoList;
	}
		//to be utilized in paperservice
	public List<ChemQuestion> dtoListToQuesList(ResponseEntity<List<ChemQuestionDto>> responseEntity){
		
		List<ChemQuestion> quesList = new ArrayList<ChemQuestion>();
		for(ChemQuestionDto dto: responseEntity.getBody()) {
			quesList.add(dto.dtoToQuestion()); 
		}
		return quesList;
	}
	
//------------- Overridden routed methods
	
	public ResponseEntity<ChemQuestionDto> getQuestionById(Integer id){
		try {
			return new ResponseEntity<>(chemQuesRepo.findById(id).get().questionToDto(),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ChemQuestionDto(), HttpStatus.BAD_REQUEST);
	}
	//all
	
	//1 @utilized
	@Override
	public ResponseEntity<List<ChemQuestionDto>> getAllQuestions() {
		
		try {
			return new ResponseEntity<>(this.quesListToDtoList(chemQuesRepo.findAll()), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new ResponseEntity<> (new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	
	// ---------- approach 2 : utilize native query to retrieve the desired data

	//2
	//all by chapter and type
	@Override
	public ResponseEntity<List<ChemQuestionDto>> getQuestionsByChapterAndTypeAndGrade
													(QuestionRequirement requirement) {
		try {
			return new ResponseEntity<> 
			(this.quesListToDtoList(chemQuesRepo.findQuestionByChapterAndtypeAndGrade
													(requirement.getChapter().toString(), 
													 requirement.getType().toString(), 
													 requirement.getGrade().toString())), 
				HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<> (new ArrayList<ChemQuestionDto>(), HttpStatus.BAD_REQUEST);
	}
	
	
	//6
	//all by chapter and type and difficulty
	@Override
	public ResponseEntity<List<ChemQuestionDto>> getQuestionsByChapterAndTypeAndDifficultyAndGrade
													(QuestionRequirement requirement) {
		try {
			return new ResponseEntity<> 
			(this.quesListToDtoList(chemQuesRepo.findQuestionByChapterAndtypeAndDifficultyAndGrade
													(requirement.getChapter().toString(), 
													 requirement.getType().toString(), 
													 requirement.getDifficulty(), 
													 requirement.getGrade().toString())), 
				HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<> (new ArrayList<ChemQuestionDto>(), HttpStatus.BAD_REQUEST);
	}
	
	
//	
	//10
	//limited number by chapter and type
	@Override
	public ResponseEntity<List<ChemQuestion>> getANumberOfQuestionsByChapterAndTypeAndGrade
													(QuestionRequirement requirement) {
		try {
			System.out.println("line 226 in chemquesService;");
			return new ResponseEntity<> 
			(chemQuesRepo.findANumberOfRandomQuesByChapterAndTypeAndGrade
													(requirement.getChapter().toString(), 
													 requirement.getNumberOfQuestion(), 
													 requirement.getType().toString(), 
													 requirement.getGrade()), 
				HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<> (new ArrayList<ChemQuestion>(), HttpStatus.BAD_REQUEST);
	}
	
	//11
	//limited number by chapter and type <unique label>
	@Override 
	public ResponseEntity<List<ChemQuestion>> getANumberOfQuestionsByChapterAndUniuqeLabelAndTypeAndGrade
													(QuestionRequirement requirement) {
		try {
			return new ResponseEntity<> 
			(chemQuesRepo.findANumberOfRandomQuesByChapterAndUniqueLabelAndTypeAndGrade
													(requirement.getChapter().toString(), 
													 requirement.getNumberOfQuestion(), 
													 requirement.getType().toString(), 
													 requirement.getGrade()), 
				HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<> (new ArrayList<ChemQuestion>(), HttpStatus.BAD_REQUEST);
	}
	

	
	//13
	//limited number by chapter and type and difficulty <unique label>
	@Override
	public ResponseEntity<List<ChemQuestion>> getANumberOfQuestionsByChapterAndUniqueLabelAndTypeAndDifficultyAndGrade
													(QuestionRequirement requirement) {
		System.out.println("line 151 chemserviceimpl : "+ requirement.toString());
		try {
			return new ResponseEntity<> 
			(chemQuesRepo.findANumberOfRandomQuesByChapterAndUniqueLabelAndTypeAndDifficultyAndGrade
													(requirement.getChapter().toString(), 
													 requirement.getNumberOfQuestion(), 
													 requirement.getType().toString(), 
													 requirement.getDifficulty(), 
													 requirement.getGrade().toString()), 
			 HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<> (new ArrayList<ChemQuestion>(), HttpStatus.BAD_REQUEST);
	}
	
	//14
	//limited number by chapter and type and difficulty
	@Override
	public ResponseEntity<List<ChemQuestion>> getANumberOfQuestionsByChapterAndTypeAndDifficultyAndGrade
													(QuestionRequirement requirement) {
		
		
		try {
			return new ResponseEntity<> 
			(chemQuesRepo.findANumberOfRandomQuesByChapterAndTypeAndDifficultyAndGrade
													(requirement.getChapter().toString().toLowerCase(), 
													 requirement.getNumberOfQuestion(), 
													 requirement.getType().toString(),
													 requirement.getDifficulty(), 
													 requirement.getGrade().toString()), 
			 HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<> (new ArrayList<ChemQuestion>(), HttpStatus.BAD_REQUEST);
	}
	

	
	//-------------------------------------
	//16
	@Override
	public ResponseEntity<String> addQuestion(ChemQuestionDto chemQuesDto) {
		String success = "Question has been added successfully. \n"
							.concat(chemQuesRepo
										.save(chemQuesDto.dtoToQuestion())
										.toString());

		try {
			return new ResponseEntity<> (success, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<> ("Question was not added to the database.", HttpStatus.BAD_REQUEST);
	}
	
	//17
	@Override
	public ResponseEntity<String> updateQuestion(ChemQuestionDto dto) {
		try {
			if(chemQuesRepo.findById(dto.getChemQuestionId()).isPresent()) {
				ChemQuestion question = dto.dtoToQuestion();
				question.setLastUpdatedOn(Formatter.dateTimeNow());
				chemQuesRepo.save(question);
				return new ResponseEntity<> ("Updated Successfully to \n".concat(dto.toString()), HttpStatus.OK);
			};
			//HttpStatus No_Content does not return this string in the responseEntity.
			return new ResponseEntity<> ("No Quesiton with this Id exists.", HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<> ("Question Update was Interrupted.", HttpStatus.BAD_REQUEST);
	}

	//18
	@Override
	public ResponseEntity<String> deleteQuestionById(Integer dtoId) {
		
		try {
			if(chemQuesRepo.findById(dtoId).isPresent()) {
				chemQuesRepo.deleteById(dtoId);
			}else {
				return new ResponseEntity<> ("No Quesiton with this Id exists.", HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<> ("Question Deleted Successfully.", HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<> ("Question Delete was Interrupted.", HttpStatus.BAD_REQUEST);
	}

	
//----------------Controller Specialized Methods

	// @utilized
	@Override
	public ResponseEntity<List<String>> addMultipleQuestions(List<ChemQuestionDto> chemQuesDtoList) {
		List<String> successList = new ArrayList<>();
		List<String> failureList = new ArrayList<>();
		for(ChemQuestionDto chemQuesDto: chemQuesDtoList) {
			try {
				if(chemQuesDto.getChemQuestionContent() != "" &&
						chemQuesDto.getChemQuestionChapter() != "" &&
						chemQuesDto.getChemQuestionGrade() != "" &&
						chemQuesDto.getChemQuestionType() != "") {
					
					ChemQuestion question = chemQuesDto.dtoToQuestion();
					
					String success = "Successfully added the question : " 
							.concat(chemQuesRepo
									.save(question)
									.toString());
					successList.add(success);					
				}else {
					throw new NoSuchElementException();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				String failure = "Failed to add "+chemQuesDto.toString();
				failureList.add(failure);
			}
				
		}
		if(successList.isEmpty()) {
			return new ResponseEntity<> (failureList, HttpStatus.BAD_REQUEST);			
		}else {
			successList.addAll(failureList);
			return new ResponseEntity<> (successList, HttpStatus.CREATED);			
		}
	}	
	
	
	
}
