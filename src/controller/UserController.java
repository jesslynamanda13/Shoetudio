package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import index.DatabaseConnection;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.User;

public class UserController {
	private DatabaseConnection db = new DatabaseConnection();
	public void createDefaultUser(){
		boolean res = false;
		boolean res2 = false;
		User admin = new User("Admin", "admin13", "admin@gmail.com",
				"adminpassword", true);
		User cust = new User("Customer", "customer23", "customer@gmail.com"
				, "custpassword", false);
		
		try {
			res = insertUser(admin);
			res2 = insertUser(cust);
			if(!res || !res2) {
				System.out.println("User already exists!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String userLoggedIn = "test";
	
	public boolean registerUser(String name, String username, String email, String password) {
		if (userExists(email, username)) {
	        return false; 
	    }
		boolean res = false;
		User cust = new User(name, username, email, password, false);
		try {
			res = insertUser(cust);
			return res;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public boolean insertUser(User user) {
		if (userExists(user.getEmail(), user.getUsername())) {
	        return false; 
	    }
        String query = "INSERT INTO users (name, username, email, password, isAdmin) "
        		+ "VALUES (?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement stmt = db.connection.prepareStatement(query);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setBoolean(5, user.getIsAdmin());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
	
	public boolean validateLoginInput(String emailTF, String passwordTF) {
		boolean result = true;
		String error = "";
		if(emailTF == "") {
			error = error + "- Email must not be null!\n";
		}
		
		if(passwordTF == "") {
			error = error + "- Password must not be null!\n";
		}
		if(error != "") {
			result = false;
			error = "Found some error(s):\n" + error;
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText(error);
			alert.show();
		}else {
			result = loginAuth(emailTF, passwordTF);
		}
		
		return result;
	}
	
	public boolean validateRegisterInput(String name, String username, String email, String password) {
		boolean result = true;
		String error = "";
		if(name == "") {
			error = error + "- Name must not be null!\n";
		}
		
		if(username == "") {
			error = error + "- Username must not be null!\n";
		}
		
		if(email == "") {
			error = error + "- Email must not be null!\n";
		}
		
		if(password == "") {
			error = error + "- Password must not be null!\n";
		}
		if(error != "") {
			result = false;
			error = "Found some error(s):\n" + error;
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText(error);
			alert.show();
		}else {
			result = registerUser(name, username, email, password);
		}
		
		return result;
	}
	
	public boolean loginAuth(String email, String password){
		String query = "SELECT * FROM users WHERE email = ?";
		String error = "";
		
		try {
			PreparedStatement preparedStatement = db.connection.prepareStatement(query);
	        preparedStatement.setString(1, email);
	        ResultSet resultSet = preparedStatement.executeQuery();
	        
	        if (resultSet.next()) {
               
                String storedPassword = resultSet.getString("password");
                String userEmail = resultSet.getString("email");
                
                if (password.equals(storedPassword)) {
                    System.out.println("Authentication successful.");
            		userLoggedIn = userEmail;
            		
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
	
	
	public boolean ifUserAdmin(String email) {
		String query = "SELECT * FROM users WHERE email = ?";
		String error = "";
		
		try {
			PreparedStatement preparedStatement = db.connection.prepareStatement(query);
	        preparedStatement.setString(1, email);
	        ResultSet resultSet = preparedStatement.executeQuery();
	        
	        if (resultSet.next()) {
               
                Boolean adminRole = resultSet.getBoolean("isAdmin");

                if (adminRole) {
                    System.out.println("Admin logged in.");
                    return true;
                } 
            } 
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return false;
	}
	
	private boolean userExists(String email, String username) {
	    String query = "SELECT COUNT(*) FROM users WHERE email = ? OR username = ?";
	    
	    try {
	        PreparedStatement stmt = db.connection.prepareStatement(query);
	        stmt.setString(1, email);
	        stmt.setString(2, username);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            int count = rs.getInt(1);
	            return count > 0; 
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false; 
	}
}
