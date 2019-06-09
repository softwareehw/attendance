package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bean.User;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		
		int id = rs.getInt("id");
		String password = rs.getString("password");
		int degree = rs.getInt("degree");
		
		User user = new User();
		user.setId(id);
		user.setPassword(password);
		user.setDegree(degree);
		
		
		return user;
	}

}
