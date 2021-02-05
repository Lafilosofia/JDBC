package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

/**
 * �ʺ�ִ�о�̬SQL���Ҳ����SQL�����û��ƴ�Ӷ�̬����
 * PrepareStatement��java.sql�����һ���ӿ�,����ִ��sql����ѯ
 * ͨ������connection.prepareStatement(sql)�������Ի�ȡPrepareStatement
 * ����,���ݿ�ϵͳ���sql������Ԥ���봦��
 * ������佫��Ԥ�����,���������sql��ѯ�������ڽ����Ĳ�ѯ������
 * ����һ��,����Statement�������ɲ�ѯ���ٶȸ���
 * 
 * Statementʹ��Statement�����ڶ����ݿ�ִֻ��һ���Դ�ȡ��ʱ��,��Statement������д���
 * 
 * PrepareStatement����Ŀ�����Statement��,����һ���Բ����������������ĺô�,ѡ��PrepareStatement
 * ����Statementȡ������ѹ����ôʹ������,����ִֻ��һ�ε�sql���ѡ��Statement����õ�,�෴��sqlִ�ж�����
 * ѡ��PrepareStatement����õ�
 * 
 * PrepareStatement�ĵ�һ��ִ�������Ǻܸߵ�,�������������ں�����ظ�ִ��
 * @author HP
 *
 */
public class PrepareStatementDemo {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("�������û���:");
		String name = scanner.next();
		System.out.println("����������:");
		String pwd = scanner.next();
		System.out.println("�������Ա�");
		String gender = scanner.next();
		Connection connection = null;
		try {
			//���ݿ�����
			connection = DButil.getConnection();
			//ͨ��Connection����PrepareStatement����
			String sql = "insert into myjdbc values (null,?,?,?)";
			PreparedStatement preparestatement = connection.prepareStatement(sql);
			preparestatement.setString(1, name);
			preparestatement.setString(2, pwd);
			preparestatement.setString(3, gender);
			int n = preparestatement.executeUpdate();
			if (n > 0) {
				System.out.println("����ɹ�!");
			}else {
				throw new RuntimeException("����ʧ��!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DButil.closeConnection(connection, null,null);
		}
	}
		
}
