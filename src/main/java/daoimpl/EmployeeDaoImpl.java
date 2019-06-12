package daoimpl;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import bean.ApplicationForEW;
import bean.ApplicationForLeave;
import bean.AttendanceRecord;
import bean.Employee;
import bean.Manager;
import bean.User;
import dao.EmployeeDao;
import mapper.ApplicationForEWRowMapper;
import mapper.ApplicationForLeaveRowMapper;
import mapper.EmployeeRowMapper;
import mapper.UserRowMapper;
import mapper.AttendanceRecordRowMapper;

@Repository
public class EmployeeDaoImpl implements EmployeeDao,Serializable {
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Override
	public Boolean setIsMasterById(int id, boolean isM) {
		String ID="";
		ID=String.valueOf(id);
		try {
			String sql="UPDATE employee set is_manager="+isM+" WHERE employee_id="+id;
			jdbcTemplate.update(sql);
		}catch(Exception e){
			System.out.println(e);
			return false;
		}
		return true;
	}
	
	@Override
	public List<Employee> getEmployeeBySId(int id) {
		// TODO Auto-generated method stub
		String ID="";
		ID=String.valueOf(id);
		String sql="SELECT * FROM employee WHERE sector_id="+ID;
		
		return (List<Employee>) jdbcTemplate.query(sql, new EmployeeRowMapper()) ;
	}
	
	
//
//	@Override
//	public int addApplicateLeave(int EmployeeId) {
//		// TODO Auto-generated method stub
//		return (Integer) null;
//	}



//	@Override
//	public List<ApplicationForEW> findById(int applicatedPerson) {
//		// TODO Auto-generated method stub
//		
//		String ApplicatedPerson="";
//		ApplicatedPerson=String.valueOf(applicatedPerson);
//		String sql="SELECT * FROM application_for_ew WHERE applicated_person="+ApplicatedPerson;
//		return (List<ApplicationForEW>) jdbcTemplate.query(sql, new ApplicationForEWRowMapper() {
//			
//		});
//	}
	
//	@Override
//	public List<ApplicationForEW> findAll() {
//		// TODO Auto-generated method stub
//		String sql = "select * from application_for_ew";
//		return jdbcTemplate.query(sql, new ApplicationForEWRowMapper() {
//			
//		});
//		
//	}

//	@Override
//	public int deleteApplicationForEW(int applicatedId) {
//		// TODO Auto-generated method stub
//		String sql = "delete from application_for_ew where applicated_id = ?";
//		return jdbcTemplate.update(sql, applicatedId);
//	
//	}

	@Override
	public List<Employee> employeeFindById(int employeeId) {
		// TODO Auto-generated method stub
		String EmployeeId="";
		EmployeeId=String.valueOf(employeeId);
		String sql="SELECT * FROM employee WHERE employee_id="+EmployeeId;
		return (List<Employee>) jdbcTemplate.query(sql,new EmployeeRowMapper() {
			
		});
	}

	//添加员工
	@Override
	public int addEmployee(Employee employee,int id) {
	
//			Calendar s=Calendar.getInstance();
//			s.setTime(e.getEnrollTime());
//			//System.out.println(s.getTimeInMillis());
//			//Date t=e.getEnrollTime();
//			int mm=s.get(s.MONTH)+1;
//			String timett=s.get(s.YEAR)+"-"+mm+"-"+s.get(s.DAY_OF_MONTH)+" "
//					+ s.get(s.HOUR_OF_DAY)+":"+s.get(s.MINUTE)+":"+s.get(s.SECOND);
//			System.out.println(timett);
			String sql="insert into employee(employee_id,sector_id,is_manager,name,age,salary,sex,phone_number,enroll_time,user_id) value(?,?,?,?,?,?,?,?,?,?)";
			
			return jdbcTemplate.update(sql, employee.getEmployeeId(),employee.getSectorId(),employee.isManager(),employee.getName(),employee.getAge(),employee.getSalary(),employee.isSex(),employee.getPhoneNumber(),new Date(),id);
			
	}
    
	//删除员工
	@Override
	public int deleteEmployee(int employeeId) {
		// TODO Auto-generated method stub
		String sql1 = "delete  from application_for_leave where applicated_person = ?";
		String sql2 = "delete  from work_arrangement where employee_id = ?";
		String sql3 = "delete  from application_for_ew where applicated_id = ?";
		String sql4 = "delete  from attendance_record where employeeid = ?";
		
	
		jdbcTemplate.update(sql1,employeeId);
		jdbcTemplate.update(sql2,employeeId);
		jdbcTemplate.update(sql3,employeeId);
		jdbcTemplate.update(sql4,employeeId);
		String sql5 = "delete from employee where employee_id = ?";
		return jdbcTemplate.update(sql5,employeeId);
	}



//	@Override
//	public int addApplicateEW(ApplicationForEW applica) {
//		// TODO Auto-generated method stub
//		String sql = "insert into application_for_ew(applicated_person,start_time,end_time,state) "
//				+ "values(?,?,?,?)";
//		return jdbcTemplate.update(sql,applica.getApplicatedPerson(),applica.getStartTime(),applica.getEndTime(),applica.getState());
//		
//	}



//	@Override
//	public int attendance(int userId) {
//		// TODO Auto-generated method stub
//		
//		String sql = "insert into attendance_record(employeeid,start_time,is_complete) values(?,?,?)";
//		
//		return jdbcTemplate.update(sql,userId,new Date(),0);
//	}



//	@Override
//	public int attendanceDown(int userId) {
//		// TODO Auto-generated method stub
//		String sql = "update attendance_record set end_time = ?,is_complete = ? where employeeid = ? and is_complete = ?";
//		return jdbcTemplate.update(sql,new Date(),1,userId,0);
//	}



//	@Override
//	public List<AttendanceRecord> attendanceFindById(int userId) {
//		// TODO Auto-generated method stub
//		String UserId="";
//		UserId=String.valueOf(userId);
//		String sql="SELECT * FROM attendance_record WHERE employeeid="+UserId;
//		return (List<AttendanceRecord>) jdbcTemplate.query(sql, new AttendanceRecordRowMapper() {
//			
//		});
//	}

