package com.tianma.data.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by fiboliu on 16-8-29.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {

    private String firstname;

    private int age;

    public Person() {

    }

    public Person(String firstname, int age) {
        this.firstname = firstname;
        this.age = age;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {

        return "Person [name=" + firstname + ", age=" + age + "]";
    }
}
