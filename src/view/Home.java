package view;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Home {
	private Stage stage;
	private Scene scene;
	private BorderPane screen;
	
	public Home(Stage stage) {
		screen = new BorderPane();
		screen.setStyle("-fx-background-color: #FFEFF9;");
		this.stage = stage;
		this.stage.setTitle("Home");
		this.scene = new Scene(screen, 600,600);
	}
	
	public Scene getScene() {
		return this.scene;
	}
}




