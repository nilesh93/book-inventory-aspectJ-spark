package com.mtit.service;

import com.mtit.models.User;
import spark.Response;

public interface UserService {

	 User loginUser(String body, Response res);
	 User registerUser(String body, Response res);

}
