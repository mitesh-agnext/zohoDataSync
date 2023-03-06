package com.agnext.zohodatasync.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ZohoTokenServiceTest {

	@Autowired
	ZohoTokenService tokenService;

	@Test
	void testAccessTokenRequest(){
		String zohoAccessToken = tokenService.getZohoAccessToken();
		System.out.println(zohoAccessToken);

	}

}
