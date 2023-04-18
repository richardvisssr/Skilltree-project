package nl.han.oose.project.data.datamapper;

import java.sql.ResultSet;

public interface Datamapper<T> {
    public T map(ResultSet resultSet);
}
