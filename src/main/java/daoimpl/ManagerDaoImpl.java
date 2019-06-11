package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import bean.ApplicationForEW;
import bean.ApplicationForLeave;
import bean.Employee;
import bean.Manager;
import bean.Sector;
import bean.WorkArrangement;
import dao.ManagerDao;
import mapper.ApplicationForLeaveRowMapper;
import mapper.ManagerRowMapper;



@Repository
public  class ManagerDaoImpl implements ManagerDao {
	/*
	 * 输入两个时间戳list，分别表示已被占用的开始时间和起止时间，输入两个时间戳，分别表示发布时间的起止
	 * 
	 * 输出一个Range的list
	 */
//	public List<Range> reduceIntersection(List<Range> l, long s , long e){
//		//答案集合
//		List<Range> list=new ArrayList<Range>();
//		int len=l.size();
//		for(int i=0;i<len;i++) {
//			//ls->left le->right
//			//l.get(i).left  l.get(i).right
//			if(l.get(i).left<=s&&l.get(i).right>s) {
//				list.add(new Range(s,l.get(i).right));
//				//System.out.println(0);
//			}
//			else if(l.get(i).left>=s&&l.get(i).right<=e) {
//				list.add(new Range(l.get(i).left,l.get(i).right));
//				//System.out.println(1);
//			}
//			else if(l.get(i).left<=e&&l.get(i).right>=e) {
//				list.add(new Range(l.get(i).left,e));
//				//System.out.println(2);
//			}
//		}
//		
//		return list;
//	}
	
	

	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public List<Manager> getManagerList() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM manager";
        return (List<Manager>) jdbcTemplate.query(sql, new RowMapper<Manager>(){

            @Override
            public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {
            	Manager manager = new Manager();
             
                manager.setName(rs.getString("name"));
                manager.setAge(rs.getInt("age"));
                manager.setSalary(rs.getInt("salary"));
                manager.setPhoneNumber(rs.getInt("phone_number"));
                manager.setEnrollTime(rs.getDate("enroll_time"));
                return manager;
            }

        });
		
	}
	//获取所有部门的信息
	
	
	//根据部门ID获取该部门员工的所有信息
//	@Override
//	public List<Employee> getEmployeeBySId(int id) {
//		// TODO Auto-generated method stub
//		String ID="";
//		ID=String.valueOf(id);
//		String sql="SELECT * FROM employee WHERE sector_id="+ID;
//		
//		return (List<Employee>) jdbcTemplate.query(sql, new RowMapper<Employee>() {
//			@Override
//			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Employee employee = new Employee();
//				employee.setEmployeeId(rs.getInt("employee_id"));
//				employee.setSectorId(rs.getInt("sector_id"));
//				employee.setManager(rs.getBoolean("is_manager"));
//				employee.setName(rs.getString("name"));
//				employee.setAge(rs.getInt("age"));
//				employee.setSalary(rs.getInt("salary"));
//				employee.setSex(rs.getBoolean("sex"));
//				employee.setPhoneNumber(rs.getInt("phone_number"));
//				employee.setEnrollTime(rs.getDate("enroll_time"));
//				return employee;
//			}
//			
//		});
//	}
//	@Override
//	public Boolean setIsMasterById(int id, boolean isM) {
//		String ID="";
//		ID=String.valueOf(id);
//		try {
//			String sql="UPDATE employee set is_manager="+isM+" WHERE employee_id="+id;
//			jdbcTemplate.update(sql);
//		}catch(Exception e){
//			System.out.println(e);
//			return false;
//		}
//		return true;
//	}

//	@Override
//	public Boolean announceEW(Calendar start, Calendar end) {
//		//全员遍历提取出每个员工id
//		String sql="SELECT employee_id FROM employee";
//		List<Integer> employeeId=new ArrayList<Integer>();
//		jdbcTemplate.query(sql, new RowMapper<Employee>() {
//			@Override
//			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
//				// TODO Auto-generated method stub
//				int id=rs.getInt("employee_id");
//				employeeId.add(id);
//				return null;
//			}
//		}
//		);
		
//		//处理时间戳的正确姿势
//		Timestamp t = rs.getTimestamp("start_time");
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTimeInMillis(t.getTime());
//		System.out.println(calendar.getTime());
		
		//定义拼接查找日期
