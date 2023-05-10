package nl.han.oose.project.resources.dto;

public class EdgeDTO {
    private String EdgeId;
    private String TargetId;
    private String SourceId;

    public EdgeDTO() {
    }

    public EdgeDTO(String EdgeId, String TargetId, String SourceId) {
        this.EdgeId = EdgeId;
        this.TargetId = TargetId;
        this.SourceId = SourceId;
    }

    public String getEdgeId() {
        return EdgeId;
    }

    public void setEdgeId(String edgeId) {
        EdgeId = edgeId;
    }

    public String getTargetId() {
        return TargetId;
    }

    public void setTargetId(String targetId) {
        TargetId = targetId;
    }

    public String getSourceId() {
        return SourceId;
    }

    public void setSourceId(String sourceId) {
        SourceId = sourceId;
    }

}
