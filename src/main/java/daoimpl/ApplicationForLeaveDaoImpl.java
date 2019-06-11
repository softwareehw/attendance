package daoimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import bean.ApplicationForLeave;
import dao.ApplicationForLeaveDao;
import mapper.ApplicationForLeaveRowMapper;

@Repository
public class ApplicationForLeaveDaoImpl implements ApplicationForLeaveDao {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	
	//插入请假记录
	@Override
	public int addApplicationForLeave(ApplicationForLeave applicationForLeave) {
		// TODO Auto-generated method stub
		String sql = "insert into application_for_leave(state,start_time,end_time,applicated_person,ratified_person,is_report_back,report_back_time,leave_reason) values(?,?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql,applicationForLeave.isState(),applicationForLeave.getStartTime(),
				applicationForLeave.getEndTime(),applicationForLeave.getApplicatdPerson(),
				applicationForLeave.getRatifiedPerson(),
				applicationForLeave.isReportBack(),applicationForLeave.getReportBackTime(),applicationForLeave.getLeaveReason());
	}
 
	//根据Id查出请假记录
	@Override
	public List<ApplicationForLeave> applicationForLeaveFindById(int applicatedPerson) {
		// TODO Auto-generated method stub
		String sql = "select * from application_for_leave where applicated_person = ?";
		return jdbcTemplate.query(sql, new Object[] {applicatedPerson},new ApplicationForLeaveRowMapper() {
			
		});
	}

  

    //查看全部请假记录
	@Override
	public List<ApplicationForLeave> applicationForLeaveFindAll() {
		// TODO Auto-generated method stub
		String sql = "select * from application_for_leave";
		
		return jdbcTemplate.query(sql, new ApplicationForLeaveRowMapper() {
			
		});
	}
	
	@Override
	public List<ApplicationForLeave> findLeavePersonInMyDepartment(int departmentId) {
		
		String sql = "SELECT application_for_leave.state, "
				+ "application_for_leave.start_time,"
				+ "application_for_leave.end_time,"
				+ "application_for_leave.applicated_person,"
				+ "application_for_leave.certificate_person,"
				+ "application_for_leave.is_report_back,"
				+ "application_for_leave.report_back_time,"
				+ "application_for_leave.id"
				+ "FROM"
				+ "application_for_leave"
				+ "where application_for_leave.applicated_person in"
				+ " (SELECT EM.EMPLOYEE_ID FROM EMPLOYEE AS EM WHERE EM.SECTOR_ID = 123) ";
		
		
		
		
       return (List<ApplicationForLeave>) jdbcTemplate.query(sql, new ApplicationForLeaveRowMapper() {
			
		});
	}

}
