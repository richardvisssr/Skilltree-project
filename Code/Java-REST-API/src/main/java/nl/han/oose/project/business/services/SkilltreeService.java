package nl.han.oose.project.business.services;

import jakarta.inject.Inject;
import nl.han.oose.project.data.dao.SkilltreeDAO;
import nl.han.oose.project.resources.dto.SkilltreeDTO;
import nl.han.oose.project.resources.dto.SkilltreesDTO;

import java.sql.SQLException;

public class SkilltreeService {
    private SkilltreeDAO skilltreeDAO;
    private static final int DOCENT_ROLE = 1;
    private static final int STUDENT_ROLE = 2;
    public SkilltreesDTO getAllSkilltrees(int gebruikerId, int roleId) throws SQLException {
        if (roleId == DOCENT_ROLE) {
            return skilltreeDAO.getAllDocentSkilltrees(gebruikerId);
        } else if (roleId == STUDENT_ROLE) {
            return skilltreeDAO.getAllStudentSkilltrees(gebruikerId);
        }
        return null;
    }

    public SkilltreesDTO createSkilltree(SkilltreeDTO skilltreeDTO, int gebruikerId) throws SQLException {
        return skilltreeDAO.createSkilltree(skilltreeDTO, gebruikerId);
    }

    public SkilltreesDTO updateSkilltree(SkilltreeDTO skilltreeDTO, int gebruikerId) throws SQLException {
        return skilltreeDAO.updateSkilltree(skilltreeDTO, gebruikerId);
    }

    public void deleteSkilltree(int skilltreeId) throws SQLException {
        skilltreeDAO.deleteSkilltree(skilltreeId);
    }

    @Inject
    public void setSkilltreeDAO(SkilltreeDAO skilltreeDAO) {
        this.skilltreeDAO = skilltreeDAO;
    }
}
