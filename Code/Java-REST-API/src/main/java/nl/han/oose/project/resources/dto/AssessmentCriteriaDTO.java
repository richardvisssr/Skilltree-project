package nl.han.oose.project.resources.dto;

public class AssessmentCriteriaDTO {
    private String description;
    private String character;

    public AssessmentCriteriaDTO() {}

    public AssessmentCriteriaDTO(String description, String character) {
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
