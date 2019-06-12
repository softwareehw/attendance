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

import bean.ApplicationForEW;
import bean.ApplicationForLeave;
import bean.Employee;
import bean.WorkArrangement;
import dao.ApplicationForEWDao;
import mapper.ApplicationForEWRowMapper;
import mapper.EmployeeRowMapper;

//用到的函数		


class Range{
	public
		long left=0;
		long right=0;
	public Range(long left,long right) {
		this.left=left;
		this.right=right;
	}
}

@Repository
public class ApplicationForEWDaoImpl implements ApplicationForEWDao {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public List<Range> reduceIntersection(List<Range> l, long s , long e){
		//答案集合
		List<Range> list=new ArrayList<Range>();
		int len=l.size();
		for(int i=0;i<len;i++) {
			//ls->left le->right
			//l.get(i).left  l.get(i).right
			if(l.get(i).left<=s&&l.get(i).right>s) {
				list.add(new Range(s,l.get(i).right));
				//System.out.println(0);
			}
			else if(l.get(i).left>=s&&l.get(i).right<=e) {
				list.add(new Range(l.get(i).left,l.get(i).right));
				//System.out.println(1);
			}
			else if(l.get(i).left<=e&&l.get(i).right>=e) {
				list.add(new Range(l.get(i).left,e));
				//System.out.println(2);
			}
		}
		
		return list;
	}
	
	//查看全部加班记录
	@Override
	public List<ApplicationForEW> findAll() {
		// TODO Auto-generated method stub
		String sql = "select * from application_for_ew";
		return jdbcTemplate.query(sql, new ApplicationForEWRowMapper() {
			
		});
		
	}

	@Override
	public int deleteApplicationForEW(int applicatedId) {
		// TODO Auto-generated method stub
		String sql = "delete from application_for_ew where applicated_id = ?";
		return jdbcTemplate.update(sql, applicatedId);
	
	}

	@Override
	public List<ApplicationForEW> findById(int applicatedPerson) {
		// TODO Auto-generated method stub
		
		String ApplicatedPerson="";
		ApplicatedPerson=String.valueOf(applicatedPerson);
		String sql="SELECT * FROM application_for_ew WHERE applicated_person="+ApplicatedPerson;
		return (List<ApplicationForEW>) jdbcTemplate.query(sql, new ApplicationForEWRowMapper() {
			
		});
	}

	@Override
	public int addApplicateEW(ApplicationForEW applica) {
		// TODO Auto-generated method stub
		String sql = "insert into application_for_ew(applicated_id,start_time,end_time,ew_state,ratify_id) "
				+ "values(?,?,?,?)";
		return jdbcTemplate.update(sql,applica.getApplicatedId(),applica.getStartTime(),applica.getEndTime(),
				0,applica.getRatifyId());
		  
	}

	
	//全员加班
	@Override
	public String announceEW(ApplicationForEW date) {
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		start.setTime(date.getStartTime());
		start.setTime(date.getEndTime());
		//全员遍历提取出每个员工id
		String sql="SELECT employee_id FROM employee";
		List<Integer> employeeId=new ArrayList<Integer>();
		jdbcTemplate.query(sql, new RowMapper<Employee>() {
			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				int id=rs.getInt("employee_id");
				employeeId.add(id);
				return null;
			}
		}
		);
		
//		//处理时间戳的正确姿势
//		Timestamp t = rs.getTimestamp("start_time");
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTimeInMillis(t.getTime());
//		System.out.println(calendar.getTime());
		
		//定义拼接查找日期
		int y=start.get(start.YEAR);
		int m=start.get(start.MONTH)+1;
		int d=start.get(start.DATE);
		String datet=y+"-"+m+"-"+d;

