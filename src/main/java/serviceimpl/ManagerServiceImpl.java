package serviceimpl;

import java.util.Calendar;
import dao.ApplicationForLeaveDao;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bean.ApplicationForLeave;
import bean.Employee;
import bean.Manager;
import bean.Sector;
import dao.ApplicationForEWDao;
import dao.EmployeeDao;
import dao.ManagerDao;
import dao.SectorDao;
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
	public Boolean announceEW(Calendar start, Calendar end) {
		
		return applicationForEWDao.announceEW(start,end);
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


	
	
     
	
}
