package nl.han.oose.project.data.datamapper;

import nl.han.oose.project.resources.dto.EdgeDTO;
import nl.han.oose.project.resources.dto.EdgesDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EdgeDatamapper implements Datamapper<EdgesDTO> {

    @Override
    public EdgesDTO map(ResultSet edgeResultSet) throws SQLException {
        try {
            var edgesDTO = new EdgesDTO();
            List<EdgeDTO> edges = new ArrayList<>();

            while (edgeResultSet.next()) {
                edges.add(new EdgeDTO(
                        edgeResultSet.getString("EdgeId"),
                        edgeResultSet.getString("TargetID"),
                        edgeResultSet.getString("SourceID"),
                        edgeResultSet.getInt("SkillTreeID")
                ));
            }
            edgesDTO.setEdges(edges);
            return edgesDTO;
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        finally {
            edgeResultSet.close();
        }
    }

    @Override
    public EdgesDTO map(ResultSet resultSet, ResultSet resultSet2) throws SQLException {
        return null;
    }
}
