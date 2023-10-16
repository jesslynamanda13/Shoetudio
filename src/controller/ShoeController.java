package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import index.DatabaseConnection;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Shoe;
import model.User;


public class ShoeController {
	private DatabaseConnection db = new DatabaseConnection();
	

	public void createDefaultShoes() {
	    Shoe one = new Shoe("Beige Canvas Chanel Espadrilles", "Chanel", "Beige", 7200);
	    Shoe two = new Shoe("White Sneakers with Red Stripes", "Adidas", "White", 1200);
	    Shoe three = new Shoe("Brown Leather Oxford Shoes", "Clarks", "Brown", 3500);
	    Shoe four = new Shoe("Blue Suede Puma Sneakers", "Puma", "Blue", 2500);
	    try {
	        boolean res = createShoe(one);
	        createShoe(two);
	        createShoe(three);
	        createShoe(four);
	        if (res) {
	            System.out.println("Successfully added.");
	        } else {
	            System.out.println("Failed to add.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public boolean validateRegisterShoe(String model, String color, String brand, Integer price) {
		boolean result = true;
		String error = "";
		if(model == "") {
			error = error + "- Model must not be null!\n";
		}
		
		if(color == "") {
			error = error + "- Color must not be null!\n";
		}
		
		if(brand == "") {
			error = error + "- Brand must not be null!\n";
		}
		
		if(price == 0) {
			error = error + "- Price must not be null!\n";
		}
		if(error != "") {
			result = false;
			error = "Found some error(s):\n" + error;
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText(error);
			alert.show();
		}else {
			result = registerShoe(model, color, brand, price);
		}
		
		return result;
	}
	
	public boolean registerShoe(String model, String color, String brand, Integer price) {
		String error = "";
		if(shoeExists(model)) {
			error = "Found some error(s):\n" + error;
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText(error);
			return false;
		}
		boolean res = false;
		Shoe newShoe = new Shoe(model, brand, color, price);
		try {
			res = createShoe(newShoe);
			return res;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updateShoe(String model, String color, String brand, Integer price) {
		System.out.println("Update shoe is requested");
		boolean res = false;
		String query = "UPDATE shoes SET brand = ?, color = ?, price = ? WHERE model = ?";
		try {
			 PreparedStatement stmt = db.connection.prepareStatement(query);
		     stmt.setString(1, brand);
		     stmt.setString(2, color);
		     stmt.setInt(3, price);
		     stmt.setString(4, model);
		     System.out.println("Executing update...");
		     int rowsAffected = stmt.executeUpdate();
		     res = rowsAffected > 0;
		     System.out.println(res);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return res;
	}

	public boolean createShoe(Shoe shoe) {
	    boolean res = false;

	    if (shoeExists(shoe.getModel())) {
	        System.out.println("Shoe already exists.");
	        return false;
	    }

	    String query = "INSERT INTO shoes (model, brand, color, price) VALUES (?, ?, ?, ?)";

	    try {
	        PreparedStatement stmt = db.connection.prepareStatement(query);
	        stmt.setString(1, shoe.getModel());
	        stmt.setString(2, shoe.getBrand());
	        stmt.setString(3, shoe.getColor());
	        stmt.setInt(4, shoe.getPrice());

	        int rowsAffected = stmt.executeUpdate();
	        res = rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return res;
	}

	
	public boolean shoeExists(String model) {
		String query = "SELECT COUNT(*) FROM shoes WHERE model = ?";
	    
	    try {
	        PreparedStatement stmt = db.connection.prepareStatement(query);
	        stmt.setString(1, model);
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
	
    
	
	
	
	public List<Shoe> getAllShoes(){
		List<Shoe> shoes = new ArrayList<>();
		String query = "SELECT * FROM shoes";
		try {
			PreparedStatement stmt = db.connection.prepareStatement(query);
			ResultSet resultSet = stmt.executeQuery();
			while (resultSet.next()) {
	              int shoeId = resultSet.getInt("id");
	              String model = resultSet.getString("model");
	              String brand = resultSet.getString("brand");
	              String color = resultSet.getString("color");
	              int price = resultSet.getInt("price");
	
	              Shoe shoe = new Shoe(shoeId, model, brand, color, price);
	              shoes.add(shoe);
	            }
		} catch (Exception e) {
			// TODO: handle exception
		}
		 return shoes;
	}
}
