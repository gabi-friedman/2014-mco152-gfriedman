package friedman.vendingmachine;

import java.text.DecimalFormat;

import org.junit.Assert;
import org.junit.Test;

public class VendingMachineTest {

	DecimalFormat formatter = new DecimalFormat("$#,##0.00");

	@Test
	public void testPay() {
		Inventory inv = new Inventory();
		Money bank = new Money(5, 0, 0, 0);
		VendingMachine vm = new VendingMachine(inv, bank);
		Money additional = new Money(1, 0, 0, 0);

		double totalPaid = vm.pay(additional);
		String tP = formatter.format(totalPaid);
		Assert.assertEquals("$1.00", tP);
	}

	@Test
	public void testBuy() throws CodeNotFoundException, NotEnoughPaidException, NotEnoughChangeException {

		Inventory inv = new Inventory();
		Item item = new Item("B02", "Chips", 1.75, 7);
		inv.add(item);
		Money bank = new Money(10, 10, 10, 10);
		VendingMachine vm = new VendingMachine(inv, bank);
		Money paid = new Money(3, 0, 0, 0);
		vm.pay(paid);
		Money change = vm.buy("B02");
		String c = formatter.format(change.getTotal()).toString();
		Assert.assertEquals("$1.25", c);

	}

	@Test
	public void testBuyThrowsNotEnoughPaidException() throws CodeNotFoundException, NotEnoughPaidException,
			NotEnoughChangeException {
		Inventory inv = new Inventory();
		Item item = new Item("B02", "Chips", 1.75, 7);
		inv.add(item);
		Money bank = new Money(10, 10, 10, 10);
		VendingMachine vm = new VendingMachine(inv, bank);
		Money paid = new Money(0, 0, 0, 1);
		vm.pay(paid);
		try {
			Money change = vm.buy("B02");
			Assert.fail("buy method doesnt throw CodeNotEnoughPaidException");
		} catch (NotEnoughPaidException e) {

		}
	}

	@Test
	public void testBuyThrowsCodeNotFoundException() throws CodeNotFoundException, NotEnoughPaidException,
			NotEnoughChangeException {
		Inventory inv = new Inventory();
		Item item = new Item("B02", "Chips", 1.75, 7);
		inv.add(item);
		Money bank = new Money(10, 10, 10, 10);
		VendingMachine vm = new VendingMachine(inv, bank);
		Money paid = new Money(3, 0, 0, 0);
		vm.pay(paid);
		try {
			Money change = vm.buy("B01");
			Assert.fail("buy method doesnt throw CodeNotFoundException");
		} catch (CodeNotFoundException e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testBuyPaidReset() throws CodeNotFoundException, NotEnoughPaidException, NotEnoughChangeException {
		Inventory inv = new Inventory();
		Item item = new Item("B02", "Chips", 1.75, 7);
		inv.add(item);
		Money bank = new Money(10, 10, 10, 10);
		VendingMachine vm = new VendingMachine(inv, bank);
		Money paid = new Money(3, 0, 0, 0);
		vm.pay(paid);
		Money change = vm.buy("B02");

		String finalPaid = formatter.format(vm.getPaid().getTotal());
	}

	@Test
	public void testBuyQuantityReducedByOne() throws CodeNotFoundException, NotEnoughPaidException,
			NotEnoughChangeException {
		Inventory inv = new Inventory();
		Item item = new Item("B02", "Chips", 1.75, 7);
		inv.add(item);
		Money bank = new Money(10, 10, 10, 10);
		VendingMachine vm = new VendingMachine(inv, bank);
		Money paid = new Money(3, 0, 0, 0);
		vm.pay(paid);
		Money change = vm.buy("B02");

		Assert.assertEquals(6, item.getQuantity());
	}


	@Test
	public void testBuyThrowsNotEnoughChangeException() throws NotEnoughPaidException, CodeNotFoundException, NotEnoughChangeException {
		Inventory inv = new Inventory();
		Item item = new Item("B02", "Chips", 1.75, 7);
		inv.add(item);
		VendingMachine vm = new VendingMachine(inv, new Money(0, 0, 0, 0));

		try {
			Money paid = new Money(10, 10, 10, 10);
			vm.pay(paid);
			vm.buy("B02");
			Assert.fail("NotEnoughChangeException should be thrown here");
		} 
		catch (NotEnoughChangeException e) {

		}

	}
	
	@Test
	public void testBuyThrowsNotEnoughChangeExceptionBankHas1sOnly() throws CodeNotFoundException, NotEnoughPaidException, NotEnoughChangeException {
		Inventory inv = new Inventory();
		Item item = new Item("B02", "Chips", 1.75, 7);
		inv.add(item);
		Money bank = new Money(10, 0, 0, 0);
		VendingMachine vm = new VendingMachine(inv, bank);
		Money paid = new Money(0, 100, 1, 1);
		vm.pay(paid);
		try {
			Money change = vm.buy("B02");
			Assert.fail("buy method doesnt throw CodeNotEnoughChangeException when bank has wrong type of change");
		} catch (NotEnoughChangeException e) {

		}
	}
	
	@Test
	public void testBuyItemOutOfStock() throws CodeNotFoundException, NotEnoughPaidException, NotEnoughChangeException{
		Inventory inv = new Inventory();
		Item item = new Item("B02", "Chips", 1.75, 0);
		inv.add(item);
		Money bank = new Money(10, 10, 10, 10);
		VendingMachine vm = new VendingMachine(inv, bank);
		Money paid = new Money(1, 3, 1, 1);
		vm.pay(paid);
		Money change = vm.buy("B02");
		
		Assert.assertEquals(0,change.getTotal(),0);
	}	
}
