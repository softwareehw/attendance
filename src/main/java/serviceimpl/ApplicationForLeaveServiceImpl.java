package serviceimpl;

import java.util.Date;
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
		JSONObject json = new JSONObject();
		//首先更改销假时间
		//然后判断销假时间和请假的结束时间哪一个大
		//如果请假结束时间大，就再把返回状态改成1
		
		ApplicationForLeave applicationForLeave = applicationForLeavedao.findLeaveByLId(leaveId).get(0);
		Date reportBackTime= applicationForLeave.getReportBackTime();
		Date endTime = applicationForLeave.getEndTime();
		
		if(reportBackTime.after(endTime)){
			applicationForLeavedao.CancelCancelLeaveByLeaveId(leaveId);
			json.put("state", 0);
			json.put("error_message", "销假时间太晚");
		}
		
		json.put("state", 1);
		return  json.toString();
	}

	@Override
	public String RatifyLeave(ApplicationForLeave applicationForLeave) {
		int i=applicationForLeavedao.RatifyLeave(applicationForLeave);
		JSONObject json = new JSONObject();
		if(i==0){
			json.put("error_message", "审批失败");
			json.put("state", 0);
		}else{
			json.put("state", 1);
		}
		
		return json.toString();
	}
	

}
