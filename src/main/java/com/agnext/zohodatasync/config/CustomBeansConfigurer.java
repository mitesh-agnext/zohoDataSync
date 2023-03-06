package com.agnext.zohodatasync.config;

import com.agnext.zohodatasync.interceptors.RequestResponseHandlerInterceptor;
import com.agnext.zohodatasync.service.ZohoTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class CustomBeansConfigurer {

	@Autowired
	RestConnectionConfigurations restConnectionConfiguration;

	@Autowired
	ZohoTokenService zohoTokenService;

	@Bean(name = "zohoApiRestTemplate")
	public RestTemplate getZohoApiRestTemplate() {
		RestTemplate restTemplate = new RestTemplateBuilder()
						.setConnectTimeout(
										Duration.ofSeconds(restConnectionConfiguration.getConnectionTimeoutInSeconds()))
						.setReadTimeout(
										Duration.ofSeconds(restConnectionConfiguration.getReadTimeoutInSeconds()))
						.build();
		restTemplate.setRequestFactory(
						new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
		restTemplate.setMessageConverters(
						Collections.singletonList(new MappingJackson2HttpMessageConverter()));
		List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
		if (CollectionUtils.isEmpty(interceptors)) {
			interceptors = new ArrayList<>();
		}
		interceptors.add(new RequestResponseHandlerInterceptor(zohoTokenService));
		restTemplate.setInterceptors(interceptors);
		restTemplate.setInterceptors(interceptors);
		return restTemplate;
	}

	@Bean
	public Boolean disableSSLValidation() throws Exception {
		final SSLContext sslContext = SSLContext.getInstance("TLS");

		sslContext.init(null, new TrustManager[] { new X509TrustManager() {
			@Override
			public void checkClientTrusted(X509Certificate[] x509Certificates, String s)
							throws CertificateException {
			}

			@Override
			public void checkServerTrusted(X509Certificate[] x509Certificates, String s)
							throws CertificateException {
			}

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return new X509Certificate[0];
			}
		} }, null);

		HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		});

		return true;
	}
}
