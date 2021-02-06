package day03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import day02.DButil;

/**
 * ����id��ѯ�û����ݲ���װ�ڶ�����
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
		//ҵ��
		Connection connection= null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User user = null;
		try {
			//1.�������ݿ�
			connection = DButil.getConnection();
			//2.����id��ѯ�û�����
			String sql = "select * from user where id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			//3.����ѯ���������ݷ�װ��User������
			while(resultSet.next()){
				int userId = resultSet.getInt("id");
				String name = resultSet.getString("user_name");
				String pwd = resultSet.getString("user_password");
				double sal = resultSet.getDouble("sal");
				int age = resultSet.getInt("user_age");
				//��ѯ���������ݷ�װ��User������
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

