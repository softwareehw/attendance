package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import bean.Employee;
import bean.Manager;
import bean.User;
import dao.EmployeeDao;
import dao.ManagerDao;
import dao.UserDao;
import service.EmployeeService;

//部署到服务器上的时候请一定使用 @CrossOrigin(origins = "http://39.105.38.34", maxAge = 3600,allowCredentials="true") 才能和前端正常交互
@CrossOrigin(origins = "http://39.105.38.34", maxAge = 3600,allowCredentials="true")
@RestController
@RequestMapping("/api/v1/login")
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private ManagerDao managerDao;
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private EmployeeService employeeService;
	
	
	


	
	@PostMapping("")
	public String judgeDegree(@RequestBody User user) {
		return employeeService.judgeDegree(user);
	}
//    public String login(@RequestBody User user ) {
//		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
//		HttpSession session = request.getSession();
//
//		JSONObject ans=new JSONObject();
//		ans.put("status", 0);
//		
//    	if(userDao.searchUserByIdAndPasswd(user).isEmpty()) return ans.toString();
//    	else {
//    		User loginuser = userDao.searchUserByIdAndPasswd(user).get(0);
//    		
//    		session.setAttribute("user", loginuser);
//        	
//       
//        	if(employeeDao.findEmployeeByUserId(loginuser.getId()).isEmpty()) {
//        		Manager manager = managerDao.findManagerByUserId(loginuser.getId()).get(0);
//        		session.setAttribute("manager", manager);
//        		ans.put("Manager", new JSONObject(manager));
//        		ans.put("status", 1);
//        	}else {
//        		Employee employee = employeeDao.findEmployeeByUserId(loginuser.getId()).get(0);
//        		if(employee.isManager()!=false){
//            		session.setAttribute("master", employee);
//            		ans.put("Master", new JSONObject(employee));
//            		ans.put("status", 2);
//            	}else{
//            		session.setAttribute("employee", employee);
//            		ans.put("employee", new JSONObject(employee));
//            		ans.put("status", 3);
//            	}
//        	}
//        		
//        }	
//        return ans.toString();
//    }
	
	
	@RequestMapping("/error")
	public String error() {
		return "login error";
	}
	

}
