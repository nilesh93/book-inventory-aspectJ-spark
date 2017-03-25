package com.mtit.dataaccess;

import com.mtit.config.DatabaseConnection;
import com.mtit.models.Book;

import java.sql.*;
import java.util.List;
import java.util.Vector;

public class BookDataAccessLayerImpl implements BookDataAccessLayer {

	@Override
	public Book view(int id) throws SQLException {
		
		Connection connection = DatabaseConnection.getConnection();
		
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM Book WHERE id = " + id);
		
		while(resultSet.next()) {
			Book book = getBookFromResultSet(resultSet);
			return book;
		}
		
		throw new SQLException("Book view failed. No such book exists. ID -> " + id);
	}

	@Override
	public Book create(Book book) throws SQLException {
		
		Connection connection = DatabaseConnection.getConnection();
		
		PreparedStatement statement = connection.prepareStatement("INSERT INTO Book VALUES (NULL, ?, ?, ?, ?)");
		statement.setString(1, book.getTitle());
		statement.setString(2, book.getAuthor());
		statement.setString(3, book.getImg());
		statement.setInt(4, book.getQty());
        
        int result = statement.executeUpdate();
        
        if(result == 1)
        	return book;
		
		throw new SQLException("Book Create Failed");
	}

	@Override
	public Book update(Book book) throws SQLException {
		
		Connection connection = DatabaseConnection.getConnection();
		
		PreparedStatement statement = connection.prepareStatement("UPDATE Book SET title=?, author=?, img=?, qty=? WHERE id=?");
		statement.setString(1, book.getTitle());
		statement.setString(2, book.getAuthor());
		statement.setString(3, book.getImg());
		statement.setInt(4, book.getQty());
		statement.setInt(5, book.getId());
        
        int result = statement.executeUpdate();
        
        if(result == 1)
        	return book;
        
        throw new SQLException("No such book exists on ID -> " +  book.getId());
	}

	@Override
	public Book delete(Book book) throws SQLException {
		
		Connection connection = DatabaseConnection.getConnection();
		
		Statement statement = connection.createStatement();
		int result = statement.executeUpdate("DELETE FROM Book WHERE id = " + book.getId());
        
        if(result == 1)
        	return book;
		
        throw new SQLException("No such book exisits -> "+ book.getId());
	}

	@Override
	public List<Book> list() throws SQLException {
		
		Connection connection = DatabaseConnection.getConnection();
		
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM Book");

		List<Book> list = new Vector<Book>();

		while(rs.next()) {
			Book book = getBookFromResultSet(rs);
			list.add(book);
		}
		
		return list;
	}

	private Book getBookFromResultSet(final ResultSet resultSet) throws SQLException {
		
		int id = resultSet.getInt("id");
		String title = resultSet.getString("title");
		String author = resultSet.getString("author");
		String img = resultSet.getString("img");
		int qty = resultSet.getInt("qty");
		
		return new Book(id, title, author, img, qty);
	}
}
