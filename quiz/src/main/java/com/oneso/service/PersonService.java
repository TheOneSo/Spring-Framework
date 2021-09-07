package com.oneso.service;

import com.oneso.domain.Person;

public interface PersonService {
	Person createNewPerson(String name, String surname);

	String getPersonInfo(String name);

	Person getPerson(String name);

	String getListOfParticipants();
}
