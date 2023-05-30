package nl.han.oose.project.resources.dto;

import java.util.ArrayList;
import java.util.List;

public class SkilltreeDTO {
    private int id;
    private String title;
    private String description;
    private List<NodeDTO> nodes = new ArrayList<>();
    private List<EdgeDTO> edges = new ArrayList<>();

    public SkilltreeDTO(){
        // Default constructor
    }

    public SkilltreeDTO(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<NodeDTO> getNodes() {
        return nodes;
    }

    public void setNodes(List<NodeDTO> nodes) {
        this.nodes = nodes;
    }

    public List<EdgeDTO> getEdges() {
        return edges;
    }

    public void setEdges(List<EdgeDTO> edges) {
        this.edges = edges;
    }
}
