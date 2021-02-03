package day01;
/**
 * ��ѯԱ����������Ϣ�Ͳ��ű�������Ϣ����Ա����Ĺ��ʽ�������
 * ÿҳ��ʱ������,��ʾ����ҳ������
 */
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.management.RuntimeErrorException;
import javax.swing.border.EmptyBorder;
import javax.xml.stream.events.StartDocument;




public class JDBCDemo8 {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("������ҳ��:");
			int page = scanner.nextInt();
			System.out.println("������ÿҳ��ʾ������");
			int tiao = scanner.nextInt();
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/store_ykt";
			String username = "root";
			String password = "XIR712LHZ";
			conn = DriverManager.getConnection(url,username,password);
			Statement stat = conn.createStatement();
			String sql = "select * from emp,dept where emp.deptno = dept.deptno order by emp.sal limit " + (page - 1 ) * tiao + "," + tiao;
			ResultSet rs = stat.executeQuery(sql);
			
			while(rs.next()){
				int empno = rs.getInt("emp.empno");
				String ename = rs.getString("emp.ename");
				String job = rs.getString("emp.job");
				int mgr = rs.getInt("emp.mgr");
				Date hiredada = rs.getDate("emp.hiredada");
				double sal = rs.getDouble("emp.sal");
				double comm = rs.getDouble("emp.comm");
				int deptno = rs.getInt("emp.deptno");
				String dname = rs.getString("dept.dname");
				String loc = rs.getString("dept.loc");
				System.out.println(empno + "," + ename + "," + job + "," + mgr + "," + hiredada + "," + sal + "," + comm + "," + deptno + "," + dname + "," + loc);
			}
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
