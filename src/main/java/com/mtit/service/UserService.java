package com.mtit.service;

import com.mtit.models.User;
import spark.Response;

import java.sql.SQLException;

public interface UserService {

	 User getUser(String body, Response res) throws SQLException;
	 User create(String body, Response res) throws SQLException;

}
