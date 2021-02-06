package day03;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.PseudoColumnUsage;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.plaf.basic.BasicToolBarUI.DockingListener;

import day02.DButil;

/**
 * DAO(Data Access Object):��װ�����ݷ����߼��Ķ���;
 * DAO��Ϊ���ݷ��ʲ�,��ҵ����߼������ݷָ�����,ҵ���߼���
 * ��Ҫ���ݾ�ҪDAOҪ����,
 * ҵ���߼���Ҫ�������ݾ�Ҫ��DAO,��DAOȥ����ҵ���߼�,
 * ��λ�ȡ����,��α�������,������DAO����
 * @author HP
 *
 */
public class UserDao {
	//�û�ע��
	public int insertUser(User user){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int n = 0;
		try {
			connection = DButil.getConnection();
			String sql = "insert into user values (null, ?, ?, ?, ?)";
			preparedStatement  = connection.prepareStatement(sql);
			preparedStatement .setString(1, user.getUserName());
			preparedStatement .setString(2, user.getPwd());
			preparedStatement .setDouble(3, user.getUserSal());
			preparedStatement .setInt(4, user.getUserAge());
			n = preparedStatement .executeUpdate();
			return n;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DButil.closeConnection(connection, null, preparedStatement );
		}
		return 0;
	}
	
	//�����û�idɾ���û�����
	public int deleteUserById(int id){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int n = 0;
		try {
			connection = DButil.getConnection();
			String sql = "delete from user where id = ?";
			preparedStatement = connection.prepareStatement(sql);
		    preparedStatement.setInt(1, id);
		    n = preparedStatement.executeUpdate();
		    return 0;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("����ʧ��");
		}finally {
			DButil.closeConnection(connection, null, preparedStatement);
		}
	}
	
	//�û���¼�ɹ������޸��û�����
	public int changeUserpassword(User user,String newPwd){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DButil.getConnection();
			String sql = "select user_name,password from user where user_name = ? and password = ?" ;
			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setString(2, user.getPwd());
			resultSet = preparedStatement.executeQuery(sql);
			if (resultSet.next()) {
				System.out.println("��¼�ɹ�,������������:");
				String sql2 = "update user set user_password = ? where user_name = ?";
				preparedStatement = connection.prepareStatement(sql2);
				preparedStatement.setString(1, newPwd);
				preparedStatement.setString(2, user.getUserName());
				int n = preparedStatement.executeUpdate();
			}else{ 
				throw new RuntimeException("��¼ʧ��");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DButil.closeConnection(connection, null, null);
		}
		
		return 0;
	}
	
	//�����û��ı�Ų�ѯ��������
	public String findDnameByempno(int empno){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DButil.getConnection();
			String sql = "select d.dname from emp e join dept d on e.deptno = d.deptno where id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, empno);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String dname = resultSet.getString("d.name");
				return dname;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DButil.closeConnection(connection, resultSet, preparedStatement);
		}
		return null;
	}
	
	//��ѯ�����û�������
	public List<User> findUserAll(){
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
			while (resultSet.next()) {
				User user = new User();
				//��װ���ݿ��е����ݵ�User��
				user.setId(resultSet.getInt("id"));
				user.setUserName(resultSet.getString("user_name"));
				user.setPwd(resultSet.getString("user_password"));
				user.setUserAge(resultSet.getInt("user_age"));
				list.add(user);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DButil.closeConnection(connection, resultSet, preparedStatement);
		}
		return null;
	}
	
	public List<Map<String, Object>> findUserAll2() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try {
			connection = DButil.getConnection();
			String sql = "select * from user";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				Map<String, Object> map = new HashMap<String,Object>();
				map.put("id", resultSet.getInt("id"));
				map.put("name", resultSet.getString("user_name"));
				map.put("password", resultSet.getString("user_password"));
				map.put("age", resultSet.getInt("user_age"));
				map.put("sal", resultSet.getDouble("sal"));
				list.add(map);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeConnection(connection, resultSet, preparedStatement);
		}
		return null;
	}
	
	public Map<String, Object> findUserAll3(){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DButil.getConnection();
			Map<String, Object> map = new HashMap<String,Object>();
			List<User> list = new ArrayList<User>();
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
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DButil.closeConnection(connection, resultSet, preparedStatement);
		}
		return null;
	}
}
