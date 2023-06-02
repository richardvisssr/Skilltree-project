package nl.han.oose.project.resources.dto;

public class FeedbackDTO {
    private int userId;
    private int nodeId;
    private String feedback;

    public FeedbackDTO() {
        // Default constructor
    }

    public FeedbackDTO(int userId, int nodeId, String feedback) {
        this.userId = userId;
        this.nodeId = nodeId;
        this.feedback = feedback;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
