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
 * 此工具类负责连接数据库和释放连接资源
 * @author HP
 *
 */
public class DButilDemo {
	//驱动
	private static String driver;
	//地址
	private static String url;
	//账号
	private static String username;
	//密码
	private static String password;
	
	static{
		//Properties该类主要用于读取Java的配置文件,不同的编程语言有自己所支持的配置文件
		try {
			//方式1
			Properties properties = new Properties();
			//properties.load(new FileInputStream("db.properties"));
			//方式2 读取加载资源或文件
			properties.load(DButilDemo.class.getClassLoader().getResourceAsStream("db.properties"));
			driver = properties.getProperty("drivername");
			url = properties.getProperty("url");
			username = properties.getProperty("username");
			password = properties.getProperty("password");
			System.out.println("连接数据库初始化...");
			System.out.println(driver);
			System.out.println(url);
			System.out.println(username);
			System.out.println(password);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	//返回数据库连接
	public static Connection getConnection() {
		try {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url,username,password);
			System.out.println("连接成功");
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("连接失败");
		}
		
	}
	
	//释放资源
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
