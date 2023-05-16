package nl.han.oose.project.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.han.oose.project.business.services.StudentService;
import nl.han.oose.project.resources.dto.StudentsRequestDTO;

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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/skilltrees/{skilltreeId}")
    public Response getStudentsBySkilltree(@PathParam("skilltreeId") int skilltreeId) {
        try {
            return Response.status(Response.Status.OK).entity(studentService.getStudentsBySkilltree(skilltreeId)).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("skilltrees/{skilltreeId}")
    public Response updateStudentsToSkilltree(@PathParam("skilltreeId") int skilltreeId, StudentsRequestDTO studentsRequestDTO) {
        try {
            var students = studentService.updateStudentsToSkilltree(studentsRequestDTO, skilltreeId);
            return Response.status(Response.Status.OK).entity(students).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Inject
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }
}
