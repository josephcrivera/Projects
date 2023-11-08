public abstract class User{
	public String name;
	public String password;
	public String type;

	public User(String name, String password, String type){
		this.name = name;
		this.password = password;
		this.type = type;
	}

	public String getName() {return name;}
	
	public String getPassword() {return password;}

	public String getType() {return type;}

}