package serviceimpl;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bean.Alert;
import dao.AlertDao;
import service.AlertService;

@Service
public class AlertServiceImpl implements AlertService {

	
	@Autowired 
	private AlertDao alertDao;
	@Override
	public String findAlert(int employeeId) {
		// TODO Auto-generated method stub
		
		List<Alert> list = alertDao.findAlert(employeeId);
		
		JSONArray json = new JSONArray(list);
		alertDao.deleteAlert(employeeId);
		return json.toString();
	}

}
