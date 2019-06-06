package service;

import java.util.List;

import bean.AttendanceRecord;

public interface AttendanceRecordService {

	public List<AttendanceRecord> attendanceFindById (int userId);
	/**
	 *@param   员工id
	 *@return
	 *
	 */
	
	public int attendance (int userId);
	
	/**
	 *@param      员工id
	 *@return
	 *
	 */
	public int attendanceDown (int userId);
}
