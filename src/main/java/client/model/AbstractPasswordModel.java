package client.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public abstract class AbstractPasswordModel
{

	StringProperty username = new SimpleStringProperty();
	StringProperty password = new SimpleStringProperty();
	
	
	ObservableList<String> requests = 
		      FXCollections.observableArrayList();
	ObservableList<String> logged = 
		      FXCollections.observableArrayList();
	

	public abstract String requestPassword();
	public abstract boolean checkPassword();
	public abstract void pullData();
	/**
	 * @return the username
	 */
	public StringProperty getUsername()
	{
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(StringProperty username)
	{
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public StringProperty getPassword()
	{
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(StringProperty password)
	{
		this.password = password;
	}
	/**
	 * @return the requests
	 */
	public ObservableList<String> getRequests()
	{
		return requests;
	}
	/**
	 * @param requests the requests to set
	 */
	public void setRequests(ObservableList<String> requests)
	{
		this.requests = requests;
	}
	/**
	 * @return the logged
	 */
	public ObservableList<String> getLogged()
	{
		return logged;
	}
	/**
	 * @param logged the logged to set
	 */
	public void setLogged(ObservableList<String> logged)
	{
		this.logged = logged;
	}
	
	
	
	
	
	
	
	
	
	
}
