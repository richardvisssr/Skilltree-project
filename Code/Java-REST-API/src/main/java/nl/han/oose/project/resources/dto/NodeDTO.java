package nl.han.oose.project.resources.dto;

import java.util.ArrayList;
import java.util.List;

public class NodeDTO {
    private int id;
    private String skill;
    private String description;
    private double positionX;
    private double positionY;
    private int skilltreeId;
    private String learningOutcome;
    private List<AssessmentCriteriaDTO> assessmentCriteria = new ArrayList<>();

    public NodeDTO() {
        // Default constructor
    }

    public NodeDTO(int id, String skill, String description, double positionX, double positionY, int skilltreeId, String learningOutcome) {
        this.id = id;
        this.skill = skill;
        this.description = description;
        this.positionX = positionX;
        this.positionY = positionY;
        this.skilltreeId = skilltreeId;
        this.learningOutcome = learningOutcome;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
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

    public List<AssessmentCriteriaDTO> getAssessmentCriteria() {
        return assessmentCriteria;
    }

    public void setAssessmentCriteria(List<AssessmentCriteriaDTO> assessmentCriteria) {
        this.assessmentCriteria = assessmentCriteria;
    }
}
