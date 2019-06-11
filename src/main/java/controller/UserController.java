package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import bean.Employee;
import bean.Manager;
import bean.User;
import dao.EmployeeDao;
import dao.ManagerDao;
import dao.UserDao;
import service.UserService;

//部署到服务器上的时候请一定使用 @CrossOrigin(origins = "http://39.105.38.34", maxAge = 3600,allowCredentials="true") 才能和前端正常交互
//@CrossOrigin(origins = "http://39.105.38.34", maxAge = 3600,allowCredentials="true")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	@Autowired
	UserService userService;
	UserDao userDao;
	EmployeeDao employeeDao;
	ManagerDao managerDao;
		
	
	/**
	 * @author ENNIE
	 * @param userId
	 * @return 成功时status=1,失败时status=0
	 */
	@RequestMapping(value="/{userId}",method=RequestMethod.PUT)
	public String ModifyEmployee(@RequestBody User user){
	
	String ans = userService.ModifyUser(user);
		//修改账号信息
		return ans;
	}
	
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@RequestBody User user ) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();

		JSONObject ans=new JSONObject();
		ans.put("status", 0);
		ans.put("error_message", "账号密码错误");
		
    	if(userDao.searchUserByIdAndPasswd(user).isEmpty()) return ans.toString();
    	else {
    		User loginuser = userDao.searchUserByIdAndPasswd(user).get(0);
    		
    		session.setAttribute("user", loginuser);
        	
       
        	if(employeeDao.findEmployeeByUserId(loginuser.getId()).isEmpty()) {
        		Manager manager = managerDao.findManagerByUserId(loginuser.getId()).get(0);
        		session.setAttribute("manager", manager);
        		ans.put("Manager", new JSONObject(manager));
        		ans.put("status", 1);
        	}else {
        		Employee employee = employeeDao.findEmployeeByUserId(loginuser.getId()).get(0);
        		if(employee.isManager()!=false){
            		session.setAttribute("master", employee);
            		ans.put("Master", new JSONObject(employee));
            		ans.put("status", 2);
            	}else{
            		session.setAttribute("employee", employee);
            		ans.put("employee", new JSONObject(employee));
            		ans.put("status", 3);
            	}
        	}
        		
        }	
        return ans.toString();
    }
	
	

}
