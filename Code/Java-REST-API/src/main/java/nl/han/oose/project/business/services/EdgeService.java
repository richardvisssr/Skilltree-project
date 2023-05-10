package nl.han.oose.project.business.services;

import jakarta.inject.Inject;
import nl.han.oose.project.resources.dto.EdgeDTO;
import nl.han.oose.project.resources.dto.EdgesDTO;
import nl.han.oose.project.data.dao.EdgeDAO;

import java.sql.SQLException;

public class EdgeService {

    private EdgeDAO edgeDAO;

    public EdgesDTO createEdge(EdgeDTO edgeDTO, int skilltreeId) throws SQLException {
        return edgeDAO.createEdge(edgeDTO, skilltreeId);
    }
    public EdgesDTO getAllEdges(int skilltreeId) throws SQLException {
        return edgeDAO.getAllEdgesFromSkilltree(skilltreeId);
    }
    @Inject
    public void setEdgeDAO(EdgeDAO edgeDAO) {
        this.edgeDAO = edgeDAO;
    }


}
