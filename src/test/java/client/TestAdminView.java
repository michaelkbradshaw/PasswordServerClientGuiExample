package client;

//import static org.assertj.core.api.Assertions.assertThat;
import org.testfx.assertions.api.Assertions;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import client.model.TestingPasswordModel;
import client.model.ViewTransitionalModel;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;


@ExtendWith(ApplicationExtension.class)
public class TestAdminView
{

	
	TestingPasswordModel model;
	ViewTransitionalModel tm; 

	
	
	String logs[] = {"A","B","C"};
	String reqs[] = {"W","X","Y","Z"};
	String reqs2[] = {"G","K","W","X","Y","Z"};
	String user = "Carol";
	
	@Start  //Before
	  private void start(Stage stage)
	  {
		model = new TestingPasswordModel();
		model.getUsername().set(user);

		Collections.addAll(model.logsA,logs);
		Collections.addAll(model.requestsA,reqs);
		
		
		tm = new ViewTransitionalModel(stage, model);
		tm.showAdminScene();
		stage.show();
	  }

	
	
	private void pressRefresh(FxRobot robot)
	{
		robot.clickOn("#refreshID");
	}
	
	private void pressLogout(FxRobot robot)
	{
		robot.clickOn("#logoutID");
	}
	
	
	
	public void checkIfListViewHasElements(FxRobot robot, String target,String elements[])
	{
		ListView<String> lv = (ListView<String>) robot.lookup(target)
	       .queryAll().iterator().next();
		
		
		Assertions.assertThat(lv).hasExactlyNumItems(elements.length);
		
		for(String i:elements)
	    {
	     Assertions.assertThat(lv).hasListCell(i); 
	      
	    }
		
	}
	
		
	@Test
	public void testDisplay(FxRobot robot)
	{
		Assertions.assertThat(robot.lookup("#userLabelID").queryAs(Label.class)).hasText(user);
		
		this.checkIfListViewHasElements(robot,"#requestLVID" , reqs);
		this.checkIfListViewHasElements(robot,"#LoggedLVID",logs);
		
	
		
		model.requestsA.clear();
		Collections.addAll(model.requestsA,reqs2);
		pressRefresh(robot);
		
		Assertions.assertThat(robot.lookup("#userLabelID").queryAs(Label.class)).hasText(user);
		
		this.checkIfListViewHasElements(robot,"#requestLVID" , reqs2);
		this.checkIfListViewHasElements(robot,"#LoggedLVID",logs);
		
		
	}
	
	
	
}
