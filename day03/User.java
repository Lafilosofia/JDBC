package day03;

import java.io.Serializable;

/**
 * Ϊ�˴�Ŵ����ݿ��в�ѯ���ļ�¼����Ϣ,���ǿ������һ����Ӧ����,
 * ����Ľṹ��Ҫ���������ݿ��Ӧ�ı�һ��
 * @author HP
 *
 */
public class User implements Serializable {
	private Integer id;//�û�ID
	private String userName;//�û���
	private String pwd;//�û�����
	private double userSal;//�û�н��
	private Integer userAge;//�û�����
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(Integer id, String userName, String pwd, double userSal, Integer userAge) {
		super();
		this.id = id;
		this.userName = userName;
		this.pwd = pwd;
		this.userSal = userSal;
		this.userAge = userAge;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public double getUserSal() {
		return userSal;
	}
	public void setUserSal(double userSal) {
		this.userSal = userSal;
	}
	public Integer getUserAge() {
		return userAge;
	}
	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pwd == null) ? 0 : pwd.hashCode());
		result = prime * result + ((userAge == null) ? 0 : userAge.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		long temp;
		temp = Double.doubleToLongBits(userSal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pwd == null) {
			if (other.pwd != null)
				return false;
		} else if (!pwd.equals(other.pwd))
			return false;
		if (userAge == null) {
			if (other.userAge != null)
				return false;
		} else if (!userAge.equals(other.userAge))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (Double.doubleToLongBits(userSal) != Double.doubleToLongBits(other.userSal))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", pwd=" + pwd + ", userSal=" + userSal + ", userAge="
				+ userAge + "]";
	}
	
}
