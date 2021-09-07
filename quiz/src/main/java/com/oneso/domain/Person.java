package com.oneso.domain;

public class Person {

	private final String name;
	private final String surname;
	private int result;

	public Person(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int getResult() {
		return result;
	}

	@Override
	public String toString() {
		return String.format("Your name = '%s' '%s' & your result = %d", name, surname, result);
	}
}
