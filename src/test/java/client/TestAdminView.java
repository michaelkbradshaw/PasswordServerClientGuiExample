package client;

//import static org.assertj.core.api.Assertions.assertThat;
import org.testfx.assertions.api.Assertions;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
public class TestAdminView
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
	
	
	
	
	@Test
	public void requestID(FxRobot robot)
	{
		//Ask for a 
		enterUser(robot,"bob");
		pressRequest(robot);
		FxAssert.verifyThat(".dialog-pane", NodeMatchers.isVisible());
		 
		String response = "Password for bob is bobPW";
    	
		Assertions.assertThat(robot.lookup(response).queryAs(Label.class)).hasText(response);
		robot.clickOn("OK");
		

		enterUser(robot,"alice");
		pressRequest(robot);
		FxAssert.verifyThat(".dialog-pane", NodeMatchers.isVisible());
		 
		response = "Password for alice is alicePW";
    	
		Assertions.assertThat(robot.lookup(response).queryAs(Label.class)).hasText(response);
		robot.clickOn("OK");

	}
	
	
	@Test
	public void badLogin(FxRobot robot)
	{
		
		enterUser(robot,"bob");
		enterPassword(robot,"obo");
		pressLogin(robot);
		FxAssert.verifyThat(".dialog-pane", NodeMatchers.isVisible());
		 
		String response = "Bad UserName/Password";
    	
		Assertions.assertThat(robot.lookup(response).queryAs(Label.class)).hasText(response);
		robot.clickOn("OK");
	}
	
	
	
	
}
