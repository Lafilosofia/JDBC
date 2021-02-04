package day02;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class BasicDataSourceDemo {
	public static void main(String[] args) {
		Thread t1 = new DemoThread(5000, "连接1");
		Thread t2 = new DemoThread(6000, "连接2");
		Thread t3 = new DemoThread(7000, "连接3");
		t1.start();
		t2.start();
		t3.start();
	}
}

class DemoThread extends Thread{
	private int wait;	//睡眠时间
	private String name;	//连接名字
	
	public DemoThread(int wait,String name){
		this.name = name;
		this.wait = wait;
		
	}

	public void run(){
		Connection connection = null;
		ResultSet resultSet  = null;
		try {
			//数据库连接
			connection = DButil.getConnection();
			Statement statement = connection.createStatement();
			String sql = "select * from myjdbc";
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				System.out.println(resultSet.getString("user_name") + "," + resultSet.getString("gender"));
				
			}
			
			Thread.sleep(wait);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DButil.closeConnection(connection, resultSet,null);
		}
	}
}

