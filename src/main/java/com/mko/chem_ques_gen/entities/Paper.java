package com.mko.chem_ques_gen.entities;

import java.util.ArrayList;
import java.util.List;

import com.mko.chem_ques_gen.entities.dto.PaperDto;
import com.mko.chem_ques_gen.enums.Grade;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Paper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer paperId;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "paper_grade")
    private Grade paperGrade;
    
    @Column(name = "title")
    private String title;

    @Column(name = "month")
    private String month;

    @Column(name = "year")
    private String year;

    
    //ON update and On Delete - cascade is done manually in the database since CascadeType.All doesn't work.
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "paper_chem_questions",
        joinColumns = @JoinColumn(name = "paper_id"),
        inverseJoinColumns = @JoinColumn(name = "chem_questions_id", updatable = true)       
    )  
    private List<ChemQuestion> questions = new ArrayList<>();



    // Constructors, Getters, Setters, and other methods omitted for brevity
    
	//----------------- End of Attributes
	
	public Paper() {
		super();
	}

	//for searching specific existing data 
	//Or for update
	public Paper(Integer paperId, Grade grade, String title, String month, String year, List<ChemQuestion> questions) {
		super();
		this.paperId = paperId;
		this.paperGrade = grade;
		this.title = title;
		this.month = month;
		this.year = year;
		this.questions = questions;
	}
	//for create 
	public Paper(Grade grade, String title, String month, String year, List<ChemQuestion> questions) {
		super();
		this.paperGrade = grade;
		this.title = title;
		this.month = month;
		this.year = year;
		this.questions = questions;
	}
	//for blank paper
	public Paper(Grade grade, String title, String month, String year) {
		super();
		this.paperGrade = grade;
		this.title = title;
		this.month = month;
		this.year = year;
	}

	//----------------- End of Constructors
	
	
	
	public Integer getPaperId() {
		return paperId;
	}

	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}

	public List<ChemQuestion> getQuestions() {
		return questions;
	}

	public void setQuestions(List<ChemQuestion> questions) {
		this.questions = questions;
	}

	public Grade getGrade() {
		return paperGrade;
	}

	public void setGrade(Grade grade) {
		this.paperGrade = grade;
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
		return questions;
	}
	public void setChemQuestions(List<ChemQuestion> chemQuestions) {
		this.questions = chemQuestions;
	}

	//------------------------------ End of Getter and Setter
	// for easier access
	

	
	public void addListOfQuestions(List<ChemQuestion> quesList){
		this.questions.addAll(quesList);
	}
	
	@Override
	public String toString() {
		return "Paper [paperId=" + paperId + ", grade=" + paperGrade + ", title=" + title + ", month=" + month + ", year="
				+ year + ", questions=" + questions + "]";
	}

	// transform to Dto
	public PaperDto paperToDto() {
		return new PaperDto(paperId, paperGrade.toString(), title, month, year, questions);
	}
	
}
