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
				+ "morning_start_time,morning_end_time,afternoon_start_time,"
				+ "afternoon_end_time,evening_start_time,evening_end_time) VALUES(?,?,?,?,?,?,?,?)";
		int i = jdbcTemplate.update(sql,new Object[]{wa.getEmployeeId(),wa.getArrangePerson(),wa.getMorningStartTime(),wa.getMorningEndTime(),wa.getAfternoonStartTime(),wa.getAfternoonEndTime(),wa.getEveningStartTime(),wa.getEveningEndTime()});
		return i;
	}

	@Override
	public List<WorkArrangement> findWorkArrangementByEId(int employeeId) {
		String sql = "SELECT * FROM WORK_ARRANGEMENT WHERE WORK_ARRANGE_ID="+employeeId ;
		List<WorkArrangement> l = jdbcTemplate.query(sql, new WorkArrangementRowMapper());
		return l;
	}

}
