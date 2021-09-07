package com.oneso.service;

import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ScannerService should")
class ScannerServiceImplTest {

	private static final String TEXT = "12345";

	private InputStream stdin;
	private ScannerService scannerService;

	@BeforeEach
	void setUp() {
		stdin = System.in;
		System.setIn(new ByteArrayInputStream(TEXT.getBytes()));
		scannerService = new ScannerServiceImpl(new Scanner(System.in));
	}

	@AfterEach
	void cleanUp() {
		scannerService.closeScanner();
		System.setIn(stdin);
	}

	@Test
	@DisplayName("should get correct text from console")
	void getCorrectTextFromConsole() {
		String result = scannerService.scanAnswer();
		assertEquals(TEXT, result);
	}
}
