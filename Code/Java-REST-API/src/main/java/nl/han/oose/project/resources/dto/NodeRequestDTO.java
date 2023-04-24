package nl.han.oose.project.resources.dto;

import java.util.List;

public class NodeRequestDTO {
    private int ID;
    private String skill;
    private String description;
    private double positionX;
    private double positionY;
    private int skilltreeId;
    private String learningOutcome;
    private List<String> assesmentCriteria;

    public NodeRequestDTO() {}

    public NodeRequestDTO(int ID, String skill, String description, double positionX, double positionY, int skilltreeId, String learningOutcome) {
        this.ID = ID;
        this.skill = skill;
        this.description = description;
        this.positionX = positionX;
        this.positionY = positionY;
        this.skilltreeId = skilltreeId;
        this.learningOutcome = learningOutcome;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public int getSkilltreeId() {
        return skilltreeId;
    }

    public void setSkilltreeId(int skilltreeId) {
        this.skilltreeId = skilltreeId;
    }

    public String getLearningOutcome() {
        return learningOutcome;
    }

    public void setLearningOutcome(String learningOutcome) {
        this.learningOutcome = learningOutcome;
    }

    public List<String> getAssesmentCriteria() {
        return assesmentCriteria;
    }

    public void setAssesmentCriteria(List<String> assesmentCriteria) {
        this.assesmentCriteria = assesmentCriteria;
    }
}
