package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * 批操作是可以将要执行的大量sql语句缓存在本地,然后一次性发送给数据库
 * 减少网络调用,提高执行效率
 * @author HP
 *
 */
public class PrepareStatementDemo4 {
	public static void main(String[] args) {
		Connection connection = null;
		try {
			connection = DButil.getConnection();
			String sql = "insert into bank values (null,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			for(int i = 1;i < 10;i ++){
				ps.setString(1,"ceshi" + i);
				ps.setDouble(2, 2000 + i);
				ps.addBatch(); 	//存储到缓存中
				//缓存6条数据发送
				if(i % 6 == 0){
					ps.executeBatch();	//发送缓存数据
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DButil.closeConnection(connection, null, null);
		}
	}
}
