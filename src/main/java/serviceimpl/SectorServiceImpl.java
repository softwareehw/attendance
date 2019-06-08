package serviceimpl;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bean.Sector;
import dao.SectorDao;
import service.SectorService;

@Service
public class SectorServiceImpl implements SectorService {
	
	@Autowired
	SectorDao sectorDao;

	@Override
	public Boolean setIsMasterById(int id, boolean isM) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findAllSector() {
		List<Sector> l = sectorDao.getAllSector();
		if(l.isEmpty()){
			JSONObject ans= new JSONObject();
			ans.put("state", 0);
			ans.put("error_message", "boooooooom");
			return ans.toString();
		}
		else return new JSONArray(l).toString();
	}

	@Override
	public String findSectorById(int sectorId) {
		List<Sector> l=sectorDao.FindSectorById(sectorId);
		if(l.isEmpty()){
			JSONObject ans= new JSONObject();
			ans.put("state", 0);
			ans.put("error_message", "没有该部门");
			return ans.toString();
		}
		else return new JSONArray(l).toString();
	}

}
