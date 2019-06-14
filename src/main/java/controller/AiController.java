package controller;

import java.io.FileOutputStream;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import face.search.FaceInteraction;
import face.search.FaceSearch;
import service.EmployeeService;

//部署到服务器上的时候请一定使用 @CrossOrigin(origins = "http://39.105.38.34", maxAge = 3600,allowCredentials="true") 才能和前端正常交互
@CrossOrigin(origins = "http://39.105.38.34", maxAge = 3600,allowCredentials="true")
@RestController
@RequestMapping("/api/v1/ai")
public class AiController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(value="attendance",method=RequestMethod.POST)
	public String Attend(){
		return null;
	}
	
	
	/**
	 *@param     一张图片
	 *@return   成功：user_id,失败：0
	 *
	 */
//	//上班打卡识别
//	@PostMapping(value="/attendance",consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
//	public String identifyP(@RequestParam("image") MultipartFile image) throws Exception{
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
	
	//删除库中某一用户
	@DeleteMapping(value="photo/delete/{id}")
	public boolean delete(@PathVariable int id) {
		return FaceInteraction.delete(id);
	}
	
	//录入照片

	@PostMapping(value="/photos/employees" , consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public String add(@RequestParam("image") MultipartFile image,@RequestParam("employeeId") int employeeId) throws Exception {
		try {
			logger.info("接受图片");
			//录入图片
			logger.info("录入图片");
			
			return FaceInteraction.add(image, employeeId);
		}
		catch (Exception e){
			e.printStackTrace();
			JSONObject ans = null;
			ans.put("state", "0");
        	ans.put("error_meaasge", "识别失败，请重新上传");
            return ans.toString();
			
		}
	}
	

}
