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
		/*
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(LoginController.class.getResource("./login.fxml"));
		Parent view = loader.load();
		LoginController cont = loader.getController();
		cont.setModel(model,new ViewTransitionalModel(stage, model));

		Scene s = new Scene(view);
		stage.setScene(s);
		*/
		stage.show();

	}

	public static void main(String[] args)
	{
		launch(args);
	}

}
