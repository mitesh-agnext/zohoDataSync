package com.agnext.zohodatasync.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class Utility {

	private static final String MATRIX_DATE_FORMAT = "dd/MM/yyyy";
	private static final String MATRIX_DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
	private static final String ZOHO_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static String getCurrentDate() {
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(MATRIX_DATE_FORMAT);
		String currentDateInStringFormat = currentDate.format(formatter);
		log.debug("CurentDate : {}", currentDateInStringFormat);
		return currentDateInStringFormat;
	}

	public static LocalDateTime getLocalDateTimeFromMatrixDate(String date) {
		DateTimeFormatter matrixDateTimeFormatter = DateTimeFormatter.ofPattern(MATRIX_DATE_TIME_FORMAT);
		return LocalDateTime.parse(date, matrixDateTimeFormatter);
	}

	public static String getZohoDateTimeFromLocalDateTime(LocalDateTime localDateTime) {
		DateTimeFormatter zohoDateTimeFormatter = DateTimeFormatter.ofPattern(ZOHO_DATE_TIME_FORMAT);
		return localDateTime.format(zohoDateTimeFormatter);
	}

	public static String getZohoDateTimeFromMatrixDateTime(String date) {
		DateTimeFormatter zohoDateTimeFormatter = DateTimeFormatter.ofPattern(ZOHO_DATE_TIME_FORMAT);
		return getLocalDateTimeFromMatrixDate(date).format(zohoDateTimeFormatter);
	}

	public static String getZohoDateTimeFormat(){
		return ZOHO_DATE_TIME_FORMAT;
	}

}
