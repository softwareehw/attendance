package daoimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import bean.WorkArrangement;
import dao.WorkArrangementDao;
import mapper.WorkArrangementRowMapper;

@Repository
public class WorkArrangementDaoImpl implements WorkArrangementDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	

	@Override
	public int AddWorkArrangement(WorkArrangement wa) {
		String sql = "INSERT INTO WORK_ARRANGEMENT(employee_id,arrange_person,"
				+ "start_time,end_time) VALUES(?,?,?,?)";
		int i = jdbcTemplate.update(sql,new Object[]{wa.getEmployeeId(),wa.getArrangePerson(),wa.getStartTime(),wa.getEndTime()});
		return i;
	}

	@Override
	public List<WorkArrangement> findWorkArrangementByEId(int employeeId) {
		String sql = "select * from work_arrangement where employee_id =  ?" ;
		List<WorkArrangement> l = jdbcTemplate.query(sql, new Object[] {employeeId} ,new WorkArrangementRowMapper() {
			
		});
		return l;
	}

}
