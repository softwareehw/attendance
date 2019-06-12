package service;

import java.util.List;

import bean.ApplicationForLeave;
import bean.Employee;

public interface ApplicationForLeaveService {

	/**
	 *@param   请假类
	 *@return  修改行数
	 *
	 */
	public String addApplicationForLeave (ApplicationForLeave applicationForLeave);
	
	/**
	 * 查找
	 *@param    请假员工ID
	 *@return   所有请假表
	 *
	 */
	public String findApplicationForLeaveById (int applicatedPerson);
	

	
	/**
	 * 找到该部门所有未批准的请假记录
	 */
	public String findUnratifiedApplicationForleaveBySectorId(int sectorId);
	
	/**
	 * 员工销假
	 */
	public String CancelLeave(int leaveId);
}
