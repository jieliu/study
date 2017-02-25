package com.tianma.spring.rest.domain;

/**
 * Created by fiboliu on 16-11-10.
 */
public class Book {

    private String name;

    private int numbers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumbers() {
        return numbers;
    }

    public void setNumbers(int numbers) {
        this.numbers = numbers;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", numbers=" + numbers +
                '}';
    }
}
