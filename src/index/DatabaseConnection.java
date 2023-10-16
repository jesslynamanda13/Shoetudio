package index;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import controller.ShoeController;
import controller.UserController;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.User;

public class DatabaseConnection {
	public Connection connection;
	public Statement statement;
	public ResultSet resultSet;
	public ResultSetMetaData resultSetMetaData;
	public PreparedStatement preparedStatement;
	private UserController controller;

	
	public DatabaseConnection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager
						.getConnection("jdbc:mysql://localhost:3306/shoetudio", "root", "");
			statement = connection.createStatement();
		
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void migrateDatabase() {
		createUsersTable();
		createShoesTable();
	}
	
	public void createUsersTable() {
		String createTableQuery = "CREATE TABLE IF NOT EXISTS users ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
				+ "name VARCHAR(255) NOT NULL,"
                + "username VARCHAR(255) NOT NULL,"
                + "email VARCHAR(255) NOT NULL,"
                + "password VARCHAR(255) NOT NULL,"
                + "isAdmin BOOLEAN NOT NULL"
                + ")";
        try {
        	exec(createTableQuery); 
        	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createShoesTable() {
	    String createTableQuery = "CREATE TABLE IF NOT EXISTS shoes ("
	            + "id INT AUTO_INCREMENT PRIMARY KEY,"
	            + "model VARCHAR(255) NOT NULL,"
	            + "brand VARCHAR(255) NOT NULL,"
	            + "color VARCHAR(255) NOT NULL,"
	            + "price DOUBLE NOT NULL"
	            + ")";
	    try {
	        exec(createTableQuery);
	       
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	// create default admin
//	public void createAdmin() {
//		String selectQuery = "SELECT COUNT(*) FROM users WHERE username = ?";
//
//	    try {
//	        PreparedStatement checkStatement = connection.prepareStatement(selectQuery);
//	        checkStatement.setString(1, "admin13");
//	        ResultSet resultSet = checkStatement.executeQuery();
//
//	        if (resultSet.next() && resultSet.getInt(1) == 0) {
//	            String insertQuery = "INSERT INTO users (name, username, email, password, isAdmin) VALUES (?, ?, ?, ?, ?)";
//	            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
//	            preparedStatement.setString(1, "Admin");
//	            preparedStatement.setString(2, "admin13");
//	            preparedStatement.setString(3, "admin@gmail.com");
//	            preparedStatement.setString(4, "adminpassword");
//	            preparedStatement.setBoolean(5, true);
//	            preparedStatement.execute();
//	            preparedStatement.close();
//	        }
//	        resultSet.close();
//	        checkStatement.close();
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    }
//        
//	}
	
	
	public ResultSet execQuery(String query) {
		try {
			resultSet = statement.executeQuery(query);
			resultSetMetaData = resultSet.getMetaData();
			System.out.println("Query accepted");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}
	
	public void exec(String query) {
		try {
			statement.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
