package com.oneso.service;

import java.util.Scanner;

public class ScannerServiceImpl implements ScannerService {

	private final Scanner scanner;

	public ScannerServiceImpl() {
		this.scanner = new Scanner(System.in);
	}

	public String scanAnswer() {
		return scanner.nextLine().trim();
	}

	public void closeScanner() {
		scanner.close();
	}
}
