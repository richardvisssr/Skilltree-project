package nl.han.oose.project.business.services;

import jakarta.inject.Inject;
import nl.han.oose.project.data.dao.SkilltreeDAO;
import nl.han.oose.project.resources.dto.SkilltreeDTO;
import nl.han.oose.project.resources.dto.SkilltreesDTO;

import java.sql.SQLException;

public class SkilltreeService {
    private SkilltreeDAO skilltreeDAO;

    public SkilltreesDTO getAllSkilltrees(int gebruikerId) throws SQLException {
        return skilltreeDAO.getAllSkilltrees(gebruikerId);
    }

    public SkilltreesDTO createSkilltree(SkilltreeDTO skilltreeDTO, int gebruikerId) throws SQLException {
        return skilltreeDAO.createSkilltree(skilltreeDTO, gebruikerId);
    }

    public SkilltreesDTO updateSkilltree(SkilltreeDTO skilltreeDTO, int gebruikerId) throws SQLException {
        return skilltreeDAO.updateSkilltree(skilltreeDTO, gebruikerId);
    }

    @Inject
    public void setSkilltreeDAO(SkilltreeDAO skilltreeDAO) {
        this.skilltreeDAO = skilltreeDAO;
    }
}
