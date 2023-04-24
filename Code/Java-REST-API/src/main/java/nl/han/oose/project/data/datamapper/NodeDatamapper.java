package nl.han.oose.project.data.datamapper;

import nl.han.oose.project.resources.dto.AssesmentCriteriaDTO;
import nl.han.oose.project.resources.dto.NodeDTO;
import nl.han.oose.project.resources.dto.NodeRequestDTO;
import nl.han.oose.project.resources.dto.NodesDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NodeDatamapper {
    public NodesDTO map(ResultSet nodeResultSet, ResultSet assesmentCriteriaResultSet) throws SQLException {
        var nodesDTO = new NodesDTO();
        List<NodeDTO> nodes = new ArrayList<>();

        while (nodeResultSet.next()) {
            nodes.add(new NodeDTO(
                    nodeResultSet.getInt("ID"),
                    nodeResultSet.getString("Skill"),
                    nodeResultSet.getString("NodeDescription"),
                    nodeResultSet.getInt("PositionX"),
                    nodeResultSet.getInt("PositionY"),
                    nodeResultSet.getInt("SkilltreeID"),
                    nodeResultSet.getString("LearningOutcomeDescription")
            ));
        }

        while (assesmentCriteriaResultSet.next()) {
            var nodeId = assesmentCriteriaResultSet.getInt("NodeID");
            for (NodeDTO node : nodes) {
                if(node != null && node.getID() == nodeId) {
                    node.getAssesmentCriteria().add(new AssesmentCriteriaDTO(
                            assesmentCriteriaResultSet.getString("AcceptationCriteriaDescription"),
                            assesmentCriteriaResultSet.getString("character")
                    ));
                }
            }
        }

        nodesDTO.setNodes(nodes);
        return nodesDTO;
    }
}
