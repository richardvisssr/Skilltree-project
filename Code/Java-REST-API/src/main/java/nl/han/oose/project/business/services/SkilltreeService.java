package nl.han.oose.project.business.services;

import jakarta.inject.Inject;
import nl.han.oose.project.data.dao.SkilltreeDAO;
import nl.han.oose.project.resources.dto.SkilltreesDTO;

public class SkilltreeService {
    private SkilltreeDAO skilltreeDAO;

    public SkilltreesDTO getAllSkilltrees(int docentId) {
        return skilltreeDAO.getAllSkilltrees(docentId);
    }

    @Inject
    public void setSkilltreeDAO(SkilltreeDAO skilltreeDAO) {
        this.skilltreeDAO = skilltreeDAO;
    }
}
