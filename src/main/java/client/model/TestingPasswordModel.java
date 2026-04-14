package client.model;

import java.util.ArrayList;

public class TestingPasswordModel extends AbstractPasswordModel
{

	
	ArrayList<String> requests = new ArrayList<>();
	ArrayList<String> logs= new ArrayList<>();
	
	@Override
	public String requestPassword()
	{
		if(! requests.contains(this.username.get()))
		{
			requests.add(this.username.get());
		}
		return this.username.get()+"PW";
	}

	@Override
	public boolean checkPassword()
	{
		
		boolean ret = requestPassword().equals(this.password.get());
		
		if(ret && ! logs.contains(this.username.get()))
		{
			logs.add(this.username.get());
		}
		
		return ret;
		
	}

	@Override
	public void pullData()
	{
		// TODO Auto-generated method stub
		
	}

}
