package serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import bean.User;
import dao.UserDao;
import service.UserService;

public class UserServiceImpl implements UserService{

	
	@Autowired 
	private  UserDao userDao;
	
	@Override
	public List<User> searchUserByIdAndPasswd(User user) {
		// TODO Auto-generated method stub
		return userDao.searchUserByIdAndPasswd(user);
	}
     
}
