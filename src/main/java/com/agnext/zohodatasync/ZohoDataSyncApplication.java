package com.agnext.zohodatasync;

import com.agnext.zohodatasync.service.CosecReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class ZohoDataSyncApplication implements CommandLineRunner  {

	public static void main(String[] args) {
		SpringApplication.run(ZohoDataSyncApplication.class, args);
	}

	@Autowired CosecReader cosecReader;

	@Override
	public void run(String... args) throws Exception {
		cosecReader.getDataForId();
	}
}
