package com.agnext.zohodatasync.service;

import com.agnext.zohodatasync.config.RestConnectionConfigurations;
import com.agnext.zohodatasync.model.ZohoAccessTokenResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;

@Service
@Slf4j
public class ZohoTokenService {

	private final String host = "https://accounts.zoho.in/oauth/v2/token";
	private final String clientId = "1000.YA5P2AB7HXHFV0AQY97RF5FV7UN7SD";
	private final String clientSecret = "da3a2c169be44d77748f82899b91733cb25f71ae7e";
	private final String refereshAccessToken = "1000.277950cc46323093efeb1315433eae2f.004598416cc77188cfa8ac5be5ea34c0";
	private final String grant_type = "refresh_token";

	@Autowired
	RestConnectionConfigurations restConnectionConfiguration;

	public String getZohoAccessToken() {
		return retrieveAccessTokenForZoho();
	}

	private String retrieveAccessTokenForZoho() {
		RestTemplate restTemplate = new RestTemplateBuilder()
						.setConnectTimeout(
										Duration.ofSeconds(restConnectionConfiguration.getConnectionTimeoutInSeconds()))
						.setReadTimeout(
										Duration.ofSeconds(restConnectionConfiguration.getReadTimeoutInSeconds()))
						.build();

		return retrieveAccessTokenForZoho(restTemplate);
	}

	public String retrieveAccessTokenForZoho(RestTemplate restTemplate) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String,String> map = new LinkedMultiValueMap<String,String>();
		map.add("grant_type", grant_type);
		map.add("client_id", clientId);
		map.add("client_secret", clientSecret);
		map.add("refresh_token", refereshAccessToken);
		HttpEntity<MultiValueMap<String,String>> multiValueMapHttpEntity = new HttpEntity<>(map, headers);
		try {
			ResponseEntity<ZohoAccessTokenResponse> accessTokenResponse =
							restTemplate.postForEntity(host, multiValueMapHttpEntity, ZohoAccessTokenResponse.class);
			log.info("Response from ZohoService has status code :{} and respond body: {}",
			         accessTokenResponse.getStatusCode(), accessTokenResponse.getBody());
			return processZohoResponse(accessTokenResponse);
		} catch (HttpStatusCodeException httpStatusCodeException) {
			log.error("API request failed with status code {} and message {}",
			          httpStatusCodeException.getStatusCode(),
			          httpStatusCodeException.getResponseBodyAsString());
			throw new ResponseStatusException(httpStatusCodeException.getStatusCode(),
			                                  httpStatusCodeException.getResponseBodyAsString());
		} catch (ResourceAccessException connectionException) {
			log.error("API request failed with status exception {} and message {}",
			          connectionException, connectionException.getMessage());
			throw new ResponseStatusException(HttpStatus.GATEWAY_TIMEOUT,
			                                  connectionException.getMessage());
		}
	}

	private String processZohoResponse(ResponseEntity<ZohoAccessTokenResponse> accessTokenResponse) {
		ZohoAccessTokenResponse tokenResponse = accessTokenResponse.getBody();
		if (tokenResponse == null) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN,
			                                  "Unable to retrive Token Response for Access Token");
		}
		return tokenResponse.getAccessToken();
	}


}
