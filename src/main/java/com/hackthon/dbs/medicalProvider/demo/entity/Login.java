package com.hackthon.dbs.medicalProvider.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "login")
public class Login extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 7614158556904783041L;

	private String username;

	private String password;

	
	@Column(name = "user_id", unique = true, nullable = false)
	public String getUserId() {
		return username;
	}

	public void setUserId(String userId) {
		this.username = userId;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Login)) {
			return false;
		}
		Login other = (Login) obj;
		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!password.equals(other.password)) {
			return false;
		}
		if (username == null) {
			if (other.username != null) {
				return false;
			}
		} else if (!username.equals(other.username)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Login [userId=" + username + ", password=" + password + "]";
	}

}