package nl.han.oose.project.resources.dto;

import java.util.ArrayList;
import java.util.List;

public class SkilltreeDTO {
    private int id;
    private String title;
    private String description;
    private List<NodeDTO> nodes = new ArrayList<>();
    private List<EdgeDTO> edges = new ArrayList<>();

    public SkilltreeDTO(){}

    public SkilltreeDTO(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.nodes = nodes;
        this.edges = edges;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNodes(List<NodeDTO> nodes) {
        this.nodes = nodes;
    }

    public void setEdges(List<EdgeDTO> edges) {
        this.edges = edges;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<NodeDTO> getNodes() {
        return nodes;
    }

    public List<EdgeDTO> getEdges() {
        return edges;
    }
}
