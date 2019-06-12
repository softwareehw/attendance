package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.EmployeeService;

@CrossOrigin(origins = "http://39.105.38.34", maxAge = 3600,allowCredentials="true")
@RestController
@RequestMapping("/api/v1/attendance")
public class AttendanceRecordController {
 
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping("/{sectorId}")
	public String findAttendanceRecordBySectorId(@PathVariable int sectorId) {
		
		return employeeService.findAttendanceRecordBySectorId(sectorId);
	}
	
}
