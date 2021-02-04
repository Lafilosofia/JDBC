package day02;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import javax.management.RuntimeErrorException;

/**
 * 根据员工编号查询领导的个人工资和编号
 * @author HP
 *
 */
public class JDBCDemo1 {
	public static void main(String[] args) {
		 Connection connection = null;
		 try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("请输入要查询的员工编号:");
			int empno = scanner.nextInt();
			//获取连接
			connection = DButilDemo.getConnection();
			Statement statement = connection.createStatement();
			String sql = "select * from emp where empno = (select empno from emp where empno = "+ empno +")";
			ResultSet resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				int en = resultSet.getInt("empno");
				double sal = resultSet.getDouble("sal");
				System.out.println("查询成功!领导的工资是:" + sal +",领导的编号是:" + en);
			}else {
				throw new RuntimeException("查询失败!找不到该员工编号");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DButilDemo.closeConnection(connection);
		}
	}
}
