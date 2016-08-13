package com.github.stantonk.api;

import io.dropwizard.jackson.JsonSnakeCase;

/**
 * Represents a Person
 */
@JsonSnakeCase // use snake_case instead of camelCase when serializing/deserializing :)
public class Person {

    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;

    public Person() {
    }

    public Person(long id, String firstName, String lastName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
