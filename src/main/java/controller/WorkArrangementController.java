package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bean.WorkArrangement;
import service.WorkArrangementService;

//@CrossOrigin(origins = "http://39.105.38.34", maxAge = 3600,allowCredentials="true")
@RestController
@RequestMapping("/api/v1/workA")
public class WorkArrangementController {
	
	@Autowired
	WorkArrangementService workArrangementService;
	
	/**
	 * 返回特定员工的工作安排
	 * @param employeeId
	 * @return
	 */
	@RequestMapping(value="/employees/{employeeId}",method=RequestMethod.GET)
	public String findWorkArrangementByEId(@PathVariable int employeeId){
		String ans = workArrangementService.findWorkArrangementByEId(employeeId);
		return ans;
	}
	
	/**
	 * 增加指定员工的工作安排
	 * @param wa
	 * @return
	 */
	@RequestMapping(value="/employees/{employeeId}",method=RequestMethod.POST)
	public String AddWorkArrangementToEmployee(@RequestBody WorkArrangement wa ){
		String ans = workArrangementService.AddWorkArrangement(wa);
		return ans;
	}
	
	/**
	 * 增加指定部门的工作安排
	 * @param wa
	 * @param sectorId
	 * @return
	 */
	@RequestMapping(value="/sectors/{sectorId}",method=RequestMethod.POST)
	public String AddWorkArrangementInSector(@RequestBody WorkArrangement wa,@PathVariable int sectorId){
		String ans = workArrangementService.AddWAInSector(sectorId, wa);
		return ans;
	}
	
	/**
	 * 删除指定工作安排
	 */
	@RequestMapping(value="/{workArrangeId}",method=RequestMethod.DELETE)
	public String deleteWAById(@PathVariable int workArrangeId){
		String ans = workArrangementService.deleteByWAId(workArrangeId);
		return ans;
	}	
	
	/**
	 * 修改工作安排
	 */
	@RequestMapping(value="/{workArrangeId}",method=RequestMethod.PUT)
	public String modifyWAById(@RequestBody WorkArrangement workArrangement,@PathVariable int workArrangeId){
		String ans = workArrangementService.modifyByWAId(workArrangement);
		return ans;
	}		

}
