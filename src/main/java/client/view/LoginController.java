package client.view;

import javax.swing.JOptionPane;

import client.model.AbstractPasswordModel;
import client.model.ViewTransitionalModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

public class LoginController
{

	    @FXML
	    private TextField PasswordTB;

	    @FXML
	    private TextField UserNameTB;

	    
	    AbstractPasswordModel model;
	    ViewTransitionalModel vm;
	    
	    public void setModel(AbstractPasswordModel model,ViewTransitionalModel vm)
	    {
	    	this.model = model;
	    	this.vm = vm;
	    	
	    	UserNameTB.textProperty().bindBidirectional(model.getUsername());
	    	PasswordTB.textProperty().bindBidirectional(model.getPassword());
	    	
	    }
	    
	    @FXML
	    void onLoginClicked(ActionEvent event) 
	    {
	    	if(model.checkPassword())
	    	{
	    		vm.showAdminScene();
	    	}
	    	else
	    	{
	    		
	    		Dialog<Boolean> dialog = new Dialog<>();
	            dialog.setTitle("alert");
	            dialog.setHeaderText("Bad UserName/Password");
	            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
	            dialog.show();
	    	}
	  
	    }

	    @FXML
	    void onRequestClicked(ActionEvent event) 
	    {
	    	
	    	Dialog<Boolean> dialog = new Dialog<>();
            dialog.setTitle("Requested Password");
            dialog.setHeaderText("Password for "+model.getUsername().get()+" is "
	    			+model.requestPassword());
            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            dialog.show();
	    }
}
