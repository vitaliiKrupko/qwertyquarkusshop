package ua.krupko.vitalii.qwertyquarkusshop.resource;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import ua.krupko.vitalii.qwertyquarkusshop.service.PaymentService;
import ua.krupko.vitalii.qwertyquarkusshop.service.dto.PaymentDto;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

@Path("/payments")
@Tag(name = "payment", description = "All the payment methods")
public class PaymentResource {
    @Inject
    PaymentService paymentService;

    @GET
    public List<PaymentDto> findAll() {
        return this.paymentService.findAll();
    }

    @GET
    @Path("/{id}")
    public PaymentDto findById(@PathParam("id") Long id) {
        return this.paymentService.findById(id);
    }

    @POST
    public PaymentDto create(PaymentDto orderItemDto) {
        return this.paymentService.create(orderItemDto);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        this.paymentService.delete(id);
    }
    @GET
    @Path("/price/{max}")
    public List<PaymentDto> findPaymentsByAmountRangeMax(@PathParam("max") double max) {
        return this.paymentService.findByPriceRange(max);
    }
}
