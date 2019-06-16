package serviceimpl;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bean.ApplicationForEW;
import dao.AlertDao;
import dao.ApplicationForEWDao;
import service.ApplicationForEWService;

@Service
public class ApplicationForEWServiceImpl implements ApplicationForEWService {
	
	@Autowired
	ApplicationForEWDao applicationForEWDao;
	@Autowired
	private AlertDao alertDao;

	@Override
	public int applicateEW(ApplicationForEW applica) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ApplicationForEW> FindById(int applicatedPerson) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ApplicationForEW> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteApplication(int applicatedId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getUncheckApplicationForEW(int sectorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String RatifyEW(ApplicationForEW applicationForEW) {
		JSONObject json = new JSONObject();
		int i = applicationForEWDao.RatifyEW(applicationForEW);
		if(i==1){
			json.put("state", 1);
			alertDao.addAlertEmployee(applicationForEW.getApplicatedId(), 2);
		}else{
			json.put("state", 0);
			json.put("error_message", "审核加班失败");
		}
		return json.toString();
	}

	@Override
	public String getEW(int applicatedId) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		
		List<ApplicationForEW> l = applicationForEWDao.updateUNEmployeeInfoApplicationForEW(applicatedId);
		JSONArray jay = new JSONArray(l);
		if(l.isEmpty()){
			json.put("state", 0);
			return json.toString();
		}else{
			
		
			return jay.toString();
		}
		
	}

	@Override
	public String applicatedNumberBySectorId(int sectorId) {
		// TODO Auto-generated method stub
		JSONObject ans = new JSONObject();
		int i = applicationForEWDao.applicatedNumberBySectorId(sectorId);
		ans.put("state", i);
		return ans.toString();
	}

	@Override
	public String applicatedNumberAll() {
		// TODO Auto-generated method stub
		JSONObject ans = new JSONObject();
		int i = applicationForEWDao.applicatedNumberAll();
		ans.put("state", i);
		return ans.toString();
		
	}
		
	}


