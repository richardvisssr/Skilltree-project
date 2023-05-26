package nl.han.oose.project.resources.dto;

public class EdgeDTO {
    private String edgeId;
    private String targetId;
    private String sourceId;

    private int skillTreeID;

    public EdgeDTO() {
        // Default constructor
    }

    public EdgeDTO(String edgeId, String targetId, String sourceId, int skillTreeID) {
        this.edgeId = edgeId;
        this.targetId = targetId;
        this.sourceId = sourceId;
        this.skillTreeID = skillTreeID;
    }

    public String getEdgeId() {
        return edgeId;
    }

    public void setEdgeId(String edgeId) {
        this.edgeId = edgeId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public int getSkillTreeID() {
        return skillTreeID;
    }

    public void setSkillTreeID(int skillTreeID) {
        this.skillTreeID = skillTreeID;
    }
}
