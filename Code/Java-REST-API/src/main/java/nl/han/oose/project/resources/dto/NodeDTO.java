package nl.han.oose.project.resources.dto;

public class NodeDTO {
    private double id;
    private String title;
    private String skill;
    private String description;
    private int positionX;
    private int positionY;
    private int skillTreeId;

    public NodeDTO(double id, String title, String skill, String description, int positionX, int positionY, int skillTreeId) {
        this.id = id;
        this.title = title;
        this.skill = skill;
        this.description = description;
        this.positionX = positionX;
        this.positionY = positionY;
        this.skillTreeId = skillTreeId;
    }

    public double getId() { return id; }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
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
    public int getPositionX() {
        return positionX;
    }
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }
    public int getPositionY() {
        return positionY;
    }
    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
    public int getSkillTreeID() {
        return skillTreeId;
    }
}
