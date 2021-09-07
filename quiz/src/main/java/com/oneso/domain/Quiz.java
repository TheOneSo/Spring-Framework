package com.oneso.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Quiz {

	private final Map<String, String> qa = new HashMap<>();

	public Quiz() {
	}

	public Quiz(Map<String, String> questionsAndAnswers) {
		qa.putAll(questionsAndAnswers);
	}

	public Quiz(String question, String answer) {
		qa.put(question, answer);
	}

	public void addQA(String question, String answer) {
		qa.put(question, answer);
	}

	public void addQA(Map<String, String> questionsAndAnswers) {
		qa.putAll(questionsAndAnswers);
	}

	public Map<String, String> getQuizAsMap() {
		return Collections.unmodifiableMap(qa);
	}
}
