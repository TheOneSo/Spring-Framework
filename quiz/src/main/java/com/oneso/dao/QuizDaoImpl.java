package com.oneso.dao;

import com.oneso.domain.Quiz;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class QuizDaoImpl implements QuizDao {

	private static final Logger LOG = LoggerFactory.getLogger(QuizDaoImpl.class);

	private final String resources;
	private Quiz quiz;

	public QuizDaoImpl(String resources) {
		this.resources = resources;
	}

	@Override
	public Quiz getQuiz() {
		if(quiz != null) {
			quiz.getQuizAsMap().keySet().forEach(s -> System.out.println(s));
			return quiz;
		}

		quiz = new Quiz();
		parseCSVAsQuiz();
		return quiz;
	}

	private void parseCSVAsQuiz() {
		URL url = QuizDaoImpl.class.getClassLoader().getResource(resources);
		if (url == null) {
			LOG.error("CSV file is not found");
		} else {
			if(url.getProtocol().equals("jar")) {
				InputStream in = getClass().getResourceAsStream("/" + resources);
				if(in == null) {
					LOG.error("CSV file is not found");
				} else {
					try(BufferedReader br = new BufferedReader(new InputStreamReader(in));) {
						quiz.addQA(readCsv(br));
					} catch (IOException | CsvValidationException | ArrayIndexOutOfBoundsException e) {
						LOG.error("CSV file is unreadable");
					}
				}
			} else {
				try(BufferedReader reader = Files.newBufferedReader(Paths.get(url.toURI()))) {
					quiz.addQA(readCsv(reader));
				} catch (IOException | CsvValidationException | URISyntaxException | ArrayIndexOutOfBoundsException e) {
					LOG.error("CSV file is unreadable");
				}
			}
		}
	}

	private Map<String, String> readCsv(BufferedReader reader) throws CsvValidationException, IOException {
		Map<String, String> out = new HashMap<>();
		var csvReader = new CSVReader(reader);

		String[] next;
		while ((next = csvReader.readNext()) != null) {
			if(next[0] != null && next[1] != null) {
				out.put(next[0], next[1]);
			}
		}
		return out;
	}
}
