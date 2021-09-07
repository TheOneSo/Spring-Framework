package com.oneso.dao;

import com.oneso.domain.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PersonDao should")
class PersonDaoImplTest {

	private PersonDao personDao;
	private Person person;

	@BeforeEach
	void setUp() {
		personDao = new PersonDaoImpl();
		person = new Person("test", "test");
	}

	@Test
	@DisplayName("should add new person and find")
	void addNewPersonAndFind() {
		personDao.addPerson(person);
		Person actual = personDao.findByName(person.getName());

		assertEquals(person, actual);
	}

	@Test
	@DisplayName("should get list of persons")
	void getPersonList() {
		List<Person> personList = personDao.getPersonList();

		assertTrue(personList.isEmpty());

		personDao.addPerson(person);

		assertFalse(personList.isEmpty());
	}

	@Test
	@DisplayName("should get exception if person doesn't exist")
	void getExceptionIfPersonDoesNotExist() {
		assertThrows(IndexOutOfBoundsException.class, () -> personDao.findByName("test"));
	}
}
