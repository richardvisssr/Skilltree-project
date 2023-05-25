package nl.han.oose.project.data.dao;

import jakarta.inject.Inject;
import nl.han.oose.project.data.datamapper.NodeDatamapper;
import nl.han.oose.project.data.utils.DatabaseProperties;
import nl.han.oose.project.resources.dto.NodeRequestDTO;
import nl.han.oose.project.resources.dto.NodeDTO;
import nl.han.oose.project.resources.dto.NodesDTO;

import java.sql.*;
import java.util.List;

public class NodeDAO {
    private NodeDatamapper nodeDatamapper;
    private DatabaseProperties databaseProperties;
    private Connection connection;

    public NodesDTO getNodesFromSkillTree(int skilltreeId) throws SQLException {
        try {
            connection = DriverManager.getConnection(databaseProperties.connectionString());
            return nodeDatamapper.map(getNodesQuery(skilltreeId), getAssessmentCriteriaQuery(skilltreeId));
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            connection.close();
        }
    }

    public NodesDTO createNode(NodeRequestDTO nodeRequestDTODTO, int skilltreeId) throws SQLException {
        try {
            connection = DriverManager.getConnection(databaseProperties.connectionString());
            var createdNodeId = createNodeQuery(nodeRequestDTODTO, skilltreeId);
            addAssessmentCriteriaQuery(nodeRequestDTODTO.getAssessmentCriteria(), createdNodeId);
            addLearningOutcomeQuery(nodeRequestDTODTO.getLearningOutcome(), createdNodeId);
            return getNodesFromSkillTree(skilltreeId);
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            connection.close();
        }
    }

    public NodesDTO updateNode(NodeRequestDTO nodeRequestDTODTO, int nodeId) throws SQLException {
        try {
            connection = DriverManager.getConnection(databaseProperties.connectionString());
            updateNodeQuery(nodeRequestDTODTO, nodeId);
            updateAssessmentCriteriaQuery(nodeRequestDTODTO.getAssessmentCriteria(), nodeId);
            updateLearningOutcomeQuery(nodeRequestDTODTO.getLearningOutcome(), nodeId);
            var skilltreeId = nodeRequestDTODTO.getSkilltreeId();
            return getNodesFromSkillTree(skilltreeId);
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            connection.close();
        }
    }

    public int getHighestNodeId() throws SQLException {
        try {
            connection = DriverManager.getConnection(databaseProperties.connectionString());
            return getHighestNodeIdQuery();
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            connection.close();
        }
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
        return stmt.executeQuery();
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
        return stmt.executeQuery();
    }

    private int getHighestNodeIdQuery() throws SQLException {
        var query = "DECLARE @IdentityValue INT;\n" +
                     "SET @IdentityValue = IDENT_CURRENT('Nodes');\n" +
                     "SELECT @IdentityValue AS 'ID'";

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

    public void deleteNode(int nodeId) throws SQLException {
        connection = DriverManager.getConnection(databaseProperties.connectionString());
        deleteNodeQuery(nodeId);
        connection.close();
    }

    private void deleteNodeQuery(int nodeId) throws SQLException {
        var deleteNodeQuery = "DELETE FROM Nodes WHERE ID = ?";
        var stmt = connection.prepareStatement(deleteNodeQuery);
        stmt.setInt(1, nodeId);
        stmt.execute();
    }

    private void addAssessmentCriteriaQuery(List<String> assessmentCriteriaDTO, int nodeId) throws SQLException {
        for (String assessmentCriterium : assessmentCriteriaDTO) {
            var query = "INSERT INTO AssessmentCriteria (Description, NodeID)\n" +
                    "VALUES (?, ?)";
            var stmt = connection.prepareStatement(query);
            stmt.setString(1, assessmentCriterium);
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

    private void updateAssessmentCriteriaQuery(List<String> assessmentCriteriaDTO, int nodeId) throws SQLException {
        // Delete existing assessment criteria for the given nodeId
        var deleteQuery = "DELETE FROM AssessmentCriteria WHERE NodeID = ?";
        var deleteStmt = connection.prepareStatement(deleteQuery);
        deleteStmt.setInt(1, nodeId);
        deleteStmt.executeUpdate();

        // Insert updated assessment criteria
        for (String assessmentCriterion : assessmentCriteriaDTO) {
            var insertQuery = "INSERT INTO AssessmentCriteria (Description, NodeID)\n" +
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
