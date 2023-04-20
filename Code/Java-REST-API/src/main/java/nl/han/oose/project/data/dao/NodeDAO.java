package nl.han.oose.project.data.dao;

import jakarta.inject.Inject;
import nl.han.oose.project.data.datamapper.NodeDatamapper;
import nl.han.oose.project.data.datamapper.TestDatamapper;
import nl.han.oose.project.data.utils.DatabaseProperties;
import nl.han.oose.project.resources.dto.NodeDTO;
import nl.han.oose.project.resources.dto.NodesDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class NodeDAO {
    private NodeDatamapper nodeDatamapper;
    private DatabaseProperties databaseProperties;
    private Connection connection;

//    public NodesDTO getNodes(int skilltreeId) {
//        connection = DriverManager.getConnection(databaseProperties.connectionString());
//        var nodes = nodeDatamapper.map(getNodesQuery(skilltreeId));
//        return nodes;
//    }
//
//    private ResultSet getNodesQuery(int skilltreeId) {
//        var query = "GET"


    //SELECT
    //	n.ID, n.Skill, n.Description as NodeDescription, n.PositionX, n.PositionY,
    //	ac.character,
    //	lo.Description as LearningOutcomeDescription
    //FROM Nodes n
    //INNER JOIN AssesmentCriteria ac
    //ON n.ID = ac.NodeID
    //INNER JOIN LearningOutcome lo
    //ON n.ID = lo.NodeID
    //WHERE n.SkillTreeID = 2
//    }

    public NodesDTO createNode(NodeDTO nodeDTO, int skilltreeId) throws SQLException {
        connection = DriverManager.getConnection(databaseProperties.connectionString());
        var createdNodeId = createNodeQuery(nodeDTO, skilltreeId);
        addAssesmentCriteriaQuery(nodeDTO.getAssesmentCriteria(), createdNodeId);
        addLearningOutcomeQuery(nodeDTO.getLearningOutcome(), createdNodeId);
        connection.close();
        //TODO haal de nodes weer op, maar eerst wachten op mailtje van Frank: moet de charachter (o,v,g) in nodes of assesmentCriteria?
//        return getNodes(skilltreeId);
        return null;
    }

    private int createNodeQuery(NodeDTO nodeDTO, int skilltreeId) throws SQLException {
        var insertNodeQuery = "INSERT INTO Nodes (Skill, Description, PositionX, PositionY, SkillTreeID)\n" +
                "VALUES \n" +
                "\t(?, ?, ?, ?, ?)\n" +
                "\n" +
                "SELECT SCOPE_IDENTITY() as id";
        var stmt = connection.prepareStatement(insertNodeQuery);
        stmt.setString(1, nodeDTO.getSkill());
        stmt.setString(2, nodeDTO.getDescription());
        stmt.setDouble(3, nodeDTO.getPositionX());
        stmt.setDouble(4, nodeDTO.getPositionY());
        stmt.setInt(5, skilltreeId);
        var resultSet = stmt.executeQuery();

//        var getIdQuery = "SELECT SCOPE_IDENTITY() as id";
//        var stmt2 = connection.prepareStatement(getIdQuery);
//        var resultSet = stmt2.executeQuery();

        int nodeId = 0;
        if(resultSet.next()){
            nodeId = resultSet.getInt("id");
        }

        return nodeId;
    }

    private void addAssesmentCriteriaQuery(List<String> assesmentCriteria, int nodeId) throws SQLException {
        for (String assesmentCriterium : assesmentCriteria) {
            var query = "INSERT INTO AssesmentCriteria (Description, NodeID)\n" +
                    "VALUES (?, ?)";
            var stmt = connection.prepareStatement(query);
            stmt.setString(1, assesmentCriterium);
            stmt.setInt(2, nodeId);
            stmt.executeUpdate();
        }
    }

    private void addLearningOutcomeQuery(String learningOutcome, int createdNodeId) throws SQLException {
        var query = "INSERT INTO LearningOutcome (Description, NodeID)\n" +
                "VALUES (?, ?)";
        var stmt = connection.prepareStatement(query);
        stmt.setString(1, learningOutcome);
        stmt.setInt(2, createdNodeId);
        stmt.executeUpdate();
    }

    @Inject
    public void setNodeDatamapper(NodeDatamapper nodeDatamapper) {
        this.nodeDatamapper = nodeDatamapper;
    }

    @Inject
    public void setDatabaseProperties(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }
}
