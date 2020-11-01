package day02;
/**
 * ���ݿ����ӵĹ���
 * DBCP ���ݿ����ӳ���һ��Java���ӳ���Ŀ,DBCPͨ�����ӳ�Ԥ��ͬ���ݿ⽨��һ������
 * �����ڴ���(�����ӳ�),Ӧ�ó�����Ҫ�������ݿ�����ʱ,ֱ�Ӵ����ӳ�������һ������ʹ��
 * ����������ӳػ��ո�����,�Ӷ��ﵽ���ӵĸ���,������Դ���ĵ�Ŀ��
 * 
 * ���ӳ�������Connection,���������ظ�ʹ��Connection,�������ӳ�,�������ǾͲ����Լ�����
 * Connection,����ͨ��������ȡConnection����,��ʹ��Connection����Connection��close����
 * ������Ĺر�Connection,���ǰ�Connection"�黹"�����ӳ�,�ؾͿ����ٴ��������Connection����
 * @author HP
 *
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.management.RuntimeErrorException;

import org.apache.commons.dbcp.BasicDataSource;




public class DButil{
	//���ӳض���
	private static BasicDataSource ds;
	static{
		try {
			Properties properties = new Properties();
			//��ȡ������Դ���ļ�
			properties.load(DButilDemo.class.getClassLoader().getResourceAsStream("db.properties"));
			
			//mysql����
			String driver = properties.getProperty("drivername");
			String url = properties.getProperty("url");
			String username = properties.getProperty("username");
			String password = properties.getProperty("password");
			
			//��ȡ�������
			int maxActive = Integer.parseInt(properties.getProperty("maxActive"));
			
			//���ȴ�ʱ��
			int maxWait = Integer.parseInt(properties.getProperty("maxWait"));
			System.out.println("��ʼ�����ݿ�...");
			System.out.println(driver);
			System.out.println(url);
			System.out.println(username);
			System.out.println(password);
			
			//�������ݿ����ӳ�
			ds = new BasicDataSource();
			
			//��������Class.forName
			ds.setDriverClassName(driver);
			
			//���õ�ַ
			ds.setUrl(url);
			
			//�����û���
			ds.setUsername(username);
			
			//��������
			ds.setPassword(password);
			
			//�������������
			ds.setMaxActive(maxActive);
			
			//�������ȴ�ʱ��
			ds.setMaxWait(maxWait);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		Connection connection;
		try {
			/* �����ӳػ�ȡ����,�����ӳ���û�п��õ�����ʱ,�÷�����������ǰ�߳�
			 * ����ʱ�������ӳ�����maxWait����,���������������ӳ�����������ô
			 * ����������󷵻�,��������Ȼ��������,�÷������׳��쳣*/
			connection = ds.getConnection();
			System.out.println("���ӳɹ�");
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("�����쳣,�Ժ�����...");
		}
	}
	
	//�黹��Դ
	public static void closeConnection(Connection connection,ResultSet resultSet,PreparedStatement preparestatement){
		try {
			/*���ӳص����Ӷ���close�����Ĵ������ӳ��е�״̬
			 * ����Ϊ����,�����ǽ���ر�*/
			if (connection != null ) 
				connection.close();
			
			if (preparestatement != null) 
				preparestatement.close();
			
			if (resultSet != null) 
				resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		DButil.getConnection();
	}

}
