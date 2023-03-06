package com.agnext.zohodatasync.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilityTest {

	@Test
	void getCurrentDate() {
		String currentDate = Utility.getCurrentDate();
		//System.out.println(currentDate);
	}

	@Test
	void getLocalDateTimeFromMatrixDate() {
		String date = "15/11/2022 18:15:15";
		Utility.getLocalDateTimeFromMatrixDate(date);
	}

	@Test
	void getZohoDateTimeFromLocalDateTime() {
		String date = "15/11/2022 18:15:15";
		String zohoDateTimeFromLocalDateTime =
						Utility.getZohoDateTimeFromLocalDateTime(Utility.getLocalDateTimeFromMatrixDate(date));
		//System.out.println("zohoTime : " + zohoDateTimeFromLocalDateTime);
	}
}
