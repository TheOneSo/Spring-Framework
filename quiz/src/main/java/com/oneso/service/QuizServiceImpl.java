package com.oneso.service;

import com.oneso.dao.QuizDao;
import com.oneso.domain.Quiz;

public class QuizServiceImpl implements QuizService {

	private final QuizDao quizDao;
	private final ScannerService scannerService;
	private String user = "";

	public QuizServiceImpl(QuizDao quizDao, ScannerService scannerService) {
		this.quizDao = quizDao;
		this.scannerService = scannerService;
	}

	@Override
	public void startQuiz() {
		if(user.isEmpty()) {
			welcomeNewUser();
		}
		int correct = 0;
		Quiz quiz = quizDao.getQuiz();

		for(var qa : quiz.getQuizAsMap().entrySet()) {

			System.out.println(qa.getKey());
			String answer = scannerService.scanAnswer();

			if(answer.equalsIgnoreCase(qa.getValue().trim())) {
				correct++;
			}
		}
		congratulations(correct);
		scannerService.closeScanner();
	}

	private void welcomeNewUser() {
		System.out.println("Welcome to the new Quiz session!");
		System.out.println("What is your name?");
		user = scannerService.scanAnswer();

		System.out.printf("Ok! %s! Let's go!%n", user);
	}

	private void congratulations(int correct) {
		if(correct == 0) {
			System.out.println("Sorry! You're a loser!");
		} else {
			System.out.printf("Congratulations! %s has answered %d questions%n", user, correct);
		}
	}
}
