package controllers;

import com.codahale.metrics.annotation.Timed;
import models.User;
import models.UserList;
import storage.GatewayFactory;
import storage.UserGateWay;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UsersResource {

    private final UserGateWay userGateWay;

    public UsersResource(GatewayFactory gatewayFactory) {
        this.userGateWay = gatewayFactory.createUserGateWay();
    }

    @GET
    @Timed
    public UserList getAllUsers() {
        List<User> users = this.userGateWay.getAllUsers();
        return new UserList(users, users.size(), 0);
    }

    @POST
    @Timed
    public Integer createUser(@Valid User user) {
        return this.userGateWay.createUser(user);
    }

    @PUT
    @Path("{id}")
    @Timed
    public void updateUser(@PathParam("id") final int id, @Valid User user) {
        this.userGateWay.updateUserById(id, user);
    }

    @GET
    @Path("{id}")
    @Timed
    public User getUserById(@PathParam("id") final int id) {
        return this.userGateWay.getUserById(id);
    }

    @DELETE
    @Path("{id}")
    @Timed
    public void removeUserById(@PathParam("id") final int id) {
        this.userGateWay.removeUserById(id);
    }
}