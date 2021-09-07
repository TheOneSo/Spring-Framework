package com.oneso.service;

import com.oneso.dao.PersonDao;
import com.oneso.domain.Person;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("personService")
public class PersonServiceImpl implements PersonService {

	private final PersonDao personDao;

	public PersonServiceImpl(PersonDao personDao) {
		this.personDao = personDao;
	}

	@Override
	public Person createNewPerson(String name, String surname) {
		var person = new Person(name, surname);
		personDao.addPerson(person);
		return person;
	}

	@Override
	public String getPersonInfo(String name) {
		var person = personDao.findByName(name);
		return person.toString();
	}

	@Override
	public Person getPerson(String name) {
		return personDao.findByName(name);
	}

	@Override
	public String getListOfParticipants() {
		List<Person> personList = personDao.getPersonList();
		return personList.stream().map(Person::toString).collect(Collectors.joining("\n"));
	}
}
