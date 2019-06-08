package service;

import java.util.List;

import org.springframework.stereotype.Service;

import bean.User;


@Service
public interface UserService {
	List<User> searchUserByIdAndPasswd(User user);
	/**
	 * @author ENNIE
	 * @return 返回一个user对象。如果不存在user对象，返回空。
	 */
	User findUserById(int id);
	public String ModifyUser(User user);
}
