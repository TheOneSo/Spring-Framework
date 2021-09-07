package com.oneso;

import com.oneso.service.QuizService;
import com.oneso.service.QuizServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class SpringQuiz {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringQuiz.class);
		QuizService quizService = context.getBean(QuizServiceImpl.class);
		quizService.startQuiz();
	}
}
