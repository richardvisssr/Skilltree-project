package nl.han.oose.project.data.datamapper;

import nl.han.oose.project.resources.dto.FeedbackDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FeedbackDatamapper implements Datamapper<FeedbackDTO> {

    @Override
    public FeedbackDTO map(ResultSet resultSet) throws SQLException {
        try {
            return new FeedbackDTO(
                    resultSet.getInt("StudentID"),
                    resultSet.getInt("NodeID"),
                    resultSet.getString("Feedback")
            );
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            resultSet.close();
        }
    }


    @Override
    public FeedbackDTO map(ResultSet resultSet, ResultSet resultSet2) throws SQLException {
        return null;
    }
}
