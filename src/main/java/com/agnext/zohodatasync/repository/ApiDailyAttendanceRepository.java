package com.agnext.zohodatasync.repository;

import com.agnext.zohodatasync.model.ApiDailyAttendanceModel;

import java.util.List;

public interface ApiDailyAttendanceRepository {

	int save(ApiDailyAttendanceModel dailyAttendance);

	int update(ApiDailyAttendanceModel dailyAttendance);

	ApiDailyAttendanceModel findById(Long id);

	int deleteById(Long id);

	List<ApiDailyAttendanceModel> findAll();

	List<ApiDailyAttendanceModel> findAllByUserId(String userId);

}
