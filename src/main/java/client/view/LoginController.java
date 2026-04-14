package client.view;

import javax.swing.JOptionPane;

import client.model.AbstractPasswordModel;
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
	    
	    public void setModel(AbstractPasswordModel model)
	    {
	    	this.model = model;
	    	
	    	UserNameTB.textProperty().bindBidirectional(model.getUsername());
	    	PasswordTB.textProperty().bindBidirectional(model.getPassword());
	    }
	    
	    @FXML
	    void onLoginClicked(ActionEvent event) 
	    {
	    	if(model.checkPassword())
	    	{
	    		JOptionPane.showMessageDialog(null,  "Success!","alert", JOptionPane.ERROR_MESSAGE);
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
