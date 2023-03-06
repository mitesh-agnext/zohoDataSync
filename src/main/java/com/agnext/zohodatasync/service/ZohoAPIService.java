package com.agnext.zohodatasync.service;


import com.agnext.zohodatasync.config.RestConnectionConfigurations;
import com.agnext.zohodatasync.utils.Utility;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Duration;

@Service
@Slf4j
public class ZohoAPIService {

	private final String host = "https://people.zoho.in";
	private final String url_bulkImportAPI = "/people/api/attendance/bulkImport";

	@Autowired
	RestConnectionConfigurations restConnectionConfiguration;

	@Autowired
	ZohoTokenService zohoTokenService;

	public boolean synchPayloadToZoho(String payloadForZohoCall) {

		RestTemplate restTemplate = new RestTemplateBuilder()
						.setConnectTimeout(
										Duration.ofSeconds(restConnectionConfiguration.getConnectionTimeoutInSeconds()))
						.setReadTimeout(
										Duration.ofSeconds(restConnectionConfiguration.getReadTimeoutInSeconds()))
						.build();

		String accessTokenForZoho = zohoTokenService.retrieveAccessTokenForZoho(restTemplate);
		log.debug("AccessTokenForZoho : {}", accessTokenForZoho);
		if (StringUtils.isBlank(accessTokenForZoho)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN,
			                                  "unable to retrieve AccessToken through Interceptor");
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.setBearerAuth(accessTokenForZoho);
		MultiValueMap<String,String> map = new LinkedMultiValueMap<String,String>();
		map.add("data", payloadForZohoCall);
		map.add("dateFormat", Utility.getZohoDateTimeFormat());
		HttpEntity<MultiValueMap<String,String>> multiValueMapHttpEntity = new HttpEntity<>(map, headers);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(host + url_bulkImportAPI);
		try {
			ResponseEntity<String> accessTokenResponse =
							restTemplate.postForEntity(builder.toUriString(), multiValueMapHttpEntity, String.class);
			log.info("Response from ZohoService has status code :{} and respond body: {}",
			         accessTokenResponse.getStatusCode(), accessTokenResponse.getBody());
			return true;
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
}
