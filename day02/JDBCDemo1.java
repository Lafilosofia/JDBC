package day02;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import javax.management.RuntimeErrorException;

/**
 * ����Ա����Ų�ѯ�쵼�ĸ��˹��ʺͱ��
 * @author HP
 *
 */
public class JDBCDemo1 {
	public static void main(String[] args) {
		 Connection connection = null;
		 try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("������Ҫ��ѯ��Ա�����:");
			int empno = scanner.nextInt();
			//��ȡ����
			connection = DButilDemo.getConnection();
			Statement statement = connection.createStatement();
			String sql = "select * from emp where empno = (select empno from emp where empno = "+ empno +")";
			ResultSet resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				int en = resultSet.getInt("empno");
				double sal = resultSet.getDouble("sal");
				System.out.println("��ѯ�ɹ�!�쵼�Ĺ�����:" + sal +",�쵼�ı����:" + en);
			}else {
				throw new RuntimeException("��ѯʧ��!�Ҳ�����Ա�����");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DButilDemo.closeConnection(connection);
		}
	}
}
