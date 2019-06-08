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
import mapper.SectorRowMapper;

@Repository
public class SectorDaoImpl implements SectorDao {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	

	@Override
	public List<Sector> getAllSector() {
		String sql = "SELECT * FROM sector";
		
        return (List<Sector>) jdbcTemplate.query(sql, new SectorRowMapper(){});
	}


	@Override
	public List<Sector> FindSectorById(int i) {
		String sql = "SELECT * FROM SECTOR WHERE SECTOR_ID = ?";
		
		return (List<Sector>)jdbcTemplate.query(sql, new Object[] {i},new SectorRowMapper(){});
	}

}
