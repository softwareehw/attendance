package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.WorkArrangement;

public class WorkArrangementRowMapper implements RowMapper<WorkArrangement> {

	@Override
	public WorkArrangement mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		WorkArrangement workarrangement = new WorkArrangement();
		
		workarrangement.setEmployeeId(rs.getInt("employee_id"));
		workarrangement.setWork_arrange_id(rs.getInt("work_arrange_id"));
		
		workarrangement.setEndTime(rs.getTimestamp("end_time"));
		workarrangement.setStartTime(rs.getTimestamp("start_time"));
		
		workarrangement.setArrangePerson(rs.getInt("arrange_person"));
	
		
		
		
		
		// TODO Auto-generated method stub
		return workarrangement;
	}

}
