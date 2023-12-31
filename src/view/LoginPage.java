package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import controller.UserController;
import index.DatabaseConnection;

public class LoginPage implements EventHandler<ActionEvent> {
	private Stage stage;
	private Scene scene;
	private GridPane messagePane;
	
	private BorderPane screen;
	private StackPane centerBox;
	private Rectangle square;
	private Pane squareElements;
	
	private Label hello, loginMessage, emailLabel, passwordLabel;
	private TextField emailTF;
	private PasswordField passwordTF;
	private Button submitBtn;
	
	private Image image;
	private ImageView imageView;
	private DatabaseConnection databaseConnection;
	
	private SignUpPage signUpPage;
	private Home home;
	private AdminDashboard admin;
	
	private Button signUpMessage;
	UserController controller = new UserController();


	private void init() {
		screen = new BorderPane();
		centerBox = new StackPane();
		square = new Rectangle();
		messagePane = new GridPane();
		
		
		screen.setStyle("-fx-background-color: #FFEFF9;");
		square = new javafx.scene.shape.Rectangle(450, 450);
	    square.setStyle("-fx-fill: white; -fx-stroke: #BE8C9F; -fx-stroke-width: 3;");
	    
	    squareElements = new Pane(square);
	    squareElements.setPadding(new Insets(20));
	    
	    String fontUrl = "https://fonts.googleapis.com/css2?family=Press+Start+2P&family=Tsukimi+Rounded&display=swap";
	    screen.getStylesheets().add(fontUrl);

	    
	    hello = new Label("Welcome!");
	    hello.setStyle("-fx-font-family: 'Press Start 2P'; -fx-font-size: 24px;");
	    
	    loginMessage = new Label("Login to shoespace");
	    loginMessage.setStyle("-fx-font-family: 'Tsukimi Rounded'; -fx-font-size: 16px; -fx-text-fill: #4E3434");
	    
	    signUpMessage = new Button("New? Sign-up here.");
	    signUpMessage.setStyle("-fx-font-family: 'Tsukimi Rounded'; -fx-font-size: 12px; -fx-text-fill: blue; -fx-background-color: transparent; -fx-border-color: transparent;");
	    signUpMessage.setOnAction(this);
	    signUpMessage.setUnderline(true);
//	    signUpMessage.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                Scene signUpScene = signUpPage.getScene();
//                stage.setScene(signUpScene);
//            }
//        });
	    
	    emailLabel = new Label("email:");
	    emailTF = new TextField("admin@gmail.com");
	    emailLabel.setStyle("-fx-font-family: 'Tsukimi Rounded'; -fx-font-size: 16px; -fx-text-fill: #4E3434");
	    
	    emailTF.setStyle("-fx-border-color: pink;\r\n"
	    		+ "    -fx-border-width: 2px;");
	    passwordLabel = new Label("password:");
	    passwordTF = new PasswordField();
	    passwordLabel.setStyle("-fx-font-family: 'Tsukimi Rounded'; -fx-font-size: 16px; -fx-text-fill: #4E3434");
	    passwordTF.setStyle("-fx-border-color: pink;\r\n"
	    		+ "    -fx-border-width: 2px;");
	    
	    submitBtn = new Button("Login");
	    submitBtn.setStyle("-fx-background-color: pink; -fx-border-color: white; "
	    		+ "-fx-font-family: 'Tsukimi Rounded'; -fx-font-size: 14px; -fx-text-fill: #4E3434;");

	    submitBtn.setOnAction(this);
	    image = new Image("file:///C:/Users/Jesslyn%20Amanda/Shoetudio/assets/login/letter.png");
	    imageView = new ImageView(image);
	    imageView.setFitWidth(100);
	    imageView.setPreserveRatio(true);
	   
	}
	
	private void setLayout() {
		messagePane.add(imageView, 0, 0);
		messagePane.add(hello, 0, 1);
		messagePane.add(loginMessage, 0, 2);
		messagePane.add(signUpMessage, 0, 3);
		messagePane.add(emailLabel, 0, 4);
		messagePane.add(emailTF, 0, 5);
		messagePane.add(passwordLabel, 0, 6);
		messagePane.add(passwordTF, 0, 7);
		messagePane.add(submitBtn, 0, 8);
		messagePane.setAlignment(Pos.CENTER);
		messagePane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

	    
		GridPane.setMargin(hello, new Insets(20, 0, 0, 0));
	    GridPane.setMargin(loginMessage, new Insets(15, 0, 0, 0));
	    GridPane.setMargin(signUpMessage, new Insets(5, 0, 0, 0));
//	    GridPane.setMargin(imageView, new Insets(0, 0, 0, ));
	    
	    GridPane.setMargin(emailLabel, new Insets(15,0,0,0));
	    GridPane.setMargin(emailTF, new Insets(10,0,0,0));
	    GridPane.setMargin(passwordLabel, new Insets(15,0,0,0));
	    GridPane.setMargin(passwordTF, new Insets(10,0,0,0));
	    GridPane.setMargin(submitBtn, new Insets(20,0,0,0));
	    
	    GridPane.setHalignment(imageView, HPos.CENTER);
	    GridPane.setHalignment(hello, HPos.CENTER);
	    GridPane.setHalignment(loginMessage, HPos.CENTER);
	    GridPane.setHalignment(signUpMessage, HPos.CENTER);
	    GridPane.setHalignment(emailLabel, HPos.CENTER);
	    GridPane.setHalignment(emailTF, HPos.CENTER);
	    GridPane.setHalignment(passwordLabel, HPos.CENTER);
	    GridPane.setHalignment(passwordTF, HPos.CENTER);
	    GridPane.setHalignment(submitBtn, HPos.CENTER);

	    
		centerBox.getChildren().addAll(square, messagePane);
		BorderPane.setAlignment(centerBox, Pos.CENTER);

        screen.setCenter(centerBox);
	}
	
	public LoginPage(Stage stage) {
		init();
		setLayout();
		this.stage = stage;
		this.home = new Home(stage);
		this.stage.setTitle("Welcome!");
		this.scene = new Scene(screen, 600,600);
		submitBtn.setOnAction(this);
	}
	
	
	public Scene getScene() {
		return this.scene;
	}

	@Override
	public void handle(ActionEvent clicked) {
		if (clicked.getSource() == signUpMessage) {
			SignUpPage signUpPage2 = new SignUpPage(stage);
			Scene signUpScene = signUpPage2.getScene();
			stage.setScene(signUpScene);
			
		}else if(clicked.getSource() == submitBtn) {
			boolean res = false;
			boolean adminRole = false;
			res = controller.validateLoginInput(emailTF.getText(), passwordTF.getText());
			adminRole = controller.ifUserAdmin(emailTF.getText());
			if (res && adminRole) {
				AdminDashboard admin = new AdminDashboard(stage);
				Scene adminScene = admin.getScene();
	            stage.setScene(adminScene);
			}else if (res) {
				Home home = new Home(stage);
				Scene homeScene = home.getScene();
	            stage.setScene(homeScene);
			}
		}
		stage.show();
	}
		
}
	
