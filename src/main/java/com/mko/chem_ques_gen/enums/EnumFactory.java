package com.mko.chem_ques_gen.enums;

public class EnumFactory {
	
	public static Grade generateGrade(String enums) {
		switch(enums.toUpperCase()) {
		case "GRADE_10":
			return Grade.GRADE_10;
		case "GRADE_11":
			return Grade.GRADE_11;
		case "GRADE_12":
			return Grade.GRADE_12;
		}
		return null;
	}
	
	public static QuestionType generateQuestionType(String enums) {
		switch(enums.toUpperCase()) {
			case "TRUEFALSE":
				return QuestionType.TRUEFALSE;
			case "FILLBLANK":
				return QuestionType.FILLBLANK;
			case "MCQ":
				return QuestionType.MCQ;
			case "FIVE_MARK":
				return QuestionType.FIVE_MARK;
		}
		return null;
	}
	
	public static Difficulty generateDifficulty(String enums) {
		switch(enums.toLowerCase()) {
			case "easy":
				return Difficulty.EASY;
			case "normal":
				return Difficulty.NORMAL;
			case "hard":
				return Difficulty.HARD;
		}
		return null;
	}
	
	public static Chapter generateChapter(String enums) {
		switch(enums.toUpperCase()) {
			case "CHAPTER_1":
				return Chapter.CHAPTER_1;
			case "CHAPTER_2":
				return Chapter.CHAPTER_2;
			case "CHAPTER_3":
				return Chapter.CHAPTER_3;
			case "CHAPTER_4":
				return Chapter.CHAPTER_4;
			case "CHAPTER_5":
				return Chapter.CHAPTER_5;
			case "CHAPTER_6":
				return Chapter.CHAPTER_6;
			case "CHAPTER_7":
				return Chapter.CHAPTER_7;
			case "CHAPTER_8":
				return Chapter.CHAPTER_8;
		}
		
		return null;
	}
}
