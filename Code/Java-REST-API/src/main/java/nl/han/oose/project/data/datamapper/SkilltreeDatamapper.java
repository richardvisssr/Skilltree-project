package nl.han.oose.project.data.datamapper;

import nl.han.oose.project.resources.dto.SkilltreeDTO;
import nl.han.oose.project.resources.dto.SkilltreesDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SkilltreeDatamapper implements Datamapper<SkilltreesDTO> {
    @Override
    public SkilltreesDTO map(ResultSet resultSet) throws SQLException {
        try {
            List<SkilltreeDTO> skilltrees = new ArrayList<>();

            while (resultSet.next()) {
                skilltrees.add(
                        new SkilltreeDTO(
                                resultSet.getInt("id"),
                                resultSet.getString("title"),
                                resultSet.getString("description")
                        )
                );
            }

            return new SkilltreesDTO(skilltrees);
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            resultSet.close();
        }
    }

    @Override
    public SkilltreesDTO map(ResultSet resultSet, ResultSet resultSet2) throws SQLException {
        return null;
    }
}
