package day01;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * ��myjdbc���в����������
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
			//��ȡ����
			conn = DriverManager.getConnection(url,username,password);
			//ͨ����ȡConnection��ȡ���ӻ�ȡִ��aql��ʵ��Statement
			Statement stat = conn.createStatement();
			//Ҫ���û������û���,����,�Ա���뵽myjdbc��
			Scanner sc = new Scanner(System.in);
			System.out.println("�������û���:");
			String name = sc.next();
			System.out.println("����������:");
			String pwd = sc.next();
			System.out.println("�������û��Ա�");
			char sex =  sc.next().charAt(0);
			String sql = "insert into myjdbc (user_name,password,gender) values('" + name + "','" + pwd + "','" + sex + "') ";
			int n = stat.executeUpdate(sql);
			
			//�жϲ����Ƿ�ɹ�
			if(n > 0)
				System.out.println("����ɹ�");
			else
				throw new RuntimeException("����ʧ��");
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//�ͷ���Դ
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
