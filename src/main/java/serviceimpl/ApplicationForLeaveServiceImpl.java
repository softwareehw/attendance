package serviceimpl;

import java.util.List;

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
	
	@Override
	public int addApplicationForLeave(ApplicationForLeave applicationForLeave) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ApplicationForLeave> applicationForLeaveFindById(int applicatedPerson) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int applicateLeave(int Employee) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<ApplicationForLeave> findAllLeavePerson(Employee master) {
		int sectorid  = master.getSectorId();
		
		return applicationForLeavedao.findLeavePersonInMyDepartment(sectorid);
	}



}
