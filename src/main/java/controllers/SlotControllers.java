package controllers;

import dto.SlotDTO;
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

@Path("api/slot")
@Produces({"application/json", "application/xml"})
public class SlotControllers {
    private EntityManager manager = EntityManagerHelper.getEntityManager();;
    CustomerServices customerServices;
    SlotServices slotServices;
    TeacherServices teacherServices;

    public SlotControllers() {
        this.customerServices = new CustomerServices();
        this.slotServices = new SlotServices();
        this.teacherServices = new TeacherServices();
    }

    @GET
    @Path("/")
    public List<SlotDTO> getSlots()  {
        return slotServices.getSlots();
    }

    @GET
    @Path("/{slotId}")
    public SlotDTO getSlotById(@PathParam("slotId") Long slotId)  {
        SlotDTO slot = slotServices.getSlotById(slotId);
        return slot;
    }

    

    @POST
    @Path("/{teacherId}")
    @Consumes("application/json")
    public Response createSlot(
            @Parameter(description = "Customer object that needs to be created", required = true) @PathParam("teacherId") Long teacherId) {
        slotServices.createSlot(teacherId);
        return Response.ok().entity("Slot created").build();
    }

    @DELETE
    @Path("/{slotId}")
    @Consumes("application/json")
    public Response deleteSlot(
            @Parameter(description = "Customer object that needs to be deleted", required = true) @PathParam("slotId") Long slotId) {
        slotServices.deleteSlot(slotId);
        return Response.ok().entity("Slot deleted").build();
    }
}