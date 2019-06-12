package service;

import java.util.List;

import bean.ApplicationForEW;

public interface ApplicationForEWService {

	public int applicateEW(ApplicationForEW applica);
	
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
	 *@param   部门id
	 *@return  本部门未审核请假加班表
	 *
	 */
	public String getUncheckApplicationForEW(int sectorId);
	
	
	
	public String RatifyEW(ApplicationForEW applicationForEW);
}
