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
    
    private PreparedStatement stmt;

    private ResultSet resultSet;

    public NodesDTO getNodesFromSkillTree(int skilltreeId) throws SQLException {
        try {
            connection = DriverManager.getConnection(databaseProperties.connectionString());
            return nodeDatamapper.map(getNodesQuery(skilltreeId), getAssessmentCriteriaQuery(skilltreeId));
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            connection.close();
            stmt.close();
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
            stmt.close();
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
            stmt.close();
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
            stmt.close();
        }
    }

    private ResultSet getAssessmentCriteriaQuery(int skilltreeId) throws SQLException {
        try {
            var query = "SELECT\n" +
                    "ac.Description as AcceptationCriteriaDescription, ac.character, ac.NodeID\n" +
                    "FROM Nodes n\n" +
                    "INNER JOIN AssessmentCriteria ac\n" +
                    "ON n.ID = ac.NodeID\n" +
                    "WHERE n.SkillTreeID = ?";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, skilltreeId);
            return stmt.executeQuery();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    private ResultSet getNodesQuery(int skilltreeId) throws SQLException {
        try {
        var query = "SELECT \n" +
                "\tn.ID, n.Skill, n.Description as NodeDescription, n.PositionX, n.PositionY, n.SkillTreeID,\n" +
                "\tlo.Description as LearningOutcomeDescription \n" +
                "FROM Nodes n\n" +
                "INNER JOIN LearningOutcome lo\n" +
                "ON n.ID = lo.NodeID\n" +
                "WHERE n.SkillTreeID = ?\n";
        stmt = connection.prepareStatement(query);
        stmt.setInt(1, skilltreeId);
        return stmt.executeQuery();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    private int getHighestNodeIdQuery() throws SQLException {
        try {
        var query = "DECLARE @IdentityValue INT;\n" +
                     "SET @IdentityValue = IDENT_CURRENT('Nodes');\n" +
                     "SELECT @IdentityValue AS 'ID'";

        stmt = connection.prepareStatement(query);
        resultSet = stmt.executeQuery();

        int nodeId = 0;
        if(resultSet.next()){
            nodeId = resultSet.getInt("ID");
        }

        return nodeId;
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        finally {
            resultSet.close();
        }
    }

    private int createNodeQuery(NodeRequestDTO nodeDTO, int skilltreeId) throws SQLException {
        try {
            var insertNodeQuery = "INSERT INTO Nodes (Skill, Description, PositionX, PositionY, SkillTreeID)\n" +
                    "VALUES (?, ?, ?, ?, ?)\n" +
                    "SELECT SCOPE_IDENTITY() as id";
            stmt = connection.prepareStatement(insertNodeQuery);
            stmt.setString(1, nodeDTO.getSkill());
            stmt.setString(2, nodeDTO.getDescription());
            stmt.setDouble(3, nodeDTO.getPositionX());
            stmt.setDouble(4, nodeDTO.getPositionY());
            stmt.setInt(5, skilltreeId);
            resultSet = stmt.executeQuery();

            int nodeId = 0;
            if (resultSet.next()) {
                nodeId = resultSet.getInt("id");
            }

            return nodeId;
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        finally {
            resultSet.close();
        }
    }

    public void deleteNode(int nodeId) throws SQLException {
        try {
            connection = DriverManager.getConnection(databaseProperties.connectionString());
            deleteNodeQuery(nodeId);
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            connection.close();
            stmt.close();
        }
    }

    private void deleteNodeQuery(int nodeId) throws SQLException {
        try {
            var deleteNodeQuery = "DELETE FROM Nodes WHERE ID = ?";
            stmt = connection.prepareStatement(deleteNodeQuery);
            stmt.setInt(1, nodeId);
            stmt.execute();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    private void addAssessmentCriteriaQuery(List<String> assessmentCriteriaDTO, int nodeId) throws SQLException {
        try {
            for (String assessmentCriterium : assessmentCriteriaDTO) {
                var query = "INSERT INTO AssessmentCriteria (Description, NodeID)\n" +
                        "VALUES (?, ?)";
                stmt = connection.prepareStatement(query);
                stmt.setString(1, assessmentCriterium);
                stmt.setInt(2, nodeId);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    private void addLearningOutcomeQuery(String learningOutcome, int createdNodeId) throws SQLException {
        try {
            var query = "INSERT INTO LearningOutcome (Description, NodeID)\n" +
                    "VALUES (?, ?)";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, learningOutcome);
            stmt.setInt(2, createdNodeId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    private void updateLearningOutcomeQuery(String learningOutcome, int nodeId) throws SQLException {
        try {
            var updateQuery = "UPDATE LearningOutcome SET Description = ? WHERE NodeID = ?";
            stmt = connection.prepareStatement(updateQuery);
            stmt.setString(1, learningOutcome);
            stmt.setInt(2, nodeId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    private void updateAssessmentCriteriaQuery(List<String> assessmentCriteriaDTO, int nodeId) throws SQLException {
        try {
            // Delete existing assessment criteria for the given nodeId
            var deleteQuery = "DELETE FROM AssessmentCriteria WHERE NodeID = ?";
            stmt = connection.prepareStatement(deleteQuery);
            stmt.setInt(1, nodeId);
            stmt.executeUpdate();

            // Insert updated assessment criteria
            for (String assessmentCriterion : assessmentCriteriaDTO) {
                var insertQuery = "INSERT INTO AssessmentCriteria (Description, NodeID)\n" +
                        "VALUES (?, ?)";
                stmt = connection.prepareStatement(insertQuery);
                stmt.setString(1, assessmentCriterion);
                stmt.setInt(2, nodeId);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }


    private void updateNodeQuery(NodeRequestDTO nodeDTO, int nodeId) throws SQLException {
        try {
            var updateNodeQuery = "UPDATE Nodes SET Skill = ?, Description = ? WHERE ID = ?";
            stmt = connection.prepareStatement(updateNodeQuery);
            stmt.setString(1, nodeDTO.getSkill());
            stmt.setString(2, nodeDTO.getDescription());
            stmt.setInt(3, nodeId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public NodesDTO updateNodesPositions(NodesDTO nodesDTO, int skilltreeId) throws SQLException {
        try {
            connection = DriverManager.getConnection(databaseProperties.connectionString());
            for (NodeDTO nodeDTO : nodesDTO.getNodes()) {
                updateNodePositionsQuery(nodeDTO, skilltreeId);
            }
            return getNodesFromSkillTree(skilltreeId);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    private void updateNodePositionsQuery(NodeDTO nodeDTO, int skilltreeId) throws SQLException {
        try {
            var query = "UPDATE Nodes\n" +
                    "SET PositionX = ?, PositionY = ?\n" +
                    "WHERE ID = ? AND SkillTreeID = ?";
            stmt = connection.prepareStatement(query);
            stmt.setDouble(1, nodeDTO.getPositionX());
            stmt.setDouble(2, nodeDTO.getPositionY());
            stmt.setInt(3, nodeDTO.getID());
            stmt.setInt(4, skilltreeId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
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
