package com.oneso.dao;

import com.oneso.domain.Quiz;
import com.oneso.exceptions.DaoException;
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
	private final Quiz quiz;

	public QuizDaoImpl(String resources) throws DaoException {
		URL url = QuizDaoImpl.class.getClassLoader().getResource(resources);
		if (url == null) {
			LOG.error("CSV file is not found");
			throw new DaoException("CSV file is not found");
		}

		if(url.getProtocol().equals("jar")) {
			InputStream in = getClass().getResourceAsStream("/" + resources);
			if(in == null) {
				LOG.error("CSV file is not found");
				throw new DaoException("CSV file is not found");
			}

			try(BufferedReader br = new BufferedReader(new InputStreamReader(in));) {
				quiz = new Quiz(readCsv(br));
			} catch (IOException | CsvValidationException | ArrayIndexOutOfBoundsException e) {
				LOG.error("CSV file is unreadable");
				throw new DaoException("CSV file is unreadable", e);
			}
		} else {
			try(BufferedReader reader = Files.newBufferedReader(Paths.get(url.toURI()))) {
				quiz = new Quiz(readCsv(reader));
			} catch (IOException | CsvValidationException | URISyntaxException | ArrayIndexOutOfBoundsException e) {
				LOG.error("CSV file is unreadable");
				throw new DaoException("CSV file is unreadable", e);
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

	@Override
	public Quiz getQuiz() {
		return quiz;
	}
}
