package serviceimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
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
import dao.SectorDao;
import dao.UserDao;
import mapper.AttendanceRecordRowMapper;
import mapper.EmployeeRowMapper;
import service.EmployeeService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
	@Autowired
	private SectorDao sectorDao;
	@Autowired
	private UserDao userDao;


	@Override
	public int applicateLeave(int EmployeeId) {
//		// TODO Auto-generated method stub
//		return applicationForLeaveDao.addApplicationForLeave(EmployeeId);
		return 1;
	}
    
	//添加加班申请
	@Override
	public String applicateEW(ApplicationForEW applica) {
		// TODO Auto-generated method stub
		JSONObject ans = new JSONObject();
		if(applicationForEWDao.addApplicateEW(applica)==1) {
			ans.put("state", 1);
			return ans.toString();
		}
		ans.put("state", 0);
		ans.put("error_message", "不符合要求");
		return ans.toString();
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
	public String addEmployee(Employee employee) {
		// TODO Auto-generated method stub

        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = con -> {
            PreparedStatement ps = con.prepareStatement("insert into user(password,degree) values(12356,0)", Statement.RETURN_GENERATED_KEYS);
            return ps;
        };

        jdbcTemplate.update(preparedStatementCreator, keyHolder);

        int id =  (int) keyHolder.getKey().longValue();
        employeeDao.addEmployee(employee,id);
        JSONObject json = new JSONObject();
        json.put("id", id);
        return json.toString();

		
//		String sql = "select * from employee where sector_id = ? and is_manager = ?";
//		List<Employee> result = jdbcTemplate.query(sql,new Object[] {employee.getSectorId(),true}, new EmployeeRowMapper() {
//			
//		});
//		JSONObject json = new JSONObject();
//		if(result.size()==0) {
//			employeeDao.addEmployee(employee,id);
//			json.put("status", 1);
//		}else if(result.size()==1&&!employee.isManager()) {
//			employeeDao.addEmployee(employee,id);
//			json.put("status", 1);
//		}else {
//			json.put("status", 0);
//			json.put("error_message", "添加失败");
//			return json.toString();
//		}
//		
//		
//		//如果成功，返回
//		return json.toString();
		
		
	}

	@Override
	public String deleteEmployee(int employeeId) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		int i = employeeDao.deleteEmployee(employeeId);
		if(i!=1){
			json.put("status", 0);
			json.put("error_message", "删除失败");
			return json.toString();
		}else{
			json.put("status", 1);
		}
		//如果成功，返回
		return json.toString();
		
		
	}

	@Override
	public String attendance(int userId) {
		// TODO Auto-generated method stub
		String sql = "select * from attendance_record where employeeid = ? and to_days(start_time) = to_days(?) and is_complete = ?";
		String sql1 = "select * from attendance_record where employeeid = ? and to_days(start_time) = to_days(?) and is_complete = ?";
		JSONObject ans=new JSONObject();
		
		List<AttendanceRecord> result = new ArrayList<AttendanceRecord>();
		List<AttendanceRecord> result1 = new ArrayList<AttendanceRecord>();
	    result = jdbcTemplate.query(sql,new Object[] {userId,new Date(),0},new AttendanceRecordRowMapper() {
	    	
	    });
	    result1 = jdbcTemplate.query(sql1,new Object[] {userId,new Date(),1},new AttendanceRecordRowMapper() {
	    	
	    });
	    if(result1.size() < 5) {
	        if(result.isEmpty()) {
	          attendanceRecordDao.attendance(userId);
	          ans.put("status", 1);
		      ans.put("id", userId);
		      ans.put("message", "上班打卡成功");
		      return ans.toString();
	    }else {
	    	attendanceRecordDao.attendanceDown(userId);
	    	ans.put("state", 1);
	    	ans.put("id", userId);
			ans.put("message", "下班打卡成功");
	    	return ans.toString();
	      }
	    }else{
	    	ans.put("state", 0);
	    	ans.put("alert", "超出打卡上限");
	    	return ans.toString();
	    }
	}  
	    
	

	@Override
	public int attendanceDown(int userId) {
		// TODO Auto-generated method stub
		return attendanceRecordDao.attendanceDown(userId);
	}

	@Override
	public List<AttendanceRecord> attendanceFindById(int userId) {
		// TODO Auto-generated method stub
		return attendanceRecordDao.attendanceFindById(userId);
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

	//修改员工
	@Override
	public int updateEmployee(Employee e) {

		String sql = "select * from employee where sector_id = ? and is_manager = true";
		List<Employee> list = jdbcTemplate.query(sql, new Object[] {e.getSectorId()},new EmployeeRowMapper() );
		
		System.out.println(e.isManager());
		System.out.println(1231111111);
		if(list.size()==1&&e.isManager()) {
			System.out.println(123);
			employeeDao.updateEmployee(e);
			String sql1 = "update employee set is_manager = false where employee_id = ?";
			return jdbcTemplate.update(sql1,list.get(0).getEmployeeId());
		}else {
			System.out.println(1234);
			return employeeDao.updateEmployee(e);
		}
		
	}

	@Override
	public String employeeInfoBySectorId(int sectorId) {
		// TODO Auto-generated method stub
		
		List<Employee> employee = sectorDao.employeeInfoBySectorId(sectorId);
		JSONArray jay = new JSONArray(employee);
		return jay.toString();
	}



	@Override
	public String updateEmployeeInfoApplicationForEW( int applicatedId) {
		// TODO Auto-generated method stub
		JSONObject ans = new JSONObject();
		List<ApplicationForEW> applicationForEW = applicationForEWDao.updateEmployeeInfoApplicationForEW(applicatedId);
		JSONArray jay = new JSONArray(applicationForEW);
		if(!applicationForEW.isEmpty()) {
			return jay.toString();
		}else {
			
			ans.put("state", 0);
			ans.put("errormessage", "已经批准，不可修改");
			return ans.toString();
		}
		
	}

	@Override
	public String getUncheckApplicationForEW(int sectorId) {
		// TODO Auto-generated method stub
		return null;
	}



}
