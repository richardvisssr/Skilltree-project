package nl.han.oose.project.data.datamapper;

import nl.han.oose.project.resources.dto.EdgeDTO;
import nl.han.oose.project.resources.dto.EdgesDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EdgeDatamapper {
    public EdgesDTO map(ResultSet edgeResultSet) throws SQLException {
        System.out.println("EdgeDatamapper: " + edgeResultSet);
        var edgesDTO = new EdgesDTO();
        List<EdgeDTO> edges = new ArrayList<>();

        while (edgeResultSet.next()) {
            edges.add(new EdgeDTO(
                    edgeResultSet.getString("EdgeId"),
                    edgeResultSet.getString("TargetID"),
                    edgeResultSet.getString("SourceID")
            ));
        }
        edgesDTO.setEdges(edges);

        return edgesDTO;
    }
}
