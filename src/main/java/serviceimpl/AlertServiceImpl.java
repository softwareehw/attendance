package serviceimpl;

import java.util.List;

import org.json.JSONArray;
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
		int a[] = new int[list.size()];
		for(int i = 0;i<list.size();i++) {
			a[i] = list.get(i).getState();
		}
		JSONArray jay = new JSONArray(a);
		alertDao.deleteAlert(employeeId);
		return jay.toString();
	}

}
