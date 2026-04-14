package client.model;

import java.util.ArrayList;

public class TestingPasswordModel extends AbstractPasswordModel
{

	
	ArrayList<String> requestsA = new ArrayList<>();
	ArrayList<String> logsA= new ArrayList<>();
	
	@Override
	public String requestPassword()
	{
		if(! requestsA.contains(this.username.get()))
		{
			requestsA.add(this.username.get());
		}
		return this.username.get()+"PW";
	}

	@Override
	public boolean checkPassword()
	{
		
		boolean ret = requestPassword().equals(this.password.get());
		
		if(ret && ! logsA.contains(this.username.get()))
		{
			logsA.add(this.username.get());
		}
		
		return ret;
		
	}

	@Override
	public void pullData()
	{
		this.getRequests().clear();
		this.getRequests().addAll(requestsA);
		
		this.getLogged().clear();
		this.getLogged().addAll(logsA);
		
	}

}
