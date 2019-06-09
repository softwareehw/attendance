package serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import bean.ApplicationForEW;
import service.ApplicationForEWService;

@Service
public class ApplicationForEWServiceImpl implements ApplicationForEWService {

	@Override
	public int applicateEW(ApplicationForEW applica) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ApplicationForEW> FindById(int applicatedPerson) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ApplicationForEW> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteApplication(int applicatedId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getUncheckApplicationForEW(int sectorId) {
		// TODO Auto-generated method stub
		return null;
	}

}
