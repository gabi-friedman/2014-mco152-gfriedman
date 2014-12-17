package friedman.vendingmachine;

import java.text.DecimalFormat;

import org.junit.Assert;
import org.junit.Test;

public class MoneyTest {
	
	
	DecimalFormat df = new DecimalFormat("$#,##0.00");
	
	@Test
	public void testAdd(){
		Money moneyTest = new Money(10,10,10,10);
		Money money = new Money(5,5,5,5);
		money.add(moneyTest);
		String regMoney = df.format(money.getTotal());
		Assert.assertEquals( "$21.00", regMoney);
	}
	
	@Test
	public void testRemove() throws NotEnoughChangeException{
		Money moneyTest = new Money(0,4,0,0);
		Money money = moneyTest.remove(.25);
		String total = df.format(money.getTotal());
		Assert.assertEquals("$0.25",total);
	}
	
	@Test
	public void testRemoveThrowsNotEnoughChangeException() {
		Money money = new Money(0,0,0,0);
		try{
			money.remove(1.00);
			Assert.fail("Doesnt throw a NotEnoughChangeException");
		}catch (NotEnoughChangeException e){
			//this will return a passed test
		}
	}
	
	@Test 
	public void testGetTotal(){
		Money moneyTest = new Money(1,2,0,0);
		String total = df.format(moneyTest.getTotal());
		Assert.assertEquals("$1.50", total);
	}
	
	
}
