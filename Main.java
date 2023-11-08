
import java.util.*;
import java.io.*;
class Main {
  public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		boolean go = true;
		Stadium msg = new Stadium("Madison Square Garden", "A$AP Rocky");
		boolean[][] resetSeats = reset();
		Seat[][] newSeats = msg.getSeats();

		for(int x = 0; x < newSeats.length; x ++){
			for(int y = 0; y < newSeats[0].length; y++){
				newSeats[x][y].setOpen(resetSeats[x][y]);
			}
		}
		msg.setSeats(newSeats);

		while(go){//main loop for whole system
			System.out.println("Welcome to "+msg.getName()+"!\nTonight we are featuring "+msg.getEvent());
			System.out.println("1. Create User\n2. Login\n3. Exit");
			String choice = input.nextLine();
			if(choice.equals("1")){//creating a user
				boolean create = true;
				while(create){
					System.out.println("\nWhich type?\n1. Admin\n2. Customer");
					String user = input.nextLine();
					System.out.println("Name?:");
					String nme = input.nextLine();
					System.out.println("Password?:");
					String pass = input.nextLine();
					String type = "";
					if(user.equals("1")){
						type = "Admin";
					}
					else if(user.equals("2")){
						type = "Customer";
					}

					try
					{
						FileWriter userWriter = new FileWriter("users.txt", true);
						userWriter.write(nme+"\n");
						userWriter.write(pass+"\n");
						userWriter.write(type+"\n");
						userWriter.close();
						System.out.println("Created User!\n");
						create = false;
					}
					catch(IOException e)
					{System.out.println("ERROR!!!");}
				}
			}
			else if(choice.equals("2")){//login
				ArrayList<Admin> adminList = new ArrayList<>();
				ArrayList<Customer> customerList = new ArrayList<>();
				try{
					File user = new File("users.txt");
					Scanner read = new Scanner(user);
					if(user.exists()){
						while(read.hasNextLine()){
							String n = read.nextLine();
							String p = read.nextLine();
							String t = read.nextLine();
							if(t.equalsIgnoreCase("Admin")){
								adminList.add(new Admin(n,p));
							}
							else if(t.equalsIgnoreCase("Customer")){
								customerList.add(new Customer(n,p));
							}
							else
							{System.out.println("ERROR");}
						}
						read.close();
					}
				}
				catch(FileNotFoundException e) {}

				boolean cont = true;
				while(cont){
					System.out.println("Name?:");
					String checkN = input.nextLine();
					System.out.println("Password?:");
					String checkP = input.nextLine();
					System.out.println("Type?:");
					String checkT = input.nextLine();
					Admin tempAd = new Admin("Billy","Bob");
					Customer tempCust = new Customer("Hey", "Yo");
					int good = -1;
					int repl = -1;
					for(int i = 0; i < adminList.size(); i++)
					{
						if(adminList.size() > i 
						&& checkN.equalsIgnoreCase(adminList.get(i).getName()) 
						&& checkP.equalsIgnoreCase(adminList.get(i).getPassword())
						&& checkT.equalsIgnoreCase(adminList.get(i).getType())){
							good = 1;
							repl = i;
							tempAd = (Admin)adminList.get(i);
						}
						else if(customerList.size() > i 
						&& checkN.equalsIgnoreCase(customerList.get(i).getName()) 
						&& checkP.equalsIgnoreCase(customerList.get(i).getPassword())
						&& checkT.equalsIgnoreCase(customerList.get(i).getType())){
							good = 2;
							repl = i;
							tempCust = (Customer)customerList.get(i);
						}
					}
					
					if(good == 1){//successful admin login
						boolean adGO = true;
						System.out.println("\nHello "+tempAd.getName());
						while(adGO){
							System.out.println("\nWhat do you want to do?\n1. Set Stadium Name\n2. Set Stadium Event\n3. Check Seats\n4. Get Revenue\n5. Logout");
							String action = input.nextLine();
							if(action.equals("1")){//set new name
								System.out.println("\nWhat is the new stadium name?:");
								String newName = input.nextLine();
								msg.setStadiumName(newName);
								System.out.println("Successfully changed the name!");
							}
							else if(action.equals("2")){//set new event
								System.out.println("\nWhat is the new event at the stadium?:");
								String newEvent = input.nextLine();
								msg.setStadiumEvent(newEvent);
								System.out.println("Successfully changed the name!");								
							}
							else if(action.equals("3")){
								msg.outputSeats();
							}
							else if(action.equals("4")){//get revenue
								System.out.println("\nRevenue: $"+msg.getRevenue());
							}
							else if(action.equals("5")) {adGO = false;}//logout
							else {System.out.println("\nInvalid Input. (Numbers only please)");}
						}
					}
					else if(good == 2){//successful customer login
						boolean boughtNow = false;
						boolean cuGO = true;
						tempCust.setBalance(resetBalance(tempCust));
						System.out.println("\nHello "+tempCust.getName());
						while(cuGO){
							System.out.println("\nWhat do you want to do?\n1. Set Balance\n2. Get Balance\n3. Buy Seat\n4. Get Seat Name\n5. Logout");
							String act2 = input.nextLine();
							if(act2.equals("1")){//set balance
								System.out.println("\nHow much money do you want to put in your account?:");
								try{
									double in = input.nextDouble();
									String trash = input.nextLine();
									tempCust.setBalance(in);
									System.out.println("Funds transferred successfully");
								}
								catch(InputMismatchException e){
									System.out.println("\nInvalid Output (Numbers only)");
								}
							}
							else if(act2.equals("2")){//get balance
								System.out.println("\nCurrent Balance: $"+Double.toString(tempCust.getBalance()));
							}
							else if(act2.equals("3")){//buy seat
								msg.setSeats(tempCust.buySeat(msg.getSeats(), tempCust.getBalance(), msg));
								boughtNow = true;
							}
							
							else if(act2.equals("4")){//get seat name
								if(boughtNow == false){
									try{
										File seats = new File("seats.txt");
										Scanner read = new Scanner(seats);
										String Name = "";
										int ok = -1;
										ArrayList<String> seatsArrL = new ArrayList<>();
										if(seats.exists()){
											while(read.hasNextLine()){
												Name = read.nextLine();
												String seatLine = read.nextLine();
												String throwaway3 = read.nextLine();
												String[] chairs = seatLine.split(" ");
												seatsArrL.clear();
												for(int i = 0; i < chairs.length; i ++){
													seatsArrL.add(chairs[i]);
												}
												if(Name.equalsIgnoreCase(tempCust.getName())){
													for(int x = 0; x < seatsArrL.size(); x++){
														System.out.println("\nSeat: "+seatsArrL.get(x));
													}
												ok = 1;
												}
											}//end of loop
											if(ok != 1)
												System.out.println("\nYou own no seats12");
											read.close();
										}
									}
									catch(FileNotFoundException e) {}

								}
								else{
									ArrayList<String> seatsOwned = tempCust.getSeatName();
									if(seatsOwned.size() > 0){
										for(int i = 0; i < seatsOwned.size(); i++){
											System.out.println("\nSeat: "+ seatsOwned.get(i));
										}
									}
									else
										System.out.println("\nYou own no seats34");
								}
							}
							else if(act2.equals("5")) {
								if(boughtNow == true){
									try{
										FileWriter seatWR = new FileWriter("seats.txt", true);
										seatWR.write(tempCust.getName()+"\n");
										for(int i = 0; i < tempCust.getSeatName().size(); i++){
											seatWR.write(tempCust.getSeatName().get(i)+" ");
										}
										seatWR.write("\n");
										seatWR.write(Double.toString(tempCust.getBalance())+"\n");
										seatWR.close();
									}
									catch(IOException e)
									{System.out.println("ERROR! Writing seats");}	
								}
								cuGO = false;
							}//logout
							else 
								{System.out.println("\nInvalid Input. (Numbers only please)\n");}
					}
				}//good != 1 || 2
				else {System.out.println("\nInvalid Input. CHeck your login information\n");}
						cont = false;
				}//ennd of cont while loop
			}//choice
			else if(choice.equals("3")){go = false;}
			else
				System.out.println("\nInvalid Input. (Number only please)\n");
			}
	}

	public static boolean[][] reset(){
		boolean[][] hold = {{true,true,true,true}, 
														{true,true,true,true}, 
														{true,true,true,true}, 
														{true,true,true,true}};
		try{
			File seats = new File("seats.txt");
			Scanner read = new Scanner(seats);
			String[][] correct = new String[4][4];
			while(read.hasNextLine()){
				String throwaway = read.nextLine();
				String lineOfSeats = read.nextLine();
				String throwaway2 = read.nextLine();
				String[] seatNames = lineOfSeats.split(" ");
				int row = -1, col = -1;
				for(int i = 0; i < seatNames.length; i ++){
					row = Integer.parseInt(seatNames[i].substring(0,1));
					col = Integer.parseInt(seatNames[i].substring(2));
					hold[row-1][col-1] = false;
				}
			}
			read.close();
		}
		catch(FileNotFoundException e) {};
		return hold;
	}

	public static double resetBalance(Customer tempCust){
		double newBalance = 0.0;
		try{
			File seats = new File("seats.txt");
			Scanner read = new Scanner(seats);
			while(read.hasNextLine()){
				String custName = read.nextLine();
				String throwaway4 = read.nextLine();
				if(custName.equalsIgnoreCase(tempCust.getName())){
					newBalance = Double.parseDouble(read.nextLine());
				}
				else{
					String throwaway5 = read.nextLine();
				}
			}
			read.close();
		}
		catch(FileNotFoundException e) {};
		return newBalance;
	}
}