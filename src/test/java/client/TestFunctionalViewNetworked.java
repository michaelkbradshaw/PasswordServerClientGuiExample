package client;

//import static org.assertj.core.api.Assertions.assertThat;
import org.testfx.assertions.api.Assertions;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.util.WaitForAsyncUtils;

import client.model.NetworkPasswordModel;
import client.model.TestingPasswordModel;
import client.model.ViewTransitionalModel;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import restPasswordServer.RESTPasswordServer;


@SpringBootTest(
		webEnvironment=WebEnvironment.RANDOM_PORT,
		classes = RESTPasswordServer.class) 
//@AutoConfigureRestTestClient
@ExtendWith(ApplicationExtension.class)
public class TestFunctionalViewNetworked
{

	@Autowired
	private RESTPasswordServer server;

	
	//@Autowired
	//private RestTestClient tClient;
	
	@LocalServerPort
    private int port;
	
	
	
	NetworkPasswordModel model;
	ViewTransitionalModel tm; 
	
	@Start  //Before
	  private void start(Stage stage)
	  {
		
		model = new NetworkPasswordModel("http://localhost:" + port+"/");
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
		
		String pw = model.requestPassword();
		
		enterPassword(robot,pw);
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
