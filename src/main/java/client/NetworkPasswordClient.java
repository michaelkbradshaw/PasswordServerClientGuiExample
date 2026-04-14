package client;

import client.model.NetworkPasswordModel;
import client.model.TestingPasswordModel;
import client.model.ViewTransitionalModel;
import javafx.application.Application;
import javafx.stage.Stage;
import restPasswordServer.RESTPasswordServer;

public class NetworkPasswordClient extends Application

{
	
	public static void main(String[] args)
	{
		launch(args);
	}
	

	@Override
	public void start(Stage stage) throws Exception
	{
		String args[] = {};
		RESTPasswordServer.main(args);
		
		
		NetworkPasswordModel model = new NetworkPasswordModel();
		ViewTransitionalModel tm = new ViewTransitionalModel(stage, model);
		tm.showLoginScene();
		stage.show();
	}

}
