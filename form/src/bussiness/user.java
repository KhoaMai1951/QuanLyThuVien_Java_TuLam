package bussiness;

public class user {
	public String Username;
	public String Password;
	 
	public void User() 
	{
	}
	 
	public void User(String Username, String Password) 
	{
	 this.Username = Username;
	 this.Password = Password;
	}
	 
	public String getUsername() 
	{
	 return Username;
	}
	
	public void setUsername(String Username) 
	{
	 this.Username = Username;
	}
	 
	public String getPassword() 
	{
	 return Password;
	}
	 
	public void setPassword(String Password) 
	{
	 this.Password = Password;
	 }

}
