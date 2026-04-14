package client;

import client.model.TestingPasswordModel;
import client.model.ViewTransitionalModel;
import client.view.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LocalPasswordClient extends Application
{

	@Override
	public void start(Stage stage) throws Exception
	{
		TestingPasswordModel model = new TestingPasswordModel();
		ViewTransitionalModel tm = new ViewTransitionalModel(stage, model);
		tm.showLoginScene();
		stage.show();
	}

	public static void main(String[] args)
	{
		launch(args);
	}

}
