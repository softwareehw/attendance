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

//部署到服务器上的时候请一定使用 @CrossOrigin(origins = "http://39.105.38.34", maxAge = 3600,allowCredentials="true") 才能和前端正常交互
@CrossOrigin(origins = {"http://39.105.38.34","172.22.245.211"}, maxAge = 3600,allowCredentials="true")
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
	
	@RequestMapping("/test")
	public String Test(){
		return "you are master";
	}
	
	@RequestMapping("/findAllLeavePerson")
	public List<ApplicationForLeave> findAllLeavePerson(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		
		Employee master = (Employee) session.getAttribute("master");
		
		return applicationForLeaveService.findAllLeavePerson(master);
	}

}
