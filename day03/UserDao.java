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
 * DAO(Data Access Object):封装了数据访问逻辑的对象;
 * DAO作为数据访问层,把业务层逻辑和数据分隔开来,业务逻辑层
 * 需要数据就要DAO要数据,
 * 业务逻辑层要保存数据就要给DAO,让DAO去保存业务逻辑,
 * 如何获取数据,如何保存数据,都是由DAO负责
 * @author HP
 *
 */
public class UserDao {
	//用户注册
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
	
	//根据用户id删除用户数据
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
			throw new RuntimeException("插入失败");
		}finally {
			DButil.closeConnection(connection, null, preparedStatement);
		}
	}
	
	//用户登录成功并且修改用户密码
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
				System.out.println("登录成功,请输入新密码:");
				String sql2 = "update user set user_password = ? where user_name = ?";
				preparedStatement = connection.prepareStatement(sql2);
				preparedStatement.setString(1, newPwd);
				preparedStatement.setString(2, user.getUserName());
				int n = preparedStatement.executeUpdate();
			}else{ 
				throw new RuntimeException("登录失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DButil.closeConnection(connection, null, null);
		}
		
		return 0;
	}
	
	//根据用户的编号查询部门名称
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
	
	//查询所有用户的数据
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
				//封装数据库中的数据到User中
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
