package friedman.vendingmachine;

public class Money {

	private int numDollars;
	private int numQuarters;
	private int numDimes;
	private int numNickles;
	
	public Money() {
		
	}
	
	public Money(int numDollars, int numQuarters, int numDimes, int numNickles) {
		this.numDollars = numDollars;
		this.numQuarters = numQuarters;
		this.numDimes = numDimes;
		this.numNickles = numNickles;
	}

	public void add(Money money) {
		int dollars = money.getNumDollars();
		this.setNumDollars(getNumDollars() + dollars);
		
		int quarters = money.getNumQuarters();
		this.setNumQuarters(getNumQuarters() + quarters);
		
		int dimes = money.getNumDimes();
		this.setNumDimes(getNumDimes() + dimes);
		
		int nickles = money.getNumNickles();
		this.setNumNickles(getNumNickles() + nickles);
	}
	
	public Money remove(double amount) throws NotEnoughChangeException {
		
		int numDollarsHere = 0;
		int numQuartersHere = 0;
		int numDimesHere = 0;
		int numNicklesHere = 0;
		
		while(amount >= 1 && this.numDollars >= 1){
			numDollarsHere++;
			amount = Math.round((amount - 1)*100.0)/100.0;
			this.numDollars--;
		}
		while(amount >= .25 && this.numQuarters >= 1){
			numQuartersHere++;
			amount = Math.round((amount - .25)*100.0)/100.0;
			this.numQuarters--;
		}
		while(amount >= .1 && this.numDimes >= 1){
			numDimesHere++;
			amount = Math.round((amount - .10)*100.0)/100.0;
			this.numDimes--;
		}
		while(amount >= .05 && this.numNickles >= 1){
			numNicklesHere++;
			amount = Math.round((amount - .05)*100.0)/100.0;
			this.numNickles--;
		}
		if(amount != 0){
			throw new NotEnoughChangeException();
		}
		
		Money back = new Money(numDollarsHere, numQuartersHere, numDimesHere, numNicklesHere);
		return back;
		
		/*
		 * while(amount >= 1){
			this.numDollars--;
			amount = amount - 1;
		}
		while(amount >= .25){
			this.numQuarters--;
			amount = amount - .25;
		}
		while(amount >= .1){
			this.numDimes--;
			amount = amount - .10;
		}
		while(amount >= .05){
			this.numNickles--;
			amount = amount - .05;
		}
		 */
	}
	
	public double getTotal() {
		double total = numDollars + numQuarters * .25 + numDimes * .1 + numNickles * .05;
		return total;
	}

	public int getNumDollars() {
		return numDollars;
	}

	public void setNumDollars(int numDollars) {
		this.numDollars = numDollars;
	}

	public int getNumQuarters() {
		return numQuarters;
	}

	public void setNumQuarters(int numQuarters) {
		this.numQuarters = numQuarters;
	}

	public int getNumNickles() {
		return numNickles;
	}

	public void setNumNickles(int numNickles) {
		this.numNickles = numNickles;
	}

	public int getNumDimes() {
		return numDimes;
	}

	public void setNumDimes(int numDimes) {
		this.numDimes = numDimes;
	}
	
}
