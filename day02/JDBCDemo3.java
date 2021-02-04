package day02;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import javax.print.attribute.standard.MediaName;

/**
 * ����Ա����Ų�ѯ�쵼�����Ĳ�����Ϣ
 * @author HP
 *
 */
public class JDBCDemo3 {
		public static void main(String[] args) {
			Connection connection = null;
			try {
				Scanner scanner = new Scanner(System.in);
				System.out.println("������Ա�����:");
				int empno = scanner.nextInt();
				connection = DButilDemo.getConnection();
				Statement statement = connection.createStatement();
				String sql = "select d.loc,d.dname,d.deptno from emp e join dept d on e.deptno = d.deptno where e.mgr = (select e.mgr from emp where mgr = "+ empno +") ";
				ResultSet resultSet = statement.executeQuery(sql);
				if (resultSet.next()) {
					String dloc = resultSet.getString("loc");
					String dname = resultSet.getString("dname");
					int deptno = resultSet.getInt("deptno");
					System.out.println("���ŵ�ַ��:" + dloc + "����������:" + dname + "���ź���:" + deptno);
				}else
					throw new RuntimeException("��ѯʧ��!");
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				DButil.closeConnection(connection, null,null);
			}
		}
}
