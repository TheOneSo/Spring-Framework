package com.oneso.dao;

import com.oneso.domain.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository("personDao")
public class PersonDaoImpl implements PersonDao {

	private final List<Person> personList = new ArrayList<>();

	@Override
	public void addPerson(Person person) {
		personList.add(person);
	}

	@Override
	public Person findByName(String name) {
		Optional<Person> personOptional = personList.stream().filter(p -> p.getName().equalsIgnoreCase(name)).findFirst();
		return personOptional.orElse(personList.get(0));
	}

	@Override
	public List<Person> getPersonList() {
		return Collections.unmodifiableList(personList);
	}
}
