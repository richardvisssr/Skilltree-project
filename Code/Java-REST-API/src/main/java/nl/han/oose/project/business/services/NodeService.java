package nl.han.oose.project.business.services;

import jakarta.inject.Inject;
import nl.han.oose.project.data.dao.NodeDAO;
import nl.han.oose.project.resources.dto.NodeRequestDTO;
import nl.han.oose.project.resources.dto.NodesDTO;

import java.sql.SQLException;

public class NodeService {
    private NodeDAO nodeDAO;

    public NodesDTO createNode(NodeRequestDTO nodeDTO, int skilltreeId) throws SQLException {
        return nodeDAO.createNode(nodeDTO, skilltreeId);
    }

    public NodesDTO getAllNodes(int skilltreeId) throws SQLException {
        return nodeDAO.getNodesFromSkillTree(skilltreeId);
    }

    public int getHighestNodeId() throws SQLException {
        return nodeDAO.getHighestNodeId();
    }

    public void updateNodesPositions(NodeRequestDTO nodeDTO, int skilltreeId) throws SQLException {
        nodeDAO.updateNodesPositions(nodeDTO, skilltreeId);
    }

    @Inject
    public void setNodeDAO(NodeDAO nodeDAO) {
        this.nodeDAO = nodeDAO;
    }

}
