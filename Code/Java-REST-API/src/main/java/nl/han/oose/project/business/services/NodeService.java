package nl.han.oose.project.business.services;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import nl.han.oose.project.data.dao.NodeDAO;
import nl.han.oose.project.resources.dto.NodeDTO;
import nl.han.oose.project.resources.dto.NodesDTO;

import java.sql.SQLException;

public class NodeService {
    private NodeDAO nodeDAO;

    public NodesDTO createNode(NodeDTO nodeDTO, int skilltreeId) throws SQLException {
        return nodeDAO.createNode(nodeDTO, skilltreeId);
    }

    @Inject
    public void setNodeDAO(NodeDAO nodeDAO) {
        this.nodeDAO = nodeDAO;
    }
}
