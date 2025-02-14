package com.mko.chem_ques_gen.entities.requirements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.mko.chem_ques_gen.enums.Chapter;
import com.mko.chem_ques_gen.enums.LabelVerification;
import com.mko.chem_ques_gen.enums.QuestionType;

public class ChemPaperRequirement extends AbstractPaperRequirement{
	
	//This is a very important list which will hold every single chapter + quesType requirement.
	//for example, c1RequirementTF, c1RequirementFB, c1RequirementMQ and so on.
	private List<QuestionRequirement> requirementList= new ArrayList<>();
	
	//This is a helper Map.
	Map<QuestionType,Integer> noOfTotalQuesByType=new HashMap<>();
		
	public ChemPaperRequirement( 	List<Chapter> chapterList,	
									List<QuestionType> typeList,
									List<Boolean> labelVerificationList,
									List<String> difficultyList,
									List<Integer> totalNoOfQuestionList,
			 
									String title,
									String month,
									String year,
									String grade,
									String timeAllowed
									) {
		
		
		//code explanation.........
		//
		for(Chapter chapter: chapterList) {
			for(int i = 0; i<typeList.size(); i++) {
				if(typeList.get(i) != null) {
					
					//assign key, value pair of quesType by totalQuestionNo to the helper Map.
					if(!noOfTotalQuesByType.containsKey(typeList.get(i))) {
						noOfTotalQuesByType.put(typeList.get(i), totalNoOfQuestionList.get(i));	
						System.out.println(noOfTotalQuesByType.toString());
					}
					
					//Creating requirement to be added to the list.
					QuestionRequirement chapterRequirement = 
							new QuestionRequirement(chapter, 
													grade,
													testLabelRepeat(labelVerificationList.get(i)), 
													typeList.get(i), 
													difficultyList.get(i), 
													0);					
					requirementList.add(chapterRequirement);
				}
			}			
			
		}
		
		this.setTitle(title);
		this.setMonth(month);
		this.setYear(year);
		this.setGrade(grade);
		this.setTimeAllowed(timeAllowed);
		
		
		//adding specific number of questions to each requirement.
		distributeQuestionToEachRequirement(requirementList);

		
	}
	//------------------End of Constructor
	



	//-------------------Overridden methods
	@Override
	public QuestionRequirement getRequirment(Chapter chapter, QuestionType type) {
		for(QuestionRequirement quesRequirement: requirementList) {
			if(quesRequirement.getChapter().equals(chapter) && quesRequirement.getType().equals(type)) {
				return quesRequirement;
			}
		}
		return null;
	}

	@Override
	public void distributeQuestionToEachRequirement(List<QuestionRequirement> requirementList) {
	    Random rand = new Random();
	    
	    for (int i = 0; i < QuestionType.values().length; i++) {
	        List<QuestionRequirement> reqListByType = new ArrayList<>();
	        
	        // Filter requirements by current QuestionType
	        for (QuestionRequirement questionReq : requirementList) {
	            if (QuestionType.values()[i].equals(questionReq.getType())) {
	                reqListByType.add(questionReq);
	            }
	        }
	        
	        // Skip if no requirements for the current type
	        if (reqListByType.isEmpty()) {
	            continue;
	        }
	        
	        // Calculating the average number of questions for each chapter by type
	        if (noOfTotalQuesByType.containsKey(QuestionType.values()[i])) {
	            int totalNumberOfChapter = reqListByType.size();
	            int totalNumberOfQuestion = noOfTotalQuesByType.get(QuestionType.values()[i]);
	            
	            int averageNumberOfQuestion = totalNumberOfQuestion / totalNumberOfChapter;
	            int remainingNumberOfQuestion = totalNumberOfQuestion % totalNumberOfChapter;
	            
	            // Assign average numbers to each requirement
	            for (QuestionRequirement questionReq : reqListByType) {
	                questionReq.setNumberOfQuestion(averageNumberOfQuestion);
	            }
	            
	            // Manage the remaining questions
	            Set<Integer> usedIndexes = new HashSet<>();
	            while (remainingNumberOfQuestion > 0) {
	                int randomChapter = rand.nextInt(reqListByType.size());
	                
	                // Ensure no chapter receives more than one extra question
	                if (!usedIndexes.contains(randomChapter)) {
	                    QuestionRequirement randomReq = reqListByType.get(randomChapter);
	                    randomReq.setNumberOfQuestion(randomReq.getNumberOfQuestion() + 1);
	                    usedIndexes.add(randomChapter);
	                    remainingNumberOfQuestion--;
	                }
	            }
	        }
	        
	    }
	}

	//-------------------Utility methods
	public LabelVerification testLabelRepeat(Boolean label) {
		if(label == null || !label) {
			return LabelVerification.NO_REPEAT;
		}else{
			return LabelVerification.REPEATABLE;
		}
	}

	//-------------------Getter s and Setters 
	public List<QuestionRequirement> getRequirementList() {
		return requirementList;
	}

	public void setRequirementList(List<QuestionRequirement> requirementList) {
		this.requirementList = requirementList;
	}

}
