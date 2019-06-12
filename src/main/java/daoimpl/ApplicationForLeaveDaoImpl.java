package daoimpl;

import java.text.SimpleDateFormat;
import java.util.Date;
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
		String sql = "insert into application_for_leave(state,start_time,end_time,applicated_person,is_report_back,leave_reason) values(?,?,?,?,?,?)";
		return jdbcTemplate.update(sql,0,applicationForLeave.getStartTime(),
				applicationForLeave.getEndTime(),applicationForLeave.getApplicatdPerson(),
				0,applicationForLeave.getLeaveReason());
	}
	
	
 
	//根据员工Id查出请假记录
	@Override
	public List<ApplicationForLeave> findApplicationForLeaveById(int applicatedPerson) {
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
	/**
	 * 查找本部门请假员工
	 */
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

	@Override
	public List<ApplicationForLeave> findUnratifiedApplicationForleaveBySectorId(int sectorId) {
		String sql = "SELECT * FROM application_for_leave WHERE applicated_person IN(select employee_id from employee WHERE sector_id = "
				+ sectorId
				+ ") and state = 0";
		return (List<ApplicationForLeave>) jdbcTemplate.query(sql,new ApplicationForLeaveRowMapper());
	}



	@Override
	public int CancelLeaveByLeaveId(int leaveId) {
		Date datenow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String sql = "update application_for_leave set is_report_back = 1,"
				+ "report_back_time="+"\'"+ft.format(datenow).toString()+"\'"
				+ " where leave_id="+leaveId ;
		int i = jdbcTemplate.update(sql);
		return i;
	}



	@Override
	public List<ApplicationForLeave> findLeaveByLId(int leaveId) {
		String sql = "SELECT * FROM application_for_leave where leave_id="+leaveId;
		List<ApplicationForLeave> l = jdbcTemplate.query(sql, new ApplicationForLeaveRowMapper());
		return l;
	}



	@Override
	public int CancelCancelLeaveByLeaveId(int leaveId) {
		String sql ="UPDATE application_for_leave set report_back_time="+"\'"
				+ "1000-06-04 20:44:38"+"\'"
				+ ",is_report_back=0 "
				+ "where leave_id="
				+ leaveId;
		int i = jdbcTemplate.update(sql);
		return i;
	}



	@Override
	public int RatifyLeave(ApplicationForLeave applicationForLeave) {
		String sql = "update application_for_leave set state=? where leave_id="+applicationForLeave.getLeaveId();
		int i= jdbcTemplate.update(sql,new Object[]{applicationForLeave.getState()});
		return i;
	}

}
