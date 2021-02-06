package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * �������ǿ��Խ�Ҫִ�еĴ���sql��仺���ڱ���,Ȼ��һ���Է��͸����ݿ�
 * �����������,���ִ��Ч��
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
				ps.addBatch(); 	//�洢��������
				//����6�����ݷ���
				if(i % 6 == 0){
					ps.executeBatch();	//���ͻ�������
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DButil.closeConnection(connection, null, null);
		}
	}
}
