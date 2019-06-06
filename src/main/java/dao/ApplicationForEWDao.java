package dao;

import java.util.Calendar;
import java.util.List;

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
	
	public Boolean announceEW(Calendar start, Calendar end);
}
