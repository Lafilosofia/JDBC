package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * �����û����������жϵ�¼�Ƿ�ɹ�
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
				System.out.println("��½�ɹ�");
			else
				throw new RuntimeException("�û������������");
			
		} catch (Exception e) {
			
		}finally {
			try {
				//�ͷ���Դ
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
