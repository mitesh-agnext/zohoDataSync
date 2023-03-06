package com.agnext.zohodatasync.service;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class MatrixZohoMappingTest {

	@Test
	void getMatrixZohoIdMap() {
		Map<String,String> matrixZohoIdMap =
						MatrixZohoMapping.MATRIX_ZOHO_MAPPING.getMatrixZohoIdMap();
		int size = matrixZohoIdMap.size();
		assertEquals("181", String.valueOf(size));
	}

	@Test
	void isUserIdMappingExist() {
		assertTrue(MatrixZohoMapping.MATRIX_ZOHO_MAPPING.isUserIdMappingExist("AG123"));
	}

	@Test
	void testIsUserIdMappingExist() {
		List<String> userIdList = Arrays.asList("AG055", "AG073", "AG074", "AG078", "AG081", "AG083", "AG123");
		assertTrue(MatrixZohoMapping.MATRIX_ZOHO_MAPPING.isUserIdMappingExist(userIdList));
	}

	@Test
	void userIdsNotMatching() {
		List<String> userIdList = Arrays.asList("AG055", "AG073", "AG074", "AG078", "AG081", "AG083", "AG0985");
		List<String> notMatchinList = MatrixZohoMapping.MATRIX_ZOHO_MAPPING.userIdsNotMatching(userIdList);
		assertEquals(notMatchinList.size(), 1);

	}
}
