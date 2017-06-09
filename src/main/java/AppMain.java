import commands.Command;
import commands.CreateMessagesTableCommand;
import commands.CreateUsersTableCommand;
import controllers.MessagesController;
import controllers.UserController;
import database.PostgresDAOFactory;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import strategies.GetMessagesByLocationStrategy;
import strategies.GetMessagesCompositeStrategy;
import strategies.GetPrivateMessagesByDistance;
import strategies.GetPublicMessagesByDistance;

import java.util.ArrayList;
import java.util.List;

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
        for (Command c : this.prepareApplication()) {
            c.execute();
        }
        System.out.println("Application ready to startg");
    }

    @Override
    public void run(LocationsConfiguration configuration, Environment environment) {
        final PostgresDAOFactory factory = new PostgresDAOFactory();

        final UserController resource = new UserController(factory);


        final SimpleHealthCheck healthCheck = new SimpleHealthCheck();
        final MessagesController messagesController = new MessagesController(factory, this.prepareStrategy(configuration));

        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
        environment.jersey().register(messagesController);
    }

    private GetMessagesByLocationStrategy prepareStrategy(LocationsConfiguration configuration) {
        GetMessagesCompositeStrategy compositeStrategy = new GetMessagesCompositeStrategy();

        compositeStrategy.addChild(new GetPrivateMessagesByDistance(configuration.getDistancePrivate()));
        compositeStrategy.addChild(new GetPublicMessagesByDistance(configuration.getDistancePublic()));

        return compositeStrategy;
    }

    private List<Command> prepareApplication() {
        ArrayList<Command> list = new ArrayList<>();

        list.add(new CreateUsersTableCommand());
        list.add(new CreateMessagesTableCommand());

        return list;
    }

}