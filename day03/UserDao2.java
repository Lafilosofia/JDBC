package day03;

import java.util.List;

public interface UserDao2 {
	//�û�ע��
	void insert(User user);
	//����idɾ���û�ע������
	int deleteUserById(int id);
	//�����û����޸��û�����
	int updateUserById(int id,String newPwd);
	//�����û�id��ѯ�û�����
	User findUserById(int id);
	//��ѯ�����û�����
	List<User> findUserAll();
	}
