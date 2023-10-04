package index;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.User;

public class DatabaseConnection {
	public Connection connection;
	public Statement statement;
	public ResultSet resultSet;
	public ResultSetMetaData resultSetMetaData;
	public PreparedStatement preparedStatement; 

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
	
	// migrate database
	public void migrateDatabase() {
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
			System.out.println("User table created successfully.");
		}
        
        
        
	}
	
	// create default admin
	public void createAdmin() {
		String insertQuery = "INSERT INTO users (name, username,email, password, isAdmin) VALUES (?, ?, ?,?, ?)";
        try {
			preparedStatement = connection.prepareStatement(insertQuery);
			preparedStatement.setString(1, "Admin");
	        preparedStatement.setString(2, "admin13");
	        preparedStatement.setString(3, "admin@gmail.com"); 
	        preparedStatement.setString(4, "adminpassword"); 
	        preparedStatement.setBoolean(5, true);
	        preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean loginAuth(String email, String password){
		String query = "SELECT * FROM users WHERE email = ?";
		String error = "";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setString(1, email);
	        ResultSet resultSet = preparedStatement.executeQuery();
	        
	        if (resultSet.next()) {
               
                String storedPassword = resultSet.getString("password");

                if (password.equals(storedPassword)) {
                    System.out.println("Authentication successful.");
                    return true;
                } else {
                	error = error + "- Authentication failed: Incorrect password.\n";
                    System.out.println("Authentication failed: Incorrect password.");
                }
            } else {
            	error = error + "- Authentication failed: User not found.\n";
                System.out.println("Authentication failed: User not found.");
            }
		} catch (SQLException e) {
			// TODO: handle exception
		}
		
		if(error != "") {
			error = "Found some error(s):\n" + error;
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText(error);
			alert.show();
		}
        
		return false;
	}
	
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
