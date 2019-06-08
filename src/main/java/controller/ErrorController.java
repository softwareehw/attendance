package controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//部署到服务器上的时候请一定使用 @CrossOrigin(origins = "http://39.105.38.34", maxAge = 3600,allowCredentials="true") 才能和前端正常交互
@CrossOrigin(origins = "*", maxAge = 3600,allowCredentials="true")
@RestController
@RequestMapping("/error")
public class ErrorController {
	
	
	@RequestMapping("/notmaster")
	public String ErrorMaster(){
		return "you are not master";
	}
	
	@RequestMapping("/notmanager")
	public String ErrorManager(){
		return "you are not manager";
	}

}
