package view;

import controller.ShoeController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class AddShoe implements EventHandler<ActionEvent>{
	private Stage stage;
	private Scene scene;
	private BorderPane screen;
	private GridPane form;
	private Rectangle square;
	private StackPane centerBox;
	private Label addShoeHeading, modelLabel, brandLabel, colorLabel, priceLabel;
	private TextField modelTF, brandTF, colorTF, priceTF;
	private Button addShoe, cancel;
	ShoeController shoectrl = new ShoeController();
	
	private void init() {
		screen = new BorderPane();
		centerBox = new StackPane();
		square = new Rectangle();
		form = new GridPane();
		
		screen.setStyle("-fx-background-color: #FFEFF9;");
		square = new javafx.scene.shape.Rectangle(450, 450);
	    square.setStyle("-fx-fill: white; -fx-stroke: #BE8C9F; -fx-stroke-width: 3;");
	    
		String fontUrl = "https://fonts.googleapis.com/css2?family=Press+Start+2P&family=Tsukimi+Rounded&display=swap";
	    screen.getStylesheets().add(fontUrl);
	    
	    addShoeHeading = new Label("Add Shoe");
	    addShoeHeading.setStyle("-fx-font-family: 'Press Start 2P'; -fx-font-size: 24px;");
	    
		modelLabel = new Label("model:");
		brandLabel = new Label("brand:");
		colorLabel = new Label("color:");
		priceLabel = new Label("price:");
		
		modelLabel.setStyle("-fx-font-family: 'Tsukimi Rounded'; -fx-font-size: 16px; -fx-text-fill: #4E3434");
		brandLabel.setStyle("-fx-font-family: 'Tsukimi Rounded'; -fx-font-size: 16px; -fx-text-fill: #4E3434");
		colorLabel.setStyle("-fx-font-family: 'Tsukimi Rounded'; -fx-font-size: 16px; -fx-text-fill: #4E3434");
		priceLabel.setStyle("-fx-font-family: 'Tsukimi Rounded'; -fx-font-size: 16px; -fx-text-fill: #4E3434");
		
		modelTF = new TextField();
		brandTF = new TextField();
		colorTF = new TextField();
		priceTF = new TextField();
		
		modelTF.setStyle("-fx-font-family: 'Tsukimi Rounded'; -fx-font-size: 16px; -fx-text-fill: #4E3434");
		brandTF.setStyle("-fx-font-family: 'Tsukimi Rounded'; -fx-font-size: 16px; -fx-text-fill: #4E3434");
		colorTF.setStyle("-fx-font-family: 'Tsukimi Rounded'; -fx-font-size: 16px; -fx-text-fill: #4E3434");
		priceTF.setStyle("-fx-font-family: 'Tsukimi Rounded'; -fx-font-size: 16px; -fx-text-fill: #4E3434");
		
		addShoe = new Button("Add Shoe");
		cancel = new Button("Cancel");
		addShoe.setStyle("-fx-background-color: pink; -fx-border-color: white; "
	    		+ "-fx-font-family: 'Tsukimi Rounded'; -fx-font-size: 14px; -fx-text-fill: #4E3434;");
		cancel.setStyle("-fx-background-color: white; -fx-border-color: pink; "
	    		+ "-fx-font-family: 'Tsukimi Rounded'; -fx-font-size: 14px; -fx-text-fill: #4E3434;");
		addShoe.setOnAction(this);
		cancel.setOnAction(this);
	}
	
	private void setLayout() {
		form.add(modelLabel, 0, 0);
		form.add(modelTF, 1, 0);
		
		form.add(brandLabel, 0, 1);
		form.add(brandTF, 1, 1);
		
		form.add(colorLabel, 0, 2);
		form.add(colorTF, 1,2);
		
		form.add(priceLabel, 0, 3);
		form.add(priceTF, 1,3);
		
		form.add(cancel, 0, 4);
		form.add(addShoe, 1, 4);
		
		form.setHgap(10);
		form.setVgap(10);
		
		form.setAlignment(Pos.CENTER);
		form.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
		
		VBox vbox = new VBox(addShoeHeading, form);
		vbox.setSpacing(20);
		vbox.setAlignment(Pos.CENTER);
		
		centerBox.getChildren().addAll(square, vbox);
		BorderPane.setAlignment(centerBox, Pos.CENTER);
        screen.setCenter(centerBox);
	}
	public AddShoe(Stage stage) {
		init();
		setLayout();
		this.stage = stage;
		this.stage.setTitle("Add Shoe");
		this.scene = new Scene(screen, 600,600);
	}
	
	public Scene getScene() {
		return this.scene;
	}
	@Override
	public void handle(ActionEvent clicked) {
		
		if(clicked.getSource() == cancel) {
			AdminDashboard adminPage = new AdminDashboard(stage);
			Scene adminScene = adminPage.getScene();
			stage.setScene(adminScene);
		}else if(clicked.getSource() == addShoe) {
			
			boolean res;
			res = shoectrl.validateRegisterShoe(modelTF.getText(), colorTF.getText(),
					brandTF.getText(), Integer.parseInt(priceTF.getText()));
			
			if(res) {
				AdminDashboard adminPage = new AdminDashboard(stage);
				Scene adminScene = adminPage.getScene();
				stage.setScene(adminScene);
			}else {
				String error = "";
				error = "Found some error(s):\n" + error;
				Alert alert = new Alert(AlertType.WARNING);
				alert.setContentText(error);
			}
		}
	}
}
