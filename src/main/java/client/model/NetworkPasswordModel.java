package client.model;

import org.springframework.web.client.RestClient;

public class NetworkPasswordModel extends AbstractPasswordModel
{


	RestClient client = RestClient.create();
	
	String uriBase = "http://localhost:9005/";
		
	
	public NetworkPasswordModel()
	{
		//use default uri
	}
	
	public NetworkPasswordModel(String uri)
	{
		uriBase = uri;
	}
			
			
	
	
	@Override
	public String requestPassword()
	{
		
		String pw = client.get()
				.uri(uriBase+"request/"+this.getUsername().get())
				.retrieve()
				.body(String.class);
		
		return pw;
	}

	@Override
	public boolean checkPassword()
	{
		String response = client.get()
				.uri(uriBase+"auth/"+
						this.getUsername().get()+"/"+
						this.getPassword().get())
				.retrieve()
				.body(String.class);
		
		return response.equals("Authentication Successful");
	}

	@Override
	public void pullData()
	{
		String auths[] = client.get()
				.uri(uriBase+"authList")
				.retrieve()
				.body(String[].class);
		
		String reqs[] = client.get()
				.uri(uriBase+"requestList")
				.retrieve()
				.body(String[].class);
		
		this.getRequests().clear();
		this.getRequests().addAll(reqs);
		
		this.getLogged().clear();
		this.getLogged().addAll(auths);
		

	}

}
