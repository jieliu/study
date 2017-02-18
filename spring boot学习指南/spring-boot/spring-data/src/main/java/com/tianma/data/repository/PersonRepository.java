package com.tianma.data.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tianma.data.domain.Person;

import java.io.IOException;
import java.util.Collection;

/**
 * Created by fiboliu on 16-8-29.
 */
public interface PersonRepository {

    int save(Person person) throws JsonProcessingException;

    Collection<Person> findByFirstname(String firstname) throws IOException;

    Collection<Person> findAll() throws IOException;

    Collection<Person> findAllPaged(int index, int perPageNumber) throws IOException;

    int deleteAll();

    int deleteAllByFirstname(String firstname) throws IOException;
}
