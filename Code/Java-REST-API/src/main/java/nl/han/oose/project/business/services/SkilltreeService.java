package nl.han.oose.project.business.services;

import jakarta.inject.Inject;
import nl.han.oose.project.data.dao.SkilltreeDAO;
import nl.han.oose.project.resources.dto.SkilltreeDTO;
import nl.han.oose.project.resources.dto.SkilltreesDTO;

import java.sql.SQLException;

public class SkilltreeService {
    private SkilltreeDAO skilltreeDAO;

    public SkilltreesDTO getAllSkilltrees(int docentId) throws SQLException {
        return skilltreeDAO.getAllSkilltrees(docentId);
    }

    public void updateSkilltree(double id, SkilltreeDTO skilltreeDTO) throws SQLException {
        skilltreeDAO.updateSkilltreeQuery(id, skilltreeDTO);
    }

    @Inject
    public void setSkilltreeDAO(SkilltreeDAO skilltreeDAO) {
        this.skilltreeDAO = skilltreeDAO;
    }
}
