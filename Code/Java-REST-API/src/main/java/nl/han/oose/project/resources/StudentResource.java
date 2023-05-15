package nl.han.oose.project.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.han.oose.project.business.services.StudentService;
import nl.han.oose.project.resources.dto.StudentsDTO;
import nl.han.oose.project.resources.dto.StudentDTO;
import nl.han.oose.project.resources.dto.StudentsRequestDTO;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    @Path("skilltrees/{skilltreeId}")

    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentsBySkilltree(@PathParam("skilltreeId") int skilltreeId) {
        try {
            return Response.status(Response.Status.OK).entity(studentService.getStudentsBySkilltree(skilltreeId)).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("skilltrees/{skilltreeId}")
    public Response addStudentsToSkilltree(@PathParam("skilltreeId") int skilltreeId, StudentsRequestDTO studentsRequestDTO) {
        try {

            studentService.addStudentsToSkilltree(studentsRequestDTO, skilltreeId);
            return Response.status(Response.Status.OK).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }



    @Inject
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }
}
