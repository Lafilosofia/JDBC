package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * .�޸����� �����û��Ƿ��½�ɹ��޸�����
 * @author HP
 *
 */
public class JDBCDemo7 {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/store_ykt";
			String username = "root";
			String password = "XIR712LHZ";
			conn = DriverManager.getConnection(url,username,password);
			Statement stat = conn.createStatement();
			Scanner scanner = new Scanner(System.in);
			System.out.println("�������û���:");
			String name = scanner.next();
			System.out.println("����������");
			String pwd = scanner.next();
			String sql = "select * from myjdbc where user_name = '" + name + "'and password = " + pwd;
			ResultSet rs = stat.executeQuery(sql);
			if (rs.next()) {
				System.out.println("��¼�ɹ�!������������");
			    String newPwd = scanner.next();
			    String sql2 = "update myjdbc set password = " + newPwd + "where user_name =" + name ;
			    stat.executeUpdate(sql2);
			}
			else
				throw new RuntimeException("��¼ʧ��!");
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
