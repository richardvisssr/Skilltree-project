package nl.han.oose.project.data.dao;

import jakarta.inject.Inject;
import nl.han.oose.project.data.datamapper.NodeDatamapper;
import nl.han.oose.project.data.utils.DatabaseProperties;
import nl.han.oose.project.resources.dto.NodeRequestDTO;
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

    public NodesDTO getNodesFromSkillTree(int skilltreeId) throws SQLException {
        connection = DriverManager.getConnection(databaseProperties.connectionString());
        var nodes = nodeDatamapper.map(getNodesQuery(skilltreeId), getAssesmentCriteriaQuery(skilltreeId));
        return nodes;
    }

    public NodesDTO createNode(NodeRequestDTO nodeRequestDTODTO, int skilltreeId) throws SQLException {
        connection = DriverManager.getConnection(databaseProperties.connectionString());
        var createdNodeId = createNodeQuery(nodeRequestDTODTO, skilltreeId);
        addAssesmentCriteriaQuery(nodeRequestDTODTO.getAssesmentCriteria(), createdNodeId);
        addLearningOutcomeQuery(nodeRequestDTODTO.getLearningOutcome(), createdNodeId);
        connection.close();
        return getNodesFromSkillTree(skilltreeId);
    }

    public NodesDTO updateNode(NodeRequestDTO nodeRequestDTODTO, int nodeId) throws SQLException {
        connection = DriverManager.getConnection(databaseProperties.connectionString());
        updateNodeQuery(nodeRequestDTODTO, nodeId);
        updateAssesmentCriteriaQuery(nodeRequestDTODTO.getAssesmentCriteria(), nodeId);
        updateLearningOutcomeQuery(nodeRequestDTODTO.getLearningOutcome(), nodeId);
        var skilltreeId = nodeRequestDTODTO.getSkilltreeId();
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

    private ResultSet getAssesmentCriteriaQuery(int skilltreeId) throws SQLException {
        var query = "SELECT\n" +
                "ac.Description as AcceptationCriteriaDescription, ac.character, ac.NodeID\n" +
                "FROM Nodes n\n" +
                "INNER JOIN AssesmentCriteria ac\n" +
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

    private void addAssesmentCriteriaQuery(List<String> assesmentCriteriaDTO, int nodeId) throws SQLException {
        for (String assesmentCriterium : assesmentCriteriaDTO) {
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

    private void updateLearningOutcomeQuery(String learningOutcome, int nodeId) throws SQLException {
        var updateQuery = "UPDATE LearningOutcome SET Description = ? WHERE NodeID = ?";
        var stmt = connection.prepareStatement(updateQuery);
        stmt.setString(1, learningOutcome);
        stmt.setInt(2, nodeId);
        stmt.executeUpdate();
    }

    private void updateAssesmentCriteriaQuery(List<String> assessmentCriteriaDTO, int nodeId) throws SQLException {
        // Delete existing assessment criteria for the given nodeId
        var deleteQuery = "DELETE FROM AssesmentCriteria WHERE NodeID = ?";
        var deleteStmt = connection.prepareStatement(deleteQuery);
        deleteStmt.setInt(1, nodeId);
        deleteStmt.executeUpdate();

        // Insert updated assessment criteria
        for (String assessmentCriterion : assessmentCriteriaDTO) {
            var insertQuery = "INSERT INTO AssesmentCriteria (Description, NodeID)\n" +
                    "VALUES (?, ?)";
            var insertStmt = connection.prepareStatement(insertQuery);
            insertStmt.setString(1, assessmentCriterion);
            insertStmt.setInt(2, nodeId);
            insertStmt.executeUpdate();
        }
    }


    private void updateNodeQuery(NodeRequestDTO nodeDTO, int nodeId) throws SQLException {
        var updateNodeQuery = "UPDATE Nodes SET Skill = ?, Description = ? WHERE ID = ?";
        var stmt = connection.prepareStatement(updateNodeQuery);
        stmt.setString(1, nodeDTO.getSkill());
        stmt.setString(2, nodeDTO.getDescription());
        stmt.setInt(3, nodeId);
        stmt.executeUpdate();
    }

    public NodesDTO updateNodesPositions(NodesDTO nodesDTO, int skilltreeId) throws SQLException {
        connection = DriverManager.getConnection(databaseProperties.connectionString());
        for (NodeDTO nodeDTO : nodesDTO.getNodes()) {
            updateNodePositionsQuery(nodeDTO, skilltreeId);
        }
        connection.close();
        return getNodesFromSkillTree(skilltreeId);
    }

    private void updateNodePositionsQuery(NodeDTO nodeDTO, int skilltreeId) throws SQLException {
        var query = "UPDATE Nodes\n" +
                "SET PositionX = ?, PositionY = ?\n" +
                "WHERE ID = ? AND SkillTreeID = ?";
        var stmt = connection.prepareStatement(query);
        stmt.setDouble(1, nodeDTO.getPositionX());
        stmt.setDouble(2, nodeDTO.getPositionY());
        stmt.setInt(3, nodeDTO.getID());
        stmt.setInt(4, skilltreeId);
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
