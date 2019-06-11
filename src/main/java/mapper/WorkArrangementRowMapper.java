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
		
		workarrangement.setAfternoonEndTime(rs.getTimestamp("afternoon_end_time"));
		workarrangement.setAfternoonStartTime(rs.getTimestamp("afternoon_start_time"));
		
		workarrangement.setArrangePerson(rs.getInt("arrange_person"));
		workarrangement.setEveningEndTime(rs.getTimestamp("evening_end_time"));
		workarrangement.setEveningStartTime(rs.getTimestamp("evening_start_time"));
		workarrangement.setMorningEndTime(rs.getTimestamp("morning_end_time"));
		workarrangement.setMorningStartTime(rs.getTimestamp("morning_start_time"));
		
		
		
		
		// TODO Auto-generated method stub
		return workarrangement;
	}

}
