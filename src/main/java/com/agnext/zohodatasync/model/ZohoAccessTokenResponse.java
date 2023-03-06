package com.agnext.zohodatasync.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ZohoAccessTokenResponse {

	@JsonProperty("access_token")
	private String accessToken;
	@JsonProperty("api_domain")
	private String apiDomain;
	@JsonProperty("token_type")
	private String tokenType;
	@JsonProperty("expires_in")
	private Integer expiresInSeconds;

}
