package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * �����û�id�޸�����
 * @author HP
 *
 */
public class JDBCDemo4 {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/store_ykt";
			String username = "root";
			String password = "XIR712LHZ";
			conn = DriverManager.getConnection(url, username, password);
			Statement stat = conn.createStatement();
			Scanner sc = new Scanner(System.in);
			System.out.println("������id:");
			int id = sc.nextInt();
			System.out.println("������������:");
			String pwd = sc.next();
			String sql = "update myjdbc set password = '"+ pwd +"'where id = " + id;
			int n = stat.executeUpdate(sql);
			System.out.println(n);
			if(n > 0)
				System.out.println("�޸ĳɹ�");
			else
				throw new RuntimeException("�޸�ʧ��");
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
