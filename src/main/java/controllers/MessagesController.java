package controllers;

import com.codahale.metrics.annotation.Timed;
import models.Message;
import models.MessageList;
import storage.DAOFactory;
import storage.MessageDAO;
import strategies.GetMessagesByLocationStrategy;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("messages")
@Produces(MediaType.APPLICATION_JSON)
public class MessagesController {

    private final MessageDAO messageDAO;
    private GetMessagesByLocationStrategy strategy;

    public MessagesController(DAOFactory DAOFactory, GetMessagesByLocationStrategy strategy) {
        this.messageDAO = DAOFactory.createMessageDAO();
        this.strategy = strategy;
    }

    @GET
    @Timed
    public MessageList getAllMessages() {
        List<Message> list = this.messageDAO.getAllMessages();

        return new MessageList(list, list.size(), 0);
    }

    @POST
    @Timed
    public Integer createMessage(@Valid Message message) {
        return this.messageDAO.createMessage(message);
    }

    @GET
    @Path("{id}")
    @Timed
    public Message getMessageBydId(@PathParam("id") final int id) {
        return this.messageDAO.getMessage(id);
    }

    @DELETE
    @Path("{id}")
    @Timed
    public void removeMessageById(@PathParam("id") final int id) {
        this.messageDAO.removeMessageById(id);
    }

    @PUT
    @Path("{id}")
    @Timed
    public void updateMessageById(@PathParam("id") final int id, @Valid Message message) {
        this.messageDAO.updateById(id, message);
    }

    @GET
    @Path("location")
    @Timed
    public List<Integer> getMessagesByLocation(@QueryParam("receiver") final int id,
                                               @QueryParam("lat") final double lat,
                                               @QueryParam("long") final double lon){

        return this.strategy.getIds(this.messageDAO, lat, lon, id);
    }
}
