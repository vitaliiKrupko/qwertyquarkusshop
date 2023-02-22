package ua.krupko.vitalii.qwertyquarkusshop.resource;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import ua.krupko.vitalii.qwertyquarkusshop.service.ProductService;
import ua.krupko.vitalii.qwertyquarkusshop.service.dto.ProductDto;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

@Path("/products")
@Tag(name = "product", description = "All the product methods")
public class ProductResource {
    @Inject
    ProductService productService;

    @GET
    public List<ProductDto> findAll() {
        return this.productService.findAll();
    }

    @GET @Path("/count")
    public Long countAllProducts() {
        return this.productService.countAll();
    }

    @GET @Path("/{id}")
    public ProductDto findById(@PathParam("id") Long id) {
        return this.productService.findById(id);
    }

    @POST
    public ProductDto create(ProductDto productDto) {
        return this.productService.create(productDto);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        this.productService.delete(id);
    }

    @GET @Path("/category/{id}")
    public List<ProductDto> findByCategoryId(@PathParam("id") Long id) {
        return this.productService.findByCategoryId(id);
    }

    @GET @Path("/count/category/{id}")
    public Long countByCategoryId(@PathParam("id") Long id) {
        return this.productService.countByCategoryId(id);
    }
}
