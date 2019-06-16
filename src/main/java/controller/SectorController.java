package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bean.Sector;
import service.SectorService;

//部署到服务器上的时候请一定使用 @CrossOrigin(origins = "http://39.105.38.34", maxAge = 3600,allowCredentials="true") 才能和前端正常交互
@CrossOrigin(origins = "http://39.105.38.34", maxAge = 3600,allowCredentials="true")
@RestController
@RequestMapping("/api/v1/sectors")
public class SectorController {
	
	@Autowired
	SectorService sectorService;
	
	
	/**
	 * 获取所有的部门信息
	 * @return
	 */
	@RequestMapping(value="",method=RequestMethod.GET)
	public String LookForAllSectors(){
		
		return sectorService.findAllSector();
	}
	
	/**
	 * 获取特定的部门信息
	 * @param sectorId
	 * @return
	 */
	@RequestMapping(value="/{sectorId}",method=RequestMethod.GET)
    public String findSpecificSector(@PathVariable int sectorId){
		
		return sectorService.findSectorById(sectorId);
	}
	
	/**
	 * 添加一个sector
	 * @param s
	 * @return
	 */
	@RequestMapping(value="",method=RequestMethod.POST)
    public String AddSector(@RequestBody Sector s){
		return sectorService.AddSector(s);
	}
	
	
	/**
	 * 修改一个sector
	 * @param sectorId
	 * @param s
	 * @return
	 */
	@RequestMapping(value="/{sectorId}",method=RequestMethod.PUT)
    public String ModifySector(@RequestBody Sector s){
		
		return sectorService.ModifySector(s);
	}
	
	@RequestMapping(value="/{sectorId}",method=RequestMethod.DELETE)
    public String deleteSector(@PathVariable int sectorId){
		
		return sectorService.DeleteSector(sectorId);
	}
	
	@GetMapping("/all/{sectorId}")
	public String findAllEmployeeBySectorId(@PathVariable int sectorId) {
		return sectorService.findAllEmployeeBySectorId(sectorId);
	}
	
	@RequestMapping(value="/peoplenum/{sectorId}",method=RequestMethod.GET)
    public String findSectorPeopleNumBySectorId(@PathVariable int sectorId){
		return sectorService.findPeopleNumInSector(sectorId);
	}
	
	@RequestMapping(value="/peoplenum/all",method=RequestMethod.GET)
    public String findSectorPeopleNum(){
		return sectorService.findPeopleNumInConpany();
	}
	
	
}
