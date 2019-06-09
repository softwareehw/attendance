package service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

import bean.ApplicationForEW;
import bean.ApplicationForLeave;
import bean.Employee;
import bean.Manager;
import bean.Sector;

public interface ManagerService{
	//查看所有经理（理论上只有一个）的信息
	public List<Manager> getList();
	//根据部门ID查看本部门所有员工的信息
	public List<Employee> getListBySId(int id);
	//设置某个员工是否为部门主管
	public Boolean setIsMasterById(int id, boolean isM);
	
	//发布全员加班动员
	public Boolean announceEW(Calendar start,Calendar end);
	
	//存在问题 手机号码 一旦超过范围使用L我就插不进去了
	//修改某个员工的信息
	public Boolean modifyEm(Employee e);
	public List<Sector> getAllSector();
	
	//获取所有部门的信息
	
	public List<Manager> findManagerByUserId(int userId);
	
	/**
	 *@param
	 *@return  查看全部请假表
	 *
	 */
	public List<ApplicationForLeave> applicationForLeaveFindAll();
	
	/**
	 *@param   部门id
	 *@return  本部门未审核请假加班表
	 *
	 */
	public String getUncheckApplicationForEW(int sectorId);
	
	/**
	 *@param   员工id
	 *@return  员工全部加班申请
	 *
	 */
	public String getEmployeeInfoApplicationForEW(int employeeId);
	
	/**      
	 *      发布全员加班
	 *@param
	 *@return 
	 *
	 */
	public String addAllApplicationForEW(ApplicationForEW date);

}
