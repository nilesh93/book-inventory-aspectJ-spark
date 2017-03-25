package com.mtit.dataaccess;

import com.mtit.models.Book;

import java.sql.SQLException;
import java.util.List;

// Book Data Access Layer Interface
public interface BookDataAccessLayer {
	
	 Book view(int id) throws SQLException;
     Book create(Book book) throws SQLException;
	 Book update(Book book) throws SQLException;
	 Book delete(Book book) throws SQLException;
	 List<Book> list() throws SQLException;
	
}
