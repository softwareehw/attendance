package daoimpl;

import java.text.SimpleDateFormat;
import java.util.Date;
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
		String sql = "insert into work_arrangement(employee_id,arrange_person,"
				+ "start_time,end_time) value(?,?,?,?)";
		int i = jdbcTemplate.update(sql,new Object[]{wa.getEmployeeId(),wa.getArrangePerson(),wa.getStartTime(),wa.getEndTime()}	
		);
		return i;
	}

	@Override
	public List<WorkArrangement> findWorkArrangementByEId(int employeeId) {
		String sql = "select * from work_arrangement where employee_id =  ?" ;
		List<WorkArrangement> l = jdbcTemplate.query(sql, new Object[] {employeeId} ,new WorkArrangementRowMapper());
		return l;
	}

	@Override
	public int deleteByWAId(int workArrangementId) {
		String sql = "DELETE from work_arrangement where work_arrange_id="+workArrangementId;
		int i = jdbcTemplate.update(sql);
		return i;
	}

	@Override
	public int ModifyByWAId(WorkArrangement workArrangement) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTime = sdf.format(workArrangement.getStartTime());
		String endTime = sdf.format(workArrangement.getEndTime());
		
		
		
		String sql = "update work_arrangement set start_time=?,end_time=? where work_arrange_id="+workArrangement.getWorkArrangeId();
		
		int i = jdbcTemplate.update(sql,new Object[]{startTime,endTime});
		return i;
	}

}
