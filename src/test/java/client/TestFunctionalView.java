package client;

//import static org.assertj.core.api.Assertions.assertThat;
import org.testfx.assertions.api.Assertions;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.util.WaitForAsyncUtils;

import client.model.TestingPasswordModel;
import client.model.ViewTransitionalModel;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
public class TestFunctionalView
{

	
	TestingPasswordModel model;
	ViewTransitionalModel tm; 
	
	@Start  //Before
	  private void start(Stage stage)
	  {
		model = new TestingPasswordModel();
		tm = new ViewTransitionalModel(stage, model);
		tm.showLoginScene();
		stage.show();
	  }

	
	private void clearTextField(FxRobot robot,String selector)
	  {
		  TextField tf = robot.lookup(selector)
		  .queryAs(TextField.class);
		  
		  Platform.runLater(()->{tf.clear();});
		  WaitForAsyncUtils.waitForFxEvents();
		  
	  }
	
	
	private void enterUser(FxRobot robot, String text)
	  {
		clearTextField(robot,"#userNameID");
	    robot.clickOn("#userNameID");
	    robot.write(text);
	  }
	
	private void enterPassword(FxRobot robot, String text)
	  {
		clearTextField(robot,"#passwordID");
	    robot.clickOn("#passwordID");
	    robot.write(text);
	  }

	
	private void pressRequest(FxRobot robot)
	{
		robot.clickOn("#requestID");
	}
	
	private void pressLogin(FxRobot robot)
	{
		robot.clickOn("#loginID");
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
	
		
		
	private void pressLogout(FxRobot robot)
	{
		robot.clickOn("#logoutID");
	}
	
	
	@Test
	public void TestFunctionalNavigation(FxRobot robot)
	{
		
		enterUser(robot,"alice");
		pressRequest(robot);
		robot.clickOn("OK");
		
		enterUser(robot,"bob");
		enterPassword(robot,"bobPW");
		pressLogin(robot);

		String reqs[] = {"alice","bob"};
		String logs[] = {"bob"};
		
		//should be in admin view
		
		
		Assertions.assertThat(robot.lookup("#userLabelID").queryAs(Label.class)).hasText("bob");
		
			
		this.checkIfListViewHasElements(robot,"#requestLVID" , reqs);
		this.checkIfListViewHasElements(robot,"#LoggedLVID",logs);
	
		
		pressLogout(robot);
		
		//should be in login view
		pressRequest(robot);
		robot.clickOn("OK");
		
		
		
		
	}
	
	
	
	
}
