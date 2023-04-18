package nl.han.oose.project.data.dao;

import jakarta.inject.Inject;
import nl.han.oose.project.data.datamapper.TestDatamapper;
import nl.han.oose.project.data.utils.DatabaseProperties;
import nl.han.oose.project.resources.dto.SkilltreeDTO;
import nl.han.oose.project.resources.dto.SkilltreesDTO;

import java.util.ArrayList;
import java.util.List;

public class SkilltreeDAO {
    private TestDatamapper testDatamapper;
    private DatabaseProperties databaseProperties;

    public SkilltreesDTO getAllSkilltrees(int docentId) {
        //TODO echte data ophalen uit database

        //test data, omdat de database nog niet is opgezet
        var skilltree1 = new SkilltreeDTO();
        skilltree1.setId(1);
        skilltree1.setTitle("Skilltree 1");
        skilltree1.setDescription("Dit is skilltree 1");

        var skilltree2 = new SkilltreeDTO();
        skilltree2.setId(2);
        skilltree2.setTitle("Skilltree 2");
        skilltree2.setDescription("Dit is skilltree 2");

        var skilltree3 = new SkilltreeDTO();
        skilltree3.setId(3);
        skilltree3.setTitle("Skilltree 3");
        skilltree3.setDescription("Dit is skilltree 3");

        List<SkilltreeDTO> testData = new ArrayList<>();
        testData.add(skilltree1);
        testData.add(skilltree2);
        testData.add(skilltree3);

        var tempSkilltreesDTO = new SkilltreesDTO();
        tempSkilltreesDTO.setSkilltrees(testData);

        return tempSkilltreesDTO;
    }

    @Inject
    public void setDatabaseProperties(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }

    @Inject
    public void setTestDatamapper(TestDatamapper testDatamapper) {
        this.testDatamapper = testDatamapper;
    }
}
