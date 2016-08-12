package com.github.stantonk.resources;

import com.github.stantonk.api.Person;
import com.github.stantonk.db.PersonDao;
import org.hibernate.validator.constraints.NotEmpty;
import org.skife.jdbi.v2.DBI;

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

    private final DBI jdbi;
    private final PersonDao personDao;

    public PersonResource(DBI jdbi, PersonDao personDao) {
        this.jdbi = jdbi;
        this.personDao = personDao;
    }

    @POST
    public Person addPerson(Person newPerson) {
        personDao.create(newPerson.getFirstName(), newPerson.getLastName(), newPerson.getAge());
        return newPerson;
//        jdbi.withHandle(h -> h.insert(""))
    }
}
