package controllers;

import com.codahale.metrics.annotation.Timed;
import models.User;
import models.UserList;
import storage.DAOFactory;
import storage.UserDAO;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    private final UserDAO userDAO;

    public UserController(DAOFactory DAOFactory) {
        this.userDAO = DAOFactory.createUserDAO();
    }

    @GET
    @Timed
    public UserList getAllUsers() {
        List<User> users = this.userDAO.getAllUsers();
        return new UserList(users, users.size(), 0);
    }

    @POST
    @Timed
    public Integer createUser(@Valid User user) {
        return this.userDAO.createUser(user);
    }

    @PUT
    @Path("{id}")
    @Timed
    public void updateUser(@PathParam("id") final int id, @Valid User user) {
        this.userDAO.updateUserById(id, user);
    }

    @GET
    @Path("{id}")
    @Timed
    public User getUserById(@PathParam("id") final int id) {
        return this.userDAO.getUserById(id);
    }

    @DELETE
    @Path("{id}")
    @Timed
    public void removeUserById(@PathParam("id") final int id) {
        this.userDAO.removeUserById(id);
    }
}