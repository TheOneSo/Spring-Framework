package com.oneso.service;

import java.util.Scanner;

public class ScannerServiceImpl implements ScannerService {

	private final Scanner scanner;

	public ScannerServiceImpl() {
		this.scanner = new Scanner(System.in);
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
