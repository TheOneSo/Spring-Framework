package com.oneso.service;

import com.oneso.dao.PersonDao;
import com.oneso.domain.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@DisplayName("Person service should")
class PersonServiceImplTest {

	private PersonService personService;
	private PersonDao personDao;
	private Person person;

	@BeforeEach
	void setUp() {
		personDao = mock(PersonDao.class);
		personService = new PersonServiceImpl(personDao);
		person = new Person("test", "test");
	}

	@Test
	@DisplayName("should create new person")
	void createNewPerson() {
		doNothing().when(personDao).addPerson(any());
		Person actual = personService.createNewPerson("test", "test");

		verify(personDao, times(1)).addPerson(any());
		assertEquals(person.getName(), actual.getName());
	}

	@Test
	@DisplayName("should get person by name")
	void getPersonByName() {
		given(personDao.findByName(any())).willReturn(person);
		Person actual = personService.getPerson("test");

		verify(personDao, times(1)).findByName(any());
		assertEquals(person, actual);
	}

	@Test
	@DisplayName("should get person info")
	void getPersonInfo() {
		given(personDao.findByName(any())).willReturn(person);
		String actual = personService.getPersonInfo("test");

		verify(personDao, times(1)).findByName(any());
		assertEquals(person.toString(), actual);
	}

	@Test
	@DisplayName("should get list of participants")
	void getListOfParticipant() {
		given(personDao.getPersonList()).willReturn(Collections.singletonList(person));
		String actual = personService.getListOfParticipants();

		verify(personDao, times(1)).getPersonList();
		assertNotNull(actual);
	}
}