//		int y=start.get(start.YEAR);
//		int m=start.get(start.MONTH)+1;
//		int d=start.get(start.DATE);
//		String datet=y+"-"+m+"-"+d;
//
//		for(int i:employeeId) {
//			//存储该员工的所有时间戳
//			List<Range> banTime=new ArrayList<Range>();
//			//提取工作安排WorkArrangement的信息并存储
//			sql="SELECT start_time,end_time FROM work_arrangement WHERE to_days(start_time)=to_days(\""+datet+"\")AND employee_id="+i;
//			jdbcTemplate.query(sql, new RowMapper<WorkArrangement>() {
//
//				@Override
//				public WorkArrangement mapRow(ResultSet rs, int rowNum) throws SQLException {
//					// TODO Auto-generated method stub
//					
//					Timestamp t = rs.getTimestamp("start_time");
//					Timestamp tt = rs.getTimestamp("end_time");
//					Range ru=new Range(t.getTime(),tt.getTime());
//					banTime.add(ru);
//					return null;
//			}
//			}
//			);
//			
//			
//			//提取加班申请ApplicationForEW的信息并存储
//			sql="SELECT start_time,end_time FROM application_for_ew WHERE "
//					+ "to_days(start_time)=to_days(\""+datet+"\") AND "
//							+ "applicated_person="+i+" AND state=1";
//			jdbcTemplate.query(sql, new RowMapper<ApplicationForEW>() {
//
//				@Override
//				public ApplicationForEW mapRow(ResultSet rs, int rowNum) throws SQLException {
//					// TODO Auto-generated method stub
//					Timestamp t = rs.getTimestamp("start_time");
//					Timestamp tt = rs.getTimestamp("end_time");
//					Range ru=new Range(t.getTime(),tt.getTime());
//					banTime.add(ru);
//					return null;
//			}
//			}
//			);	
//			
//			//提取请假申请ApplicationForLeave的信息并存储
//			sql="SELECT start_time,end_time FROM application_for_leave WHERE "
//					+ "to_days(start_time)=to_days(\""+datet+"\") AND "
//					+ "applicated_person="+i+" AND state=1";
//			jdbcTemplate.query(sql, new RowMapper<ApplicationForLeave>() {
//
//				@Override
//				public ApplicationForLeave mapRow(ResultSet rs, int rowNum) throws SQLException {
//					// TODO Auto-generated method stub
//					Timestamp t = rs.getTimestamp("start_time");
//					Timestamp tt = rs.getTimestamp("end_time");
//					Range ru=new Range(t.getTime(),tt.getTime());
//					banTime.add(ru);
//					return null;
//			}
//			}
//			);	
//
//			//对banTime按照left从小到大的顺序排列。
//			Collections.sort(banTime, new Comparator<Range>() {
//				@Override
//				public int compare(Range o1, Range o2) {
//					// TODO Auto-generated method stub
//					if(o1.left>o2.left) {
//						return 1;
//					}
//					if(o1.left==o2.left) {
//						return 0;
//					}
//					return -1;
//				}
//				
//			});
//			
//			//利用提取出来的时间求哪些区间段的时间可以被创建已被批准的加班
//			List<Range> test=reduceIntersection(banTime , start.getTimeInMillis() , end.getTimeInMillis());
//			System.out.println(i);
//			
//			if(start.getTimeInMillis()<test.get(0).left) {
//				//System.out.println(start.getTimeInMillis()+","+test.get(0).left);
//				Calendar e=Calendar.getInstance();
//				e.setTimeInMillis(test.get(0).left);
//				int mm=start.get(start.MONTH)+1;
//				String timestart=start.get(start.YEAR)+"-"+mm+"-"+start.get(start.DAY_OF_MONTH)+" "
//						+ start.get(start.HOUR_OF_DAY)+":"+start.get(start.MINUTE)+":"+start.get(start.SECOND);
//				String timeend=e.get(e.YEAR)+"-"+mm+"-"+e.get(e.DAY_OF_MONTH)+" "
//						+ e.get(e.HOUR_OF_DAY)+":"+e.get(e.MINUTE)+":"+e.get(e.SECOND);
//				
//				sql="INSERT INTO application_for_ew (applicated_person,start_time,end_time,state,applicated_id) values("+i+",\""+timestart+"\",\""+timeend+"\",1,0)";
//				jdbcTemplate.update(sql);
//				
//			}
//
//			for(int i1=0;i1<test.size()-1;i1++) {	
//				if(test.get(i1).right!=test.get(i1+1).left) {
//					//System.out.println(test.get(i1).right+","+test.get(i1+1).left);
//					Calendar e=Calendar.getInstance();
//					Calendar s=Calendar.getInstance();
//					s.setTimeInMillis(test.get(i1).right);
//					e.setTimeInMillis(test.get(i1+1).left);
//					int mm=s.get(start.MONTH)+1;
//					String timestart=s.get(start.YEAR)+"-"+mm+"-"+s.get(start.DAY_OF_MONTH)+" "
//							+ s.get(start.HOUR_OF_DAY)+":"+s.get(start.MINUTE)+":"+s.get(start.SECOND);
//					String timeend=e.get(e.YEAR)+"-"+mm+"-"+e.get(e.DAY_OF_MONTH)+" "
//							+ e.get(e.HOUR_OF_DAY)+":"+e.get(e.MINUTE)+":"+e.get(e.SECOND);
//					
//					sql="INSERT INTO application_for_ew (applicated_person,start_time,end_time,state,applicated_id) values("+i+",\""+timestart+"\",\""+timeend+"\",1,0)";
//					jdbcTemplate.update(sql);
//
//				}
//			}
//			
//			if(end.getTimeInMillis()>test.get(test.size()-1).right) {
//				Calendar e=Calendar.getInstance();
//				Calendar s=Calendar.getInstance();
//				s.setTimeInMillis(test.get(test.size()-1).right);
//				e.setTimeInMillis(end.getTimeInMillis());
//				int mm=s.get(start.MONTH)+1;
//				String timestart=s.get(start.YEAR)+"-"+mm+"-"+s.get(start.DAY_OF_MONTH)+" "
//						+ s.get(start.HOUR_OF_DAY)+":"+s.get(start.MINUTE)+":"+s.get(start.SECOND);
//				String timeend=e.get(e.YEAR)+"-"+mm+"-"+e.get(e.DAY_OF_MONTH)+" "
//						+ e.get(e.HOUR_OF_DAY)+":"+e.get(e.MINUTE)+":"+e.get(e.SECOND); //有问题！
//				
//				sql="INSERT INTO application_for_ew (applicated_person,start_time,end_time,state,applicated_id) values("+i+",\""+timestart+"\",\""+timeend+"\",1,0)";
//				jdbcTemplate.update(sql);
//			}
//			
//		}
//		return null;
//	}
	
	
	//无法修改employee_id 要求前端每次都把employee的完整信息发送过来
