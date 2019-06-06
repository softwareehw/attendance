package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import bean.Employee;
import bean.Master;

/**
 *@author 29226
 *@注释 : 实现RowMapper接口，返回Employee对象
*/
public class EmployeeRowMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
	    int employeeId = rs.getInt("employee_id");
	    int sectorId = rs.getInt("sector_id");
		boolean isManager = rs.getBoolean("is_manager"); //1 is manager,0 isn't 
		//个人信息
		String name = rs.getString("name");
		int age = rs.getInt("age");
		float salary = rs.getFloat("salary");
		boolean sex = rs.getBoolean("sex"); //1 is man,0 is woman
		long phoneNumber = rs.getLong("phone_number");
		Date enrollTime = rs.getDate("enroll_time");
		
		Employee employee = new Employee();
		employee.setEmployeeId(employeeId);
		employee.setAge(age);
		employee.setManager(isManager);
		employee.setName(name);
		employee.setPhoneNumber(phoneNumber);
		employee.setSalary(salary);
		employee.setSex(sex);
		employee.setSectorId(sectorId);
		employee.setEnrollTime(enrollTime);
		
		
		return employee;
	}

}
