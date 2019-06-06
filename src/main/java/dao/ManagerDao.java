package dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import bean.ApplicationForLeave;
import bean.Employee;
import bean.Manager;
import bean.Sector;


public interface ManagerDao {
       public List<Manager> getManagerList();
//       public List<Employee> getEmployeeBySId(int id);
       
//       public Boolean setIsMasterById(int id, boolean isM);
//       public Boolean announceEW(Calendar start, Calendar end);
//	   public Boolean modifyEm(Employee e);
//	   public List<Sector> getAllSector();	
	   
	   public List<Manager> findManagerByUserId(int userId);
	   
//	   public List<ApplicationForLeave> applicationForLeaveFindAll();
}
