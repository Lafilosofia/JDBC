package day03;

import java.io.Serializable;

/**
 * 为了存放从数据库中查询到的记录的信息,我们可以设计一个对应的类,
 * 该类的结构与要操作的数据库对应的表一致
 * @author HP
 *
 */
public class User implements Serializable {
	private Integer id;//用户ID
	private String userName;//用户名
	private String pwd;//用户密码
	private double userSal;//用户薪资
	private Integer userAge;//用户年龄
	
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
