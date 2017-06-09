import controllers.MessagesResource;
import controllers.UsersResource;
import database.PostgresGateWayFactory;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import strategies.GetMessagesBySquareSelection;

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
    public void run(LocationsConfiguration configuration, Environment environment) {
        PostgresGateWayFactory factory = new PostgresGateWayFactory();
        final UsersResource resource = new UsersResource(factory);
//
        final SimpleHealthCheck healthCheck = new SimpleHealthCheck();
        environment
                .healthChecks()
                .register("template", healthCheck);

        environment.jersey().register(resource);
        environment.jersey().register(new MessagesResource(factory, new GetMessagesBySquareSelection(100000d)));
    }

}