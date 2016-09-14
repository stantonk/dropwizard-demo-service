Overview
--------
This is a practical technology demonstration of many of the features of
the Dropwizard REST Framework. It integrates many of the features with
common external tools and systems, e.g.

* PostgreSQL
* StatsD/Graphite (https://localhost:8443/)
* Nagios

The service will implement a simple phone book. It demonstrates some
simple CRUD operations. The real goal is not the service itself, but to
demonstrate a reasonable and fairly robust approach to building a
**production-ready** RESTful HTTP service in Dropwizard.

Additionally, several features and practical approaches of modern Java 
development will be demonstrated wherever possible.

For example, Maven is an indispensible build tool, test runner, and
dependency management system for Java (and really all JVM development).
While there are certainly many other newer tools similar to Maven
(e.g. Gradle, Ivy, Sbt), Maven is probably the most ubiquitous and also
one of the easiest to find answers to questions on StackOverflow ;-).

Before getting started, make sure you have the [latest JDK ](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) installed,
as well as Maven (easily installable via Homebrew).

Now, we can bootstrap a skeleton Dropwizard service quickly using
[Maven Archetypes](https://maven.apache.org/guides/introduction/introduction-to-archetypes.html).
Archetypes are templates for any number of types of projects, there are
literally hundreds of them in [Maven Central](http://search.maven.org/).

```
mvn archetype:generate \
  -DgroupId=com.github.stantonk \
  -DartifactId=demo-service \
  -Dpackage=com.github.stantonk \
  -Dversion=0.0.1-SNAPSHOT \
  -DarchetypeArtifactId=java-simple \
  -DarchetypeGroupId=io.dropwizard.archetypes \
  -DinteractiveMode=false \
  -Dname=DemoService
```

....

This example leverages postgresql, via:

https://wiki.postgresql.org/wiki/PostgreSQL_For_Development_With_Vagrant

vagrant up

# bootstrap db schema
psql -h localhost -U demoservice --password -p 15432 -f ./schema.sql

How to start the DemoService application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/demo-service-0.0.1-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Exercising Crud Operations
--------------------------
curl -X POST -H 'Content-Type: application/json' -d '{"first_name": "Kevin", "last_name": "Stanton", "age": 35}' -vs "http://localhost:8080/person"
curl -X GET -H 'Content-Type: application/json' -vs "http://localhost:8080/person/1"

Health Check
---
To see your applications health enter url `http://localhost:8081/healthcheck`

You get a database and thread deadlock healthcheck out of the box.

For example, this demonstrates:
 
 1. The Database Healthcheck
 2. Recovery upon return of the postgres database

You could, for instance, use the healthcheck to pull an instance of your
service out of a load balancer.
```
$  curl -s "http://localhost:8081/healthcheck" | jqc .
{
  "deadlocks": {
    "healthy": true
  },
  "postgresql": {
    "healthy": true
  }
}

$  vagrant halt
==> default: Attempting graceful shutdown of VM...

$  curl -s "http://localhost:8081/healthcheck" | jqc .
{
  "deadlocks": {
    "healthy": true
  },
  "postgresql": {
    "healthy": false,
    "message": "Unable to successfully check in 3 seconds"
  }
}

$  vagrant up
Bringing machine 'default' up with 'virtualbox' provider...
... snip

$  curl -s "http://localhost:8081/healthcheck" | jqc .
{
  "deadlocks": {
    "healthy": true
  },
  "postgresql": {
    "healthy": true
  }
}
```

Request tracing
---------------
TODO:

Validators and HTTP Error codes
-------------------------------
By using validations, the appropriate HTTP error codes are automatically
returned.

E.g. by adding validators to fields on the `Person` Representation and
@Valid` to the `addPerson()` method:

```
public class Person {

    private Long id;

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private Integer age;
```

```
        @POST
        @Timed
        public Person addPerson(@Valid @NotEmpty Person newPerson) {
            Person p = personDao.create(newPerson);
            logger.info("Created new person {}", p);
            return p;
        }
```

A request missing a required `Person` attribute gets an HTTP 422 and an
informative, standardized error message:

```
$  curl -X POST -H 'Content-Type: application/json' -d '{"last_name": "Washington", "age": 284}' -vs "http://localhost:8080/person" | jq .
...
> POST /person HTTP/1.1
...
< HTTP/1.1 422
...
{
  "errors": [
    "firstName may not be null"
  ]
}
```

# TODO
* healthchecks
* add hikari connection pooling
* load test demonstration with randomness
* graphite/statsd monitoring via GraphiteReporter and Dropwizard-metrics
* admin interface demo
* unit/integration testing examples
* authentication
* more stuff...


Licenses from software leveraged in this demo
---------------------------------------------
https://raw.githubusercontent.com/jackdb/pg-app-dev-vm/master/LICENSE

