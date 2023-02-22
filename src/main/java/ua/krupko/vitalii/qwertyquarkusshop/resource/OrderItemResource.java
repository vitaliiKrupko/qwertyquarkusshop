package ua.krupko.vitalii.qwertyquarkusshop.resource;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import ua.krupko.vitalii.qwertyquarkusshop.service.OrderItemService;
import ua.krupko.vitalii.qwertyquarkusshop.service.dto.OrderItemDto;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

@Path("/order-items")
@Tag(name = "orderItem", description = "All the orderItem methods")
public class OrderItemResource {
    @Inject
    OrderItemService itemService;

    @GET
    @Path("/order/{id}")
    public List<OrderItemDto> findByOrderId(@PathParam("id") Long id) {
        return this.itemService.findByOrderId(id);
    }

    @GET
    @Path("/{id}")
    public OrderItemDto findById(@PathParam("id") Long id) {
        return this.itemService.findById(id);
    }

    @POST
    public OrderItemDto create(OrderItemDto orderItemDto) {
        return this.itemService.create(orderItemDto);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        this.itemService.delete(id);
    }
}
