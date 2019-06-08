package service;

import java.util.List;

import org.springframework.stereotype.Service;

import bean.Sector;

@Service
public interface SectorService {

	public Boolean setIsMasterById(int id, boolean isM);
	
	public String findAllSector();
	
	public String findSectorById(int sectorId);
}
