package nl.han.oose.project.data.datamapper;

import nl.han.oose.project.resources.dto.SkilltreeDTO;
import nl.han.oose.project.resources.dto.SkilltreesDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SkilltreeDatamapper implements Datamapper{
    @Override
    public SkilltreesDTO map(ResultSet resultSet) throws SQLException {
        //TODO
        var skilltreesDTO = new SkilltreesDTO();
        List<SkilltreeDTO> skillttrees = new ArrayList<>();

        while(resultSet.next()) {
            skillttrees.add(
                new SkilltreeDTO(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("description")
                )
            );
        }

        skilltreesDTO.setSkilltrees(skillttrees);
        return skilltreesDTO;
    }
}
