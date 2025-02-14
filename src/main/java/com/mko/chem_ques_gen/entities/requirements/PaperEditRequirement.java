package com.mko.chem_ques_gen.entities.requirements;

import java.util.ArrayList;
import java.util.List;

public class PaperEditRequirement {
	private List<Integer> oldQuestionsIdList = new ArrayList<>();
	private List<Integer> newQuestionsIdList = new ArrayList<>();
	
	
	public PaperEditRequirement(List<Integer> oldQuesIdList, List<Integer> newQuesIdList) {
		this.oldQuestionsIdList.addAll(oldQuesIdList);
		this.newQuestionsIdList.addAll(newQuesIdList);
	}


	public List<Integer> getOldQuestionsIdList() {
		return oldQuestionsIdList;
	}


	public void setOldQuestionsIdList(List<Integer> oldQuestionsIdList) {
		this.oldQuestionsIdList = oldQuestionsIdList;
	}


	public List<Integer> getNewQuestionsIdList() {
		return newQuestionsIdList;
	}


	public void setNewQuestionsIdList(List<Integer> newQuestionsIdList) {
		this.newQuestionsIdList = newQuestionsIdList;
	}


	@Override
	public String toString() {
		return "PaperEditRequirement [oldQuestionsIdList=" + oldQuestionsIdList + ", newQuestionsIdList="
				+ newQuestionsIdList + "]";
	}
	
	
	
}
