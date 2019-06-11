package dao;

import java.util.List;

import bean.ApplicationForLeave;

public interface ApplicationForLeaveDao {

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
	 *@param  员工ID
	 *@return 一张请假表，同时数据库添加一张请假表
	 *
	 */
	
	
	public List<ApplicationForLeave> applicationForLeaveFindAll();
	
	/**
	 * 查找本部门的请假员工
	 * @return
	 */
	public List<ApplicationForLeave> findLeavePersonInMyDepartment(int departmentId);
	
	//根据部门id查处所有未审核的请假表
	
	public List<ApplicationForLeave> findApplicationForleaveBySectorId(int sectorId);
}
