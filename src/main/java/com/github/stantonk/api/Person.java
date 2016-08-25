package com.github.stantonk.api;

import com.google.common.base.MoreObjects;
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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("firstName", firstName)
                .add("lastName", lastName)
                .add("age", age)
                .toString();
    }
}
