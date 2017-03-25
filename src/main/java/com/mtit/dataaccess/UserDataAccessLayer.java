package com.mtit.dataaccess;

import com.mtit.models.User;

import java.sql.SQLException;

// User Data Access Interface
public interface UserDataAccessLayer {

	 User login(String userName, String password) throws SQLException;
	 User register(User user) throws SQLException;

}
