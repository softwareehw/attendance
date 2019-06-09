package controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bean.ApplicationForLeave;
import bean.Employee;
import bean.Manager;
import bean.Sector;
import service.ManagerService;
//部署到服务器上的时候请一定使用 @CrossOrigin(origins = "http://39.105.38.34", maxAge = 3600,allowCredentials="true") 才能和前端正常交互
@CrossOrigin(origins = {"http://39.105.38.34","172.22.245.211"}, maxAge = 3600,allowCredentials="true")
@RestController
@RequestMapping("/manager")
public class ManagerController {

    private static final Logger logger = LoggerFactory.getLogger(ManagerController.class);

    @Autowired
    private ManagerService managerService;
    
    @RequestMapping("/test")
    public String Test(){
    	return "you are manager";
    }
    
    //获取所有经理的的信息
    @RequestMapping ("/list")
    public List<Manager> getStus(String id){
        logger.info("从数据库读取Manager信息");
        return managerService.getList();
    }
    
    @RequestMapping ("/applicationForLeave/list")
    public List<ApplicationForLeave> getApplicationForLeaveAll(){
        logger.info("从数据库读取所有请假信息");
        return managerService.applicationForLeaveFindAll();
    }
    
    //根据部门id获取该部门的所有员工的信息
    @RequestMapping("/list/{id}")
    public List<Employee> getEmployeeBySId(@PathVariable int id){
		logger.info("从数据库中根据部门id="+id+",读取Employee信息");
    	return managerService.getListBySId(id);
    }
    
    //根据员工id任免员工为部门主管 判断是否已为主管由前端控制 在后端不再验证其是否为主管
    @RequestMapping("/{id}/isMaster/{isM}")
    public Boolean setIsMaster(@PathVariable int id,@PathVariable boolean isM) {
		if(isM) {
			logger.info("设置员工"+id+"为部门主管");
		}
		else {
			logger.info("设置员工"+id+"不再为部门主管");
		}
		
    	return managerService.setIsMasterById(id,isM);
    }
    
    //经理发布全员加班
//    @RequestMapping("/list/{id}")
//    @Post
    
    //修改员工信息
    @PostMapping("/update/employee")
    public String modifyEm(@RequestBody Employee e) {
    	managerService.modifyEm(e);
    	return "Success!";

    }
    
    //获取所有部门的信息
    @PostMapping("/sectorAll")
    public List<Sector> getAllSector(){
    	return managerService.getAllSector();
    	
    }
    
    //获取所有员工
	@RequestMapping("/employeeAll")
	public int employeeAll(){
		return 20;
	}
    
}
 