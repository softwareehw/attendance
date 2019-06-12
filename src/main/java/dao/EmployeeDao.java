package dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import bean.ApplicationForEW;
import bean.ApplicationForLeave;
import bean.AttendanceRecord;
import bean.Employee;
import bean.User;

@Repository
public interface EmployeeDao {

//	/**
//	 *@param  员工ID
//	 *@return 一张申请加班表，同时数据库添加一张申请加班表
//	 *
//	 */
//	public int addApplicateEW(ApplicationForEW applica);
	
//	/**
//	 *@param  员工ID
//	 *@return 一张请假表，同时数据库添加一张请假表
//	 *
//	 */
//	public int addApplicateLeave(int EmployeeId);
	
//	/**
//	 *@param
//	 *@return
//	 *
//	 */
//	public List<ApplicationForEW> findAll();
//	
//	/**
//	 *@param
//	 *@return
//	 *
//	 */
//	public int deleteApplicationForEW(int applicatedId);
//	
//	
//	/**
//	 *@param
//	 *@return
//	 *
//	 */
//	
//	public List<ApplicationForEW> findById(int applicatePerson);
	
	/**
	 *@param  员工ID
	 *@return 员工表
	 *
	 */
     
	public List<Employee> employeeFindById(int employeeId);
	
	/**
	 *@param
	 *@return
	 *
	 */
	
	
	/**
	 *@param  员工Id
	 *@return 修改行数
	 *
	 */
	
	public int deleteEmployee(int employeeId);
	
	/**
	 *@param   员工类
	 *@return  修改行数
	 *
	 */
	
	public int updateEmployee(Employee e);
	
//	/**
//	 *@param   员工Id
//	 *@return
//	 *
//	 */
//	
//	public int attendance (int userId);
//	
//	/**
//	 *@param   员工Id
//	 *@return
//	 *
//	 */
//	
//	public int attendanceDown (int userId);
	
	public Boolean setIsMasterById(int id, boolean isM);
	
	public Boolean modifyEm(Employee e);
	
	public List<Employee> getEmployeeBySId(int id);
	
//	/**
//	 *@param    员工id
//	 *@return
//	 *
//	 */
//	public List<AttendanceRecord> attendanceFindById (int userId);
	
	public List<Employee> findEmployeeByUserId(int userId);

	public int addEmployee(Employee employee, int id);
	
//	/**
//	 *@param   请假类
//	 *@return  修改行数
//	 *
//	 */
//	public int addApplicationForLeave (ApplicationForLeave applicationForLeave);
//	
//	/**
//	 *@param    请假员工ID
//	 *@return   所有请假表
//	 *
//	 */
//	
//	public List<ApplicationForLeave> applicationForLeaveFindById (int applicatedPerson);
	
	public String judgeDegree(User user);
	
}
