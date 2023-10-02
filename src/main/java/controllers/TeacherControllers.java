package controllers;

import domain.Teacher;
import dto.TeacherDTO;
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

@Path("api/teacher")
@Produces({"application/json", "application/xml"})
public class TeacherControllers {

    private EntityManager manager = EntityManagerHelper.getEntityManager();;

    CustomerServices customerServices;
    SlotServices slotServices;
    TeacherServices teacherServices;

    public TeacherControllers() {
        this.customerServices = new CustomerServices();
        this.slotServices = new SlotServices();
        this.teacherServices = new TeacherServices();
    }

    @GET
    @Path("/{teacherId}")
    public TeacherDTO getTeacherById(@PathParam("teacherId") Long teacherId)  {
        TeacherDTO teacher = teacherServices.getTeacherById(teacherId);
        return teacher;
    }

    @GET
    @Path("/")
    public List<TeacherDTO> getTeachers()  {
        return teacherServices.getTeachers();
    }


    @POST
    @Path("/{name}")
    @Consumes("application/json")
    public Response createTeacher(
            @Parameter(description = "Teacher object that needs to be created", required = true) @PathParam("name") String name) {
        teacherServices.createTeacher(name);
        return Response.ok().entity("Teacher created").build();
    }

    @DELETE
    @Path("/{teacherId}")
    @Consumes("application/json")
    public Response deleteTeacher(
            @Parameter(description = "Teacher object that needs to be deleted", required = true) @PathParam("teacherId") Long teacherId) {
        teacherServices.deleteTeacher(teacherId);
        return Response.ok().entity("Teacher deleted").build();
    }
}