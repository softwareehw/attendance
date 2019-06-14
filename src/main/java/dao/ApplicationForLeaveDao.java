package dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import bean.ApplicationForLeave;

@Repository
public interface ApplicationForLeaveDao {

	/**
	 * 添加一个请假申请
	 */
	public int addApplicationForLeave (ApplicationForLeave applicationForLeave);
	

	/**
	 * 根据员工id查找请假记录
	 */
	public List<ApplicationForLeave> findApplicationForLeaveById (int applicatedPerson);
	
	/**
	 * 查找所有的请假记录
	 */
	public List<ApplicationForLeave> applicationForLeaveFindAll();
	
	
	/**
	 * 查找本部门的请假员工
	 */
	public List<ApplicationForLeave> findLeavePersonInMyDepartment(int departmentId);
	
	
	/**
	 * 根据部门id查处所有未审核的请假表
	 */
	public List<ApplicationForLeave> findUnratifiedApplicationForleaveBySectorId(int sectorId);
	
	/**
	 * 员工销假
	 */
	public int CancelLeaveByLeaveId(int leaveId);
	
	/**
	 * 根据leaveId查找请假记录
	 */
	public List<ApplicationForLeave> findLeaveByLId(int leaveId);
	
	public int CancelCancelLeaveByLeaveId(int leaveId);
	
	public int RatifyLeave(ApplicationForLeave applicationForLeave);

    //获取当天每个部门请假人数
	public int applicationNumberBySectorId(int sectorId);

    //获取当天公司请假人数
	public int applicationNumberAll();
	
	
}
