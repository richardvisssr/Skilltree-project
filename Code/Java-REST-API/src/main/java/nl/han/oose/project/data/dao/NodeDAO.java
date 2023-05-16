package nl.han.oose.project.data.dao;

import jakarta.inject.Inject;
import nl.han.oose.project.data.datamapper.NodeDatamapper;
import nl.han.oose.project.data.utils.DatabaseProperties;
import nl.han.oose.project.resources.dto.NodeRequestDTO;
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

    public NodesDTO getNodesFromSkillTree(int skilltreeId) throws SQLException {
        connection = DriverManager.getConnection(databaseProperties.connectionString());
        var nodes = nodeDatamapper.map(getNodesQuery(skilltreeId), getAssessmentCriteriaQuery(skilltreeId));
        return nodes;
    }

    private ResultSet getAssessmentCriteriaQuery(int skilltreeId) throws SQLException {
        var query = "SELECT\n" +
                "ac.Description as AcceptationCriteriaDescription, ac.character, ac.NodeID\n" +
                "FROM Nodes n\n" +
                "INNER JOIN AssessmentCriteria ac\n" +
                "ON n.ID = ac.NodeID\n" +
                "WHERE n.SkillTreeID = ?";
        var stmt = connection.prepareStatement(query);
        stmt.setInt(1, skilltreeId);
        var result = stmt.executeQuery();
        return result;
    }

    private ResultSet getNodesQuery(int skilltreeId) throws SQLException {
        var query = "SELECT \n" +
                "\tn.ID, n.Skill, n.Description as NodeDescription, n.PositionX, n.PositionY, n.SkillTreeID,\n" +
                "\tlo.Description as LearningOutcomeDescription \n" +
                "FROM Nodes n\n" +
                "INNER JOIN LearningOutcome lo\n" +
                "ON n.ID = lo.NodeID\n" +
                "WHERE n.SkillTreeID = ?\n";
        var stmt = connection.prepareStatement(query);
        stmt.setInt(1, skilltreeId);
        var result = stmt.executeQuery();
        return result;
    }

    public NodesDTO createNode(NodeRequestDTO nodeRequestDTODTO, int skilltreeId) throws SQLException {
        connection = DriverManager.getConnection(databaseProperties.connectionString());
        var createdNodeId = createNodeQuery(nodeRequestDTODTO, skilltreeId);
        addAssessmentCriteriaQuery(nodeRequestDTODTO.getAssessmentCriteria(), createdNodeId);
        addLearningOutcomeQuery(nodeRequestDTODTO.getLearningOutcome(), createdNodeId);
        connection.close();
        return getNodesFromSkillTree(skilltreeId);
    }

    public int getHighestNodeId() throws SQLException {
        connection = DriverManager.getConnection(databaseProperties.connectionString());
        var highestNodeId = getHighestNodeIdQuery();
        connection.close();
        return highestNodeId;
    }

    private int getHighestNodeIdQuery() throws SQLException {
        var query = "SELECT TOP 1 ID \n" +
                "from Nodes\n" +
                "ORDER BY ID\n" +
                "DESC";
        var stmt = connection.prepareStatement(query);
        var resultSet = stmt.executeQuery();

        int nodeId = 0;
        if(resultSet.next()){
            nodeId = resultSet.getInt("ID");
        }

        return nodeId;
    }

    private int createNodeQuery(NodeRequestDTO nodeDTO, int skilltreeId) throws SQLException {
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

        int nodeId = 0;
        if(resultSet.next()){
            nodeId = resultSet.getInt("id");
        }

        return nodeId;
    }

    private void addAssessmentCriteriaQuery(List<String> assessmentCriteriaDTO, int nodeId) throws SQLException {
        for (String assesmentCriterium : assessmentCriteriaDTO) {
            var query = "INSERT INTO AssessmentCriteria (Description, NodeID)\n" +
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
