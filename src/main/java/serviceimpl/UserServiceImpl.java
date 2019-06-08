package serviceimpl;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bean.User;
import dao.UserDao;
import service.UserService;

@Service 
public class UserServiceImpl implements UserService{

	
	@Autowired 
	private  UserDao userDao;
	
	@Override
	public List<User> searchUserByIdAndPasswd(User user) {
		// TODO Auto-generated method stub
		return userDao.searchUserByIdAndPasswd(user);
	}

	@Override
	public String ModifyUser(User user) {
		
		int i = userDao.ModifyUser(user);
		JSONObject ans=new JSONObject();
		ans.put("state", i);
		
		return ans.toString();
	}
	
	
	
	
	/**
	 * @author ENNIE
	 * @return 返回一个user对象。如果不存在user对象，返回空。
	 */
	@Override
	public User findUserById(int id) {
		if(userDao.findUserById(id).isEmpty())return null;
		else return userDao.findUserById(id).get(0);
	}
     
}
