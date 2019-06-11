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
		workarrangement.setWork_arrange_id(rs.getInt("work_arrangement_id"));
		
		workarrangement.setAfternoonEndTime(rs.getDate("afternoon_end_time"));
		workarrangement.setAfternoonStartTime(rs.getDate("afternoon_start_time"));
		
		workarrangement.setArrangePerson(rs.getInt("arrange_person"));
		workarrangement.setEveningEndTime(rs.getDate("evening_end_time"));
		workarrangement.setEveningStartTime(rs.getDate("evening_start_time"));
		workarrangement.setMorningEndTime(rs.getDate("morning_end_time"));
		workarrangement.setMorningStartTime(rs.getDate("morning_start_time"));
		
		
		
		
		// TODO Auto-generated method stub
		return null;
	}

}
