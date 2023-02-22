package ua.krupko.vitalii.qwertyquarkusshop.resource;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.Test;
import ua.krupko.vitalii.qwertyquarkusshop.utils.TestContainerResource;

import javax.inject.Inject;

import static javax.ws.rs.core.Response.Status.NO_CONTENT;
import static javax.ws.rs.core.Response.Status.OK;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.delete;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.greaterThan;


@QuarkusTest
@QuarkusTestResource(TestContainerResource.class)
class CartResourceTest {
    private static final String INSERT_WRONG_CART_IN_DB =
            "insert into carts values (999, current_timestamp, current_timestamp,'NEW', 3)";
    private static final String DELETE_WRONG_CART_IN_DB =  "delete from carts where id = 999";

//    @Inject
//    Datasource datasource;

    @Test
    void testFindAll() {
        get("/carts")
        .then()
        .statusCode(OK.getStatusCode())
        .body("size()", greaterThan(0));
    }

    @Test
    void testFindAllActiveCarts() {
        get("/carts/active").then()
                .statusCode(OK.getStatusCode());
    }

    @Test
    void testGetActiveCartForCustomer() {
        get("/carts/customer/3").then()
                .contentType(ContentType.JSON)
                .statusCode(OK.getStatusCode())
                .body(containsString("Peter"));
    }

    @Test
    void testFindById() {
        get("/carts/3").then()
                .statusCode(OK.getStatusCode())
                .body(containsString("status"))
                .body(containsString("NEW"));
        get("/carts/100").then()
                .statusCode(NO_CONTENT.getStatusCode());
    }

    @Test
    void testDelete() {
        get("/carts/active").then()
                .statusCode(OK.getStatusCode())
                .body(containsString("Jason"))
                .body(containsString("NEW"));
        delete("/carts/1").then()
                .statusCode(NO_CONTENT.getStatusCode());
        get("/carts/1").then()
                .statusCode(OK.getStatusCode())
                .body(containsString("Jason"))
                .body(containsString("CANCELED"));
    }
}
