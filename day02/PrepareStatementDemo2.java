package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * ����ִ����ͬ��sql���,perparestatement���Գ���ִ�мƻ�,�������ݿ⿪��
 * @author HP
 *
 */
public class PrepareStatementDemo2 {
	public static void main(String[] args) {
		Connection connection = null;
		try {
			connection = DButil.getConnection();
			/*Statement statement = connection.createStatement();
			for(int i = 0;i < 100;i ++){
				String sql = "insert into myjdbc  (user_name) values ('ceshi')";
				statement.executeUpdate(sql);
			}*/
			
			String sql = "insert into myjdbc (user_name) values (?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			long time1 = System.currentTimeMillis();
			for(int i = 0;i < 100;i ++){
				preparedStatement.setString(1, "ceshi" + i);
				preparedStatement.executeUpdate();
			}
			long time2 = System.currentTimeMillis();
			System.out.println("����ɹ�,ʱ����" + (time2 - time1) );
		System.out.println("����ɹ�!");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DButil.closeConnection(connection, null,null);
		}
	}
}
