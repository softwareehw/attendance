package service;

import java.util.Date;
import java.util.List;

import bean.ApplicationForEW;
import bean.ApplicationForLeave;
import bean.AttendanceRecord;
import bean.Employee;
/**
 *@author 29226
 *@注释   员工接口
*/
public interface EmployeeService {
    /**
	 *@param  申请加班表中的属性
	 *@return 数据库修改行数
	 *
	 */
	public String applicateEW(ApplicationForEW applica);
	
	/**
	 *@param     申请加班号
	 *@return    申请加班表
	 *
	 */
	public List<ApplicationForEW> FindById(int applicatedPerson);
	
	
	/**
	 *@param    
	 *@return    所有申请加班表
	 *
	 */
	public List<ApplicationForEW> findAll();
	
	/**
	 *@param   申请加班号
	 *@return  数据库修改行数
	 *
	 */
	
	public int deleteApplication(int applicatedId);
	
	/**
	 *@param  员工ID
	 *@return 请假申请表
	 *
	 */
	
	public int applicateLeave(int Employee);
	
    /**
	 *@param  员工ID
	 *@return 员工信息表
	 *
	 */
	
	public List<Employee> employeeFindById(int employeeId);
	
	/**
	 *@param  员工类
	 *@return 修改行数
	 *
	 */
	
	public int addEmployee(Employee employee);
	
	/**
	 *@param
	 *@return
	 *
	 */
	
	public int deleteEmployee(int employeeId);
	
	/**
	 *@param   员工id
	 *@return
	 *
	 */
	
	public String attendance (int userId);
	
	/**
	 *@param      员工id
	 *@return
	 *
	 */
	public int attendanceDown (int userId);
	
	/**
	 *@param     员工id
	 *@return
	 *
	 */
	public List<AttendanceRecord> attendanceFindById (int userId);
	
	/**
	 *@param   请假类
	 *@return  修改行数
	 *
	 */
	public int addApplicationForLeave (ApplicationForLeave applicationForLeave);
	
	/**
	 *@param    请假员工ID
	 *@return   所有请假表
	 *
	 */
	
	public List<ApplicationForLeave> applicationForLeaveFindById (int applicatedPerson);
	
	/**
	 *@param
	 *@return
	 *
	 */
	
	
	/**
	 *@param    员工类
	 *@return   修改行数
	 *
	 */
    public int updateEmployee (Employee e);
    
    /**
	 *@param   sectorId
	 *@return  员工信息
	 * 
	 */
    
    public String employeeInfoBySectorId(int sectorId);
    
    /**
	 *@param     sectorID
	 *@return    未批准申请加班表
	 *
	 */
    public String getUncheckApplicationForEW(int sectorId);

    /**
	 *@param     申请人id
	 *@return    
	 *
	 */
	
	public String updateEmployeeInfoApplicationForEW( int applicatedId);
}
