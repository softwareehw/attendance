package controller;

import org.springframework.web.bind.annotation.CrossOrigin;

//部署到服务器上的时候请一定使用 @CrossOrigin(origins = "http://39.105.38.34", maxAge = 3600,allowCredentials="true") 才能和前端正常交互
<<<<<<< HEAD
@CrossOrigin(origins = {"http://39.105.38.34","172.22.245.211"}, maxAge = 3600,allowCredentials="true")
=======
@CrossOrigin(origins = "http://39.105.38.34", maxAge = 3600,allowCredentials="true")
>>>>>>> master
public class CommonController {

}
