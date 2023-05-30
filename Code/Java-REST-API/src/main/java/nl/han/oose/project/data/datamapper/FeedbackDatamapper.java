package nl.han.oose.project.data.datamapper;

import nl.han.oose.project.resources.dto.FeedbackDTO;
import nl.han.oose.project.resources.dto.FeedbacksDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDatamapper implements Datamapper<FeedbacksDTO> {

    @Override
    public FeedbacksDTO map(ResultSet resultSet) throws SQLException {
        try {
            List<FeedbackDTO> feedbacks = new ArrayList<>();

            while (resultSet.next()) {
                feedbacks.add(
                        new FeedbackDTO(
                                resultSet.getInt("StudentID"),
                                resultSet.getInt("NodeID"),
                                resultSet.getString("Feedback")
                        )
                );
            }

            return new FeedbacksDTO(feedbacks);
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            resultSet.close();
        }
    }


    @Override
    public FeedbacksDTO map(ResultSet resultSet, ResultSet resultSet2) throws SQLException {
        return null;
    }
}
