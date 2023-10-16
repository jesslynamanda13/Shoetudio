package index;
import controller.ShoeController;
import controller.UserController;
import javafx.application.Application;
import javafx.stage.Stage;
import view.LoginPage;

public class Main extends Application{
	DatabaseConnection dbConnection = new DatabaseConnection();
	UserController userctrl = new UserController();
	ShoeController shoectrl = new ShoeController();

	public Main() {
		dbConnection.migrateDatabase();
		userctrl.createDefaultUser();
		shoectrl.createDefaultShoes();
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
