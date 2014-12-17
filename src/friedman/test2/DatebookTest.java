package friedman.test2;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class DatebookTest {

	/**
	 * 
	 * @param year
	 *            4 digit year
	 * @param month
	 *            Calendar.JANUARY, Calendar.FEBRUARY...
	 * @param dayOfMonth
	 *            starting from 1
	 * @return A Date from the specified parameters
	 */
	private Date getDate(int year, int month, int dayOfMonth) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(year, month, dayOfMonth, 0, 0, 0);
		return calendar.getTime();
	}

	@Test
	/**
	 * After calling addSingleEvent() verify that the Event is returned when calling getEvents()
	 */
	public void testAddSingleEvent() {
		Datebook datebook = new Datebook();

		// given an event
		Event event = new Event("EVENT 1", 1200);
		Date today = getDate(2014, Calendar.NOVEMBER, 25);

		// when the event is added today
		datebook.addSingleEvent(event, today);

		// then the event is returned for today
		List<Event> list = datebook.getEvents(today);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
		Assert.assertSame(event, list.get(0));

		// then the event is not returned tomorrow
		Date tomorrow = getDate(2014, Calendar.NOVEMBER, 26);
		list = datebook.getEvents(tomorrow);
		Assert.assertNotNull(list);
		Assert.assertTrue(list.isEmpty());
	}

	@Test
	/**
	 * After calling addYearlyEvent() verify that the Event is returned when calling getEvents()
	 */
	public void testAddYearlyEvent() {
		Datebook datebook = new Datebook();

		// given an event
		Event event = new Event("EVENT 1", 1200);
		Date today = getDate(2014, Calendar.JANUARY, 1);

		// when the event is added today
		datebook.addYearlyEvent(event, 1);

		// then the event is returned for today
		List<Event> list = datebook.getEvents(today);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
		Assert.assertSame(event, list.get(0));


		// then the event is not returned tomorrow
		Date tomorrow = getDate(2014, Calendar.JANUARY, 2);
		list = datebook.getEvents(tomorrow);
		Assert.assertNotNull(list);
		Assert.assertTrue(list.isEmpty());

		// then the event is returned next year
		Date nextYear = getDate(2015, Calendar.JANUARY, 1);
		list = datebook.getEvents(nextYear);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
		Assert.assertSame(event, list.get(0));
	}

	@Test
	/**
	 * After calling addMonthlyEvent() verify that the Event is returned when calling getEvents()
	 */
	public void testAddMonthlyEvent() {
		Datebook datebook = new Datebook();

		// given an event
		Event event = new Event("EVENT 1", 1200);
		Date today = getDate(2014, Calendar.JANUARY, 1);

		// when the event is added today
		datebook.addMonthlyEvent(event, 1);

		// then the event is returned for today
		List<Event> list = datebook.getEvents(today);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
		Assert.assertSame(event, list.get(0));

		// then the event is not returned tomorrow
		Date tomorrow = getDate(2014, Calendar.JANUARY, 2);
		list = datebook.getEvents(tomorrow);
		Assert.assertNotNull(list);
		Assert.assertTrue(list.isEmpty());

		// then the event is returned next month
		Date nextMonth = getDate(2014, Calendar.FEBRUARY, 1);
		list = datebook.getEvents(nextMonth);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
		Assert.assertSame(event, list.get(0));
	}

	@Test
	/**
	 * After calling addWeeklyEvent() verify that the Event is returned when calling getEvents()
	 */
	public void testAddWeeklyEvent() {
		Datebook datebook = new Datebook();

		// given an event
		Event event = new Event("EVENT 1", 1200);
		Date today = getDate(2014, Calendar.JANUARY, 1);

		// when the event is added today
		datebook.addWeeklyEvent(event, Calendar.WEDNESDAY);

		// then the event is returned for today
		List<Event> list = datebook.getEvents(today);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
		Assert.assertSame(event, list.get(0));

		// then the event is not returned tomorrow
		Date tomorrow = getDate(2014, Calendar.JANUARY, 2);
		list = datebook.getEvents(tomorrow);
		Assert.assertNotNull(list);
		Assert.assertTrue(list.isEmpty());

		//then the event is returned next week
		Date nextWeek = getDate(2014, Calendar.JANUARY, 8);
		list = datebook.getEvents(nextWeek);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
		Assert.assertSame(event, list.get(0));
	}

	@Test
	/**
	 * After calling addDailyEvent() verify that the Event is returned when calling getEvents()
	 */
	public void testAddDailyEvent() {
		Datebook datebook = new Datebook();

		// given an event
		Event event = new Event("EVENT 1", 1200);
		Date today = getDate(2014, Calendar.JANUARY, 1);

		// when the event is added today
		datebook.addDailyEvent(event);

		// then the event is returned for today
		List<Event> list = datebook.getEvents(today);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
		Assert.assertSame(event, list.get(0));

		//then the event is returned a different day
		Date difDay = getDate(2014, Calendar.JANUARY, 7);
		list = datebook.getEvents(difDay);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
		Assert.assertSame(event, list.get(0));
	}

	@Test
	/**
	 * After adding multiple Events, verify that they are all returned from getEvents() in the correct order.
	 */
	public void testGetEventsReturnsSortedList() {
		Datebook datebook = new Datebook();

		// given an event
		Event event1 = new Event("EVENT 1", 1200);
		Event event2 = new Event("EVENT 2", 1300);
		Event event3 = new Event("EVENT 3", 1400);
		Event event4 = new Event("EVENT 4", 1500);
		Event event5 = new Event("EVENT 5", 1600);
		Date today = getDate(2014, Calendar.JANUARY, 1);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today); 
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);

		// when the event is added today
		datebook.addDailyEvent(event5);
		datebook.addWeeklyEvent(event4, dayOfWeek);
		datebook.addMonthlyEvent(event3, dayOfMonth);
		datebook.addYearlyEvent(event2, dayOfYear);
		datebook.addSingleEvent(event1, today);
		
		// then the event is returned for today
		List<Event> list = datebook.getEvents(today);
		Assert.assertNotNull(list);
		Assert.assertEquals(5, list.size());
		Assert.assertSame(event2, list.get(1));

		//then the event is returned a different day
		Date difDay = getDate(2014, Calendar.JANUARY, 2);
		list = datebook.getEvents(difDay);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
		Assert.assertSame(event5, list.get(0));
	}

}
