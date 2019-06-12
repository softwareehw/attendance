package dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import bean.WorkArrangement;

@Repository
public interface WorkArrangementDao {
	
	/**
	 * 增加一个工作安排
	 * @param wa
	 * @return
	 */
	public int AddWorkArrangement(WorkArrangement wa); 
	
	/**
	 * 查看某员工的工作安排
	 * @param employeeId
	 * @return
	 */
	public List<WorkArrangement> findWorkArrangementByEId(int employeeId);
	
	public int deleteByWAId(int workArrangementId);
	
	public int ModifyByWAId(WorkArrangement workArrangement);

}
