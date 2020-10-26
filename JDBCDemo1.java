package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC是SUN提供的一套用于操作数据库的标准接口,不同的数据库厂商都提供了 一套JDBC接口的实现,打成一个jar包发布,各个包叫驱动包.
 * JDBC接口中提供了: DriverManager:加载驱动并负责与数据库连接
 * Connection:表示与数据库的连接,负责管理事务,创建执行SQL语句的对象Statement;
 * Statement:用来执行SQL语句,若执行的是查询语句,会得到查询的结果集ResultSet;
 * ResultSet:表示查询的结果集,遍历结果即可获得查询的具体数据;
 * 
 * @author LENOVO
 *
 */
public class JDBCDemo1 {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			// 1.加载驱动包,不同的数据库参数值不一样
			Class.forName("com.mysql.jdbc.Driver");

			// 2.简历连接,不同的数据库有不同的连接格式
			// 例如:MySQL数据库地址 jdbc:mysql://数据库IP地址:端口号/数据库名
			String url = "jdbc:mysql://localhost:3306/store_ykt";

			// 3.数据库连接账号
			String username = "root";

			// 4.数据库连接密码
			String password = "XIR712LHZ";

			/*
			 * DriverManager提供了连接数据库的方法 getConnection(url, user,
			 * password)返回Connection
			 */
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("建立连接!");
			//通过Connection创建用户用于执行SQL语句的Statement语句
			Statement stat = conn.createStatement();
			//创建表
			String sql = "create table myjdbc(id int primary key auto_increment,user_name varchar(10),password varchar(10),gender char(1))";
			/*
			 * Statement执行sql语句的相关方法
			 * int executedUpdate(String url) 若返回1则执行成功,返回0则执行失败
			 * 这是专门用表执行DML语句
			 * 
			 * ResultSet executedQuery(String sql)(查询)专门来执行DQL语句,返回查询的结果集
			 * 用ResultSet实力返回
			 * 
			 * boolean executed(String sql)可以执行任何类型的SQL语句,但由于DML,DQL,都是
			 * 专门的方法执行,所有该方法一般用来执行DDL语句,返回值为执行后是否有结果
			 * */
			
			//用Statement执行sql语句
			stat.execute(sql);
			System.out.println("创建完毕");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}