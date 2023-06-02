package nl.han.oose.project.resources.dto;


import java.util.ArrayList;
import java.util.List;

public class FeedbacksDTO {
    private List<FeedbackDTO> feedbacks = new ArrayList<>();

    public FeedbacksDTO() {
        // Default constructor
    }

    public FeedbacksDTO(List<FeedbackDTO> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public List<FeedbackDTO> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<FeedbackDTO> feedbacks) {
        this.feedbacks = feedbacks;
    }
}


