package day02;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 * ���ݲ��ű��,����ò��ŵ�ƽ������,��͹���,��߹���
 * @author HP
 *
 */
public class JDBCDemo2 {
	public static void main(String[] args) {
		Connection connection = null;
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("�����벿�ű��:");
			int deptno = scanner.nextInt();
			connection = DButilDemo.getConnection();
			Statement statement = connection.createStatement();
			String sql = "select avg(sal),min(sal),max(sal) from emp where deptno = " + deptno + ";";
			ResultSet resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				double avgsal = resultSet.getDouble("avg(sal)");
				double minsal = resultSet.getDouble("min(sal)");
				double maxsal = resultSet.getDouble("max(sal)");
				System.out.println("���ŵ�ƽ��������:" + avgsal + ",��͹�����" + minsal + ",��߹�����" + maxsal);
			}else{
				throw new RuntimeException("��ѯʧ��!�Ҳ������ű��!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DButilDemo.closeConnection(connection);
		}
	}
}
