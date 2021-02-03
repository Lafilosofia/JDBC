package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 根据用户名和密码判断登录是否成功
 * @author HP
 *
 */
public class JDBCDemo6 {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/store_ykt";
			String username = "root";
			String password = "XIR712LHZ";
			conn = DriverManager.getConnection(url,username,password);
			Statement stat = conn.createStatement();
	
			String sql = "select id,user_name,password from myjdbc where user_name = '"+ username +"'and password = '" + password +"'";
		    ResultSet rs = stat.executeQuery(sql);
		    
			if(rs.next())
				System.out.println("登陆成功");
			else
				throw new RuntimeException("用户名或密码错误");
			
		} catch (Exception e) {
			
		}finally {
			try {
				//释放资源
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
