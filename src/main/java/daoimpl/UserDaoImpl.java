package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import bean.User;
import dao.UserDao;

@Service
public class UserDaoImpl implements UserDao {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public List<User>  searchUserByIdAndPasswd(User user) {
		// TODO Auto-generated method stub
		
		int id = user.getId();
		String passward = user.getPassword();
		
		String sql = "SELECT * FROM user WHERE ID="+id+" AND PASSWARD="+passward;
        return (List<User>) jdbcTemplate.query(sql, new RowMapper<User>(){

            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            	User user = new User();
            	
            	user.setId(rs.getInt("id"));
            	user.setPassword(rs.getString("passward"));
                
                return user;
            }

        });
		
	}

}
