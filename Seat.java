public class Seat{
	private boolean open;
	private double price;
	private String category;

	public Seat(boolean open, double price, String category){
		this.open = open;
		this.price = price;
		this.category = category;
	}

	public boolean getOpen() {return open;}

	public void setOpen(boolean open) {this.open = open;}

	public double getPrice() {return price;}

	private void setPrice(double price) {this.price = price;}

	public String getCategory() {return category;}
	
	public void setCategory(String category) {this.category = category;}

	public String toString(){
		if(getOpen()) {return category+": "+price+". The seat is open.";}
		else {return category+": "+price+". The seat is taken.";}
	}
}