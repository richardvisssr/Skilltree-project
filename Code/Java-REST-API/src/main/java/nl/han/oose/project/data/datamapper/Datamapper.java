package nl.han.oose.project.data.datamapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Datamapper<T> {
    public T map(ResultSet resultSet) throws SQLException;

    public T map(ResultSet resultSet, ResultSet resultSet2) throws SQLException;
}
