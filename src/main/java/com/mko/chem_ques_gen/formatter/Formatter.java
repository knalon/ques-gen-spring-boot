package com.mko.chem_ques_gen.formatter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Formatter {

	public static String dateTimeNow() {
        LocalDateTime now = LocalDateTime.now().withNano(0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return now.format(formatter);
	}
}
