package service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import bean.Sector;

@Service
public interface SectorService {

	public Boolean setIsMasterById(int id, boolean isM);
	
	public String findAllSector();
	
	public String findSectorById(int sectorId);
	
	/**
	 * 增加一个部门。成功返回1，失败返回0
	 * @param s
	 * @return
	 */
	public String AddSector(Sector s);
	
	public String ModifySector(Sector s);
	
	public String DeleteSector(int sectorId);
	
	public String findAllEmployeeBySectorId( int sectorId);
}
