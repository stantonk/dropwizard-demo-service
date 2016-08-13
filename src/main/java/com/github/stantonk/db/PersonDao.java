package com.github.stantonk.db;

import com.github.stantonk.api.Person;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data Access Object for the Person entity/Representation.
 */
@RegisterMapper(PersonDao.PersonMapper.class)
public interface PersonDao {
    /**
     * Create a new Person in the database.
     * <p>
     * Demonstrates @BindBean binding annotation that automatically binds properties on
     * the object to the SQL query by using the People POJO's field names.
     * <p>
     * This is part of JDBI's SQL Object Argument Binding features, see:
     * <p>
     * http://jdbi.org/sql_object_api_argument_binding/
     * <p>
     * N.B. This is really bad-ass, it is a nice balance between the syntactic brevity of
     * an ORM and the clarity of writing and executing the raw queries.
     *
     * This also demonstrates a cool feature I didn't know existed in JDBI, the
     * GetGeneratedKeys annotation combined with a ResultSetMapper.
     * see: http://stackoverflow.com/questions/32478300/how-to-combine-jdbi-getgeneratedkeys-with-mapper
     *
     * @param p
     * @return number of rows created
     */
    @SqlUpdate("insert into person (first_name, last_name, age) values (:p.firstName, :p.lastName, :p.age)")
    @GetGeneratedKeys(PersonMapper.class)
    Person create(@BindBean("p") Person p);

    /**
     * Lookup a Person in the database by the primary key.
     *
     * Also demonstrates the ResultSetMapper, which is admittedly a bit manual of a thing to write,
     * but it also gives you ultimate control over object construction and it is reusable throughout
     * the Dao, as demonstrated above in the create() method.
     *
     * @param id
     * @return
     */
    @SqlQuery("select id, first_name, last_name, age from person where id= :id")
    Person findById(@Bind("id") int id);

    class PersonMapper implements ResultSetMapper<Person> {
        @Override
        public Person map(int i, ResultSet rs, StatementContext statementContext) throws SQLException {
            return new Person(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getInt("age"));
        }
    }
}
