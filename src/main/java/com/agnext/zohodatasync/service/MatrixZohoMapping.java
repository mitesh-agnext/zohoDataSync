package com.agnext.zohodatasync.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum MatrixZohoMapping {
	MATRIX_ZOHO_MAPPING(getAttendanceMapping());
	private Map<String,String> matrixZohoIdMap;

	MatrixZohoMapping(Map<String,String> matrixZohoIdMap) {
		this.matrixZohoIdMap = matrixZohoIdMap;
	}

	public Map<String,String> getMatrixZohoIdMap() {
		return Collections.unmodifiableMap(this.matrixZohoIdMap);
	}

	public boolean isUserIdMappingExist(String userId) {
		return this.matrixZohoIdMap.keySet().contains(userId);
	}

	public String getZohoIdForMatrixId(String userId){
		if(!isUserIdMappingExist(userId)){
			return null;
		}
		return this.matrixZohoIdMap.get(userId);
	}

	public boolean isUserIdMappingExist(List<String> userIdList) {
		return this.matrixZohoIdMap.keySet().containsAll(userIdList);
	}

	public List<String> userIdsNotMatching(List<String> userIdList) {
		List<String> matrixKeyList = new ArrayList<>(this.matrixZohoIdMap.keySet());
		return userIdList.stream().filter(userId -> !matrixKeyList.contains(userId))
						.collect(Collectors.toList());
	}

	private static Map<String,String> getAttendanceMapping() {
		Map<String,String> matrixZohoIdMap = new HashMap<>();
		matrixZohoIdMap.put("AG001", "AG001");
		matrixZohoIdMap.put("AG002", "AG002");
		matrixZohoIdMap.put("AG005", "AG005");
		matrixZohoIdMap.put("AG028", "AG028");
		matrixZohoIdMap.put("AG035", "AG035");
		matrixZohoIdMap.put("AG036", "AG036");
		matrixZohoIdMap.put("AG043", "AG043");
		matrixZohoIdMap.put("AG046", "AG046");
		matrixZohoIdMap.put("AG049", "AG049");
		matrixZohoIdMap.put("AG053", "AG053");
		matrixZohoIdMap.put("AG055", "AG055");
		matrixZohoIdMap.put("AG073", "AG073");
		matrixZohoIdMap.put("AG074", "AG074");
		matrixZohoIdMap.put("AG078", "AG078");
		matrixZohoIdMap.put("AG081", "AG081");
		matrixZohoIdMap.put("AG083", "AG083");
		matrixZohoIdMap.put("AG085", "AG085");
		matrixZohoIdMap.put("AG093", "AG093");
		matrixZohoIdMap.put("AG094", "AG094");
		matrixZohoIdMap.put("AG101", "AG101");
		matrixZohoIdMap.put("AG102", "AG102");
		matrixZohoIdMap.put("AG105", "AG105");
		matrixZohoIdMap.put("AG120", "AG120");
		matrixZohoIdMap.put("AG121", "AG121");
		matrixZohoIdMap.put("AG123", "AG123");
		matrixZohoIdMap.put("AG126", "AG126");
		matrixZohoIdMap.put("AG127", "AG127");
		matrixZohoIdMap.put("AG128", "AG128");
		matrixZohoIdMap.put("AG132", "AG132");
		matrixZohoIdMap.put("AG138", "AG138");
		matrixZohoIdMap.put("AG140", "AG140");
		matrixZohoIdMap.put("AG143", "AG143");
		matrixZohoIdMap.put("AG145", "AG145");
		matrixZohoIdMap.put("AG147", "AG147");
		matrixZohoIdMap.put("AG148", "AG148");
		matrixZohoIdMap.put("AG150", "AG150");
		matrixZohoIdMap.put("AG157", "AG157");
		matrixZohoIdMap.put("AG159", "AG159");
		matrixZohoIdMap.put("AG160", "AG160");
		matrixZohoIdMap.put("AG161", "AG161");
		matrixZohoIdMap.put("AG162", "AG162");
		matrixZohoIdMap.put("AG163", "AG163");
		matrixZohoIdMap.put("AG164", "AG164");
		matrixZohoIdMap.put("AG166", "AG166");
		matrixZohoIdMap.put("AG168", "AG168");
		matrixZohoIdMap.put("AG169", "AG169");
		matrixZohoIdMap.put("AG171", "AG171");
		matrixZohoIdMap.put("AG174", "AG174");
		matrixZohoIdMap.put("AG175", "AG175");
		matrixZohoIdMap.put("AG177", "AG177");
		matrixZohoIdMap.put("AG182", "AG182");
		matrixZohoIdMap.put("AG186", "AG186");
		matrixZohoIdMap.put("AG187", "AG187");
		matrixZohoIdMap.put("AG189", "AG189");
		matrixZohoIdMap.put("AG190", "AG190");
		matrixZohoIdMap.put("AG197", "AG197");
		matrixZohoIdMap.put("AG202", "AG202");
		matrixZohoIdMap.put("AG205", "AG205");
		matrixZohoIdMap.put("AG206", "AG206");
		matrixZohoIdMap.put("AG208", "AG208");
		matrixZohoIdMap.put("AG209", "AG209");
		matrixZohoIdMap.put("AG213", "AG213");
		matrixZohoIdMap.put("AG215", "AG215");
		matrixZohoIdMap.put("AG217", "AG217");
		matrixZohoIdMap.put("AG223", "AG223");
		matrixZohoIdMap.put("AG228", "AG228");
		matrixZohoIdMap.put("AG229", "AG229");
		matrixZohoIdMap.put("AG230", "AG230");
		matrixZohoIdMap.put("AG231", "AG231");
		matrixZohoIdMap.put("AG233", "AG233");
		matrixZohoIdMap.put("AG234", "AG234");
		matrixZohoIdMap.put("AG235", "AG235");
		matrixZohoIdMap.put("AG236", "AG236");
		matrixZohoIdMap.put("AG240", "AG240");
		matrixZohoIdMap.put("AG245", "AG245");
		matrixZohoIdMap.put("AG246", "AG246");
		matrixZohoIdMap.put("AG247", "AG247");
		matrixZohoIdMap.put("AG253", "AG253");
		matrixZohoIdMap.put("AG255", "AG255");
		matrixZohoIdMap.put("AG256", "AG256");
		matrixZohoIdMap.put("AG261", "AG261");
		matrixZohoIdMap.put("AG262", "AG262");
		matrixZohoIdMap.put("AG267", "AG267");
		matrixZohoIdMap.put("AG269", "AG269");
		matrixZohoIdMap.put("AG270", "AG270");
		matrixZohoIdMap.put("AG271", "AG271");
		matrixZohoIdMap.put("AG277", "AG277");
		matrixZohoIdMap.put("AG278", "AG278");
		matrixZohoIdMap.put("AG279", "AG279");
		matrixZohoIdMap.put("AG282", "AG282");
		matrixZohoIdMap.put("AG286", "AG286");
		matrixZohoIdMap.put("AG292", "AG292");
		matrixZohoIdMap.put("AG293", "AG293");
		matrixZohoIdMap.put("AG297", "AG297");
		matrixZohoIdMap.put("AG298", "AG298");
		matrixZohoIdMap.put("AG299", "AG299");
		matrixZohoIdMap.put("AG302", "AG302");
		matrixZohoIdMap.put("AG303", "AG303");
		matrixZohoIdMap.put("AG305", "AG305");
		matrixZohoIdMap.put("AG306", "AG306");
		matrixZohoIdMap.put("AG307", "AG307");
		matrixZohoIdMap.put("AG308", "AG308");
		matrixZohoIdMap.put("AG310", "AG310");
		matrixZohoIdMap.put("AG312", "AG312");
		matrixZohoIdMap.put("AG315", "AG315");
		matrixZohoIdMap.put("AG317", "AG317");
		matrixZohoIdMap.put("AG318", "AG318");
		matrixZohoIdMap.put("AG319", "AG319");
		matrixZohoIdMap.put("AG320", "AG320");
		matrixZohoIdMap.put("AG321", "AG321");
		matrixZohoIdMap.put("AG326", "AG326");
		matrixZohoIdMap.put("AG327", "AG327");
		matrixZohoIdMap.put("AG329", "AG329");
		matrixZohoIdMap.put("AG330", "AG330");
		matrixZohoIdMap.put("AG331", "AG331");
		matrixZohoIdMap.put("AG333", "AG333");
		matrixZohoIdMap.put("AG334", "AG334");
		matrixZohoIdMap.put("AG335", "AG335");
		matrixZohoIdMap.put("AG336", "AG336");
		matrixZohoIdMap.put("AG337", "AG337");
		matrixZohoIdMap.put("AG338", "AG338");
		matrixZohoIdMap.put("AG340", "AG340");
		matrixZohoIdMap.put("AG341", "AG341");
		matrixZohoIdMap.put("AG342", "AG342");
		matrixZohoIdMap.put("AG344", "AG344");
		matrixZohoIdMap.put("AG345", "AG345");
		matrixZohoIdMap.put("AG346", "AG346");
		matrixZohoIdMap.put("AG347", "AG347");
		matrixZohoIdMap.put("AG348", "AG348");
		matrixZohoIdMap.put("AG349", "AG349");
		matrixZohoIdMap.put("AG351", "AG351");
		matrixZohoIdMap.put("AG353", "AG353");
		matrixZohoIdMap.put("AG355", "AG355");
		matrixZohoIdMap.put("AG356", "AG356");
		matrixZohoIdMap.put("AG357", "AG357");
		matrixZohoIdMap.put("AG360", "AG360");
		matrixZohoIdMap.put("AG361", "AG361");
		matrixZohoIdMap.put("AG362", "AG362");
		matrixZohoIdMap.put("AG363", "AG363");
		matrixZohoIdMap.put("AG364", "AG364");
		matrixZohoIdMap.put("AG367", "AG367");
		matrixZohoIdMap.put("AG369", "AG369");
		matrixZohoIdMap.put("AG371", "AG371");
		matrixZohoIdMap.put("AG372", "AG372");
		matrixZohoIdMap.put("AG373", "AG373");
		matrixZohoIdMap.put("AG374", "AG374");
		matrixZohoIdMap.put("AG375", "AG375");
		matrixZohoIdMap.put("AG376", "AG376");
		matrixZohoIdMap.put("AG378", "AG378");
		matrixZohoIdMap.put("AG379", "AG379");
		matrixZohoIdMap.put("AG382", "AG382");
		matrixZohoIdMap.put("AG383", "AG383");
		matrixZohoIdMap.put("AG384", "AG384");
		matrixZohoIdMap.put("AG385", "AG385");
		matrixZohoIdMap.put("AG386", "AG386");
		matrixZohoIdMap.put("AG387", "AG387");
		matrixZohoIdMap.put("AG388", "AG388");
		matrixZohoIdMap.put("AG389", "AG389");
		matrixZohoIdMap.put("AG395", "AG395");
		matrixZohoIdMap.put("AG396", "AG396");
		matrixZohoIdMap.put("AG397", "AG397");
		matrixZohoIdMap.put("AG398", "AG398");
		matrixZohoIdMap.put("AG402", "AG402");
		matrixZohoIdMap.put("AG403", "AG403");
		matrixZohoIdMap.put("AG404", "AG404");
		matrixZohoIdMap.put("AG405", "AG405");
		matrixZohoIdMap.put("AG406", "AG406");
		matrixZohoIdMap.put("AG407", "AG407");
		matrixZohoIdMap.put("AG408", "AG408");
		matrixZohoIdMap.put("AG409", "AG409");
		matrixZohoIdMap.put("AG410", "AG410");
		matrixZohoIdMap.put("AG412", "AG412");
		matrixZohoIdMap.put("AG413", "AG414");
		matrixZohoIdMap.put("AG414", "AG414");
		matrixZohoIdMap.put("AGC23", "AGC23");
		matrixZohoIdMap.put("AGC24", "AGC24");
		matrixZohoIdMap.put("AGC30", "AGC30");
		matrixZohoIdMap.put("AGF03", "AGF03");
		matrixZohoIdMap.put("AGF06", "AGF06");
		matrixZohoIdMap.put("AGI138", "AGI138");
		matrixZohoIdMap.put("AGP0001", "AGP0001");
		return matrixZohoIdMap;
	}
}
