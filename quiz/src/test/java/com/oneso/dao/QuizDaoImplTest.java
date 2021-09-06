package com.oneso.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("QuizDao should")
class QuizDaoImplTest {

	private static final String CORRECT_CSV_PATH = "csv/quizTest.csv";
	private static final String INCORRECT_CSV_PATH = "csv/failCSVTest.csv";

	@Test
	@DisplayName("shouldn't read csv if file is not correct")
	void getEmptyQuizIfFileIsNotCorrect() {
		QuizDao quizDao = new QuizDaoImpl(INCORRECT_CSV_PATH);

		assertTrue(quizDao.getQuiz().getQuizAsMap().isEmpty());
	}

	@Test
	@DisplayName("should get an empty quiz with incorrect path to file")
	void getEmptyQuizWithIncorrectPathToFile() {
		QuizDao quizDao = new QuizDaoImpl("123");

		assertTrue(quizDao.getQuiz().getQuizAsMap().isEmpty());
	}

	@Test
	@DisplayName("should create new Quiz")
	void createNewQuiz() {
		QuizDao quizDao = new QuizDaoImpl(CORRECT_CSV_PATH);

		assertEquals("1", quizDao.getQuiz().getQuizAsMap().get("test1").trim());
		assertNotNull(quizDao.getQuiz().getQuizAsMap().get("test2"));
	}
}
