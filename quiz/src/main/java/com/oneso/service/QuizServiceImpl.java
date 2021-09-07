package com.oneso.service;

import com.oneso.dao.QuizDao;
import com.oneso.domain.Person;
import com.oneso.domain.Quiz;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service("quizService")
@PropertySource("classpath:app.properties")
public class QuizServiceImpl implements QuizService {

	private final QuizDao quizDao;
	private final ScannerService scannerService;
	private final PersonService personService;

	@Value("${min.result}")
	private int minResult;

	public QuizServiceImpl(QuizDao quizDao, ScannerService scannerService, PersonService personService) {
		this.quizDao = quizDao;
		this.scannerService = scannerService;
		this.personService = personService;
	}

	@Override
	public void startQuiz() {
		var person = welcomeNewUser();
		System.out.println("Ok! Let's go!");

		int correct = 0;
		Quiz quiz = quizDao.getQuiz();

		for (var qa : quiz.getQuizAsMap().entrySet()) {

			System.out.println(qa.getKey());
			String answer = scannerService.scanAnswer();

			if (answer.equalsIgnoreCase(qa.getValue().trim())) {
				correct++;
			}
		}
		person.setResult(correct);
		congratulations(person);

		System.out.println("List of results:");
		System.out.println(personService.getListOfParticipants());

		if (repeat()) {
			startQuiz();
		}
		scannerService.closeScanner();
	}

	private Person welcomeNewUser() {
		System.out.println("Welcome to the new Quiz session!");
		System.out.println("What is your name?");
		String name = scannerService.scanAnswer();
		System.out.println("What is your surname?");
		String surname = scannerService.scanAnswer();
		return personService.createNewPerson(name, surname);
	}

	private void congratulations(Person person) {
		if (person.getResult() >= minResult) {
			System.out.printf("Congratulations! %s%n", person);
		} else {
			System.out.println("Sorry! You're a loser!");
		}
	}

	private boolean repeat() {
		System.out.println("Do you want to repeat? [y/n]");
		String answer = scannerService.scanAnswer();

		return answer.equalsIgnoreCase("y");
	}
}
