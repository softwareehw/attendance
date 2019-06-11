package serviceimpl;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bean.WorkArrangement;
import dao.WorkArrangementDao;
import service.WorkArrangementService;

@Service
public class WorkArrangementServiceImpl implements WorkArrangementService {
	
	@Autowired 
	WorkArrangementDao workarrangementDao;

	@Override
	public String AddWorkArrangement(WorkArrangement workarrangement) {
		
		
		
		
		return null;
	}

	@Override
	public String findWorkArrangementByEId(int employeeId) {
		JSONObject json = new JSONObject();
		List<WorkArrangement> l = workarrangementDao.findWorkArrangementByEId(employeeId);
		if(!l.isEmpty()){
			JSONArray json1  =new JSONArray(l);
			return json1.toString();
		}else{
			json.put("error_message", "没有这个员工");
			json.put("state", "0");
			return json.toString();
		}
	}

}
