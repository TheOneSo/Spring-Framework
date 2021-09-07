package com.oneso.config;

import com.oneso.dao.QuizDao;
import com.oneso.dao.QuizDaoImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:app.properties")
public class DaoConfig {

	@Value("${csv.path}")
	private String csvPath;

	@Bean
	public QuizDao quizDao() {
		return new QuizDaoImpl(csvPath);
	}
}
