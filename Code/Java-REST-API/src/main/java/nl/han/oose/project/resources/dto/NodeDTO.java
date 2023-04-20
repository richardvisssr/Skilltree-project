package nl.han.oose.project.resources.dto;

import java.util.List;

public class NodeDTO {
    private String skill;
    private String description;
    private double positionX;
    private double positionY;
    private int skilltreeId;
    private String learningOutcome;
    private List<String> assesmentCriteria;

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
