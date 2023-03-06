package com.agnext.zohodatasync.service;


import com.agnext.zohodatasync.model.ApiDailyAttendanceModel;
import com.agnext.zohodatasync.model.ZohoRecords;
import com.agnext.zohodatasync.repository.ApiDailyAttendanceRepository;
import com.agnext.zohodatasync.utils.Utility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.javafx.geom.DirtyRegionPool;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class AttendanceSynchService {

	private ApiDailyAttendanceRepository dailyAttendanceRepository;
	private ZohoAPIService zohoAPIService;


	public void synchMatrixToZoho(){
		//String date = Utility.getCurrentDate();
		String date = "05/01/2023";
		List<ApiDailyAttendanceModel> allRecordsForDate = dailyAttendanceRepository.findAllRecordsForDate(date);
		/*allRecordsForDate.stream().forEach(record -> {
			log.debug("record for ID : {} and punchinTime :{} and punchOutTime :{}", record.getUserID(), record.getPunch1_Date(), record.getOutPunch_Date());
		});*/
		String payloadForZohoCall = createPayloadForZohoCall(allRecordsForDate);
		log.info("Payload is : {}", payloadForZohoCall);
		//zohoAPIService.synchPayloadToZoho(payloadForZohoCall);
	}


	private String createPayloadForZohoCall(List<ApiDailyAttendanceModel> allRecordsForDate) {
		ObjectMapper mapper = new ObjectMapper();
		MatrixZohoMapping matrixZohoMapping = MatrixZohoMapping.MATRIX_ZOHO_MAPPING;
		Map<String,String> matrixZohoIdMap = matrixZohoMapping.getMatrixZohoIdMap();
		List<String> idsNotProcessed = new ArrayList<>();
		List<ZohoRecords> zohoPayloadList = new ArrayList<>();
		allRecordsForDate.stream().forEach(atdRecord -> {
			String zohoId = getZohoId(atdRecord.getUserID());
			if((StringUtils.isBlank(zohoId)) || (StringUtils.isBlank(atdRecord.getPunch1()) && StringUtils.isBlank(atdRecord.getPunch2()))){
				idsNotProcessed.add(atdRecord.getUserID());
				log.error("Record not processed for userId : {}, with InPunch :{} and Outpunch:{}",
				          atdRecord.getUserID(), atdRecord.getPunch1(), atdRecord.getOutPunch());
				return;
			}
			String checkIn = getDateForZoho(atdRecord.getPunch1());
			String checkout = getDateForZoho(atdRecord.getOutPunch());
			ZohoRecords checkInRecord = ZohoRecords.builder().empId(getZohoId(atdRecord.getUserID())).checkIn(checkIn).build();
			ZohoRecords checkOutRecord = ZohoRecords.builder().empId(getZohoId(atdRecord.getUserID())).checkOut(checkout).build();
			zohoPayloadList.add(checkInRecord);
			zohoPayloadList.add(checkOutRecord);
		});

		Map<String,ApiDailyAttendanceModel> collect =
						allRecordsForDate.stream().collect(Collectors.toMap(a -> a.getUserID(), Function.identity()));

		idsNotProcessed.stream().forEach(record -> {
			ApiDailyAttendanceModel apiDailyAttendanceModel = collect.get(record);
			System.out.println("MatrixId:" + apiDailyAttendanceModel.getUserID() + " UserName:" + apiDailyAttendanceModel.getUserName());
		});
		try{
			return mapper.writeValueAsString(zohoPayloadList);
		}catch (JsonProcessingException e) {
			log.error("Unable to parse Payload for Zoho");
			throw new RuntimeException(e);
		}
	}

	private String getDateForZoho(String punch1) {
		if(StringUtils.isBlank(punch1)){
			return null;
		}
		return Utility.getZohoDateTimeFromMatrixDateTime(punch1);
	}

	private String getZohoId(String userID) {
		if(MatrixZohoMapping.MATRIX_ZOHO_MAPPING.isUserIdMappingExist(userID)){
			return MatrixZohoMapping.MATRIX_ZOHO_MAPPING.getZohoIdForMatrixId(userID);
		}
		return null;
	}

}
