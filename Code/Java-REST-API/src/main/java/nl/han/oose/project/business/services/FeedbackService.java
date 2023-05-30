package nl.han.oose.project.business.services;

import jakarta.inject.Inject;
import nl.han.oose.project.data.dao.FeedbackDAO;
import nl.han.oose.project.resources.dto.FeedbackDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FeedbackService {
    private FeedbackDAO feedbackDAO;

    public FeedbackDTO getFeedback(int studentId, int nodeId) throws SQLException {
        return feedbackDAO.getFeedback(studentId, nodeId);
    }

    @Inject
    public void setFeedbackDAO(FeedbackDAO feedbackDAO) {
        this.feedbackDAO = feedbackDAO;
    }

}
