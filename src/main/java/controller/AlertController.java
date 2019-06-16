package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.AlertService;

//@CrossOrigin(origins = "http://39.105.38.34", maxAge = 3600,allowCredentials="true")
@RestController
@RequestMapping("/api/v1/alert")
public class AlertController {

	@Autowired
	private AlertService alertService;
	
	@GetMapping("/find/{employeeId}")
	public String findAlert(@PathVariable int employeeId) {
		return alertService.findAlert(employeeId);
		
	}
}
