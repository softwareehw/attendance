package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import bean.Employee;
import bean.User;
import dao.UserDao;
import mapper.UserRowMapper;

@Service
public class UserDaoImpl implements UserDao {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public List<User>  searchUserByIdAndPasswd(User user) {
		// TODO Auto-generated method stub
		
		int id = user.getId();
		String passward = user.getPassword();
		
		String sql = "SELECT * FROM user WHERE ID="+id+" AND PASSWORD="+passward;
		
        return (List<User>) jdbcTemplate.query(sql, new RowMapper<User>(){

            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            	User user = new User();
            	
            	user.setId(rs.getInt("id"));
            	user.setPassword(rs.getString("password"));
                
                return user;
            }

        });
		
	}

	@Override
	public int ModifyUser(User user) {
		String sql = "UPDATE USER SET PASSWORD="+user.getPassword()+", DEGREE="+user.getDegree()+" WHERE ID="+user.getId();
		int i = jdbcTemplate.update(sql);
		return i;
	}

	@Override
	public List<User> findUserById(int id) {
		String sql="SELECT * FROM USER WHERE ID="+id;
		return (List<User>)jdbcTemplate.query(sql, new UserRowMapper());
	}
    
	@Override
	public int addUser() {
		String sql = "insert into user(password,degree) value(?,?)";
		jdbcTemplate.update(sql,123456,0);
		int i=0;
		return i;
	}
}
