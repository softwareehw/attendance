package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import service.SectorService;

//部署到服务器上的时候请一定使用 @CrossOrigin(origins = "http://39.105.38.34", maxAge = 3600,allowCredentials="true") 才能和前端正常交互
@CrossOrigin(origins = {"http://39.105.38.34","172.22.245.211"}, maxAge = 3600,allowCredentials="true")
@RestController
@RequestMapping("/api/v1/sectors")
public class SectorController {
	
	@Autowired
	SectorService sectorService;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String LookForAllSectors(){
		
		return sectorService.findAllSector();
	}
	
	@RequestMapping(value="/{sectorId}",method=RequestMethod.GET)
    public String findSpecificSector(@PathVariable int sectorId){
		
		return sectorService.findSectorById(sectorId);
	}
}
