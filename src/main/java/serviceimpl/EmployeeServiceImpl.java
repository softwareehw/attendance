package serviceimpl;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bean.ApplicationForEW;
import bean.ApplicationForLeave;
import bean.AttendanceRecord;
import bean.Employee;
import dao.ApplicationForEWDao;
import dao.ApplicationForLeaveDao;
import dao.AttendanceRecordDao;
import dao.EmployeeDao;
import mapper.AttendanceRecordRowMapper;
import mapper.EmployeeRowMapper;
import service.EmployeeService;
import org.springframework.jdbc.core.JdbcTemplate;
@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired 
	private EmployeeDao employeeDao;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired 
	private ApplicationForLeaveDao applicationForLeaveDao;
	@Autowired 
	private ApplicationForEWDao applicationForEWDao ;
	@Autowired 
	private AttendanceRecordDao attendanceRecordDao;


	@Override
	public int applicateLeave(int EmployeeId) {
		// TODO Auto-generated method stub
		return applicationForLeaveDao.addApplicateLeave(EmployeeId);
	}

	@Override
	public int applicateEW(ApplicationForEW applica) {
		// TODO Auto-generated method stub
		return applicationForEWDao.addApplicateEW(applica);
	}

	@Override
	public List<ApplicationForEW> FindById(int applicatedPerson) {
		// TODO Auto-generated method stub
		return applicationForEWDao.findById(applicatedPerson);
	}

	@Override
	public List<ApplicationForEW> findAll() {
		// TODO Auto-generated method stub
		return applicationForEWDao.findAll();
	}

	@Override
	public int deleteApplication(int applicatedId) {
		// TODO Auto-generated method stub
		
		return applicationForEWDao.deleteApplicationForEW(applicatedId);
	}

	@Override
	public List<Employee> employeeFindById(int employeeId) {
		// TODO Auto-generated method stub
		return employeeDao.employeeFindById(employeeId);
	}

	@Override
	public int addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeDao.addEmployee(employee);
	}

	@Override
	public int deleteEmployee(int employeeId) {
		// TODO Auto-generated method stub
		return employeeDao.deleteEmployee(employeeId);
	}

	@Override
	public String attendance(int userId) {
		// TODO Auto-generated method stub
		String sql = "select * from attendance_record where employeeid = ? and to_days(start_time) = to_days(?)";
		String sql1 = "select * from attendance_record where employeeid = ? and to_days(start_time) = to_days(?) and is_complete = ?";
		JSONObject ans=new JSONObject();
		
		List<AttendanceRecord> result = new ArrayList<AttendanceRecord>();
		List<AttendanceRecord> result1 = new ArrayList<AttendanceRecord>();
	    result = jdbcTemplate.query(sql,new Object[] {userId,new Date()},new AttendanceRecordRowMapper() {
	    	
	    });
	    result1 = jdbcTemplate.query(sql1,new Object[] {userId,new Date(),0},new AttendanceRecordRowMapper() {
	    	
	    });
	    if(result.isEmpty()) {
	    attendanceRecordDao.attendance(userId);
	    ans.put("status", 1);
		ans.put("id", userId);
		ans.put("message", "上班打卡成功");
		return ans.toString();
	    }else if(!result1.isEmpty()) {
	    	attendanceRecordDao.attendanceDown(userId);
	    	ans.put("state", 1);
	    	ans.put("id", userId);
			ans.put("message", "下班打卡成功");
	    	return ans.toString();
	    }else{
	    	ans.put("state", 0);
	    	ans.put("error_message", "超出打卡次数");
	    	return ans.toString();
	    }
	}  
	    
	

	@Override
	public int attendanceDown(int userId) {
		// TODO Auto-generated method stub
		return attendanceRecordDAo.attendanceDown(userId);
	}

	@Override
	public List<AttendanceRecord> attendanceFindById(int userId) {
		// TODO Auto-generated method stub
		return attendanceRecordDAo.attendanceFindById(userId);
	}

	@Override
	public int addApplicationForLeave(ApplicationForLeave applicationForLeave) {
		// TODO Auto-generated method stub
		return applicationForLeaveDao.addApplicationForLeave(applicationForLeave);
	}

	@Override
	public List<ApplicationForLeave> applicationForLeaveFindById(int applicatedPerson) {
		// TODO Auto-generated method stub
		return applicationForLeaveDao.applicationForLeaveFindById(applicatedPerson);
	}

	@Override
	public int updateEmployee(Employee e) {
		// TODO Auto-generated method stub
		return employeeDao.updateEmployee(e);
	}

}
