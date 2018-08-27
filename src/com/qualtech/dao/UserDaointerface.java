package com.qualtech.dao;

import java.util.ArrayList;

import com.qualtech.beans.User;

public interface UserDaointerface {
 	public Long addUser(User ref);
 	public User getUser(String user_email,String user_password);	
}
