package client.view;
import client.model.AbstractPasswordModel;
import client.model.ViewTransitionalModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class AdminController {

    @FXML
    private ListView<String> LoggedInLV;

    @FXML
    private ListView<String> RequestedLV;

    @FXML
    private Label UserName;

    @FXML
    void logout(ActionEvent event) 
    {
    	vm.showLoginScene();
    	
    	
    }
    
    AbstractPasswordModel model;
    ViewTransitionalModel vm;
    
    public void setModel(AbstractPasswordModel model, ViewTransitionalModel vm)
    {
    	this.model = model;
    	this.vm = vm;
    	
    	LoggedInLV.setItems(model.getLogged());
    	RequestedLV.setItems(model.getRequests());
    	
    	UserName.textProperty().bindBidirectional(model.getUsername());
    }
    
    
}
