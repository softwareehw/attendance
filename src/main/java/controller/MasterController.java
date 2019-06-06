package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import bean.ApplicationForLeave;
import bean.Employee;
import service.ApplicationForLeaveService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/master")
public class MasterController {
	
	@Autowired
	ApplicationForLeaveService applicationForLeaveService;
	
//	@RequestMapping("/certificatecancal")
//	public String certificateCancal(){
//		
//		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
//		HttpSession session = request.getSession();
//		
//		Master m=(Master)session.getAttribute("master");
//		
//		int num = masterService.批准销假;
//		
//		if(num==1) return "Successful certificate";
//		else return  "/error";
//	}
	
	
	
	
	@RequestMapping("/findAllLeavePerson")
	public List<ApplicationForLeave> findAllLeavePerson(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		
		Employee master = (Employee) session.getAttribute("master");
		
		return applicationForLeaveService.findAllLeavePerson(master);
	}

}
