package com.agnext.zohodatasync.repository;

import com.agnext.zohodatasync.entity.AttendanceViewEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AttendanceViewRepository extends PagingAndSortingRepository<AttendanceViewEntity,Long> {

	List<AttendanceViewEntity> findAllByUserId(String userId);
}
