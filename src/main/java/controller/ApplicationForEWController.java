package controller;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bean.ApplicationForEW;
import bean.Employee;
import service.ApplicationForEWService;
import service.EmployeeService;
import service.ManagerService;

//部署到服务器上的时候请一定使用 @CrossOrigin(origins = "http://39.105.38.34", maxAge = 3600,allowCredentials="true") 才能和前端正常交互
@CrossOrigin(origins = "*", maxAge = 3600,allowCredentials="true")
@RestController
@RequestMapping("/api/v1/EWs")
public class ApplicationForEWController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private ManagerService managerService;
	@Autowired

	private ApplicationForEWService applicationForEWService;

	
	//申请加班
	@RequestMapping("/employees/{employeeId}")
	public String addAppicateForEW(@RequestBody ApplicationForEW e) {
		return employeeService.applicateEW(e);
		
	}
	
	//获取某部门所有未审核加班申请
	@GetMapping("uncheck/sectors/{sectorId}")
	public String getUncheckApplicationForEW(@PathVariable int sectorId) {
		return managerService.getUncheckApplicationForEW(sectorId);
	}
	
	//查看某员工全部加班申请
	@GetMapping("employees/{employeeId}")
	public String getEmployeeInfoApplicationForEW(@PathVariable int employeeId) {
		return managerService.getEmployeeInfoApplicationForEW(employeeId);
	}
	
	//修改加班申请
	@RequestMapping(value="{applicatedId}",method=RequestMethod.GET)
	public String updateEmployeeInfoApplicationForEW(HttpServletRequest request,@PathVariable int applicatedId) {
		return employeeService.updateEmployeeInfoApplicationForEW(applicatedId);
	}
	
	//发布全员加班
	@RequestMapping("")
	public String addAllApplicationForEW(@RequestBody ApplicationForEW date) {
		return managerService.announceEW(date);
	}
	
	
	@RequestMapping(value="/ratify",method=RequestMethod.POST)
	public String RatifyEW(@RequestBody ApplicationForEW applicationForEW) {
		return applicationForEWService.RatifyEW(applicationForEW);
	}
	
	@RequestMapping(value="/applicateEWNum/{sectorId}",method=RequestMethod.GET)
	public String applicatedNumberBySectorId(@PathVariable int sectorId) {
		return applicationForEWService.applicatedNumberBySectorId(sectorId);
	}
	
	@RequestMapping(value="/applicateEWNumAll",method=RequestMethod.GET)
	public String applicatedNumberAll() {
		return applicationForEWService.applicatedNumberAll();
	}
}
