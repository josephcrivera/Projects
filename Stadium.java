public class Stadium{
	private Seat[][] seats = new Seat[4][4];
	private String name = "Madison Square Garden";
	private String event = "The Strokes";
	private double revenue = 0.0;

	public Stadium(String name, String event)
	{
		this.name = name;
		this.event = event;
		createSeats();
	}

	public void createSeats()
	{
		for(int x = 0; x < 4; x++)
		{
			seats[0][x] = new Seat(true, 50, "Nosebleeds");
		}

		for(int x = 0; x < 4; x++)
		{
			seats[1][x] = new Seat(true, 100, "Top Row");
		}

		for(int x = 0; x < 4; x++)
		{
			seats[2][x] = new Seat(true, 200, "Front Row");
		}

		for(int x = 0; x < 4; x++)
		{
			seats[3][x] = new Seat(true, 800, "VIP");
		}
	}

	public void outputSeats()
	{
		String[][] ans = new String[4][4];
		for(int i = 0; i < this.seats.length; i ++){
				for(int x = 0; x < this.seats[0].length; x++){
					if(seats[i][x].getOpen()) {ans[i][x] = "0 ";}
					else {ans[i][x] = "X ";}
				}
			}

			for(int y = 0; y < ans.length; y++){
				for(int z = 0; z < ans[0].length; z++){
					System.out.print(ans[y][z]);
				}
				System.out.print("\n"+seats[y][0].getCategory()+": $"+seats[y][0].getPrice()+" per seat\n");//working here
			}
			System.out.println("(Open seats are marked with Os, taken seats with Xs)");
	}

	public Seat[][] getSeats() {return seats;}

	public void setSeats(Seat[][] seats) {this.seats = seats;}

	public String getName() {return name;}
	
	public void setStadiumName(String name) {this.name = name;}

	public double getRevenue() {return revenue;}

	public void setRevenue(double revenue) {this.revenue = revenue;}

	public String getEvent() {return event;}

	public void setStadiumEvent(String event) {this.event = event;}

	public String toString() {return name+ ": Featuring "+event;}
}