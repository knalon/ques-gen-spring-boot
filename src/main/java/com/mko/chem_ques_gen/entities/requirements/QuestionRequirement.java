package com.mko.chem_ques_gen.entities.requirements;

import com.mko.chem_ques_gen.enums.Chapter;
import com.mko.chem_ques_gen.enums.LabelVerification;
import com.mko.chem_ques_gen.enums.QuestionType;

import jakarta.persistence.Embeddable;

@Embeddable
public class QuestionRequirement {
	
	private Chapter chapter;
	private String grade;
	private LabelVerification label;
	private QuestionType type;
	private String difficulty;
	private Integer numberOfQuestion;
	
	//-------------- end of attributes
	
	public QuestionRequirement() {
		super();
	}
	
	public QuestionRequirement(Chapter chapter, 
								String grade,
								LabelVerification label, 
								QuestionType type, 
								String difficulty,
								Integer numberOfQuestion) {
		super();
		this.chapter = chapter;
		this.grade = grade;
		this.label = label;
		this.type = type;
		this.difficulty = difficulty;
		this.numberOfQuestion = numberOfQuestion;
	}
	
	//----------------------------- end of constructor
	
	
	public Chapter getChapter() {
		return chapter;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}

	public LabelVerification getLabel() {
		return label;
	}

	public void setLabel(LabelVerification label) {
		this.label = label;
	}

	public QuestionType getType() {
		return type;
	}

	public void setType(QuestionType type) {
		this.type = type;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public Integer getNumberOfQuestion() {
		return numberOfQuestion;
	}

	public void setNumberOfQuestion(Integer numberOfQuestion) {
		this.numberOfQuestion = numberOfQuestion;
	}
	
	
	
	
	//------------------- end of getter and setter

	@Override
	public String toString() {
		return "ChemQuestionRequirementDto [chapter=" + chapter + ", label=" + label + ", type=" + type
				+ ", difficulty=" + difficulty + ", numberOfQuestion=" + numberOfQuestion + "]";
	}

	//------------------------
	
	
	
}
