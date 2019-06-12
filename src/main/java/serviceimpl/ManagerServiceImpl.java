package serviceimpl;

import java.util.Calendar;
import java.util.Date;

import dao.ApplicationForLeaveDao;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bean.ApplicationForEW;
import bean.ApplicationForLeave;
import bean.Employee;
import bean.Manager;
import bean.Sector;
import bean.User;
import dao.ApplicationForEWDao;
import dao.EmployeeDao;
import dao.ManagerDao;
import dao.SectorDao;
import dao.UserDao;
import service.ManagerService;

@Service
public class ManagerServiceImpl implements ManagerService{
	
	@Autowired 
	private ManagerDao managerDao;
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private ApplicationForEWDao applicationForEWDao;
	@Autowired
	private SectorDao sectorDao;
	@Autowired
	private ApplicationForLeaveDao appplicationForLeaveDao;
	@Autowired
	private UserDao userDao;


	@Override
	public List<Manager> getList() {
		
		// TODO Auto-generated method stub
		return managerDao.getManagerList();
	}

	@Override
	public List<Employee> getListBySId(int id) {
		// TODO Auto-generated method stub
		return employeeDao.getEmployeeBySId(id);
	}

	@Override
	public Boolean setIsMasterById(int id, boolean isM) {
		// TODO Auto-generated method stub
		return employeeDao.setIsMasterById(id,isM);
	}

	@Override
	public String announceEW(ApplicationForEW date) {
		return applicationForEWDao.announceEW(date);
	}

	@Override
	public Boolean modifyEm(Employee e) {
		
		return employeeDao.modifyEm(e);
	}

	@Override
	public List<Sector> getAllSector() {
		return sectorDao.getAllSector();
	}

	@Override
	public List<Manager> findManagerByUserId(int userId) {
		// TODO Auto-generated method stub
		return managerDao.findManagerByUserId(userId);
	}

	@Override
	public List<ApplicationForLeave> applicationForLeaveFindAll() {
		// TODO Auto-generated method stub
		
		return appplicationForLeaveDao.applicationForLeaveFindAll();
	}

	@Override
	public String getUncheckApplicationForEW(int sectorId) {
		// TODO Auto-generated method stub
		if(!applicationForEWDao.getUncheckApplicationForEW(sectorId).isEmpty()) {
			List<ApplicationForEW> list = applicationForEWDao.getUncheckApplicationForEW(sectorId);
			JSONArray jay = new JSONArray(list);
			return jay.toString();
		}
		JSONObject ans = new JSONObject();
		ans.put("state", 0);
		ans.put("errormessage", "没有未批准的申请加班表或没有这个部门");
		return ans.toString();
	}

	@Override
	public String getEmployeeInfoApplicationForEW(int employeeId) {
		// TODO Auto-generated method stub
		if(!applicationForEWDao.getEmployeeInfoApplicationForEW(employeeId).isEmpty()) {
			List<ApplicationForEW> list = applicationForEWDao.getEmployeeInfoApplicationForEW(employeeId);
			JSONArray jay = new JSONArray(list);
			return jay.toString();
		}
		JSONObject ans = new JSONObject();
		ans.put("state", 0);
		ans.put("errormessage", "没有这个员工");
		return ans.toString();
	}

	@Override
	public String ModifyManager(Manager m) {
		JSONObject json = new JSONObject();
		int i = managerDao.ModifyManager(m);
	
		//如果失败，返回
		if(i!=1){
			json.put("status", 0);
			json.put("error_message", "修改经理信息失败");
			return json.toString();
		}else{
			json.put("status", 1);
		}
		//如果成功，返回
		return json.toString();
	}

	/**
	 * 通过manager id查找经理
	 */
	@Override
	public Manager findManagerByManagerId(int managerId) {
		List<Manager> l = managerDao.findManagerByManagerId(managerId);
		if(!l.isEmpty()) return l.get(0);
		else {
			return null;
		}
	}

	//添加user
	@Override
	public String addUser(User user) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		int i = userDao.addUser();
		if(i!=1){
			json.put("status", 0);
			json.put("error_message", "添加失败");
			return json.toString();
		}else{
			json.put("status", 1);
		}
		//如果成功，返回
		return json.toString();
		
	}

	@Override
	public String findApplicationForleaveBySectorId(int sectorId) {
		// TODO Auto-generated method stub
		List<ApplicationForLeave> list = appplicationForLeaveDao.findUnratifiedApplicationForleaveBySectorId(sectorId);
		JSONObject ans = new JSONObject();
		if(list.isEmpty()) {
			ans.put("state", 0);
			ans.put("errormessage", "没有未批准的请假申请表或没有这个部门");
			return ans.toString();
		}else{
			JSONArray jay = new JSONArray(list);
			return jay.toString();
		}
		
	}


	
	
     
	
}
