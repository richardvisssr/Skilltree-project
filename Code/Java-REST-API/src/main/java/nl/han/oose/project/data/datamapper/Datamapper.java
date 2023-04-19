package nl.han.oose.project.data.datamapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Datamapper<T> {
    public T map(ResultSet resultSet) throws SQLException;
}
