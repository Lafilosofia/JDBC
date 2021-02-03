package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 查询数据
 * @author HP
 * ResultSet:表示查询的结果集,遍历结果即可获得查询的具体数据;
 * ResultSet executedQuery(String sql)(查询)专门来执行DQL语句,返回查询的结果集用ResultSet实力返回
 * 
 */
public class JDBCDemo5 {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/store_ykt";
			String username = "root";
			String password = "XIR712LHZ";
			conn = DriverManager.getConnection(url,username,password);
			Statement stat = conn.createStatement();
			
			String sql = "select * from myjdbc";
			 //见到ResultSet就用while或if查询数据用while 判断用if
			 //ResultSet方法提供了.next()
			//ResultSet提供了getXX(int column根据数据字段的列获取元素)
			//ResultSet提供了getXX(String column根据数据字段名获取元素)
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()){
				//根据数据库字段获取字段名列的数据
				int id = rs.getInt("id");
				String name = rs.getString("user_name");
				String pwd = rs.getString("password");
				char gender = rs.getString("gender").charAt(0);
				System.out.println("id" + "user_name" + "password" + "gender");
			}
			
			
		} catch (Exception e) {
			
		}finally{
			try {
				//释放资源
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
