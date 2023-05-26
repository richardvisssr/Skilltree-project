package nl.han.oose.project.resources;

import jakarta.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class ApplicationResource extends jakarta.ws.rs.core.Application {
    public String getApplicationPath() {
        return "/";
    }
}
