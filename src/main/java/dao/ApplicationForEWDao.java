package dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import bean.ApplicationForEW;

public interface ApplicationForEWDao {

	/**
	 *@param
	 *@return
	 *
	 */
	public List<ApplicationForEW> findAll();
	
	/**
	 *@param
	 *@return
	 *
	 */
	public int deleteApplicationForEW(int applicatedId);
	
	
	/**
	 *@param
	 *@return
	 *
	 */
	
	public List<ApplicationForEW> findById(int applicatePerson);
	
	/**
	 *@param  员工ID
	 *@return 一张申请加班表，同时数据库添加一张申请加班表
	 *
	 */
	public int addApplicateEW(ApplicationForEW applica);
	
	
	/**
	 * 发布全员加班
	 *@param
	 *@return
	 *
	 */
	public String announceEW(ApplicationForEW date);
	
	/**
	 *@param   部门ID
	 *@return  未审核申请加班
	 *
	 */
	
	public List<ApplicationForEW> getUncheckApplicationForEW( int sectorId);
	/**
	 *@param    员工ID
	 *@return   所有申请加班表
	 *
	 */
	
	public List<ApplicationForEW> getEmployeeInfoApplicationForEW(int employeeId);
	
	/**
	 *@param    员工id，日期
	 *@return   加班申请表
	 *
	 */
	public List<ApplicationForEW> updateEmployeeInfoApplicationForEW(int applicated);
	
	

}
