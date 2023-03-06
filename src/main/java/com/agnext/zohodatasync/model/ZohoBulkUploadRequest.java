package com.agnext.zohodatasync.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ZohoBulkUploadRequest {

	@JsonProperty("data")
	private String data;
	@JsonProperty("dateFormat")
	private String dateFormat;

}
