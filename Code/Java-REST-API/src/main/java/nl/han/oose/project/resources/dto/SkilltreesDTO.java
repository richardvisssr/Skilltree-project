package nl.han.oose.project.resources.dto;

import java.util.ArrayList;
import java.util.List;

public class SkilltreesDTO {
    private List<SkilltreeDTO> skilltrees = new ArrayList<>();

    public List<SkilltreeDTO> getSkilltrees() {
        return skilltrees;
    }

    public void setSkilltrees(List<SkilltreeDTO> skilltrees) {
        this.skilltrees = skilltrees;
    }
}
