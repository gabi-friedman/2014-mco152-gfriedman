package friedman.vendingmachine;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class InventoryTest {

	@Test
	public void testLoad() throws IOException{
		Inventory inv = new Inventory();
		inv.load("./inventory.txt");
		String invent = inv.toString();
		Assert.assertEquals("A01 Candy Bar @ $1.55 x 5\nB02 Chips @ $1.30 x 3\nC03 Pretzels @ $1.00 x 1\nD04 Nuts @ $2.25 x 10\nE05 Gum @ $1.75 x 20\n", invent);
	}

	@Test
	public void testGet() {
		Inventory inv = new Inventory();
		Item item = new Item("A01","Candy Bar",1.55,5);
		inv.add(item);
		String code = item.getCode();
		Item expected = inv.get(code);
		Assert.assertEquals(expected, item); 
	}
	
	@Test
	public void testAdd() {
		 Inventory inv = new Inventory();
		 Item item = new Item("B02","Chips",1.75,7);
		 inv.add(item);
		 Assert.assertEquals("B02 Chips @ $1.75 x 7\n", inv.toString());
	}
	
	@Test
	public void testRemoveOne() {
		 Inventory inv = new Inventory();
		 Item item = new Item("B02","Chips",1.75,7);
		 inv.add(item);
		 inv.removeOne(item.getCode());
		 int expected = item.getQuantity();
		 Assert.assertEquals(6, expected);
	}
	
	@Test
	public void testIsEmptyFalse(){
		Inventory inv = new Inventory();
		Item item = new Item("B02","Chips",1.75,5);
		inv.add(item);
		String code = item.getCode();
		boolean empty = inv.isEmpty(code);
		Assert.assertFalse(empty);
	}
	
	@Test
	public void testIsEmptyTrue(){
		Inventory inv = new Inventory();
		Item item = new Item("B02","Chips",1.75,0);
		inv.add(item);
		String code = item.getCode();
		boolean empty = inv.isEmpty(code);
		Assert.assertTrue(empty);
	}
}


