package day01;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 给myjdbc表中插入相关数据
 * @author HP
 *
 */
public class JDBCDemo2 {
	public static void main(String[] args) {
		Connection conn = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/store_ykt";
			String username = "root";
			String password = "XIR712LHZ";
			//获取连接
			conn = DriverManager.getConnection(url,username,password);
			//通过获取Connection获取连接获取执行aql的实例Statement
			Statement stat = conn.createStatement();
			//要求用户输入用户名,密码,性别插入到myjdbc中
			Scanner sc = new Scanner(System.in);
			System.out.println("请输入用户名:");
			String name = sc.next();
			System.out.println("请输入密码:");
			String pwd = sc.next();
			System.out.println("请输入用户性别");
			char sex =  sc.next().charAt(0);
			String sql = "insert into myjdbc (user_name,password,gender) values('" + name + "','" + pwd + "','" + sex + "') ";
			int n = stat.executeUpdate(sql);
			
			//判断插入是否成功
			if(n > 0)
				System.out.println("插入成功");
			else
				throw new RuntimeException("插入失败");
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//释放资源
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
