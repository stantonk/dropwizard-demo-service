package com.github.stantonk.resources;

import com.github.stantonk.api.Person;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by stantonk on 8/9/16.
 */
@Path("/person")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class PersonResource {

    public PersonResource() {
    }

    @POST
    public Person addPerson(@Valid Person newPerson) {
        return new Person("John", "Smith", 21);
    }
}
