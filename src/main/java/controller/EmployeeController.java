package controller;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import bean.ApplicationForEW;
import bean.ApplicationForLeave;
import bean.AttendanceRecord;
import bean.Employee;
import face.search.FaceInteraction;
import face.search.FaceSearch;
import service.EmployeeService;

//部署到服务器上的时候请一定使用 @CrossOrigin(origins = "http://39.105.38.34", maxAge = 3600,allowCredentials="true") 才能和前端正常交互
@CrossOrigin(origins = "http://39.105.38.34", maxAge = 3600,allowCredentials="true")
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;
	
//	@RequestMapping("/add")
//	public int addAppicateForEW(@RequestBody ApplicationForEW e) {
//		return employeeService.applicateEW(e);
//		
//	}
	
	@RequestMapping("/appEWfind/{applicatedPerson}")
	public List<ApplicationForEW> findByIdApplicationForEW(@PathVariable  int applicatedPerson) {
		return employeeService.FindById(applicatedPerson);
		
	}
	
	@RequestMapping("/findall")
	public List<ApplicationForEW> findAllApplicationForEW(){
		logger.info("从数据库读取ApplicationForEW信息");
		return employeeService.findAll();
	}
	
	@RequestMapping("/delete")
	public int deleteApplicationForEW(HttpServletRequest request,@RequestParam("applicatedId") int applicatedId) {
		logger.info("删除数据");
		return employeeService.deleteApplication(applicatedId);
	}
	
	@RequestMapping(value="/{employeeId}",method=RequestMethod.PUT)
	public String updateEmployee( @PathVariable Employee e){
		logger.info("修改员工信息");
		JSONObject ans=new JSONObject();
		ans.put("state", 1);
		if(employeeService.updateEmployee(e)==1) {
			return ans.toString();
		}
		else {
			ans.put("error_message","不存在这个员工");
			ans.put("state", 0);
			return ans.toString();
			
		}
    }
	
	/**
	 *@param     员工id
	 *@return    员工信息
	 *
	 */
	@RequestMapping("/{employeeId}")
    public String getEmployeeById(@PathVariable int employeeId){
		logger.info("从数据库中根据员工id="+employeeId+",读取Employee信息");
		JSONObject ans=new JSONObject();
		ans.put("state", 0);
		if(employeeService.employeeFindById(employeeId).isEmpty()) {
			ans.put("error_message","不存在这个员工");
			return ans.toString();
		}
		else {
			List<Employee> employee = employeeService.employeeFindById(employeeId);
			JSONArray jay = new JSONArray(employee);
			return jay.toString();
			
			
		}
    	
    }
	@RequestMapping("/sectors/{sectorId}")
    public String employeeInfoBySectorId(@PathVariable int sectorId){
		logger.info("从数据库中根据sectorId="+sectorId+",读取员工信息");
    	return employeeService.employeeInfoBySectorId(sectorId);
    }
	
	
	@RequestMapping("/attendanceinfo/{userId}")
    public List<AttendanceRecord> getAttendanceById(@PathVariable int userId){
		logger.info("从数据库中根据员工id="+userId+",读取attendance_record信息");
    	return employeeService.attendanceFindById(userId);
    }
	
	@RequestMapping("/applicationForLeaveinfo/{applicaedPerson}")
    public List<ApplicationForLeave> getApplicationForLeaveById(@PathVariable int applicaedPerson){
		logger.info("从数据库中根据员工id="+applicaedPerson+",读取applicationForLeave信息");
    	return employeeService.applicationForLeaveFindById(applicaedPerson);
    }
	
	@RequestMapping("/employeeAdd")
    public int addEmployee(@RequestBody Employee employee){
		logger.info("向数据库添加员工");
    	return employeeService.addEmployee(employee);
    }
	
	@RequestMapping("/deleteemployee/{employeeId}")
    public int deleteEmployeeById(@PathVariable int employeeId){
		logger.info("从数据库中根据员工id="+employeeId+",删除相应Employee信息");
    	return employeeService.deleteEmployee(employeeId);
    }
	
	@RequestMapping("/applicationForLeave")
	public int addApplicationForLeave(@RequestBody ApplicationForLeave applicationForLeave){
		logger.info("向数据库添加请假表");
    	return employeeService.addApplicationForLeave(applicationForLeave);
    }
	/**
	 *@param     一张图片
	 *@return   成功：user_id,失败：0
	 *
	 */
	//上班打卡识别
//	@PostMapping(value="/ai/photo/identify",consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
//	public String identifyP(@RequestParam("image") MultipartFile image) throws Exception{
//		
//		
//			logger.info("接受图片");
//			logger.info("开始识别");
//			//return FaceSearch.search(path);
//			String user_id = FaceSearch.search(image);
//			JSONObject ans=new JSONObject();
//			ans.put("state", 0);
//			
//			if(!user_id.equals("false")) {
//				int userId = Integer.parseInt(user_id);
//				return employeeService.attendance(userId);
//			}
//			else {
//				ans.put("error_message", "识别失败，不匹配");
//				return ans.toString();
//			}
//			
//	}
	
	
//	/**
//	 *@param    一张图片
//	 *@return
//	 *
//	 */
//	//下班打卡识别
//	@PostMapping(value="/ai/photo/identifydown",consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
//	public int identifyPDown(@RequestParam("image") MultipartFile image) throws Exception{
//		try {
//			logger.info("接受图片");
//			//保存文件
//			
//			logger.info("开始识别");
//			//return FaceSearch.search(path);
//			String user_id = FaceSearch.search(image);
//			
//			if(!user_id.equals("false")) {
//				int userId = Integer.parseInt(user_id);
//				return employeeService.attendanceDown(userId);
//			}
//		}
//		catch (Exception e){
//			e.printStackTrace();
//			return 0;
//		}
//		return 0;
//	}
	
	//录入照片入库
//	@PostMapping(value="/ai/{id}/photo/add" , consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
//	public boolean add(@RequestParam("image") MultipartFile image,@PathVariable int id) throws Exception {
//		try {
//			logger.info("接受图片");
//			//保存文件
//			FileOutputStream fos=new FileOutputStream("target/"+image.getOriginalFilename());
//			IOUtils.copy(image.getInputStream(), fos);
//			fos.close();
//			String path="target/"+image.getOriginalFilename();
//			//录入图片
//			logger.info("录入图片");
//			return FaceInteraction.add(path, id);
//		}
//		catch (Exception e){
//			e.printStackTrace();
//			return false;
//		}
//	}
	
	
	//人脸更新
	@PostMapping(value="/ai/{id}/photo/update" , consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public boolean update(@RequestParam("image") MultipartFile image,@PathVariable int id) throws Exception {
		try {
			logger.info("接受图片");
			//保存文件
			FileOutputStream fos=new FileOutputStream("target/"+image.getOriginalFilename());
			IOUtils.copy(image.getInputStream(), fos);
			fos.close();
			String path="target/"+image.getOriginalFilename();
			//更新图片
			logger.info("更新图片");
			return FaceInteraction.update(path, id);
		}
		catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	//这应该是经理有的权限 暂时先放在这了
	//获取所有库中用户id
	@GetMapping(value="/ai/getAllUsers")
	public List<String> getAll(){
		List<String> ans=new ArrayList<String>();
		ans=FaceInteraction.getUsers();
		return ans;
	}
	
	//删除库中某一用户
	@GetMapping(value="/ai/{id}/photo/delete")
	public boolean delete(@PathVariable int id) {
		return FaceInteraction.delete(id);
	}
	
	
}
