import database.PostgreSQLJDBC;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class AppMain extends Application<LocationsConfiguration> {
    public static void main(String[] args) throws Exception {
        new AppMain().run(args);
    }

    @Override
    public String getName() {
        return "app";
    }

    @Override
    public void initialize(Bootstrap<LocationsConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(LocationsConfiguration configuration,
                    Environment environment) {
        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );

        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment
                .healthChecks()
                .register("template", healthCheck);


        PostgreSQLJDBC.getInstance();

        environment.jersey().register(resource);
    }

}