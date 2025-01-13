package com.mko.chem_ques_gen.enums;

public class EnumFactory {
	
	public static Grade generateGrade(String enums) {
		switch(enums.toUpperCase()) {
		case "G1":
			return Grade.G1;
		case "G2":
			return Grade.G2;
		case "G3":
			return Grade.G3;
		case "G4":
			return Grade.G4;
		case "G5":
			return Grade.G5;
		case "G6":
			return Grade.G6;
		case "G7":
			return Grade.G7;
		case "G8":
			return Grade.G8;
		case "G9":
			return Grade.G9;
		case "G10":
			return Grade.G10;
		case "G11":
			return Grade.G11;
		case "G12":
			return Grade.G12;
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
			case "C1":
				return Chapter.C1;
			case "C2":
				return Chapter.C2;
			case "C3":
				return Chapter.C3;
			case "C4":
				return Chapter.C4;
			case "C5":
				return Chapter.C5;
			case "C6":
				return Chapter.C6;
			case "C7":
				return Chapter.C7;
			case "C8":
				return Chapter.C8;
		}
		
		return null;
	}
}
