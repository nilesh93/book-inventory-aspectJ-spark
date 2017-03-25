package com.mtit.dataaccess;

import com.mtit.config.DatabaseConnection;
import com.mtit.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDataAccessLayerImpl implements UserDataAccessLayer {

	/**
	 * Login Function
	 * @param userName
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	@Override
	public User login(String userName, String password) throws SQLException {
		
		Connection connection = DatabaseConnection.getConnection();
		
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM User WHERE username=? AND password=?");
		
		statement.setString(1, userName);
		statement.setString(2, password);
		
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			return getUserFromResultSet(resultSet);
		}
		
		throw new SQLException("Invalid Login -> un :"+ userName);
	}

	/**
	 * Register Function
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	@Override
	public User register(User user) throws SQLException {

		Connection connection = DatabaseConnection.getConnection();

		PreparedStatement statement = connection.prepareStatement("INSERT INTO User VALUES (NULL, ?, ?)");
		statement.setString(1, user.getUserName());
		statement.setString(2, user.getPassword());
		int result = statement.executeUpdate();

		if(result == 1) {
            user.setPassword("");
			return   user;
		}
		throw new SQLException("Registration failed for the user -> "+ user.getUserName());
	}


	private User getUserFromResultSet(final ResultSet resultSet) throws SQLException {
		String username = resultSet.getString("username");
		return new User(username, "");
	}
	
}
