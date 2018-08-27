package com.qualtech.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qualtech.beans.User;

@Repository
public class UserDaoImpl implements UserDaointerface {
	private Long uid = (long) 0;

	@Autowired
	private SessionFactory sf;
	
	@Override
	public Long addUser(User ref) {
		Session s = sf.openSession();
		System.out.println("reached in addUser of Dao");
		String saltedPassword = "qualtech" + ref.getUserPassword();
		String hashedPassword = generateHash(saltedPassword);
		System.out.println(hashedPassword);
		System.out.println(ref.toString());
		ref.setUserPassword(hashedPassword);
		Transaction txn = s.beginTransaction();
		Long uid = null;
		try {
			uid = (Long) s.save(ref);
			txn.commit();
		} catch (Exception e) {
			txn.rollback();
			e.printStackTrace();

		}

		return uid;
	}

	@Override
	public User getUser(String user_email, String user_password) {
		Session s = sf.openSession();
		System.out.println("dao:" + user_email + " " + user_password);
		String saltedPassword = "qualtech" + user_password;
		String hashedPassword = generateHash(saltedPassword);
		User loginuserobj=null;
		String hql = "select user FROM User user where user.userEmail=:user_email and user.userPassword=:user_password";
		try{
			loginuserobj = (User) s.createQuery(hql).setParameter("user_email", user_email)
				.setParameter("user_password", hashedPassword).uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
		}
		return loginuserobj;
	}

	public static String generateHash(String input) {
		StringBuilder hash = new StringBuilder();

		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] hashedBytes = sha.digest(input.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
			for (int idx = 0; idx < hashedBytes.length; ++idx) {
				byte b = hashedBytes[idx];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		} catch (NoSuchAlgorithmException e) {
			// handle error here.
		}

		return hash.toString();
	}

}
