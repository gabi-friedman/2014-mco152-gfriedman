package friedman.vendingmachine;


import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class RunVendingMachine {

	private static VendingMachine vm;
	private static Inventory inventory;
	private static Money bank;
	
	// print inventory
	// make selection
	// input money
	// input selection
	// output change
	public static void main(String[] args) {		
		DecimalFormat form = new DecimalFormat("$#,##0.00");
		
		try {
			inventory = new Inventory();
			inventory.load("./inventory.txt");
			bank = new Money();
			bank.add(new Money(10, 10, 10, 10));
			vm = new VendingMachine(inventory, bank);
			System.out.println(vm.getInventory());
			
			Scanner console = new Scanner(System.in);
			
			System.out.println("Add Money/Make Selection? \n1. Dollar\n2. Quarter\n3. Dime\n4. Nickle\nor enter in the Item Code\n");
			
			String userChoice;
			System.out.println("Balance: " + form.format(vm.getPaid().getTotal()));
			boolean cont = true;
		//	Money change;
			
			do{
				userChoice = console.nextLine();
				switch(userChoice){
				case "1":
					Money forDollars = new Money(1,0,0,0);
					vm.pay(forDollars);
					System.out.println("Balance: " + form.format(vm.getPaid().getTotal()));
					break;
				case "2":
					Money forQuarters = new Money(0,1,0,0);
					vm.pay(forQuarters);
					System.out.println("Balance: " + form.format(vm.getPaid().getTotal()));
					break;
				case "3":
					Money forDimes = new Money(0,0,1,0);
					vm.pay(forDimes);
					System.out.println("Balance: " + form.format(vm.getPaid().getTotal()));
					break;
				case "4":
					Money forNickles = new Money(0,0,0,1);
					vm.pay(forNickles);
					System.out.println("Balance: " + form.format(vm.getPaid().getTotal()));
					break;
				default:
					try{
						Money change = new Money();
						change = vm.buy(userChoice);
						System.out.println("Dispensing " + vm.getInventory().get(userChoice).getName());
						System.out.println("Change: " + form.format(change.getTotal()));
						cont = false;
					} 
					catch (CodeNotFoundException e) {
						System.out.println("Code not found");
						cont = true;
					} 
					catch (NotEnoughPaidException e) {
						System.out.println("Not enough paid");
						cont = true;
					} 
					catch (NotEnoughChangeException e) {
						System.out.println("Not enough change");
						cont = true;
					}
					catch (NullPointerException e){
						System.out.println("Code not found");
						cont = true;
					}
					break;
				}
			}while(cont);
		}catch (IOException e) {
			System.out.println("Invalid filename");
			e.printStackTrace();
		}
		
		
		
	}

}
