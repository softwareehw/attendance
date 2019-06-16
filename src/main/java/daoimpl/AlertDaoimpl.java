package daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.*;

import dao.AlertDao;
import mapper.AlertRowMapper;
import mapper.EmployeeRowMapper;
import bean.Alert;
import bean.Employee;

@Repository
public class AlertDaoimpl implements AlertDao {

	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	//提醒经理
	@Override
	public int addAlert(int employeeId,int state) {
		// TODO Auto-generated method stub
		String sql = "insert into alert(employee_id,state) value(?,?)";
		String sql1 = "select * from employee where employee_id = ?";
		String sql2 = "select * from employee where sector_id = ? and is_manager = 1";
		
		List<Employee> list = jdbcTemplate.query(sql1, new Object[] {employeeId},new EmployeeRowMapper(){
			
		});
		int sectorId = list.get(0).getSectorId();
		
		List<Employee> list2 = jdbcTemplate.query(sql2, new Object[] {sectorId},new EmployeeRowMapper(){
			
		});
		int employeeid = list2.get(0).getEmployeeId();
		
		return jdbcTemplate.update(sql, new Object[] {employeeid,state});
	}

	@Override
	public int deleteAlert(int employeeId) {
		// TODO Auto-generated method stub
		
		String sql = "delete  from alert where employee_id = ?";
		
		return jdbcTemplate.update(sql,new Object[] {employeeId});
	}

	@Override
	public List<Alert> findAlert(int employeeId) {
		// TODO Auto-generated method stub
		String sql = "select * from alert where employee_id = ?";
		List<Alert> list = jdbcTemplate.query(sql, new Object[] {employeeId},new AlertRowMapper() {
			
		});
		
		return list;
	}

	//提醒员工
	@Override
	public int addAlertEmployee(int employeeId, int state) {
		// TODO Auto-generated method stub
		String sql = "insert into alert(employee_id,state) value(?,?)";
		
		return jdbcTemplate.update(sql, new Object[] {employeeId,state});
	}

}
