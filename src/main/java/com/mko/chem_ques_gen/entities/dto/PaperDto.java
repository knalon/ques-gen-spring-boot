package com.mko.chem_ques_gen.entities.dto;


import java.util.List;

import com.mko.chem_ques_gen.entities.ChemQuestion;
import com.mko.chem_ques_gen.entities.Paper;
import com.mko.chem_ques_gen.enums.EnumFactory;

public class PaperDto {

	private Integer paperId;
	private String grade;
	
	private String title;
	private String month;
	private String year;
	private List<ChemQuestion> chemQuestions;
	
	//--------------end of attribute
	public PaperDto() {
		super();
	}

	public PaperDto(Integer paperId, String grade, String title, String month, String year, 
						List<ChemQuestion> questions) {
		super();
		this.paperId = paperId;
		this.grade = grade;
		this.title = title;
		this.month = month;
		this.year = year;
		
		this.chemQuestions = questions;
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

	//end of getter and setter
	public Paper dtoToPaper() {
		return new Paper(this.getPaperId(), EnumFactory.generateGrade(this.getGrade()), 
								this.getTitle(), this.getMonth(), this.getYear(), this.getChemQuestions());
	}

	@Override
	public String toString() {
		return "PaperDto [paperId=" + paperId + ", grade=" + grade + ", title=" + title + ", month=" + month + ", year="
				+ year + ", chemQuestions=" + chemQuestions + "]";
	}
	
	
}
