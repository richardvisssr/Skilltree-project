package nl.han.oose.project.business.services;

import jakarta.inject.Inject;
import nl.han.oose.project.data.dao.TestDAO;

public class TestService {
    private TestDAO testDAO;
    public String testServiceMethod() {
        return testDAO.testDAOMethod();
    }

    @Inject
    public void setTestDAO(TestDAO testDAO) {
        this.testDAO = testDAO;
    }
}
