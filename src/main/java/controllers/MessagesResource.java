package controllers;

import com.codahale.metrics.annotation.Timed;
import models.Message;
import models.MessageList;
import storage.GatewayFactory;
import storage.MessageGateWay;
import strategies.GetMessagesByLocationStrategy;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("messages")
@Produces(MediaType.APPLICATION_JSON)
public class MessagesResource {

    private final MessageGateWay messageGateWay;
    private GetMessagesByLocationStrategy strategy;

    public MessagesResource(GatewayFactory gatewayFactory, GetMessagesByLocationStrategy strategy) {
        this.messageGateWay = gatewayFactory.createMessageGateWay();
        this.strategy = strategy;
    }

    @GET
    @Timed
    public MessageList getAllMessages() {
        List<Message> list = this.messageGateWay.getAllMessages();

        return new MessageList(list, list.size(), 0);
    }

    @POST
    @Timed
    public Integer createMessage(@Valid Message message) {
        return this.messageGateWay.createMessage(message);
    }

    @GET
    @Path("{id}")
    @Timed
    public Message getMessageBydId(@PathParam("id") final int id) {
        return this.messageGateWay.getMessage(id);
    }

    @DELETE
    @Path("{id}")
    @Timed
    public void removeMessageById(@PathParam("id") final int id) {
        this.messageGateWay.removeMessageById(id);
    }

    @PUT
    @Path("{id}")
    @Timed
    public void updateMessageById(@PathParam("id") final int id, @Valid Message message) {
        this.messageGateWay.updateById(id, message);
    }

    @GET
    @Path("location")
    @Timed
    public List<Integer> getMessagesByLocation(@QueryParam("receiver") final int id,
                                               @QueryParam("lat") final double lat,
                                               @QueryParam("long") final double lon){

        return this.strategy.getMessagesIds(this.messageGateWay, lat, lon, id);
    }
}