//	@Override
//	public Boolean modifyEm(Employee e) {
//		Calendar s=Calendar.getInstance();
//		s.setTime(e.getEnrollTime());
//		//System.out.println(s.getTimeInMillis());
//		//Date t=e.getEnrollTime();
//		int mm=s.get(s.MONTH)+1;
//		String timett=s.get(s.YEAR)+"-"+mm+"-"+s.get(s.DAY_OF_MONTH)+" "
//				+ s.get(s.HOUR_OF_DAY)+":"+s.get(s.MINUTE)+":"+s.get(s.SECOND);
//		System.out.println(timett);
//		String sql="UPDATE employee SET sector_id=?,is_manager=?,name=?,age=?,salary=?,sex=?,phone_number=?,enroll_time=? WHERE employee_id=?";//,,,,,"+timet+"\" WHERE employee_id=?;";
//		//timett哪里有问题不知道问题在哪 出在，的问题
//		jdbcTemplate.update(sql, e.getSectorId(),e.isManager(),e.getName(),e.getAge(),e.getSalary(),e.isSex(),e.getPhoneNumber(),timett,e.getEmployeeId());//,e.getName(),e.getAge(),e.getSalary(),e.isSex(),e.getPhoneNumber(),e.getEmployeeId());
//		System.out.println("success!");
//		return null;
//	}

	//活得所有部门的信息
//	@Override
//	public List<Sector> getAllSector() {
//		String sql = "SELECT * FROM sector";
//		
//        return (List<Sector>) jdbcTemplate.query(sql, new RowMapper<Sector>(){
//
//            @Override
//            public Sector mapRow(ResultSet rs, int rowNum) throws SQLException {
//            
//            	Sector sector=new Sector();
//            	sector.setSectorId(rs.getInt("sector_id"));
//            	sector.setSectorName(rs.getString("sector_name"));
//            	sector.setSectorPeopleNumber(rs.getInt("sector_people_number"));
//            
//                return sector;
//            }
//
//        });
//	}

        /**
        *@param    userId
        *@return   有关信息
        *
        */
	@Override
	public List<Manager> findManagerByUserId(int userId) {
		String sql = "SELECT * FROM manager WHERE user_id="+userId;
		
		
        return (List<Manager>) jdbcTemplate.query(sql, new RowMapper<Manager>(){

            @Override
            public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {
            	Manager manager = new Manager();
             
                manager.setName(rs.getString("name"));
                manager.setAge(rs.getInt("age"));
                manager.setSalary(rs.getInt("salary"));
                manager.setPhoneNumber(rs.getInt("phone_number"));
                manager.setEnrollTime(rs.getDate("enroll_time"));
                return manager;
            }

        });
		
	}


		@Override
		public int ModifyManager(Manager manager) {
			String sql = "UPDATE MANAGER SET NAME=?,AGE=?,SALARY=?,SEX=?,ENROLL_TIME=?";
			int i = jdbcTemplate.update(sql,new Object[]{manager.getName(),manager.getAge(),manager.getSalary(),manager.isSex(),manager.getEnrollTime()});
			return i;
		}


		@Override
		public List<Manager> findManagerByManagerId(int managerId) {
			String sql = "SELECT * FROM MANAGER WHERE MANAGER_ID="+managerId;
			
			return ( List<Manager>)jdbcTemplate.query(sql, new ManagerRowMapper());
		}


//		@Override
//		public List<ApplicationForLeave> applicationForLeaveFindAll() {
//			// TODO Auto-generated method stub
//			String sql = "select * from application_for_leave";
//			
//			return jdbcTemplate.query(sql, new ApplicationForLeaveRowMapper() {
//				
//			});
//		}
}
