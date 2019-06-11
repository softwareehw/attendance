package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.Manager;

public class ManagerRowMapper implements RowMapper<Manager>{

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

}
