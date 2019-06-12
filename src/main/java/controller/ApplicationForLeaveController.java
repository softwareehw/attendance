package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bean.ApplicationForLeave;
import face.search.FaceInteraction;
import service.ApplicationForLeaveService;
import service.EmployeeService;
import service.ManagerService;

//部署到服务器上的时候请一定使用 @CrossOrigin(origins = "http://39.105.38.34", maxAge = 3600,allowCredentials="true") 才能和前端正常交互
//@CrossOrigin(origins = "http://39.105.38.34", maxAge = 3600,allowCredentials="true")
@RestController
@RequestMapping("/api/v1/leaves")
public class ApplicationForLeaveController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private ManagerService managerService;
	@Autowired
	private ApplicationForLeaveService applicationForLeaveService;
	
//	@RequestMapping(value="/{sectorId}",method=RequestMethod.POST)
//	public String findApplicationForleaveBySectorId(@PathVariable int sectorId){
//		
//		return managerService.findApplicationForleaveBySectorId(sectorId);
//	}
	
	/**
	 * 添加一个请假申请
	 */
	@RequestMapping(value="/{employeeId}",method=RequestMethod.POST)
	public String AddLeave(@RequestBody ApplicationForLeave applicationForLeave){
		
		return applicationForLeaveService.addApplicationForLeave(applicationForLeave);
	}
	
	/**
	 * 查看该员工所有请假申请
	 */
	@RequestMapping(value="/employees/{employeeId}",method=RequestMethod.GET)
	public String findLeaveByEId(@PathVariable int employeeId){
		return applicationForLeaveService.findApplicationForLeaveById(employeeId);
	}
	
	/**
	 * 查看所有等待审批的请假记录
	 */
	@RequestMapping(value="/uncheck/sectors/{sectorId}",method=RequestMethod.GET)
	public String findUnratifyLeave(@PathVariable int sectorId){
		return applicationForLeaveService.findUnratifiedApplicationForleaveBySectorId(sectorId);
	}
	
	/**
	 * 销假
	 */
	@RequestMapping(value="/cancel/{leaveId}",method=RequestMethod.GET)
	public String CancelLeave(@PathVariable int leaveId){
		return applicationForLeaveService.CancelLeave(leaveId);
	}
	
	
				
	
	
}
