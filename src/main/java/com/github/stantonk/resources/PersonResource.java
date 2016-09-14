package com.github.stantonk.resources;

import com.codahale.metrics.annotation.Timed;
import com.github.stantonk.api.Person;
import com.github.stantonk.db.PersonDao;
import org.hibernate.validator.constraints.NotEmpty;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.validation.Valid;
import javax.ws.rs.*;
import java.util.Optional;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

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
    @Timed
    public Person getPerson(@PathParam("id") int id) {
        // demonstrates java.util.Optional's .orElseThrow()
        // and the "Dropwizard preferred" way of returning JSON-serialized errors
        // leveraging JAX-RS's WebApplicationException and standardized HTTP error Status enum.
        // see: https://jax-rs-spec.java.net/nonav/2.0-rev-a/apidocs/index.html
        return personDao
                .findById(id)
                .orElseThrow(() -> new WebApplicationException("Person not found.", NOT_FOUND));
    }

    @POST
    @Timed
    public Person addPerson(@Valid @NotEmpty Person newPerson) {
        Person p = personDao.create(newPerson);
        logger.info("Created new person {}", p);
        return p;
    }
}
