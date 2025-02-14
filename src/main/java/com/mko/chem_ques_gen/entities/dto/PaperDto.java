package com.mko.chem_ques_gen.entities.dto;


import java.util.List;

import com.mko.chem_ques_gen.entities.ChemQuestion;
import com.mko.chem_ques_gen.entities.Paper;
import com.mko.chem_ques_gen.enums.EnumFactory;
import com.mko.chem_ques_gen.formatter.Formatter;

public class PaperDto {

	private Integer paperId;
	private String grade;
	
	private String title;
	private String month;
	private String year;
	private List<ChemQuestion> chemQuestions;
	private String timeAllowed;
	private String createdOn;
	private String lastUpdatedOn;
	
	//--------------end of attribute
	public PaperDto() {
		super();
	}

	public PaperDto(Integer paperId, String grade, String title, String month, String year, 
						List<ChemQuestion> questions, String timeAllowed) {
		super();
		this.paperId = paperId;
		this.grade = grade;
		this.title = title;
		this.month = month;
		this.year = year;
		this.timeAllowed = timeAllowed;
		
		this.chemQuestions = questions;
	}
	
	public PaperDto(Integer paperId, String grade, String title, String month, String year, 
			List<ChemQuestion> questions, String timeAllowed, String createdOn, String lastUpdatedOn) {
		super();
		this.paperId = paperId;
		this.grade = grade;
		this.title = title;
		this.month = month;
		this.year = year;
		
		this.chemQuestions = questions;
		this.createdOn = createdOn;
		this.lastUpdatedOn = Formatter.dateTimeNow();
	}
	//------------- end of constructors
	
	
	public Integer getPaperId() {
		return paperId;
	}
	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}

	public List<ChemQuestion> getChemQuestions() {
		return chemQuestions;
	}
	public void setChemQuestions(List<ChemQuestion> chemQuestions) {
		this.chemQuestions = chemQuestions;
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

	
	public String gettimeAllowed() {
		return timeAllowed;
	}

	public void settimeAllowed(String timeAllowed) {
		this.timeAllowed = timeAllowed;
	}

	//end of getter and setter
	public Paper dtoToPaper() {
		return new Paper(this.getPaperId(), EnumFactory.generateGrade(this.getGrade()), 
								this.getTitle(), this.getMonth(), this.getYear(), this.getChemQuestions(),
								this.timeAllowed, this.getCreatedOn(), this.getLastUpdatedOn());
	}

	
	
}
