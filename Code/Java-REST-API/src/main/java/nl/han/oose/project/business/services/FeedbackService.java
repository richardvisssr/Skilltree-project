package nl.han.oose.project.business.services;

import jakarta.inject.Inject;
import nl.han.oose.project.data.dao.FeedbackDAO;
import nl.han.oose.project.resources.dto.FeedbacksDTO;

import java.sql.SQLException;

public class FeedbackService {
    private FeedbackDAO feedbackDAO;

    public FeedbacksDTO getFeedback(int studentId, int nodeId) throws SQLException {
        return feedbackDAO.getFeedback(studentId, nodeId);
    }

    public FeedbacksDTO updateFeedback(int nodeId, int userId, String feedback) throws SQLException {
        return feedbackDAO.updateFeedback(nodeId, userId, feedback);
    }

    @Inject
    public void setFeedbackDAO(FeedbackDAO feedbackDAO) {
        this.feedbackDAO = feedbackDAO;
    }


}
