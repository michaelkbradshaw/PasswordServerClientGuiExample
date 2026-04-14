package client.model;

import java.io.IOException;

import client.view.AdminController;
import client.view.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewTransitionalModel
{

	
	Stage stage;
	AbstractPasswordModel model;
	
	public ViewTransitionalModel(Stage stage,AbstractPasswordModel model)
	{
		this.stage = stage; 
		this.model = model;
	}
	
	
	public Scene getLoginScene()
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(LoginController.class.getResource("./login.fxml"));
		Parent view;
		try
		{
			view = loader.load();
			LoginController cont = loader.getController();
			cont.setModel(model,this);

			Scene s = new Scene(view);
			return s;
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	
	public void showLoginScene()
	{
		
		stage.setScene(getLoginScene());
	}
	
	
	
	public Scene getAdminScene()
	{
	
		model.pullData();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(LoginController.class.getResource("./admin.fxml"));
		Parent view;
		try
		{
			view = loader.load();
			AdminController cont = loader.getController();
			cont.setModel(model,this);

			Scene s = new Scene(view);
			return s;
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	
	public void showAdminScene()
	{
		
		stage.setScene(getAdminScene());
	}

	
	
	
	
	
}
