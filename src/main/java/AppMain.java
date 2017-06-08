import commands.CreateUsersTableCommand;
import database.PostgresConnectionManager;
import database.PostgresMessagesGateWay;
import database.PostgresUserGateWay;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import models.Message;
import models.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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


        PostgresConnectionManager.getInstance();

        boolean executed = new CreateUsersTableCommand().execute();

        ArrayList<Integer> integers = new ArrayList<>(Arrays.asList(1, 2, 3));
        Message message = new Message("Hello world",
                LocalDateTime.now(),
                Double.valueOf(12.2d),
                Double.valueOf(12.2d),
                integers,
                Integer.valueOf(1),
                Boolean.valueOf(false),
                null
        );


        User jak = new User("jak");

        PostgresUserGateWay way = new PostgresUserGateWay();

        way.createUser(jak);


        PostgresMessagesGateWay gateWay = new PostgresMessagesGateWay();

        gateWay.createMessage(message);
        message.setSenderId(2);
        gateWay.createMessage(message);
        message.setSenderId(3);
        gateWay.createMessage(message);

        List<Message> allMessages = gateWay.getAllMessages();
        for (Message m : allMessages) {
            System.out.println(m.getSenderId() + " cca: " + m.getCreatedAt());
        }

        environment.jersey().register(resource);
    }

}