package day03;

import java.util.List;

public interface UserDao2 {
	//用户注册
	void insert(User user);
	//根据id删除用户注册数据
	int deleteUserById(int id);
	//根据用户名修改用户密码
	int updateUserById(int id,String newPwd);
	//根据用户id查询用户数据
	User findUserById(int id);
	//查询所有用户数据
	List<User> findUserAll();
	}
