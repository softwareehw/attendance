package daoimpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import bean.WorkArrangement;
import dao.WorkArrangementDao;

@Repository
public class WorkArrangementDaoImpl implements WorkArrangementDao {

	@Override
	public int AddWorkArrangement(WorkArrangement wa) {
		String sql = "INSERT INTO WORKARRANGEMENT()";
		return 0;
	}

	@Override
	public List<WorkArrangement> findWorkArrangementByEId(int employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

}
