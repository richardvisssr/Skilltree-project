package nl.han.oose.project.resources.dto;
import java.util.ArrayList;
import java.util.List;

public class NodeDTO {
    private int id;
    private String skill;
    private String description;
    private double positionX;
    private double positionY;

    public NodeDTO(int id, String title, String skill, String description, int positionX, int positionY, int skillTreeId) {
        this.id = id;
        this.skill = skill;
        this.description = description;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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


}
