package com.oneso;

import com.oneso.service.QuizService;
import com.oneso.service.QuizServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringQuiz {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
		QuizService quizService = context.getBean(QuizServiceImpl.class);
		quizService.startQuiz();
	}
}
