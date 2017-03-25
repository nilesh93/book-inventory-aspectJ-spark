package com.mtit.config;


import java.sql.*;

public class DatabaseConnection {

    private static Connection connection = null;

    // singleton implememntation of db connection
    public static Connection getConnection()
    {
        if(connection == null) {
            createConnection();
        }
        return connection;
    }

    // create connection to db
    public static void createConnection() {
        try {
            connection = DriverManager.getConnection(Constants.DATABASE_URL);
            runMigrations(connection);
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }

    public static void closeConnection() {
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // run migrations on the db on initial db connect to create the database structures
    private static void runMigrations(Connection connection) {

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS BOOK (" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " TITLE VARCHAR (255)," +
                    " AUTHOR VARCHAR (255),IMG VARCHAR (255), " +
                    "QTY INT DEFAULT 0);");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS USER (" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " USERNAME VARCHAR (255)," +
                    " PASSWORD VARCHAR (255));");

            insertAdminMigration(statement);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // after running migrations, insert a default admin to the system
    private static void insertAdminMigration(Statement statement)throws SQLException{

        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM USER");
            if(!resultSet.next())
                statement.executeUpdate("INSERT INTO USER VALUES(NULL,'ADMIN','ADMIN')");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
