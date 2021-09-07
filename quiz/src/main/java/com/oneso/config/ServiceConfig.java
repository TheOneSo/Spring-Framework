package com.oneso.config;

import com.oneso.service.ScannerService;
import com.oneso.service.ScannerServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class ServiceConfig {

	@Bean
	public ScannerService scannerService() {
		return new ScannerServiceImpl(new Scanner(System.in));
	}
}
