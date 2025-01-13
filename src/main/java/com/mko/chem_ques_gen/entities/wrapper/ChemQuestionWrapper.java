package com.mko.chem_ques_gen.entities.wrapper;

public class ChemQuestionWrapper {

	private Integer paperId;
	private Integer chemQuestionId;
	private String chemQuestionContent;
	private String chemQuestionAnswer;
	private String chemQuestionType;
	
	public ChemQuestionWrapper() {
		super();
	}
	
	public ChemQuestionWrapper(Integer paperId, Integer chemQuestionId, String chemQuestionContent, String chemQuestoinAnswer,
			String chemQuestionType) {
		super();
		this.paperId = paperId;
		this.chemQuestionId = chemQuestionId;
		this.chemQuestionContent = chemQuestionContent;
		this.chemQuestionAnswer = chemQuestoinAnswer;
		this.chemQuestionType = chemQuestionType;
	}

	public Integer getChemQuestionId() {
		return chemQuestionId;
	}

	public void setChemQuestionId(Integer chemQuesId) {
		this.chemQuestionId = chemQuesId;
	}

	public String getChemQuestionContent() {
		return chemQuestionContent;
	}

	public void setChemQuestionContent(String chemQuestionContent) {
		this.chemQuestionContent = chemQuestionContent;
	}



	public Integer getPaperId() {
		return paperId;
	}

	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}

	public String getChemQuestionAnswer() {
		return chemQuestionAnswer;
	}

	public void setChemQuestionAnswer(String chemQuestoinAnswer) {
		this.chemQuestionAnswer = chemQuestoinAnswer;
	}

	public String getChemQuestionType() {
		return chemQuestionType;
	}

	public void setChemQuestionType(String type) {
		this.chemQuestionType = type;
	}

	@Override
	public String toString() {
		return "ChemQuestionWrapper [paperId=" + paperId + ", chemQuestionId=" + chemQuestionId + ", chemQuestionContent="
				+ chemQuestionContent + ", chemQuestoinAnswer=" + chemQuestionAnswer + ", chemQuestiontype=" + chemQuestionType + "]";
	}



	
	
	
}
