package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ��ѯ����
 * @author HP
 * ResultSet:��ʾ��ѯ�Ľ����,����������ɻ�ò�ѯ�ľ�������;
 * ResultSet executedQuery(String sql)(��ѯ)ר����ִ��DQL���,���ز�ѯ�Ľ������ResultSetʵ������
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
			 //����ResultSet����while��if��ѯ������while �ж���if
			 //ResultSet�����ṩ��.next()
			//ResultSet�ṩ��getXX(int column���������ֶε��л�ȡԪ��)
			//ResultSet�ṩ��getXX(String column���������ֶ�����ȡԪ��)
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()){
				//�������ݿ��ֶλ�ȡ�ֶ����е�����
				int id = rs.getInt("id");
				String name = rs.getString("user_name");
				String pwd = rs.getString("password");
				char gender = rs.getString("gender").charAt(0);
				System.out.println("id" + "user_name" + "password" + "gender");
			}
			
			
		} catch (Exception e) {
			
		}finally{
			try {
				//�ͷ���Դ
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
