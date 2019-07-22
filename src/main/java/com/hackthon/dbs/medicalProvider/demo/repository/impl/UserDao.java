package com.hackthon.dbs.medicalProvider.demo.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.hackthon.dbs.medicalProvider.demo.entity.Login;
import com.hackthon.dbs.medicalProvider.demo.entity.User;
import com.hackthon.dbs.medicalProvider.demo.repository.IUserDao;

@Repository
public class UserDao implements IUserDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	public User findById(String userId) {
		return entityManager.find(User.class, userId);
	}

	public boolean isUserExist(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	public void saveUser(User user) {
		entityManager.persist(user);
		saveLoginCredentials(user.getEmail(), user.getPassword());

	}

	public void updateUser(User currentUser) {
		// TODO Auto-generated method stub

	}

	public User login(String userId, String password) {
		String hql = "from Login as l where l.username=:username";
		int count = entityManager.createQuery(hql).setParameter("username", userId).getResultList().size();
		if (count == 1) {

			return this.findByEmail(userId);

		} else {
			return null;
		}
	}

	private User findByEmail(String userId) {
		String hql = "from User as l where l.email=:email";
		List<User> users = entityManager.createQuery(hql).setParameter("email", userId).getResultList();
		return users.get(0);
	}

	public void deleteUserById(long id) {
		// TODO Auto-generated method stub

	}

	public void deleteAllUsers() {
		// TODO Auto-generated method stub

	}

	private void saveLoginCredentials(String userId, String password) {
		Login login = new Login();
		login.setUserId(userId);
		login.setPassword(password);
		entityManager.merge(login);
	}
}
