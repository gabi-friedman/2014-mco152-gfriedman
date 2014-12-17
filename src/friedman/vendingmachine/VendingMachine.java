package friedman.vendingmachine;

public class VendingMachine {
	
	private Inventory inventory;
	private Money bank;
	
	/**
	 * The amount of money the person has put into the Vending Machine so far
	 */
	private Money paid;
	
	public VendingMachine(Inventory inventory, Money bank) {
		this.inventory = inventory;
		this.bank = bank;
		paid = new Money();
	}

	public Inventory getInventory() {
		return inventory;
	}

	public Money getBank() {
		return bank;
	}
	
	public Money getPaid() {
		return paid;
	}
	
	/**
	 * Add additional Money to the machine
	 * @param additional
	 * @return the amount that the person has put into the machine
	 */
	public double pay(Money additional) {
		paid.add(additional);
		return paid.getTotal();
	}
	
	/**
	 * 
	 * @param code
	 * @return the amount of change as a Money object
	 * @throws CodeNotFoundException if there is no item with that code
	 * @throws NotEnoughPaidException if paid is not enough to buy the item
	 * @throws NotEnoughChangeException if the transaction cannot be completed because
	 * 			there isn't enough money in the vending machine for the change
	 */
	public Money buy(String code) throws CodeNotFoundException, NotEnoughPaidException, NotEnoughChangeException {
		Money change;
		if(inventory.get(code) == null){
			throw new CodeNotFoundException();
		}
		else if(inventory.get(code).getQuantity() == 0){
			change = new Money(0,0,0,0);
			return change;
		}
		else if(paid.getTotal() >= inventory.get(code).getPrice()){
			bank.add(paid);
		
			inventory.removeOne(code);
			
			double toMakeChange = paid.getTotal() - inventory.get(code).getPrice();
			change = bank.remove(toMakeChange);
			
			if(change.getNumDollars() > bank.getNumDollars() || change.getNumQuarters() > bank.getNumQuarters() || change.getNumDimes() > bank.getNumDimes() || change.getNumNickles() > bank.getNumNickles()){
				throw new NotEnoughChangeException();
			}
			
			this.paid.setNumDollars(0);
			this.paid.setNumQuarters(0);
			this.paid.setNumDimes(0);
			this.paid.setNumNickles(0);
			
			return change;
		}
		else{
			throw new NotEnoughPaidException();
		}
		
	}
	
	
}
