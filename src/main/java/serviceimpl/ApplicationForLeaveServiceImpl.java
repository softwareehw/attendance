package serviceimpl;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bean.ApplicationForLeave;
import bean.Employee;
import dao.ApplicationForLeaveDao;
import service.ApplicationForLeaveService;

@Service
public class ApplicationForLeaveServiceImpl implements ApplicationForLeaveService {


	@Autowired
	ApplicationForLeaveDao applicationForLeavedao;
	
	/**
	 * 
	 * 员工申请放假
	 */
	@Override
	public String addApplicationForLeave(ApplicationForLeave applicationForLeave) {
		int i = applicationForLeavedao.addApplicationForLeave(applicationForLeave);
		JSONObject json =new JSONObject();
		if(i==1){
			json.put("state", 1);
		}else{
			json.put("state", 0);
			json.put("error_message", "请假失败");
		}
		return json.toString();
	}

	/**
	 * 查询员工放假情况
	 */
	@Override
	public String findApplicationForLeaveById(int applicatedPerson) {
		List<ApplicationForLeave> l=applicationForLeavedao.findApplicationForLeaveById(applicatedPerson);
		if(l.isEmpty()){
			JSONObject json = new JSONObject();
			json.put("state", 0);
			json.put("error_message", "该员工没有任何请假记录");
			return json.toString();
		}else{
			JSONArray json = new JSONArray(l);
			return json.toString();
		}
	}




	@Override
	public String findUnratifiedApplicationForleaveBySectorId(int sectorId) {
		List<ApplicationForLeave> l = applicationForLeavedao.findUnratifiedApplicationForleaveBySectorId(sectorId);
		if(l.isEmpty()){
			JSONObject json = new JSONObject();
			json.put("state", 0);
			json.put("error_message", "没有任何待审批的请假记录");
			return json.toString();
		}else{
			JSONArray json = new JSONArray(l);
			return json.toString();
		}
	}

	@Override
	public String CancelLeave(int leaveId) {
		int i = applicationForLeavedao.CancelLeaveByLeaveId(leaveId);
		
		
		
		
		
		
		
		
		
		if(i==1){
			JSONObject json = new JSONObject();
			json.put("state", 1);
		}else{
			JSONObject json = new JSONObject();
			json.put("state", 0);
			
		}
		return null;
	}



}
