package serviceimpl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bean.Employee;
import bean.WorkArrangement;
import dao.EmployeeDao;
import dao.SectorDao;
import dao.WorkArrangementDao;
import service.WorkArrangementService;

@Service
public class WorkArrangementServiceImpl implements WorkArrangementService {
	
	@Autowired 
	WorkArrangementDao workarrangementDao;
	@Autowired 
	EmployeeDao employeeDao;
	@Autowired 
	SectorDao sectorDao;

	@Override
	public String AddWorkArrangement(WorkArrangement workarrangement) {
		int i = workarrangementDao.AddWorkArrangement(workarrangement);
		JSONObject json = new JSONObject();
		if(i==1){
			json.put("state", 1);
		}else{
			json.put("state", 0);
			json.put("error_message", "添加工作安排失败");
		}
		
		return json.toString();
	}

	@Override
	public String findWorkArrangementByEId(int employeeId) {
		JSONObject json = new JSONObject();
		List<WorkArrangement> l = workarrangementDao.findWorkArrangementByEId(employeeId);
		if(!l.isEmpty()){
			JSONArray json1  =new JSONArray(l);
			return json1.toString();
		}else{
			json.put("error_message", "没有这个员工的工作安排");
			json.put("state", "0");
			return json.toString();
		}
	}

	@Override
	public String AddWAInSector(int sectorId, WorkArrangement workArrangement) {
		WorkArrangement wa = workArrangement;
		JSONObject json = new JSONObject();
		List<Employee> employeeIdList ;
	    employeeIdList = sectorDao.employeeInfoBySectorId(sectorId);
		
		for (Employee employee : employeeIdList) {
			workArrangement.setEmployeeId(employee.getEmployeeId());
			int i = workarrangementDao.AddWorkArrangement(workArrangement);
			if(i==0){
				json.put(String.valueOf(employee.getEmployeeId()), "error");
			}else{
				json.put(String.valueOf(employee.getEmployeeId()), "success");
			}
		}
		return json.toString();
	}

	@Override
	public String deleteByWAId(int workArrangeId) {
		int i = workarrangementDao.deleteByWAId(workArrangeId);
		JSONObject json = new JSONObject();
		if(i==1){
			json.put("state", 1);
		}else{
			json.put("state", 0);
			json.put("error_message", "删除失败");
		}
		return json.toString();
	}

	@Override
	public String modifyByWAId(WorkArrangement workArrangement) {
		int i = workarrangementDao.ModifyByWAId(workArrangement);
		JSONObject json = new JSONObject();
		if(i==1){
			json.put("state", 1);
		}else{
			json.put("state", 0);
			json.put("error_message", "修改失败");
		}
		return json.toString();
	}

	@Override
	public int findNormalWorkTime(int employeeId) {
		List<WorkArrangement> l = workarrangementDao.findWorkArrangementByEId(employeeId);
		// 获取当月第一天和最后一天  
		Calendar cale = null;
        int month = cale.get(Calendar.MONTH) + 1;

		for (WorkArrangement workArrangement : l) {
			if(workArrangement.getStartTime().getMonth()==month){
				
			}
		}
		
		
		
		
		return 0;
	}

}
