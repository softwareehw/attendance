package service;

import org.springframework.stereotype.Service;

import bean.WorkArrangement;

@Service
public interface WorkArrangementService {
	
	/**
	 * 添加一个工作安排
	 * @return
	 */
	public String AddWorkArrangement(WorkArrangement workarrangement);
	
	/**
	 * 查看工作班次
	 * @return
	 */
	public String findWorkArrangementByEId(int employeeId);

}
