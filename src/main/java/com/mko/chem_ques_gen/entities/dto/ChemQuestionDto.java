package com.mko.chem_ques_gen.entities.dto;

import com.mko.chem_ques_gen.entities.ChemQuestion;
import com.mko.chem_ques_gen.enums.EnumFactory;

public class ChemQuestionDto {
	private Integer chemQuestionId;
	private String chemQuestionGrade;
	
	private String chemQuestionChapter;
	private String chemQuestionLabe;
	private String chemQuestionType;
	
	private String chemQuestionContent;
	private String chemQuestionAnswer;
	private String chemQuestionDifficulty;

	//------------------------ end of attributes
	
	public ChemQuestionDto() {
		super();
	}
	
	//for create
	public ChemQuestionDto(String chemQuestionGrade, String chemQuestionChapter, String chemQuestionLabe, String chemQuesType,
			String chemQuestionContent, String chemQuestionAnswer, String chemQuestionDifficulty) {
		super();
		this.chemQuestionGrade = chemQuestionGrade;
		this.chemQuestionChapter = chemQuestionChapter;
		this.chemQuestionLabe = chemQuestionLabe;
		this.chemQuestionType = chemQuesType;
		this.chemQuestionContent = chemQuestionContent;
		this.chemQuestionAnswer = chemQuestionAnswer;
		this.chemQuestionDifficulty = chemQuestionDifficulty;
	}
	
	//for update
	public ChemQuestionDto(Integer chemQuesId,String chemQuestionGrade, String chemQuestionChapter, String chemQuestionLabe, String chemQuesType,
			String chemQuestionContent, String chemQuestionAnswer, String chemQuestionDifficulty) {
		super();
		this.chemQuestionId = chemQuesId;
		this.chemQuestionGrade = chemQuestionGrade;
		this.chemQuestionChapter = chemQuestionChapter;
		this.chemQuestionLabe = chemQuestionLabe;
		this.chemQuestionType = chemQuesType;
		this.chemQuestionContent = chemQuestionContent;
		this.chemQuestionAnswer = chemQuestionAnswer;
		this.chemQuestionDifficulty = chemQuestionDifficulty;
	}
	
	//------------------------ end of constructors
	
	public Integer getChemQuestionId() {
		return chemQuestionId;
	}
	public void setChemQuestionId(Integer chemQuesId) {
		this.chemQuestionId = chemQuesId;
	}
	public String getChemQuestionGrade() {
		return chemQuestionGrade;
	}

	public void setChemQuestionGrade(String chemQuestionGrade) {
		this.chemQuestionGrade = chemQuestionGrade;
	}

	public String getChemQuestionChapter() {
		return chemQuestionChapter;
	}
	public void setChemQuestionChapter(String chemQuestionChapter) {
		this.chemQuestionChapter = chemQuestionChapter;
	}
	public String getChemQuestionLabe() {
		return chemQuestionLabe;
	}
	public void setChemQuestionLabe(String chemQuestionLabe) {
		this.chemQuestionLabe = chemQuestionLabe;
	}
	public String getChemQuestionContent() {
		return chemQuestionContent;
	}
	public void setChemQuestionContent(String chemQuestionContent) {
		this.chemQuestionContent = chemQuestionContent;
	}
	public String getChemQuestionAnswer() {
		return chemQuestionAnswer;
	}
	public void setChemQuestionAnswer(String chemQuestionAnswer) {
		this.chemQuestionAnswer = chemQuestionAnswer;
	}
	public String getChemQuestionDifficulty() {
		return chemQuestionDifficulty;
	}
	public void setChemQuestionDifficulty(String chemQuestionDifficulty) {
		this.chemQuestionDifficulty = chemQuestionDifficulty;
	}
	public String getChemQuestionType() {
		return chemQuestionType;
	}	
	public void setChemQuestionType(String chemQuestionType) {
		this.chemQuestionType = chemQuestionType;
	}


	//------------------------- end of getter and setter
	
	public ChemQuestion dtoToQuestion() {
		return new ChemQuestion(this.getChemQuestionId(),
								EnumFactory.generateGrade(this.getChemQuestionGrade()),
								EnumFactory.generateChapter(this.getChemQuestionChapter()), 
								this.getChemQuestionLabe(), 
								EnumFactory.generateQuestionType(this.getChemQuestionType()), 
								this.getChemQuestionContent(),
								this.getChemQuestionAnswer(), 
								EnumFactory.generateDifficulty(this.getChemQuestionDifficulty()));
	}

	@Override
	public String toString() {
		return "ChemQuestionDto [chemQuestionId=" + chemQuestionId + ", chemQuestionGrade=" + chemQuestionGrade
				+ ", chemQuestionChapter=" + chemQuestionChapter + ", chemQuestionLabe=" + chemQuestionLabe
				+ ", chemQuestionType=" + chemQuestionType + ", chemQuestionContent=" + chemQuestionContent
				+ ", chemQuestionAnswer=" + chemQuestionAnswer + ", chemQuestionDifficulty=" + chemQuestionDifficulty
				+ "]";
	}


	
	
}
