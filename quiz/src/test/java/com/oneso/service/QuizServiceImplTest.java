package com.oneso.service;

import com.oneso.dao.QuizDao;
import com.oneso.domain.Person;
import com.oneso.domain.Quiz;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Quiz service should")
class QuizServiceImplTest {

	@Mock
	private QuizDao quizDao;

	@Mock
	private ScannerService scannerService;

	@Mock
	private PersonService personService;

	private QuizService quizService;
	private Quiz quiz;
	private Person person;

	@BeforeEach
	void setUp() {
		quizService = new QuizServiceImpl(quizDao, scannerService, personService);
		quiz = new Quiz("test", "test");
		person = new Person("test", "test");
	}

	@Test
	@DisplayName("should to run quiz")
	void runQuiz() {
		given(quizDao.getQuiz()).willReturn(quiz);
		given(scannerService.scanAnswer()).willReturn("test");
		given(personService.getListOfParticipants()).willReturn("test");
		given(personService.createNewPerson("test", "test")).willReturn(person);
		doNothing().when(scannerService).closeScanner();

		quizService.startQuiz();

		verify(scannerService, times(4)).scanAnswer();
		verify(quizDao, times(1)).getQuiz();
		verify(personService, times(1)).getListOfParticipants();
	}
}
