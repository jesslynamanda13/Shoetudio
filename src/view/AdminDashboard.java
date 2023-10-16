package view;



import java.util.List;

import controller.ShoeController;
import controller.UserController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Shoe;

public class AdminDashboard implements EventHandler<ActionEvent>{
	UserController userctrl = new UserController();
	ShoeController shoectrl = new ShoeController();
	List<Shoe> shoes = shoectrl.getAllShoes();
	
	private Stage stage;
	private Scene scene;
	private BorderPane screen;
	
	private Button addShoe, editShoe;
	private Label titleLabel;
	private HBox headerBox;
	private GridPane menu, displayShoe;
	private ScrollPane scrollPane;
	private VBox vbox;
	private AddShoe addPage;
	 
	private void init() {
		screen = new BorderPane();
		String fontUrl = "https://fonts.googleapis.com/css2?family=AR+One+Sans&family=Lora:wght@600&family=Tsukimi+Rounded&display=swap";
		screen.getStylesheets().add(fontUrl);
		
		scrollPane = new ScrollPane();
		
		menu = new GridPane();
		displayShoe = new GridPane();
		
		scrollPane.setFitToWidth(true);
		screen.setStyle("-fx-background-color: #FFEFF9;");
		titleLabel = new Label("Admin Management");
		titleLabel.setStyle("-fx-font-family: 'Tsukimi Rounded'; -fx-font-size: 24px; -fx-text-fill: #4E3434");
		
		addShoe = new Button("Add Shoe");
		addShoe.setStyle("-fx-background-color: pink; -fx-border-color: white; "
	    		+ "-fx-font-family: 'Tsukimi Rounded'; -fx-font-size: 14px; -fx-text-fill: #4E3434;");
		addShoe.setOnAction(this);
		
		editShoe = new Button("Edit Shoe");
		editShoe.setStyle("-fx-background-color: pink; -fx-border-color: white; "
	    		+ "-fx-font-family: 'Tsukimi Rounded'; -fx-font-size: 14px; -fx-text-fill: #4E3434;");
		editShoe.setOnAction(this);
		
		int col = 0;
        int row = 0;
        for (Shoe shoe : shoes) {
            VBox shoeBox = new VBox();
            shoeBox.setStyle("-fx-border-color: pink; -fx-border-width: 2px; -fx-background-color: white;");
            shoeBox.setPadding(new Insets(10));
            shoeBox.setSpacing(5);

            Label nameLabel = new Label(shoe.getModel());
            nameLabel.setStyle("-fx-font-family: 'Lora'; -fx-font-size: 20px; -fx-text-fill: #4E3434");
            Label brandLabel = new Label(shoe.getBrand());
            brandLabel.setStyle("-fx-font-family: 'Tsukimi Rounded'; -fx-font-size: 18px; -fx-text-fill: #4E3434");
            Label priceLabel = new Label("$" + shoe.getPrice());
            priceLabel.setStyle("-fx-font-family: 'Lora'; -fx-font-size: 18px; -fx-text-fill: #4E3434");

            shoeBox.getChildren().addAll(nameLabel, brandLabel, priceLabel);
            displayShoe.add(shoeBox, col, row);

            col++;
            if (col == 2) {
                col = 0;
                row++;
            }
        }
	}
	
	private void setLayout() {
		headerBox = new HBox(titleLabel);
		headerBox.setSpacing(10);
		headerBox.setPadding(new Insets(10));
		menu.add(addShoe, 0, 0);
		menu.add(editShoe, 1, 0);
		menu.setHgap(20);
		
        
		displayShoe.setHgap(10);
		displayShoe.setVgap(10);
		
		scrollPane.setContent(displayShoe);

		VBox vbox = new VBox(headerBox, menu, scrollPane); 
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));
        
       
		screen.setCenter(vbox);
	}

	public AdminDashboard(Stage stage) {
		init();
		setLayout();
		this.stage = stage;
		this.stage.setTitle("Admin Dashboard");
		this.scene = new Scene(screen, 600,600);
	}
	
	public Scene getScene() {
		return this.scene;
	}

	@Override
	public void handle(ActionEvent clicked) {
		if(clicked.getSource() == addShoe) {
			AddShoe addPage = new AddShoe(stage);
			Scene addScene = addPage.getScene();
			stage.setScene(addScene);
		}else if(clicked.getSource() == editShoe) {
			EditShoe editPage = new EditShoe(stage);
			Scene editScene = editPage.getScene();
			stage.setScene(editScene);
		}
		// TODO Auto-generated method stub
		
	}
}
