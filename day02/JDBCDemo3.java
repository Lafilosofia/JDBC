package day02;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import javax.print.attribute.standard.MediaName;

/**
 * 根据员工编号查询领导所属的部门信息
 * @author HP
 *
 */
public class JDBCDemo3 {
		public static void main(String[] args) {
			Connection connection = null;
			try {
				Scanner scanner = new Scanner(System.in);
				System.out.println("请输入员工编号:");
				int empno = scanner.nextInt();
				connection = DButilDemo.getConnection();
				Statement statement = connection.createStatement();
				String sql = "select d.loc,d.dname,d.deptno from emp e join dept d on e.deptno = d.deptno where e.mgr = (select e.mgr from emp where mgr = "+ empno +") ";
				ResultSet resultSet = statement.executeQuery(sql);
				if (resultSet.next()) {
					String dloc = resultSet.getString("loc");
					String dname = resultSet.getString("dname");
					int deptno = resultSet.getInt("deptno");
					System.out.println("部门地址是:" + dloc + "部门名称是:" + dname + "部门号是:" + deptno);
				}else
					throw new RuntimeException("查询失败!");
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				DButil.closeConnection(connection, null,null);
			}
		}
}
