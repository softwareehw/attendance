package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import face.search.FaceInteraction;
import service.EmployeeService;
import service.ManagerService;

//部署到服务器上的时候请一定使用 @CrossOrigin(origins = "http://39.105.38.34", maxAge = 3600,allowCredentials="true") 才能和前端正常交互
@CrossOrigin(origins = "http://39.105.38.34", maxAge = 3600,allowCredentials="true")
@RestController
@RequestMapping("/api/v1/leave")
public class ApplicationForLeaveController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private ManagerService managerService;
	
	@GetMapping(value="/{sectorId}")
	public String findApplicationForleaveBySectorId(@PathVariable int sectorId){
		
		return managerService.findApplicationForleaveBySectorId(sectorId);
	}
}
