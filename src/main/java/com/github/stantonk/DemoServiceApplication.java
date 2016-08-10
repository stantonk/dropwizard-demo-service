package com.github.stantonk;

import com.github.stantonk.resources.PersonResource;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

public class DemoServiceApplication extends Application<DemoServiceConfiguration> {

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

        environment.jersey().register(new PersonResource());
    }

}
