package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

/**
 * ת�˹���
 * ����ת���˺ŵ��û���,������ת���˺ŵ��û���,�������Ҫת�˵Ľ�����ת�ع���
 * @author HP
 *
 */
public class PrepareStatementDemo3 {
	public static void main(String[] args) {
		Connection connection = null;
		Scanner scanner = new Scanner(System.in);
		System.out.println("������һ��ת���˺�����:");
		String  outname = scanner.next();
		System.out.println("������һ��ת���˺�����:");
		String inname = scanner.next();
		System.out.println("������ת�ʽ��:");
		int money = scanner.nextInt();
		System.out.println("������ת����:");
		double price = scanner.nextDouble();	
		try {
			connection = DButil.getConnection();
			/*
			 * JDBCĬ�����Զ��ύ����,Ҳ���ǵ�ִ��һ��sql�����󶼻��Զ��ύ����
			 * ��ϣ���Զ���������,��Ҫ���Զ��ύ�ر�
			 */
			//�����ֶ��ύ����
			String sql1 = "update bank set money = money - " + price + "where name = ?";
			PreparedStatement preparestatement = connection.prepareStatement(sql1);
			preparestatement.setString(1, outname);
			int n1 = preparestatement.executeUpdate();
			if (n1 > 0) {
				String sql2 = "update bank set price = money + " + price + "where name = ?";
				PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
				preparedStatement2.setString(1, inname);
				int n2 = preparedStatement2.executeUpdate();
				if (n2 > 0) {
					System.out.println("ת�ʳɹ�");
					//�ֶ��ύ
					connection.commit();
				}else {
					System.out.println("ת��ʧ��,����ع�");
					connection.rollback();
				}
			}else {
				System.out.println("�û�������");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ת��ʧ��");
		}finally {
			DButil.closeConnection(connection, null,null);
		}
		
	}
}
