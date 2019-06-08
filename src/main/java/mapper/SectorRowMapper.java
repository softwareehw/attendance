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
		String sectorDescription = rs.getString("sector_description");
		
		Sector sector = new Sector();
		sector.setSectorId(sectorId);
		sector.setSectorName(sectorName);
		sector.setSectorPeopleNumber(sectorPeopleNumber);
		sector.setSectorDescription(sectorDescription);
		
		
		
		
		return sector;
	}

}
