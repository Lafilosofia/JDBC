package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 根据id删除用户数据
 * @author HP
 *
 */
public class JDBCDemo3 {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/store_ykt";
			String username = "root";
			String password = "XIR712LHZ";
			conn = DriverManager.getConnection(url,username,password);
			Statement stat = conn.createStatement();
			
			System.out.println("请输入id");
			Scanner sc = new Scanner(System.in);
			int id = sc.nextInt();
			
			
			String sql = "delete from myjdbc where id = " + id;
			int n = stat.executeUpdate(sql);
			System.out.println(n);
			if(n > 0)
				System.out.println("删除成功");
			else
				throw new RuntimeException("耶巴蒂");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
