package com.oneso.service;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service("scannerService")
public class ScannerServiceImpl implements ScannerService {

	private final Scanner scanner;

	public ScannerServiceImpl(Scanner scanner) {
		this.scanner = scanner;
	}

	@Override
	public String scanAnswer() {
		return scanner.nextLine().trim();
	}

	@Override
	public void closeScanner() {
		scanner.close();
	}
}
