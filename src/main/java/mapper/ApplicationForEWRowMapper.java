package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import bean.ApplicationForEW;

/**
 *@author 29226
 *@注释 : 实现RowMapper接口，返回applicationForEW对象
*/
public class ApplicationForEWRowMapper implements RowMapper<ApplicationForEW> {

	@Override
	public ApplicationForEW mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		int applicatedId = rs.getInt("applicated_id");
		int applicatedPerson = rs.getInt("applicated_person");
		Date startTime = rs.getDate("start_time");
		Date endTime = rs.getDate("end_time");
		int state = rs.getInt("state");
		
		ApplicationForEW applicationForEW = new ApplicationForEW();
		applicationForEW.setApplicatedId(applicatedId);
		applicationForEW.setApplicatedPerson(applicatedPerson);
		applicationForEW.setStartTime(startTime);
		applicationForEW.setEndTime(endTime);
		applicationForEW.setState(state);
		
		return applicationForEW;
	}

}
