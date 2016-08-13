package com.github.stantonk.resources;

import com.github.stantonk.api.Person;
import com.github.stantonk.db.PersonDao;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Defines the Person Resource and all the supported HTTP verbs.
 * This is where the CRUD operations happen.
 */
@Path("/person")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class PersonResource {
    private static final Logger logger = LoggerFactory.getLogger(PersonResource.class);

    /**
     * The DBI object is injected in case you want a lower-level access point to the
     * database.
     */
    private final DBI jdbi;
    private final PersonDao personDao;

    public PersonResource(DBI jdbi, PersonDao personDao) {
        this.jdbi = jdbi;
        this.personDao = personDao;
    }

    @GET
    @Path("/{id}")
    public Person getPerson(@PathParam("id") int id) {
        return personDao.findById(id);
    }

    @POST
    public Person addPerson(Person newPerson) {
        Person p = personDao.create(newPerson);
        logger.info("Created {} new person", p);
        return p;
    }
}
