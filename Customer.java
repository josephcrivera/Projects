import java.util.*;
import java.io.*;
public class Customer extends User{
	private double balance;
	private String seatName;
	private ArrayList<String> seatsOwned = new ArrayList<>();

	public Customer(String name, String password){
		super(name,password, "Customer");
	}

	public double getBalance() {return balance;}

	public void setBalance(double balance) {this.balance = balance;}

	public ArrayList<String> getSeatName() {return seatsOwned;}

	public Seat[][] buySeat(Seat[][] seats, double balance, Stadium msg){
		Scanner input = new Scanner(System.in);
		boolean exit = false;
		System.out.print("\n");
		while(exit != true){
			for(int i = 0; i < seats.length; i ++){
				for(int x = 0; x < seats[0].length; x++){
					if(seats[i][x].getOpen())
					{System.out.print("O ");}
					else
					{System.out.print("X ");}
				}
				System.out.print("\n"+seats[i][0].getCategory()+": $"+seats[i][0].getPrice()+" per seat\n");//working here

			}
			System.out.println("(Open seats are marked with Os, taken seats with Xs)");

			System.out.print("Which row would you like?: ");
			int row = -1;
			try{
				row = input.nextInt();
				row--;
			}
			catch(InputMismatchException e){
				System.out.println("(You can only enter whole numbers)");
				break;
			}
			int col = -1;

			System.out.print("Which seat would you like?: ");
			try{
				col = input.nextInt();
				col--;
			}
			catch(InputMismatchException e){
				System.out.println("(You can only enter whole numbers)");
				break;
			}

			String trash = input.nextLine();
			if(row < seats.length && col < seats[0].length){//problme here
				System.out.println("Confirm selection(y/n): Seat "+Integer.toString(row+1)+","+Integer.toString(col+1)+" - "+seats[row][col].getCategory()+", $"+seats[row][col].getPrice());
				String conf = input.nextLine();

				if(conf.equalsIgnoreCase("y")){//if they say yes
					if(seats[row][col].getOpen()){//if seat is open
						if(balance >= seats[row][col].getPrice()){//if they have enough money
							setBalance(balance - seats[row][col].getPrice());
							seats[row][col].setOpen(false);
							msg.setRevenue(msg.getRevenue()+seats[row][col].getPrice());
							seatName = Integer.toString(row+1)+","+Integer.toString(col+1);
							seatsOwned.add(seatName);
							System.out.println("You have successfully bought seat "+seatName);
							exit = true;
						}
						else//if they dont have enough money
						{
							System.out.println("You do not have a sufficient balance to purchase this seat\n");
							exit = true;
						}
					}
					else//if the seat is taken
					{
						System.out.println("This seat is unavailible for purchase. It is taken.\n");
						exit = true;
					}
				}
			}
			else {
				System.out.println("This seat does not exist");
				break;
			}

		}
		return seats;
	}

	public String toString()
	{return "Customer: "+getName()+" , Balance: "+getBalance();}
}