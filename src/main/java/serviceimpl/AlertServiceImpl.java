package serviceimpl;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AlertDao;
import service.AlertService;

@Service
public class AlertServiceImpl implements AlertService {

	
	@Autowired 
	private AlertDao alertDao;
	@Override
	public String findAlert(int employeeId) {
		// TODO Auto-generated method stub
		
		JSONArray jay = new JSONArray(alertDao.findAlert(employeeId));
		alertDao.deleteAlert(employeeId);
		return jay.toString();
	}

}
