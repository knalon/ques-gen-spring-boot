package com.mko.chem_ques_gen.entities.requirements;

import java.util.List;

import com.mko.chem_ques_gen.enums.Chapter;
import com.mko.chem_ques_gen.enums.QuestionType;

public abstract class AbstractPaperRequirement {
	private String month;
	private String year;
	private String title;
	private String grade;
	private String timeAllowed;
	
	//----------end of attributes
	
	public AbstractPaperRequirement() {
		super();
	}
	
	public AbstractPaperRequirement(String month, String year, String title) {
		super();
		this.month = month;
		this.year = year;
		this.title = title;
	}
	
	// --------------- end of constructors



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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getGrade() {
		return grade;
	}
	
	public void setGrade(String grade) {
		this.grade = grade;
	}
	 
	public String getTimeAllowed() {
		return timeAllowed;
	}
	public void setTimeAllowed(String timeAllowed) {
		this.timeAllowed = timeAllowed;
	}
	
	
	//---------------end of getter and setter
	
	
	//common method for multi-chapter requirements


	public abstract QuestionRequirement getRequirment(Chapter chapter, QuestionType type);
	public abstract void distributeQuestionToEachRequirement(List<QuestionRequirement> requirementList);
	
}
