package com.agnext.zohodatasync.interceptors;

import com.agnext.zohodatasync.service.ZohoTokenService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

public class RequestResponseHandlerInterceptor implements ClientHttpRequestInterceptor {


	private static Logger logger = LoggerFactory.getLogger(RequestResponseHandlerInterceptor.class);
	private static final String AUTHORIZATION = "Authorization";
	private final ZohoTokenService tokenService;

	public RequestResponseHandlerInterceptor(ZohoTokenService tokenService) {
		this.tokenService = tokenService;
	}

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body,
					ClientHttpRequestExecution clientHttpRequestExecution)
					throws IOException {
		logger.info("ReqOn|URI:[{}]{}, Headers|{}, Body|{}", request.getMethod(), request.getURI(),
		            request.getHeaders(),
		            StreamUtils.copyToString(new ByteArrayInputStream(body), Charset.defaultCharset()));
		//request.getHeaders().setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		request.getHeaders().setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		String accessToken = tokenService.getZohoAccessToken();
		if (StringUtils.isBlank(accessToken)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN,
			                                  "unable to retrieve AccessToken through Interceptor");
		}
		request.getHeaders().remove(AUTHORIZATION);
		request.getHeaders().setBearerAuth(accessToken);
		//retry
		ClientHttpResponse response = clientHttpRequestExecution.execute(request, body);
		logger.info("Response|StatusCode:{}, StatusText:{}, Headers:{}, ResponseBody:{}", response.getStatusCode(),
		            response.getStatusText(), response.getHeaders(),
		            StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()));
		return response;
	}
}
