package com.agnext.zohodatasync.service;

import com.agnext.zohodatasync.model.ApiDailyAttendanceModel;
import com.agnext.zohodatasync.repository.AttendanceViewRepository;
import com.agnext.zohodatasync.repository.impl.JdbcApiDailyAttendanceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CosecReader {

	private final JdbcApiDailyAttendanceImpl attendanceImpl;

	private final AttendanceViewRepository attendanceViewRepository;


	public void getDataByDateFromCosecDBServer(){

	}

	public void getDataForId(){
		List<ApiDailyAttendanceModel> byId = attendanceImpl.findAllByUserId("AG123");
		byId.stream().forEach(id -> {
			System.out.println(id);
		});
		log.debug(String.valueOf(byId.size()));
	}


}
