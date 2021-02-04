package day02;
/**
 * 数据库连接的管理
 * DBCP 数据库连接池是一个Java连接池项目,DBCP通过连接池预先同数据库建立一个连接
 * 放在内存中(即连接池),应用程序需要建立数据库连接时,直接从连接池中申请一个连接使用
 * 用完后有连接池回收该数据,从而达到连接的复用,减少资源消耗的目的
 * 
 * 连接池来管理Connection,这样可以重复使用Connection,有了连接池,所有我们就不用自己创建
 * Connection,而是通过池来获取Connection对象,当使用Connection调用Connection的close方法
 * 不会真的关闭Connection,而是把Connection"归还"给连接池,池就可以再次利用这个Connection对象
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
	//连接池对象
	private static BasicDataSource ds;
	static{
		try {
			Properties properties = new Properties();
			//读取加载资源或文件
			properties.load(DButilDemo.class.getClassLoader().getResourceAsStream("db.properties"));
			
			//mysql驱动
			String driver = properties.getProperty("drivername");
			String url = properties.getProperty("url");
			String username = properties.getProperty("username");
			String password = properties.getProperty("password");
			
			//获取最大连接
			int maxActive = Integer.parseInt(properties.getProperty("maxActive"));
			
			//最大等待时间
			int maxWait = Integer.parseInt(properties.getProperty("maxWait"));
			System.out.println("初始化数据库...");
			System.out.println(driver);
			System.out.println(url);
			System.out.println(username);
			System.out.println(password);
			
			//创建数据库连接池
			ds = new BasicDataSource();
			
			//设置驱动Class.forName
			ds.setDriverClassName(driver);
			
			//设置地址
			ds.setUrl(url);
			
			//设置用户名
			ds.setUsername(username);
			
			//设置密码
			ds.setPassword(password);
			
			//设置最大连接数
			ds.setMaxActive(maxActive);
			
			//设置最大等待时间
			ds.setMaxWait(maxWait);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		Connection connection;
		try {
			/* 向连接池获取连接,若连接池中没有可用的连接时,该方法会阻塞当前线程
			 * 阻塞时间有连接池设置maxWait决定,当阻塞过程中连接池有了连接那么
			 * 则会立刻请求返回,若超市仍然可用连接,该方法会抛出异常*/
			connection = ds.getConnection();
			System.out.println("连接成功");
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("连接异常,稍后再试...");
		}
	}
	
	//归还资源
	public static void closeConnection(Connection connection,ResultSet resultSet,PreparedStatement preparestatement){
		try {
			/*连接池的连接对于close方法的处理将连接池中的状态
			 * 设置为空闲,而不是将其关闭*/
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
