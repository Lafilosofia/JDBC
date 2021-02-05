package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 适合执行静态SQL语句也就是SQL语句中没有拼接动态数据
 * PrepareStatement是java.sql下面的一个接口,用来执行sql语句查询
 * 通过调用connection.prepareStatement(sql)方法可以获取PrepareStatement
 * 对象,数据库系统会对sql语句进行预编译处理
 * 处理语句将被预编译好,这条编译的sql查询语句可能在将来的查询中重用
 * 这样一来,它比Statement对象生成查询的速度更快
 * 
 * Statement使用Statement对象在对数据库只执行一次性存取的时候,用Statement对象进行处理
 * 
 * PrepareStatement对象的开销比Statement大,对于一次性操作并不会带来额外的好处,选择PrepareStatement
 * 还是Statement取决于你压迫怎么使用它们,对于只执行一次的sql语句选择Statement是最好的,相反用sql执行多次语句
 * 选用PrepareStatement是最好的
 * 
 * PrepareStatement的第一次执行消耗是很高的,它的性能体现在后面的重复执行
 * @author HP
 *
 */
public class PrepareStatementDemo {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入用户名:");
		String name = scanner.next();
		System.out.println("请输入密码:");
		String pwd = scanner.next();
		System.out.println("请输入性别");
		String gender = scanner.next();
		Connection connection = null;
		try {
			//数据库连接
			connection = DButil.getConnection();
			//通过Connection创建PrepareStatement对象
			String sql = "insert into myjdbc values (null,?,?,?)";
			PreparedStatement preparestatement = connection.prepareStatement(sql);
			preparestatement.setString(1, name);
			preparestatement.setString(2, pwd);
			preparestatement.setString(3, gender);
			int n = preparestatement.executeUpdate();
			if (n > 0) {
				System.out.println("插入成功!");
			}else {
				throw new RuntimeException("插入失败!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DButil.closeConnection(connection, null,null);
		}
	}
		
}
