package com.oneso.dao;

import com.oneso.domain.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonDao {
	void addPerson(Person person);

	Person findByName(String name);

	List<Person> getPersonList();
}
