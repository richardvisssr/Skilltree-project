package nl.han.oose.project.resources.dto;

public class AssesmentCriteriaDTO {
    private String description;
    private String character;

    public AssesmentCriteriaDTO() {}

    public AssesmentCriteriaDTO(String description, String character) {
        this.description = description;
        this.character = character;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }
}
