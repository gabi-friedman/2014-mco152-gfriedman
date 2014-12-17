package friedman.test1;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class AirplaneTest {

	@Test
	public void testToStringWithEmptyPlane() {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		Assert.assertEquals(
				"    AB_CD_EF\n" +
				"001 .._.._..\n" + 
				"002 .._.._..\n" + 
				"003 .._.._..\n", plane.toString());
	}
	
	@Test
	public void testToStringWithFullPlane() throws UnknownSeatException {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		plane.occupy(
				"1A", "1B", "1C", "1D", "1E", "1F",
				"2A", "2B", "2C", "2D", "2E", "2F",
				"3A", "3B", "3C", "3D", "3E", "3F");
		Assert.assertEquals(
				"    AB_CD_EF\n" +
				"001 OO_OO_OO\n" + 
				"002 OO_OO_OO\n" + 
				"003 OO_OO_OO\n", plane.toString());
	}
	
	@Test
	public void testOccupy() throws UnknownSeatException {
		Airplane plane = new Airplane("AB_CD", 3);
		plane.occupy("1B");
		Assert.assertEquals(true, plane.getSeat("1B").isOccupied());
		
	}
	
	@Test
	public void testIsNextLetter() throws UnknownSeatException{
		Airplane plane = new Airplane("AB_CD_EF", 3);
		Seat seat1 = plane.getSeat("1B");
		Seat seat2 = plane.getSeat("1C");
		boolean next = plane.isNextSeat(seat1, seat2);
		
		Assert.assertFalse(next);
	}
	
	@Test
	public void testIsNextSeat() throws UnknownSeatException{
		Airplane plane = new Airplane("AB_CD_EF", 3);
		Seat seat1 = plane.getSeat("1A");
		Seat seat2 = plane.getSeat("1B");
		boolean next = plane.isNextSeat(seat1, seat2);
		
		Assert.assertTrue(next);
	}
	
	@Test
	public void testIsNextSeatIsntNext() throws UnknownSeatException{
		Airplane plane = new Airplane("AB_CD_EF", 3);
		Seat seat1 = plane.getSeat("1B");
		Seat seat2 = plane.getSeat("1C");
		boolean next = plane.isNextSeat(seat1, seat2);
		
		Assert.assertFalse(next);
	}
	
	@Test
	public void testGetNumSeats() {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		
		int numSeats = plane.getNumEmptySeats();
		Assert.assertEquals(18, numSeats);
		
	}
	
	@Test
	public void testGetNumEmptySeats() throws UnknownSeatException {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		plane.occupy("1A","1B");
		int emptySeats = plane.getNumEmptySeats();
		Assert.assertEquals(16, emptySeats);
	}

	@Test
	public void testIsFull() throws UnknownSeatException {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		plane.occupy(
				"1A", "1B", "1C", "1D", "1E", "1F",
				"2A", "2B", "2C", "2D", "2E", "2F",
				"3A", "3B", "3C", "3D", "3E", "3F");
		boolean full = plane.isFull();
		Assert.assertEquals(true, full);
	}
	
	@Test
	public void testGetSeatThrowsUnknownSeatException() throws UnknownSeatException {
		Seat seat;
		Airplane plane = new Airplane("AB_CD_EF", 3);
		plane.occupy(
				"1A", "1B", "1C", "1D", "1E", "1F",
				"2A", "2B", "2C", "2D", "2E", "2F",
				"3A", "3B", "3C", "3D", "3E", "3F");
		try{
			seat = plane.getSeat("1X");
			Assert.fail("getSeat() doesnt throw UnknownSeatException");
			
		} catch (UnknownSeatException e){
			
		}
	}
	
	@Test
	public void testGetEmptySeats() throws UnknownSeatException{
		boolean allGood = false;
		List<Seat> emptySeats = new ArrayList<Seat>();
		Airplane plane = new Airplane("AB_CD_EF", 3);
		plane.occupy(
				/*"1A",*/ "1B", "1C", "1D", "1E", "1F",
				/*"2A",*/ "2B", "2C", "2D", "2E", "2F",
				/*"3A",*/ "3B", "3C", "3D", "3E", "3F");
		emptySeats = plane.getEmptySeats();
		
		List<Seat> realEmptySeats = new ArrayList<Seat>();
		realEmptySeats.add(plane.getSeat("1A"));
		realEmptySeats.add(plane.getSeat("2A"));
		realEmptySeats.add(plane.getSeat("3A"));
		
		Assert.assertEquals(realEmptySeats, emptySeats);	
		
	}
	
	@Test
	public void testOccupySeats() 
			throws UnknownSeatException, FullPlaneException, NotEnoughSeatsTogeatherException {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		plane.occupy(
				/*"1A", "1B","1C", "1D",  */"1E", "1F",
				"2A", "2B", "2C", "2D", "2E", "2F",
				"3A", "3B", "3C", "3D", "3E", "3F");
		
		List<Seat> realGroupSeats = new ArrayList<Seat>();
		realGroupSeats.add(plane.getSeat("1A"));
		realGroupSeats.add(plane.getSeat("1B"));
		
		List<Seat> groupSeats = new ArrayList<Seat>();
		groupSeats = plane.occupySeats(2);

		Assert.assertEquals(realGroupSeats, groupSeats);
	}
	
	@Test
	public void testOccupySeats2() 
			throws UnknownSeatException, FullPlaneException, NotEnoughSeatsTogeatherException {
		Airplane plane = new Airplane("AB_CDE_FG", 3);
		
		
		/*plane.occupy(
				"1A", "1B", "1C", "1D", "1E", "1F", "1G"
				"2A", "2B", "2C", "2D", "2E", "2F",
				"3A", "3B", "3C", "3D", "3E", "3F");*/
		
		List<Seat> realGroupSeats = new ArrayList<Seat>();
		realGroupSeats.add(plane.getSeat("1C"));
		realGroupSeats.add(plane.getSeat("1D"));
		realGroupSeats.add(plane.getSeat("1E"));
		
		List<Seat> groupSeats = new ArrayList<Seat>();
		groupSeats = plane.occupySeats(3);

		Assert.assertEquals(realGroupSeats, groupSeats);
	}
	
	@Test
	public void testOccupySeatsThrowsNotEnoughSeatsTogeatherException() 
			throws FullPlaneException, UnknownSeatException {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		List<Seat> groupSeats = new ArrayList<Seat>();
		
		try{
			groupSeats = plane.occupySeats(5);
			Assert.fail("occupySeat() doesnt throw NotEnoughSeatsTogetherException");
			
		} catch (NotEnoughSeatsTogeatherException e){
			
		}
	}
	
	@Test
	public void testOccupySeatsThrowsFullPlaneException() throws NotEnoughSeatsTogeatherException, UnknownSeatException {
		Airplane plane = new Airplane("AB", 1);
		List<Seat> groupSeats = new ArrayList<Seat>();
		
		try{
			groupSeats = plane.occupySeats(2);
			groupSeats = plane.occupySeats(2);
			Assert.fail("occupySeat() doesnt throw FullPlaneException");
			
		} catch (FullPlaneException e){
			
		}
	}

}
