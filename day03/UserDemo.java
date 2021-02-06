package day03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import day02.DButil;

/**
 * 根据id查询用户数据并封装在对象中
 * @author HP
 *
 */
public class UserDemo {
	
	public static void main(String[] args) {
		UserDemo demo = new UserDemo();
		User use = demo.findUserById(1);
		System.out.println(use);
	}
	
	public User findUserById(int id){
		//业务
		Connection connection= null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User user = null;
		try {
			//1.连接数据库
			connection = DButil.getConnection();
			//2.根据id查询用户数据
			String sql = "select * from user where id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			//3.将查询出来的数据封装在User对象中
			while(resultSet.next()){
				int userId = resultSet.getInt("id");
				String name = resultSet.getString("user_name");
				String pwd = resultSet.getString("user_password");
				double sal = resultSet.getDouble("sal");
				int age = resultSet.getInt("user_age");
				//查询出来的数据封装在User对象中
				user = new User();
				user.setId(userId);
				user.setUserName(name);
				user.setPwd("user_password");
				user.setUserSal(sal);
				user.setUserAge(age);
				
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DButil.closeConnection(connection, resultSet, preparedStatement);
		}
		
		return null;
	}
	
}

