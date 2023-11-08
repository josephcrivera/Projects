import java.util.*;
public class Admin extends User{

	public Admin(String name, String password){
		super(name, password, "Admin");}

	public String toString() {return "Admin: "+ getName();}
}