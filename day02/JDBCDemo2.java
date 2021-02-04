package day02;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 根据部门编号,求出该部门的平均工资,最低工资,最高工资
 * @author HP
 *
 */
public class JDBCDemo2 {
	public static void main(String[] args) {
		Connection connection = null;
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("请输入部门编号:");
			int deptno = scanner.nextInt();
			connection = DButilDemo.getConnection();
			Statement statement = connection.createStatement();
			String sql = "select avg(sal),min(sal),max(sal) from emp where deptno = " + deptno + ";";
			ResultSet resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				double avgsal = resultSet.getDouble("avg(sal)");
				double minsal = resultSet.getDouble("min(sal)");
				double maxsal = resultSet.getDouble("max(sal)");
				System.out.println("部门的平均工资是:" + avgsal + ",最低工资是" + minsal + ",最高工资是" + maxsal);
			}else{
				throw new RuntimeException("查询失败!找不到部门编号!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DButilDemo.closeConnection(connection);
		}
	}
}
