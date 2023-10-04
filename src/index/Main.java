package index;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import view.LoginPage;

public class Main extends Application{
//	DatabaseConnection dbConnection = new DatabaseConnection();
	
	public Main() {
//		dbConnection.migrateDatabase();
//		dbConnection.createAdmin();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
	
	
	@Override
	public void start(Stage stage) throws Exception {
		LoginPage loginPage = new LoginPage(stage);
		stage.setScene(loginPage.getScene());
		stage.setResizable(false);
		stage.show();
		
	}
	
	

}
