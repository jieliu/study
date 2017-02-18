package com.tianma.data.repository;

import com.tianma.data.Application;
import com.tianma.data.domain.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by fiboliu on 16-8-30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class PersonRepositoryImplTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void save() throws Exception {
        Person person = new Person("liujie", 26);
        personRepository.save(person);
    }

    @Test
    public void findByFirstname() throws Exception {
        Collection<Person> persons = personRepository.findByFirstname("liujie");
        if(persons == null) {
            System.out.println("error!!");
        }
        else {
            System.out.println(persons.toString());
        }
    }

    @Test
    public void findAll() throws Exception {
        Collection<Person> persons = personRepository.findAll();
        if(persons == null) {
            System.out.println("error!!");
        }
        else {
            System.out.println(persons.toString());
        }

    }

    @Test
    public void findAllPaged() throws Exception {

    }

    @Test
    public void deleteAll() throws Exception {

    }

    @Test
    public void deleteAllByFirstname() throws Exception {

    }

}