		for(int i:employeeId) {
			//存储该员工的所有时间戳
			List<Range> banTime=new ArrayList<Range>();
			//提取工作安排WorkArrangement的信息并存储
			sql="SELECT start_time,end_time FROM work_arrangement WHERE to_days(start_time)=to_days(\""+datet+"\")AND employee_id="+i;
			jdbcTemplate.query(sql, new RowMapper<WorkArrangement>() {

				@Override
				public WorkArrangement mapRow(ResultSet rs, int rowNum) throws SQLException {
					// TODO Auto-generated method stub
					
					Timestamp t = rs.getTimestamp("start_time");
					Timestamp tt = rs.getTimestamp("end_time");
					Range ru=new Range(t.getTime(),tt.getTime());
					banTime.add(ru);
					return null;
			}
			}
			);
			
			
			//提取加班申请ApplicationForEW的信息并存储
			sql="SELECT start_time,end_time FROM application_for_ew WHERE "
					+ "to_days(start_time)=to_days(\""+datet+"\") AND "
							+ "applicated_person="+i+" AND ew_state=1";
			jdbcTemplate.query(sql, new RowMapper<ApplicationForEW>() {

				@Override
				public ApplicationForEW mapRow(ResultSet rs, int rowNum) throws SQLException {
					// TODO Auto-generated method stub
					Timestamp t = rs.getTimestamp("start_time");
					Timestamp tt = rs.getTimestamp("end_time");
					Range ru=new Range(t.getTime(),tt.getTime());
					banTime.add(ru);
					return null;
			}
			}
			);	
			
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

			//对banTime按照left从小到大的顺序排列。
			Collections.sort(banTime, new Comparator<Range>() {
				@Override
				public int compare(Range o1, Range o2) {
					// TODO Auto-generated method stub
					if(o1.left>o2.left) {
						return 1;
					}
					if(o1.left==o2.left) {
						return 0;
					}
					return -1;
				}
				
			});
			
			//利用提取出来的时间求哪些区间段的时间可以被创建已被批准的加班
			List<Range> test=reduceIntersection(banTime , start.getTimeInMillis() , end.getTimeInMillis());
			System.out.println(i);
			
			if(start.getTimeInMillis()<test.get(0).left) {
				//System.out.println(start.getTimeInMillis()+","+test.get(0).left);
				Calendar e=Calendar.getInstance();
				e.setTimeInMillis(test.get(0).left);
				int mm=start.get(start.MONTH)+1;
				String timestart=start.get(start.YEAR)+"-"+mm+"-"+start.get(start.DAY_OF_MONTH)+" "
						+ start.get(start.HOUR_OF_DAY)+":"+start.get(start.MINUTE)+":"+start.get(start.SECOND);
				String timeend=e.get(e.YEAR)+"-"+mm+"-"+e.get(e.DAY_OF_MONTH)+" "
						+ e.get(e.HOUR_OF_DAY)+":"+e.get(e.MINUTE)+":"+e.get(e.SECOND);
				
				sql="INSERT INTO application_for_ew (applicated_person,start_time,end_time,state,applicated_id) values("+i+",\""+timestart+"\",\""+timeend+"\",1,0)";
				jdbcTemplate.update(sql);
				
			}

			for(int i1=0;i1<test.size()-1;i1++) {	
				if(test.get(i1).right!=test.get(i1+1).left) {
					//System.out.println(test.get(i1).right+","+test.get(i1+1).left);
					Calendar e=Calendar.getInstance();
					Calendar s=Calendar.getInstance();
					s.setTimeInMillis(test.get(i1).right);
					e.setTimeInMillis(test.get(i1+1).left);
					int mm=s.get(start.MONTH)+1;
					String timestart=s.get(start.YEAR)+"-"+mm+"-"+s.get(start.DAY_OF_MONTH)+" "
							+ s.get(start.HOUR_OF_DAY)+":"+s.get(start.MINUTE)+":"+s.get(start.SECOND);
					String timeend=e.get(e.YEAR)+"-"+mm+"-"+e.get(e.DAY_OF_MONTH)+" "
							+ e.get(e.HOUR_OF_DAY)+":"+e.get(e.MINUTE)+":"+e.get(e.SECOND);
					
					sql="INSERT INTO application_for_ew (applicated_person,start_time,end_time,state,applicated_id) values("+i+",\""+timestart+"\",\""+timeend+"\",1,0)";
					jdbcTemplate.update(sql);

				}
			}
			
			if(end.getTimeInMillis()>test.get(test.size()-1).right) {
				Calendar e=Calendar.getInstance();
				Calendar s=Calendar.getInstance();
				s.setTimeInMillis(test.get(test.size()-1).right);
				e.setTimeInMillis(end.getTimeInMillis());
				int mm=s.get(start.MONTH)+1;
				String timestart=s.get(start.YEAR)+"-"+mm+"-"+s.get(start.DAY_OF_MONTH)+" "
						+ s.get(start.HOUR_OF_DAY)+":"+s.get(start.MINUTE)+":"+s.get(start.SECOND);
				String timeend=e.get(e.YEAR)+"-"+mm+"-"+e.get(e.DAY_OF_MONTH)+" "
						+ e.get(e.HOUR_OF_DAY)+":"+e.get(e.MINUTE)+":"+e.get(e.SECOND); //有问题！
				
				sql="INSERT INTO application_for_ew (applicated_id,start_time,end_time,ew_state,ratify_id,ew_reason) values("+i+",\""+timestart+"\",\""+timeend+"\",1,"+date.getRatifyId()+",\""+date.getEwReason+"\")";
				jdbcTemplate.update(sql);
			}
			
		}
		return null;
	}
	
	@Override
	public List<ApplicationForEW> getUncheckApplicationForEW(int sectorId) {
		// TODO Auto-generated method stub
		String sql = "select * from application_for_ew where sectorid = ?";
		
		return (List<ApplicationForEW>)jdbcTemplate.query(sql, new Object[] {sectorId},new ApplicationForEWRowMapper() {
			
		});
	}
	@Override
	public List<ApplicationForEW> getEmployeeInfoApplicationForEW(int employeeId) {
		// TODO Auto-generated method stub
		String sql = "select * from application_for_ew where applicated_person = ?";
        return (List<ApplicationForEW>)jdbcTemplate.query(sql, new Object[] {employeeId},new ApplicationForEWRowMapper() {
			
		});
		
	}
	@Override
	public List<ApplicationForEW> updateEmployeeInfoApplicationForEW( int applicatedId) {
		// TODO Auto-generated method stub
		String sql = "select * from application_for_ew where applicated_id = ? and state = 0";
		return (List<ApplicationForEW>)jdbcTemplate.query(sql, new Object[] {applicatedId},new ApplicationForEWRowMapper() {
			
		});
		
	}

}
