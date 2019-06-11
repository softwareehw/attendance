package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import service.WorkArrangementService;

//@CrossOrigin(origins = "http://39.105.38.34", maxAge = 3600,allowCredentials="true")
@RestController
@RequestMapping("/api/v1/workA")
public class WorkArrangementController {
	
	@Autowired
	WorkArrangementService workArrangementService;
	
	
	@RequestMapping(value="/employees/{employeeId}",method=RequestMethod.GET)
	public String findWorkArrangementByEId(@PathVariable int employeeId){
		String ans = workArrangementService.findWorkArrangementByEId(employeeId);
		return ans;
	}

}
