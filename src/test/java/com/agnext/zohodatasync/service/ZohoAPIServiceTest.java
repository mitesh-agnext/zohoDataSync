package com.agnext.zohodatasync.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ZohoAPIServiceTest {

	String payload = "[{\"empId\": \"AG123\", \"checkOut\": \"2023-02-23 18:31:09\"}, {\"empId\": \"AG123\", \"checkIn\": \"2023-02-23 09:27:24\"}]";

	@Autowired
	ZohoAPIService zohoAPIService;

	@Test
	void testZohoBulkUpload() throws UnsupportedEncodingException {
		assertTrue(zohoAPIService.synchPayloadToZoho(payload));
	}
}
