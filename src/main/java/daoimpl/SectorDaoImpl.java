package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import bean.Sector;
import dao.SectorDao;

@Repository
public class SectorDaoImpl implements SectorDao {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	

	@Override
	public List<Sector> getAllSector() {
		String sql = "SELECT * FROM sector";
		
        return (List<Sector>) jdbcTemplate.query(sql, new RowMapper<Sector>(){

            @Override
            public Sector mapRow(ResultSet rs, int rowNum) throws SQLException {
            	Sector sector=new Sector();
            	sector.setSectorId(rs.getInt("sector_id"));
            	sector.setSectorName(rs.getString("sector_name"));
            	sector.setSectorPeopleNumber(rs.getInt("sector_people_number"));
            	sector.setDescription(rs.getString("sector_description"));
                return sector;
            }

        });
	}

}
