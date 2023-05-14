package nl.han.oose.project.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.han.oose.project.business.services.StudentService;

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

//    @POST
//    @Path("/{studentId}/skilltrees/{skilltreeId}")
//    public Response addStudentToSkilltree(@PathParam("studentId") int studentId, @PathParam("skilltreeId") int skilltreeId) {
//        try {
//            studentService.addStudentToSkilltree(studentId, skilltreeId);
//            return Response.status(Response.Status.OK).build();
//        } catch (SQLException e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//        }
//    }
    @POST
    @Path("/{skilltreeId}")
    public Response addStudentsToSkilltree(@PathParam("skilltreeId") int skilltreeId, @QueryParam("studentIds") String studentIds) {
        try {
            List<Integer> studentIdList = Arrays.stream(studentIds.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            studentService.addStudentsToSkilltree(studentIdList, skilltreeId);
            return Response.status(Response.Status.OK).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }



    @Inject
    public void setStudentService(StudentService studentService){
        this.studentService = studentService;
    }
}
