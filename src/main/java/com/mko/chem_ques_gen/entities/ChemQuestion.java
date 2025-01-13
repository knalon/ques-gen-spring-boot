package com.mko.chem_ques_gen.entities;

import java.util.ArrayList;
import java.util.List;

import com.mko.chem_ques_gen.entities.dto.ChemQuestionDto;
import com.mko.chem_ques_gen.enums.Chapter;
import com.mko.chem_ques_gen.enums.Difficulty;
import com.mko.chem_ques_gen.enums.Grade;
import com.mko.chem_ques_gen.enums.QuestionType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class ChemQuestion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer chemQuesId;
	
	@Enumerated(EnumType.STRING)
	@Column(name="grade")
	private Grade chemQuestionGrade;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "chapter")
	private Chapter chemQuestionChapter;
	
	@Column(name = "label")
	private String chemQuestionLabel;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private QuestionType chemQuestionType;
	
	
	@Column(name = "content")
	private String chemQuestionContent;
	
	@Column(name = "answer")
	private String chemQuestionAnswer;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "difficulty")
	private Difficulty chemQuestionDifficulty;
	
    @ManyToMany(cascade =  CascadeType.ALL,
				mappedBy = "questions")
    private List<Paper> papers = new ArrayList<>();
	
	//----------------- End of Attributes
	public ChemQuestion() {
		super();
	}
	
	//for create
	public ChemQuestion(Grade chemQuestionGrade, Chapter chemQuestionChapter, String chemQuestionLabe, QuestionType chemQuestionType,
			String chemQuestionContent, String chemQuestionAnswer, Difficulty chemQuestionDifficulty) {
		super();
		this.chemQuestionGrade = chemQuestionGrade;
		this.chemQuestionChapter = chemQuestionChapter;
		this.chemQuestionLabel = chemQuestionLabe;
		this.chemQuestionType = chemQuestionType;
		this.chemQuestionContent = chemQuestionContent;
		this.chemQuestionAnswer = chemQuestionAnswer;
		this.chemQuestionDifficulty = chemQuestionDifficulty;
	}
	//for update
	public ChemQuestion(Integer chemQuesId, Grade chemQuestionGrade, Chapter chemQuestionChapter, String chemQuestionLabe, QuestionType chemQuestionType,
			String chemQuestionContent, String chemQuestionAnswer, Difficulty chemQuestionDifficulty) {
		super();
		this.chemQuesId = chemQuesId;
		this.chemQuestionGrade = chemQuestionGrade;
		this.chemQuestionChapter = chemQuestionChapter;
		this.chemQuestionLabel = chemQuestionLabe;
		this.chemQuestionType = chemQuestionType;
		this.chemQuestionContent = chemQuestionContent;
		this.chemQuestionAnswer = chemQuestionAnswer;
		this.chemQuestionDifficulty = chemQuestionDifficulty;
	}
	
	//--------------------------------- End of Constructors
	
	
	public Integer getChemQuesId() {
		return chemQuesId;
	}
	public void setChemQuesId(Integer chemQuesId) {
		this.chemQuesId = chemQuesId;
	}
	public Grade getChemQuestionGrade() {
		return chemQuestionGrade;
	}

	public void setChemQuestionGrade(Grade chemQuestionGrade) {
		this.chemQuestionGrade = chemQuestionGrade;
	}

	public Chapter getChemQuestionChapter() {
		return chemQuestionChapter;
	}
	public void setChemQuestionChapter(Chapter chemQuestionChapter) {
		this.chemQuestionChapter = chemQuestionChapter;
	}
	public String getChemQuestionLabe() {
		return chemQuestionLabel;
	}
	public void setChemQuestionLabe(String chemQuestionLabe) {
		this.chemQuestionLabel = chemQuestionLabe;
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
		
	public Difficulty getChemQuestionDifficulty() {
		return chemQuestionDifficulty;
	}

	public void setChemQuestionDifficulty(Difficulty chemQuestionDifficulty) {
		this.chemQuestionDifficulty = chemQuestionDifficulty;
	}
	
	public QuestionType getChemQuestionType() {
		return chemQuestionType;
	}
	
	public void setChemQuestionType(QuestionType chemQuestionType) {
		this.chemQuestionType = chemQuestionType;
	}
	

	//------------------------------- End of Getter and Setter
	

	// transform to Dto
	public ChemQuestionDto questionToDto() {
		return new ChemQuestionDto(this.getChemQuesId(), 
									this.getChemQuestionGrade().toString(),
									this.getChemQuestionChapter().toString(), 
									this.getChemQuestionLabe(), 
									this.getChemQuestionType().toString(),
									this.getChemQuestionContent(), 
									this.getChemQuestionAnswer(), 
									this.getChemQuestionDifficulty().toString());
	}

	// Easier access
	@Override
	public String toString() {
		return "ChemQuestion [chemQuesId=" + chemQuesId + ", chemQuestionChapter=" + chemQuestionChapter
				+ ", chemQuestionLabe=" + chemQuestionLabel + ", chemQuestionType=" + chemQuestionType
				+ ", chemQuestionContent=" + chemQuestionContent + ", chemQuestionAnswer=" + chemQuestionAnswer
				+ ", chemQuestionDifficulty=" + chemQuestionDifficulty + "]";
	}

}