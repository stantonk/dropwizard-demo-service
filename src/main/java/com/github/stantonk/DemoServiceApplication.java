package com.github.stantonk;

import com.github.stantonk.db.PersonDao;
import com.github.stantonk.resources.PersonResource;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoServiceApplication extends Application<DemoServiceConfiguration> {
    public static final Logger logger = LoggerFactory.getLogger(DemoServiceConfiguration.class);

    public static void main(final String[] args) throws Exception {
        new DemoServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "DemoService";
    }

    @Override
    public void initialize(final Bootstrap<DemoServiceConfiguration> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );

    }

    @Override
    public void run(final DemoServiceConfiguration config,
                    final Environment environment) {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, config.getDataSourceFactory(), "postgresql");
        PersonDao personDao = jdbi.onDemand(PersonDao.class);

//        environment.jersey().register(new WebExceptionMapper());
        environment.jersey().register(new PersonResource(jdbi, personDao));
    }

}
