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
	public int addApplicationForLeave (ApplicationForLeave applicationForLeave);
	
	/**
	 * 查找
	 *@param    请假员工ID
	 *@return   所有请假表
	 *
	 */
	public List<ApplicationForLeave> applicationForLeaveFindById (int applicatedPerson);
	
	/**
	 *@param  员工ID
	 *@return 请假申请表
	 *
	 */
	
	public int applicateLeave(int Employee);
	
	public List<ApplicationForLeave> findAllLeavePerson(Employee master);
	
	
}
