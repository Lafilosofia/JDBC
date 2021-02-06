package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 转账功能
 * 输入转出账号的用户名,在输入转入账号的用户名,最后输入要转账的金额完成转载功能
 * @author HP
 *
 */
public class PrepareStatementDemo3 {
	public static void main(String[] args) {
		Connection connection = null;
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入一个转出账号名字:");
		String  outname = scanner.next();
		System.out.println("请输入一个转入账号名字:");
		String inname = scanner.next();
		System.out.println("请输入转帐金额:");
		int money = scanner.nextInt();
		System.out.println("请输入转入金额:");
		double price = scanner.nextDouble();	
		try {
			connection = DButil.getConnection();
			/*
			 * JDBC默认是自动提交事务,也就是当执行一条sql操作后都会自动提交事务
			 * 若希望自动控制事务,需要将自动提交关闭
			 */
			//设置手动提交事务
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
					System.out.println("转帐成功");
					//手动提交
					connection.commit();
				}else {
					System.out.println("转账失败,事务回滚");
					connection.rollback();
				}
			}else {
				System.out.println("用户不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("转账失败");
		}finally {
			DButil.closeConnection(connection, null,null);
		}
		
	}
}
