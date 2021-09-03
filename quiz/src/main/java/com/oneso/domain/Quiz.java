package com.oneso.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Quiz {

	private static final Map<String, String> qa = new HashMap<>();

	public Quiz(Map<String, String> questionsAndAnswers) {
		qa.putAll(questionsAndAnswers);
	}

	public Quiz(String question, String answer) {
		qa.put(question, answer);
	}

	public void addQA(String question, String answer) {
		qa.put(question, answer);
	}

	public Map<String, String> getQuizAsMap() {
		return Collections.unmodifiableMap(qa);
	}
}
