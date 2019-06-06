package service;

import java.util.List;

import bean.User;

public interface UserService {
	List<User> searchUserByIdAndPasswd(User user);
}
