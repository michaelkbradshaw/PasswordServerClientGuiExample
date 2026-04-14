package restPasswordServer;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.springframework.beans.factory.annotation.Autowired;


@SpringBootTest(
		webEnvironment=WebEnvironment.RANDOM_PORT,
		classes = RESTPasswordServer.class) 
@AutoConfigureRestTestClient
class RESTPasswordServerTest
{
	@Autowired
	private RESTPasswordServer server;

	
	@Autowired
	private RestTestClient tClient;
	
	@Test
	void testroot()
	{
		tClient.get()
		.uri("/")
		.exchange()
		.expectBody(String.class)
		.isEqualTo(
				"""	
<html>
<body>
<h1>Password server</h1>
<p>This is a REST server for retrieving and storing passwords.  Here are the following links to services provide on the server.</p>
<ol>
<li><a href="/request">/request"</a> a JSON list of all the people that have requested passwords.
<li><a href="/auth">/auth"</a> a JSON list of all the people that have successfully submitted their password
<li><a href="/request/janedoe">/request/:username"</a> A request for a password for the username provided.
<li><a href="/auth/janedoe/4132">/auth/:username/:password"</a> Attempts to authenticate the password for the username provided.
</ol>
</html>"""
				);
		
		
	}
	
	
	@Test
	void testInteraction()
	{
		//nothing at start
		tClient.get().uri("/request").exchange()
		.expectBody(String.class)
		.isEqualTo("Requesters: []");
		
		tClient.get().uri("/auth").exchange()
		.expectBody(String.class)
		.isEqualTo("Authorized: []");
		
		//request first password
		
		tClient.get().uri("/request/michael").exchange()
		.expectBody(String.class)
		.isEqualTo("7575"); 
		//TODO add a mock to return a value I can check
		
		//calling again to make sure we don't double book
		tClient.get().uri("/request/michael").exchange()
		.expectBody(String.class);
		 		
		tClient.get().uri("/request").exchange()
		.expectBody(String.class)
		.isEqualTo("Requesters: [michael]");
	
		//no change to auth
		tClient.get().uri("/auth").exchange()
		.expectBody(String.class)
		.isEqualTo("Authorized: []");
		
		tClient.get().uri("/auth/michael/7575").exchange()
		.expectBody(String.class)
		.isEqualTo("Authentication Successful");
		
		//check for double insert
		tClient.get().uri("/auth/michael/7575").exchange()
		.expectBody(String.class)
		.isEqualTo("Authentication Successful");

		//no change to auth
		tClient.get().uri("/auth").exchange()
				.expectBody(String.class)
				.isEqualTo("Authorized: [michael]");
	
		
		
		
		assertThat(server.requesters).contains("michael");
		assertThat(server.requesters).hasSize(1);
	}

	
	
	
}
