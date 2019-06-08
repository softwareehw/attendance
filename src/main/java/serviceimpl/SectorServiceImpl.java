package serviceimpl;

import java.util.List;

import org.json.JSONArray;
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
		if(l.isEmpty()) return null;
		else return new JSONArray(l).toString();
	}

}
