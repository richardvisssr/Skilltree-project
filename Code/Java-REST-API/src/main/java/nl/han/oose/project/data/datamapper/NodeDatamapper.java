package nl.han.oose.project.data.datamapper;

import nl.han.oose.project.resources.dto.NodeDTO;
import nl.han.oose.project.resources.dto.NodesDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NodeDatamapper implements Datamapper {
    @Override
    public NodesDTO map(ResultSet resultSet) throws SQLException {
        var nodesDTO = new NodesDTO();
        List<NodeDTO> nodes = new ArrayList<>();

        do {
            if(resultSet.next()) {
                nodes.add(new NodeDTO(
                        resultSet.getString("Skill"),
                        resultSet.getString("NodeDescription"),
                        resultSet.getInt("PositionX"),
                        resultSet.getInt("PositionY"),
                        resultSet.getInt("SkilltreeID"),
                        resultSet.getString("LearningOutcomeDescription")
                ));
            }
        } while (resultSet.next());

        nodesDTO.setNodes(nodes);
        return nodesDTO;
    }
}
