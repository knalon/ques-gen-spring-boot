package com.mko.chem_ques_gen.entities.dto;


import com.mko.chem_ques_gen.entities.ChemQuestion;
import com.mko.chem_ques_gen.enums.EnumFactory;

public class ChemQuestionDto {
	private Integer chemQuestionId;
	private String chemQuestionGrade;
	
	private String chemQuestionChapter;
	private String chemQuestionLabel;
	private String chemQuestionType;
	
	private String chemQuestionContent;
	private String chemQuestionAnswer;
	private String chemQuestionDifficulty;
	private String createdOn;
	private String lastUpdatedOn;

	//------------------------ end of attributes
	
	public ChemQuestionDto() {
		super();
	}
	
	//for update
	public ChemQuestionDto(Integer chemQuesId,String chemQuestionGrade, String chemQuestionChapter, String chemQuestionLabel, String chemQuesType,
			String chemQuestionContent, String chemQuestionAnswer, String chemQuestionDifficulty,
			String createdOn, String lastUpdatedOn) {
		super();
		this.chemQuestionId = chemQuesId;
		this.chemQuestionGrade = chemQuestionGrade;
		this.chemQuestionChapter = chemQuestionChapter;
		this.chemQuestionLabel = chemQuestionLabel;
		this.chemQuestionType = chemQuesType;
		this.chemQuestionContent = chemQuestionContent;
		this.chemQuestionAnswer = chemQuestionAnswer;
		this.chemQuestionDifficulty = chemQuestionDifficulty;
		this.createdOn = createdOn ;
		this.lastUpdatedOn = lastUpdatedOn;
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
	public String getchemQuestionLabel() {
		return chemQuestionLabel;
	}
	public void setchemQuestionLabel(String chemQuestionLabel) {
		this.chemQuestionLabel = chemQuestionLabel;
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
	
	public String getCreatedOn() {
		return createdOn;
	}

	public String getLastUpdatedOn() {
		return lastUpdatedOn;
	}
	
	public void setLastUpdatedOn(String lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}
	

	//------------------------- end of getter and setter
	

	public ChemQuestion dtoToQuestion() {
		return new ChemQuestion(this.getChemQuestionId(),
								EnumFactory.generateGrade(this.getChemQuestionGrade()),
								EnumFactory.generateChapter(this.getChemQuestionChapter()), 
								this.getchemQuestionLabel(), 
								EnumFactory.generateQuestionType(this.getChemQuestionType()), 
								this.getChemQuestionContent(),
								this.getChemQuestionAnswer(), 
								EnumFactory.generateDifficulty(this.getChemQuestionDifficulty()),
								this.getCreatedOn(),
								this.getLastUpdatedOn());
	}

	@Override
	public String toString() {
		return "ChemQuestionDto [chemQuestionId=" + chemQuestionId + ", chemQuestionGrade=" + chemQuestionGrade
				+ ", chemQuestionChapter=" + chemQuestionChapter + ", chemQuestionLabel=" + chemQuestionLabel
				+ ", chemQuestionType=" + chemQuestionType + ", chemQuestionContent=" + chemQuestionContent
				+ ", chemQuestionAnswer=" + chemQuestionAnswer + ", chemQuestionDifficulty=" + chemQuestionDifficulty
				+ ", createdOn=" + createdOn + ", lastUpdatedOn=" + lastUpdatedOn + "]";
	}


}
