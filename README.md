# TODO healthchecks
# TODO database access with connection pooling
# TODO load test demonstration with randomness
# TODO graphite/statsd monitoring via GraphiteReporter and Dropwizard-metrics
# TODO connection pooling
# TODO admin interface




# DemoService

# Bootstrap DropWizard Service
# Bootstrap a service

mvn archetype:generate \
  -DgroupId=com.github.stantonk \
  -DartifactId=demo-service \
  -Dpackage=com.github.stantonk \
  -Dversion=0.0.1-SNAPSHOT \
  -DarchetypeArtifactId=java-simple \
  -DarchetypeGroupId=io.dropwizard.archetypes \
  -DinteractiveMode=false \
  -Dname=DemoService

This example leverages postgresql, via:

https://wiki.postgresql.org/wiki/PostgreSQL_For_Development_With_Vagrant

vagrant up

How to start the DemoService application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/demo-service-0.0.1-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`
