package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
import dao.AlertDao;
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
	@Autowired
	private AlertDao alertDao;
	
	public List<Range> reduceIntersection(List<Range> l, long s , long e){
		//答案集合
		List<Range> list=new ArrayList<Range>();
		int len=l.size();
		if(len==0) {
			list.add(new Range(s,e));
		}
		for(int i=0;i<len;i++) {
			//ls->left le->right
			//l.get(i).left  l.get(i).right
			if(l.get(i).left==s&&l.get(i).right==e) {
				return list;
			}
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

	//根据id删除申请加班表
	@Override
	public int deleteApplicationForEW(int applicatedId) {
		// TODO Auto-generated method stub
		String sql = "delete from application_for_ew where applicated_id = ?";
		return jdbcTemplate.update(sql, applicatedId);
	
	}

	//查看某申请人全部申请加班
	@Override
	public List<ApplicationForEW> findById(int applicatedId) {
		// TODO Auto-generated method stub
		
		String ApplicatedPerson="";
		ApplicatedPerson=String.valueOf(applicatedId);
		String sql="SELECT * FROM application_for_ew WHERE applicated_id="+applicatedId;
		return (List<ApplicationForEW>) jdbcTemplate.query(sql, new ApplicationForEWRowMapper() {
			
		});
	}

	//申请加班
	@Override
	public int addApplicateEW(ApplicationForEW applica) {
		// TODO Auto-generated method stub
		String sql = "insert into application_for_ew(applicated_id,start_time,end_time,ew_state,ew_reason) "
				+ "values(?,?,?,?,?)";
		alertDao.addAlert(applica.getApplicatedId(), 0);
		
		return jdbcTemplate.update(sql,applica.getApplicatedId(),applica.getStartTime(),applica.getEndTime(),
				0,applica.getEwReason());
		  
	}

	
	//全员加班
	@Override
	public String announceEW(ApplicationForEW date) {
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		start.setTime(date.getStartTime());
		end.setTime(date.getEndTime());
		
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
							+ "applicated_id="+i+" AND ew_state=1";
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
			
			if(test.isEmpty()) {
				return null;
			}
			if(start.getTimeInMillis()==test.get(0).left&&end.getTimeInMillis()==test.get(0).right) {
				System.out.println("天无绝人之路");
				Calendar e=Calendar.getInstance();
				Calendar s=Calendar.getInstance();
				s.setTimeInMillis(test.get(0).left);
				e.setTimeInMillis(test.get(0).right);
				int mm=s.get(start.MONTH)+1;
				int tt=s.get(start.DAY_OF_MONTH);
				String timestart=s.get(s.YEAR)+"-"+mm+"-"+tt+" "
						+ s.get(s.HOUR_OF_DAY)+":"+s.get(s.MINUTE)+":"+s.get(start.SECOND);
				String timeend=s.get(start.YEAR)+"-"+mm+"-"+tt+" "
						+ e.get(Calendar.HOUR_OF_DAY)+":"+e.get(Calendar.MINUTE)+":"+e.get(Calendar.SECOND);
				System.out.println(s.toString());
				System.out.println(e.toString());
				System.out.println(timestart);
				System.out.println(timeend);
				sql="INSERT INTO application_for_ew (applicated_id,start_time,end_time,ew_state,ratify_id,ew_reason) values("+i+",\""+timestart+"\",\""+timeend+"\",1,"+date.getRatifyId()+",\""+date.getEwReason()+"\")";
				jdbcTemplate.update(sql);
			}
			
			if(start.getTimeInMillis()<test.get(0).left) {
				//System.out.println(start.getTimeInMillis()+","+test.get(0).left);
				Calendar e=Calendar.getInstance();
				e.setTimeInMillis(test.get(0).left);
				int mm=start.get(start.MONTH)+1;
				String timestart=start.get(start.YEAR)+"-"+mm+"-"+start.get(start.DAY_OF_MONTH)+" "
						+ start.get(start.HOUR_OF_DAY)+":"+start.get(start.MINUTE)+":"+start.get(start.SECOND);
				String timeend=e.get(e.YEAR)+"-"+mm+"-"+e.get(e.DAY_OF_MONTH)+" "
						+ e.get(e.HOUR_OF_DAY)+":"+e.get(e.MINUTE)+":"+e.get(e.SECOND);
				
				sql="INSERT INTO application_for_ew (applicated_id,start_time,end_time,ew_state,ratify_id,ew_reason) values("+i+",\""+timestart+"\",\""+timeend+"\",1,"+date.getRatifyId()+",\""+date.getEwReason()+"\")";
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
					
					sql="INSERT INTO application_for_ew (applicated_id,start_time,end_time,ew_state,ratify_id,ew_reason) values("+i+",\""+timestart+"\",\""+timeend+"\",1,"+date.getRatifyId()+",\""+date.getEwReason()+"\")";
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
				
				sql="INSERT INTO application_for_ew (applicated_id,start_time,end_time,ew_state,ratify_id,ew_reason) values("+i+",\""+timestart+"\",\""+timeend+"\",1,"+date.getRatifyId()+",\""+date.getEwReason()+"\")";
				jdbcTemplate.update(sql);
			}
			
		}
		return null;
	}
	

	//得到某部门所有未批的申请加班表
	@Override
	public List<ApplicationForEW> getUncheckApplicationForEW(int sectorId) {
		// TODO Auto-generated method stub
		String sql = "select * from application_for_ew where applicated_id in (select employee_id from employee where sector_id=?)";
		return (List<ApplicationForEW>)jdbcTemplate.query(sql, new Object[] {sectorId},new ApplicationForEWRowMapper() {});
	}
	
	
	@Override
	public List<ApplicationForEW> getEmployeeInfoApplicationForEW(int employeeId) {
		// TODO Auto-generated method stub
		String sql = "select * from application_for_ew where applicated_id = ?";
        return (List<ApplicationForEW>)jdbcTemplate.query(sql, new Object[] {employeeId},new ApplicationForEWRowMapper() {
			
		});
		
	}
	
	//某人未批的加班表
	@Override
	public List<ApplicationForEW> updateEmployeeInfoApplicationForEW( int applicatedId) {
		// TODO Auto-generated method stub
		String sql = "select * from application_for_ew where applicated_id = ? and ew_state = 0";
		return (List<ApplicationForEW>)jdbcTemplate.query(sql, new Object[] {applicatedId},new ApplicationForEWRowMapper() {});
		
	}
	
	//已批记录
	@Override
	public List<ApplicationForEW> updateUNEmployeeInfoApplicationForEW( int applicatedId) {
		// TODO Auto-generated method stub
		String sql = "select * from application_for_ew where applicated_id = ? and ew_state = 1";
		return (List<ApplicationForEW>)jdbcTemplate.query(sql, new Object[] {applicatedId},new ApplicationForEWRowMapper() {
			
		});
		
	}


	@Override
	public int RatifyEW(ApplicationForEW applicationForEW) {
		String sql = "update application_for_ew set ew_state=?, ratify_id=? where ew_id=?";
		int i = jdbcTemplate.update(sql,new Object[]{applicationForEW.getEwState(),applicationForEW.getRatifyId(),applicationForEW.getEwId()});
		return i;
	}


	//获取每个部门某天加班人数
	@Override
	public int applicatedNumberBySectorId(int sectorId) {
		// TODO Auto-generated method stub
		Date date = new Date();
		SimpleDateFormat dxf = new SimpleDateFormat("yyyyMMdd");
		String sql = "SELECT * from application_for_ew WHERE applicated_id in "
				+ "(select employee_id FROM employee WHERE sector_id = ?) "
				+ "and (? >= start_time and ? <= end_time) and ew_state = 1";
		List<ApplicationForEW> list = jdbcTemplate.query(sql, new Object[] {sectorId,dxf.format(date),dxf.format(date)},new ApplicationForEWRowMapper() {
			
		});
		return list.size();
	}

	//获取公司当天加班人数
	@Override
	public int applicatedNumberAll() {
		// TODO Auto-generated method stub
		Date date = new Date();
		SimpleDateFormat dxf = new SimpleDateFormat("yyyyMMdd");
		String sql = "SELECT * from application_for_ew  "
				+ "where (? >= start_time and  ? <= end_time) and ew_state = 1";
		List<ApplicationForEW> list = jdbcTemplate.query(sql, new Object[] {dxf.format(date),dxf.format(date)},new ApplicationForEWRowMapper() {

		});
		System.out.println(dxf.format(date));
		System.out.println(list);
		return list.size();
	}

	

}
