package com.agnext.zohodatasync.repository.impl;

import com.agnext.zohodatasync.model.ApiDailyAttendanceModel;
import com.agnext.zohodatasync.repository.ApiDailyAttendanceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
@Slf4j
public class JdbcApiDailyAttendanceImpl implements ApiDailyAttendanceRepository {

	private final JdbcTemplate jdbcTemplate;

	@Override
	public int save(ApiDailyAttendanceModel dailyAttendance) {
		return 0;
	}

	@Override
	public int update(ApiDailyAttendanceModel dailyAttendance) {
		return 0;
	}

	@Override
	public ApiDailyAttendanceModel findById(Long id) {
		try {
			ApiDailyAttendanceModel dailyAttendanceModel = jdbcTemplate.queryForObject("SELECT * FROM dbo.Mx_VEW_APIDailyAttendance WHERE userID=?",
			                                                                           BeanPropertyRowMapper.newInstance(ApiDailyAttendanceModel.class), id);

			return dailyAttendanceModel;
		} catch (IncorrectResultSizeDataAccessException e) {
			return null;
		}
	}

	@Override
	public int deleteById(Long id) {
		return 0;
	}

	@Override
	public List<ApiDailyAttendanceModel> findAll() {
		return null;
	}


	//"select UserID, UserName, DPTID, ProcessDate, Punch1_Date, Punch1_Time, Punch2_Date, Punch2_Time,ScheduleShift,WorkingShift,FirstHalf,SecondHalf,Outpunch_Date, OutPunch_Time,SiteID,SUMMARY from dbo.Mx_VEW_APIDailyAttendance where UserId = 'AG140'"
	@Override
	public List<ApiDailyAttendanceModel> findAllByUserId(String userId) {
		try {
			String query = "SELECT * FROM dbo.Mx_VEW_APIDailyAttendance WHERE userID='" + userId +"'";
			List<ApiDailyAttendanceModel> dailyAttendanceModel = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(ApiDailyAttendanceModel.class));
			return dailyAttendanceModel;
		} catch (IncorrectResultSizeDataAccessException e) {
			return null;
		}
	}

	@Override
	public String findlatestDateByUserId(String userId) {
		return null;
	}

	//select UserID,UserName,ProcessDate,Punch1_Date,Punch1_Time,Punch2_Date,Punch2_Time,OutPunch_Date,OutPunch_Time,SUMMARY,WorkTime_HHMM,FirstHalf,SecondHalf FROM COSEC.dbo.Mx_VEW_APIDailyAttendance where ProcessDate = '05/01/2023'

	@Override
	public List<ApiDailyAttendanceModel> findAllRecordsForDate(String date) {
		try {
			String query = "select UserID,UserName,ProcessDate,Punch1,Punch1_Date,Punch1_Time,Punch2,Punch2_Date,Punch2_Time,OutPunch,OutPunch_Date,OutPunch_Time,SUMMARY,WorkTime_HHMM,FirstHalf,SecondHalf FROM COSEC.dbo.Mx_VEW_APIDailyAttendance where ProcessDate ='" +  date + "'";
			List<ApiDailyAttendanceModel> dailyAttendanceModel = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(ApiDailyAttendanceModel.class));
			return dailyAttendanceModel;
		} catch (IncorrectResultSizeDataAccessException e) {
			log.error("got Exception : {}", e.getLocalizedMessage());
			e.printStackTrace();
		}catch (Exception exception){
			log.error("got Exception : {}", exception.getLocalizedMessage());
			exception.printStackTrace();
		}
		return null;
	}
}


