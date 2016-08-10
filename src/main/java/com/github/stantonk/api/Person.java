package com.github.stantonk.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.jackson.JsonSnakeCase;

/**
 * Immutable Person object
 */
@JsonSnakeCase // use snake_case instead of camelCase when serializing/deserializing :)
public class Person {

    private final String firstName;
    private final String lastName;
    private final Integer age;

    public Person(@JsonProperty String firstName, @JsonProperty String lastName,
                  @JsonProperty Integer age) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @JsonProperty
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty
    public String getLastName() {
        return lastName;
    }

    @JsonProperty
    public Integer getAge() {
        return age;
    }
}
