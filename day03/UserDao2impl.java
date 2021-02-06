package day03;

import java.security.PrivilegedAction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import day02.DButil;

public class UserDao2impl implements UserDao2{

	@Override
	public void insert(User user) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int n = 0;
		try {
			connection = DButil.getConnection();
			String sql = "insert into user values(null,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, user.getId());
			preparedStatement .setString(2, user.getUserName());
			preparedStatement .setString(3, user.getPwd());
			preparedStatement .setDouble(4, user.getUserSal());
			preparedStatement .setInt(5, user.getUserAge());
			n = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DButil.closeConnection(connection, null, preparedStatement);
		}
	}

	@Override
	public int deleteUserById(int id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DButil.getConnection();
			String sql = "delete from user where id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("≤Â»Î ß∞‹");
		}finally {
			DButil.closeConnection(connection, null, preparedStatement);
		}
		return 0;
	}

	@Override
	public int updateUserById(int id,String newPwd) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DButil.getConnection();
			String sql = "update user set user_password = ? where id = ?";
			preparedStatement.setInt(1, id);
			preparedStatement.setString(3, newPwd);
			preparedStatement = connection.prepareStatement(sql);
			int n  = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DButil.closeConnection(connection, null, preparedStatement);
		}
		return id;
	}

	@Override
	public User findUserById(int id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User user = null;
		try {
			connection = DButil.getConnection();
			String sql = "select * from user where id = ?";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user = new User();
				/*int id2 = resultSet.getInt("id");
				String name = resultSet.getString("user_name");
				String password = resultSet.getString("user_password");
				double sal = resultSet.getDouble("sql");
				int age = resultSet.getInt("user_age");
				user.setId(id2);
				user.setUserName(name);
				user.setPwd(password);
				user.setUserAge(age);
				user.setUserSal(sal);*/
				user.setId(resultSet.getInt("id"));
				user.setUserName(resultSet.getString("user_name"));
				user.setPwd(resultSet.getString("user_password"));
				user.setUserAge(resultSet.getInt("user_age"));
				user.setUserSal(resultSet.getDouble("sal"));
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("≤È’“ ß∞‹");
		}finally {
			DButil.closeConnection(connection, resultSet, preparedStatement);
		}
		
	}

	@Override
	public List<User> findUserAll() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<User> list = null;
		try {
			list = new ArrayList<User>();
			connection = DButil.getConnection();
			String sql = "select * from user";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setUserName(resultSet.getString("user_name"));
				user.setPwd(resultSet.getString("user_password"));
				user.setUserAge(resultSet.getInt("user_age"));
				user.setUserSal(resultSet.getDouble("sal"));
				list.add(user);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("≤È—Ø ß∞‹");
		}finally {
			DButil.closeConnection(connection, resultSet, preparedStatement);
		}
	}
	
	public Map<String, Object> find(){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			List<User> list = new ArrayList<>();
			connection = DButil.getConnection();
			String sql = "select * from user";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setUserName(resultSet.getString("user_name"));
				user.setPwd(resultSet.getString("user_password"));
				user.setUserAge(resultSet.getInt("user_age"));
				user.setUserSal(resultSet.getDouble("sal"));
				list.add(user);
			}
			map.put("list", list);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("≤È—Ø ß∞‹");
		}finally {
			DButil.closeConnection(connection, resultSet, preparedStatement);
		}
		return null;
	}

}
