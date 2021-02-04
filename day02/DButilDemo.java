package day02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import javax.management.RuntimeErrorException;

/**
 * �˹����ฺ���������ݿ���ͷ�������Դ
 * @author HP
 *
 */
public class DButilDemo {
	//����
	private static String driver;
	//��ַ
	private static String url;
	//�˺�
	private static String username;
	//����
	private static String password;
	
	static{
		//Properties������Ҫ���ڶ�ȡJava�������ļ�,��ͬ�ı���������Լ���֧�ֵ������ļ�
		try {
			//��ʽ1
			Properties properties = new Properties();
			//properties.load(new FileInputStream("db.properties"));
			//��ʽ2 ��ȡ������Դ���ļ�
			properties.load(DButilDemo.class.getClassLoader().getResourceAsStream("db.properties"));
			driver = properties.getProperty("drivername");
			url = properties.getProperty("url");
			username = properties.getProperty("username");
			password = properties.getProperty("password");
			System.out.println("�������ݿ��ʼ��...");
			System.out.println(driver);
			System.out.println(url);
			System.out.println(username);
			System.out.println(password);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	//�������ݿ�����
	public static Connection getConnection() {
		try {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url,username,password);
			System.out.println("���ӳɹ�");
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("����ʧ��");
		}
		
	}
	
	//�ͷ���Դ
	public static void closeConnection(Connection connection){
		try {
			if (connection != null) 
				connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Connection connection = DButilDemo.getConnection();
		System.out.println(connection);
	}
	
}
