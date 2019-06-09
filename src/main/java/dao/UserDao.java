package dao;

import java.util.List;

import org.springframework.stereotype.Service;

import bean.User;

@Service
public interface UserDao {
	List<User> searchUserByIdAndPasswd(User user);
	List<User> findUserById(int id);
	int ModifyUser(User user);

}
