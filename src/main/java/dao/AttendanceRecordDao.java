package dao;

import java.util.List;

import bean.AttendanceRecord;

public interface AttendanceRecordDao {

	/**
	 *@param    员工id
	 *@return
	 *
	 */
	public List<AttendanceRecord> attendanceFindById (int userId);
	
	/**
	 *@param   员工Id
	 *@return
	 *
	 */
	
	public int attendance (int userId);
	
	/**
	 *@param   员工Id
	 *@return
	 *
	 */
	
	public int attendanceDown (int userId);
	
	
	public List<AttendanceRecord> findAttendanceRecordBySectorId(int sectorId);
}
