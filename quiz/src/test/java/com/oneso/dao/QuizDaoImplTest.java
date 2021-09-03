package com.oneso.dao;

import com.oneso.exceptions.DaoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("QuizDao should")
class QuizDaoImplTest {

	private static final String CORRECT_CSV_PATH = "csv/quizTest.csv";
	private static final String INCORRECT_CSV_PATH = "csv/failCSVTest.csv";

	@Test
	@DisplayName("should get exception if path to csv is incorrect")
	void getExceptionWithIncorrectPathToCSV() {
		assertThrows(DaoException.class, () -> new QuizDaoImpl("123"));
	}

	@Test
	@DisplayName("should get exception is csv is incorrect")
	void getExceptionFromIncorrectCSV() {
		assertThrows(DaoException.class, () -> new QuizDaoImpl(INCORRECT_CSV_PATH));
	}

	@Test
	@DisplayName("should create new Quiz")
	void createNewQuiz() throws DaoException {
		QuizDao quizDao = new QuizDaoImpl(CORRECT_CSV_PATH);

		assertEquals("1", quizDao.getQuiz().getQuizAsMap().get("test1").trim());
		assertNotNull(quizDao.getQuiz().getQuizAsMap().get("test2"));
	}
}
