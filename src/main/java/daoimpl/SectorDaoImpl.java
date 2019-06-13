package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import bean.Employee;
import bean.Sector;
import dao.SectorDao;
import mapper.EmployeeRowMapper;
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
		String sql = "SELECT * FROM SECTOR WHERE SECTOR_ID = "+i;
		
		return (List<Sector>)jdbcTemplate.query(sql, new SectorRowMapper(){});
	}


	@Override
	public int AddSector(Sector s) {
		String sql = "INSERT INTO sector (sector_id,sector_name,sector_people_number,sector_description) VALUE(?,?,?,?)";
		int i = jdbcTemplate.update(sql,s.getSectorId(),s.getSectorName(),s.getSectorPeopleNumber(),s.getSectorDescription());
		return i;
	}


	@Override
	public int ModifySector(Sector s) {
		String sql = "UPDATE SECTOR SET SECTOR_NAME=?,SECTOR_PEOPLE_NUMBER=?,SECTOR_DESCRIPTION=? WHERE SECTOR_ID= "+s.getSectorId();
		int i = jdbcTemplate.update(sql,new Object[]{s.getSectorName(),s.getSectorPeopleNumber(),s.getSectorDescription()});
		
		return i;
	}


	@Override
	public int DeleteSector(int sectorId) {
		String sql = "DELETE FROM SECTOR WHERE SECTOR_ID=?";
		int i = jdbcTemplate.update(sql,new Object[]{sectorId});
		return i;
	}


	@Override
	public List<Employee> employeeInfoBySectorId(int sectorId) {
		// TODO Auto-generated method stub
		String sql = "select * from employee where sector_id = ?";
		
		return (List<Employee>) jdbcTemplate.query(sql, new Object[] {sectorId},new EmployeeRowMapper() {
			
		});
	}


	@Override
	public int findAllEmployeeBySectorId(int sectorId) {
		// TODO Auto-generated method stub
		String sql = "select * from employee where sector_id = ?";
		List<Employee> list = jdbcTemplate.query(sql, new Object[] {sectorId},new EmployeeRowMapper() {
			
		});
		return list.size();
	}

}
