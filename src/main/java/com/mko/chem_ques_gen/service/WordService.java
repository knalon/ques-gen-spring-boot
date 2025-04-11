package com.mko.chem_ques_gen.service;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.xmlbeans.XmlException;

public interface WordService {
	public String createWord(Integer paperId) throws IOException, XmlException, InvalidFormatException;
}
