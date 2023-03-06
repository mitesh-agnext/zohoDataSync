package com.agnext.zohodatasync.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;


@Getter
@Setter
@Builder
@ToString
@JsonIgnoreProperties
@JsonInclude(Include.NON_NULL)
public class ZohoRecords {

	private String empId;
	private String checkIn;
	private String checkOut;

}
