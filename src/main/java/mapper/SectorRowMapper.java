package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.Sector;

public class SectorRowMapper implements RowMapper<Sector> {

	@Override
	public Sector mapRow(ResultSet rs, int rowNum) throws SQLException {
		int sectorId = rs.getInt("sector_id");
		String sectorName = rs.getString("sector_name");
		int sectorPeopleNumber = rs.getInt("sector_people_number");
		String sectorDiscription = rs.getString("sector_discription");
		
		Sector sector = new Sector();
		sector.setSectorId(sectorId);
		sector.setSectorName(sectorName);
		sector.setSectorPeopleNumber(sectorPeopleNumber);
		sector.setSectorDescription(sectorDiscription);
		
		
		
		
		return null;
	}

}