	@Override
	public List<Employee> findEmployeeByUserId(int id) {
		String sql="SELECT * FROM employee WHERE user_id="+id;
		
		return (List<Employee>) jdbcTemplate.query(sql, new RowMapper<Employee>() {
			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Employee employee = new Employee();
				employee.setEmployeeId(rs.getInt("employee_id"));
				employee.setSectorId(rs.getInt("sector_id"));
				employee.setisManager(rs.getBoolean("is_manager"));
				employee.setName(rs.getString("name"));
				employee.setAge(rs.getInt("age"));
				employee.setSalary(rs.getInt("salary"));
				employee.setSex(rs.getBoolean("sex"));
				employee.setPhoneNumber(rs.getInt("phone_number"));
				employee.setEnrollTime(rs.getDate("enroll_time"));
				return employee;
			}
			
		});
	}
	
	@Override
	public Boolean modifyEm(Employee e) {
		Calendar s=Calendar.getInstance();
		s.setTime(e.getEnrollTime());
		//System.out.println(s.getTimeInMillis());
		//Date t=e.getEnrollTime();
		int mm=s.get(s.MONTH)+1;
		String timett=s.get(s.YEAR)+"-"+mm+"-"+s.get(s.DAY_OF_MONTH)+" "
				+ s.get(s.HOUR_OF_DAY)+":"+s.get(s.MINUTE)+":"+s.get(s.SECOND);
		System.out.println(timett);
		String sql="UPDATE employee SET sector_id=?,is_manager=?,name=?,age=?,salary=?,sex=?,phone_number=?,enroll_time=? WHERE employee_id=?";//,,,,,"+timet+"\" WHERE employee_id=?;";
		//timett哪里有问题不知道问题在哪 出在，的问题
		jdbcTemplate.update(sql, e.getSectorId(),e.isManager(),e.getName(),e.getAge(),e.getSalary(),e.isSex(),e.getPhoneNumber(),timett,e.getEmployeeId());//,e.getName(),e.getAge(),e.getSalary(),e.isSex(),e.getPhoneNumber(),e.getEmployeeId());
		System.out.println("success!");
		return null;
	}

	@Override
	public int updateEmployee(Employee e) {
		// TODO Auto-generated method stub
		String sql = "update employee set sector_id= ?,is_manager= ?,name= ?,age=?,salary=?,sex=?,phone_number=?" + 
				" where user_id = ?";
		return jdbcTemplate.update(sql,new Object[] {e.getSectorId(),e.isManager(),e.getName(),e.getAge()
				,e.getSalary(),e.isSex(),e.getPhoneNumber(),e.getUserId()});
	}

	@Override
	public String judgeDegree(User user) {
		// TODO Auto-generated method stub
		String sql = "select * from employee where user_id = ? and is_manager = ?";
		List<Employee> list = jdbcTemplate.query(sql, new Object[] {user.getId(),true},new EmployeeRowMapper() {
			
		});
		String sql2 =  "select * from employee where user_id = ? and is_manager = ?";
		List<Employee> list2 = jdbcTemplate.query(sql2, new Object[] {user.getId(),false},new EmployeeRowMapper() {
			
		});
		String sql1 = "select * from manager where user_id = ?";
        List<User> list1 = jdbcTemplate.query(sql1, new Object[] {user.getId()},new UserRowMapper() {
			
		});
        
        JSONObject ans = new JSONObject();
        if(list.size()==1) {
        	ans.put("degree", 2);
        	ans.put("employeeId", list.get(0).getEmployeeId());
        	return ans.toString();
        }else if(list2.size()==1) {
        	ans.put("degree", 1);
        	ans.put("employeeId", list2.get(0).getEmployeeId());
        	return ans.toString();
        }
        ans.put("degree", 3);
    	ans.put("employeeId", 0);
    	return ans.toString();
	}




//	@Override
//	public int addApplicationForLeave(ApplicationForLeave applicationForLeave) {
//		// TODO Auto-generated method stub
//		String sql = "insert into application_for_leave(state,start_time,end_time,applicated_person,certificate_person,is_report_back,report_back_time) values(?,?,?,?,?,?,?)";
//		return jdbcTemplate.update(sql,applicationForLeave.isState(),applicationForLeave.getStartTime(),
//				applicationForLeave.getEndTime(),applicationForLeave.getApplicatdPerson(),
//				applicationForLeave.getCertificatedPerson(),
//				applicationForLeave.isReportBack(),applicationForLeave.getReportBackTime());
//	}



//	@Override
//	public List<ApplicationForLeave> applicationForLeaveFindById(int applicatedPerson) {
//		// TODO Auto-generated method stub
//		String sql = "select * from application_for_leave where applicated_person = ?";
//		return jdbcTemplate.query(sql, new Object[] {applicatedPerson},new ApplicationForLeaveRowMapper() {
//			
//		});
//	}
}
