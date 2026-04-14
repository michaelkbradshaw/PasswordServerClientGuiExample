package client.view;

import javax.swing.JOptionPane;

import client.model.AbstractPasswordModel;
import client.model.ViewTransitionalModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

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
	    		JOptionPane.showMessageDialog(null,  "Bad UserName/Password", "alert", JOptionPane.ERROR_MESSAGE);
	    	}
	  
	    }

	    @FXML
	    void onRequestClicked(ActionEvent event) 
	    {
	    	JOptionPane.showMessageDialog(null, "Password for "+model.getUsername().get()+" is "
	    			+model.requestPassword(), "Requested Password",  JOptionPane.INFORMATION_MESSAGE);
	    }
}
