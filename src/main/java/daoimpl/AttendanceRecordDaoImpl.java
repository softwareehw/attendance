package daoimpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import bean.AttendanceRecord;
import dao.AttendanceRecordDao;
import mapper.AttendanceRecordRowMapper;

@Repository
public class AttendanceRecordDaoImpl implements AttendanceRecordDao {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public List<AttendanceRecord> attendanceFindById(int userId) {
		// TODO Auto-generated method stub
		String UserId="";
		UserId=String.valueOf(userId);
		String sql="SELECT * FROM attendance_record WHERE employeeid="+UserId;
		return (List<AttendanceRecord>) jdbcTemplate.query(sql, new AttendanceRecordRowMapper() {
			
		});
	}

	@Override
	public int attendance(int userId) {
		// TODO Auto-generated method stub
		
		String sql = "insert into attendance_record(employeeid,start_time,is_complete) values(?,?,?)";
		
		return jdbcTemplate.update(sql,userId,new Date(),0);
	}

	@Override
	public int attendanceDown(int userId) {
		// TODO Auto-generated method stub
		String sql = "update attendance_record set end_time = ?,is_complete = ? where employeeid = ? and is_complete = ?";
		return jdbcTemplate.update(sql,new Date(),1,userId,0);
	}

}
