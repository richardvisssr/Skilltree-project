package nl.han.oose.project.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.han.oose.project.business.services.StudentService;

import java.sql.SQLException;

@Path("/students")
public class StudentResource {
    private StudentService studentService;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllStudents() {
        try {
            return Response.status(Response.Status.OK).entity(studentService.getAllStudents()).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Inject
    public void setStudentService(StudentService studentService){
        this.studentService = studentService;
    }
}
