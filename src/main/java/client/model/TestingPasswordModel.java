package client.model;

import java.util.ArrayList;

import javafx.util.Pair;

public class TestingPasswordModel extends AbstractPasswordModel
{

	
	ArrayList<String> requests;
	ArrayList<String> logs;
	
	@Override
	public String requestPassword()
	{
		return this.username.get()+"PW";
	}

	@Override
	public boolean checkPassword()
	{
		return requestPassword().equals(this.password.get());
	}

	@Override
	public void pullData()
	{
		// TODO Auto-generated method stub
		
	}

}
