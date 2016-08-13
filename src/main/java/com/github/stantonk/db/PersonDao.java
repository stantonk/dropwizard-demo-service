package com.github.stantonk.db;

import com.github.stantonk.api.Person;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

/**
 * Data Access Object for the Person entity/Representation.
 */
public interface PersonDao {
    /**
     * Create a new Person in the database.
     *
     * Demonstrates @BindBean binding annotation that automatically binds properties on
     * the object to the SQL query by using the People POJO's field names.
     *
     * This is part of JDBI's SQL Object Argument Binding features, see:
     *
     *      http://jdbi.org/sql_object_api_argument_binding/
     *
     * N.B. This is really bad-ass, it is a nice balance between the syntactic brevity of
     * an ORM and the clarity of writing and executing the raw queries.
     *
     * @param p
     * @return number of rows created
     */
    @SqlUpdate("insert into person (first_name, last_name, age) values (:p.firstName, :p.lastName, :p.age)")
    int create(@BindBean("p")Person p);

//    @SqlQuery("select name from something where id = :id")
//    String findNameById(@Bind("id") int id);
}
