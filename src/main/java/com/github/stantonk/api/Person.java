package com.github.stantonk.api;

import com.google.common.base.MoreObjects;
import io.dropwizard.jackson.JsonSnakeCase;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

/**
 * Represents a Person
 */
@JsonSnakeCase // use snake_case instead of camelCase when serializing/deserializing :)
public class Person {

    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    @Pattern(regexp="\\d{3}-\\d{3}-\\d{4}", message="Please enter a valid phone number as XXX-YYY-ZZZZ.")
    private String phoneNumber;

    public Person() {
    }

    public Person(long id, String firstName, String lastName, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("firstName", firstName)
                .add("lastName", lastName)
                .add("phoneNumber", phoneNumber)
                .toString();
    }
}
