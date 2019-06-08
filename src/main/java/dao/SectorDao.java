package dao;

import java.util.List;

import bean.Sector;

public interface SectorDao {

	public List<Sector> getAllSector();	
	
	public List<Sector> FindSectorById(int i);
}
