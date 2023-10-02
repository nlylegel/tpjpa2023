package controllers;

import domain.Customer;
import dto.CustomerDTO;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jpa.EntityManagerHelper;
import services.CustomerServices;
import services.SlotServices;
import services.TeacherServices;
import java.util.List;

@Path("api/customer")
@Produces({"application/json", "application/xml"})
public class CustomerControllers {

    private EntityManager manager;

    CustomerServices customerServices;
    SlotServices slotServices;
    TeacherServices teacherServices;

    public CustomerControllers() {
        this.customerServices = new CustomerServices();
        this.slotServices = new SlotServices();
        this.teacherServices = new TeacherServices();
    }

        @GET
    @Path("/")
    public List<CustomerDTO> getCustomers()  {
        return customerServices.getCustomers();
    }

    @GET
    @Path("/{customerId}")
    public CustomerDTO getCustomerById(@PathParam("customerId") Long customerId)  {
        CustomerDTO customer = customerServices.getCustomerById(customerId);
        return customer;
    }



    @POST
    @Path("/{name}")
    @Consumes("application/json")
    public Response createCustomer(
            @Parameter(description = "Customer object that needs to be created", required = true) @PathParam("name") String name) {
        customerServices.createCustomer(name);
        return Response.ok().entity("Customer created").build();
    }

    @POST
    @Path("/book/{slotId}/{customerId}")
    @Consumes("application/json")
    public Response bookAppointment(
            @Parameter(description = "Customer books specified slot", required = true) @PathParam("slotId") Long slotId, @PathParam("customerId") Long customerId) {
        customerServices.bookAppointment(slotId, customerId);
        return Response.ok().entity("Slot successfully booked").build();
    }

    @POST
    @Path("/cancel/{slotId}/{customerId}")
    @Consumes("application/json")
    public Response cancelAppointment(
            @Parameter(description = "Customer books specified slot", required = true) @PathParam("slotId") Long slotId, @PathParam("customerId") Long customerId) {
        customerServices.cancelAppointment(slotId, customerId);
        return Response.ok().entity("Slot successfully canceled").build();
    }

    @DELETE
    @Path("/{customerId}")
    @Consumes("application/json")
    public Response deleteCustomer(
            @Parameter(description = "Customer object that needs to be deleted", required = true) @PathParam("customerId") Long customerId) {
        customerServices.deleteCustomer(customerId);
        return Response.ok().entity("Customer deleted").build();
    }
}