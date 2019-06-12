package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;


import bean.ApplicationForLeave;
/**
 *@author 29226
 *@注释 : 实现RowMapper接口，返回applicationForEW对象
*/
public class ApplicationForLeaveRowMapper implements RowMapper<ApplicationForLeave> {

	@Override
	public ApplicationForLeave mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		int leaveId = rs.getInt("leave_id");
		
	    int state = rs.getInt("state");
		//开始时间
		Date startTime = rs.getTimestamp("start_time");
		//结束时间
		Date endTime = rs.getTimestamp("end_time");
		//申请人id
		int applicatdPerson = rs.getInt("applicated_person");
		//批准人id
		int ratifiedPerson = rs.getInt("ratified_person");
		//是否销假
		boolean isReportBack = rs.getBoolean("is_report_back");
		//销假结束日期
	    Date reportBackTime = rs.getDate("report_back_time");
	    
	    String leaveReason = rs.getString("leave_reason");
	    
	    String rejectReason = rs.getString("reject_reason");
	    
	    boolean leaveType = rs.getBoolean("leave_type");
	
		
		ApplicationForLeave applicationForLeave = new ApplicationForLeave();
		applicationForLeave.setLeaveId(leaveId);
		applicationForLeave.setState(state);
		applicationForLeave.setStartTime(startTime);
		applicationForLeave.setEndTime(endTime);
		applicationForLeave.setApplicatdPerson(applicatdPerson);
		applicationForLeave.setRatifiedPerson(ratifiedPerson);
		applicationForLeave.setReportBackTime(reportBackTime);
		applicationForLeave.setReportBack(isReportBack);
		applicationForLeave.setLeaveReason(leaveReason);
		applicationForLeave.setRejectReason(rejectReason);
		applicationForLeave.setLeaveType(leaveType);
		
		return applicationForLeave;
	}

}
