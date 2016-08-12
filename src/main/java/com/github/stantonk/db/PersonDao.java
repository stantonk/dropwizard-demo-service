package com.github.stantonk.db;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

/**
 * Created by stantonk on 8/10/16.
 */
public interface PersonDao {
    @SqlUpdate("insert into person (first_name, last_name, age) values (:first_name, :last_name, :age)")
    void create(@Bind("first_name") String firstName, @Bind("last_name") String lastName, @Bind("age") Integer age);

//    @SqlQuery("select name from something where id = :id")
//    String findNameById(@Bind("id") int id);
}
