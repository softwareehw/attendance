package dao;

import java.util.List;

import bean.Sector;

public interface SectorDao {

	public List<Sector> getAllSector();	
	
	public List<Sector> FindSectorById(int i);
	
	/**
	 * @author ENNIE
	 * @param s
	 * @return 1 代表成功
	 * 增加一个sector
	 */
	public int AddSector(Sector s);
	
	/**
	 * 修改部门信息
	 * @param s
	 * @return
	 */
	public int ModifySector(Sector s);
	
	/**
	 * 删除一个sector
	 * @param s
	 * @return
	 */
	public int DeleteSector(int sectorId);
	
	
}
