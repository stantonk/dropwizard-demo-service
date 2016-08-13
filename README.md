Overview
--------
This is a practical technology demonstration of many of the features of
the Dropwizard REST Framework. It integrates many of the features with
common external tools and systems, e.g.

* PostgreSQL
* StatsD/Graphite
* Nagios

The service **will** implement a simple phone book. It demonstrates some
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

# TODO
* healthchecks
* add hikari connection pooling
* load test demonstration with randomness
* graphite/statsd monitoring via GraphiteReporter and Dropwizard-metrics
* admin interface demo
* unit/integration testing examples
* more stuff...


Licenses from software leveraged in this demo
---------------------------------------------
https://raw.githubusercontent.com/jackdb/pg-app-dev-vm/master/LICENSE

