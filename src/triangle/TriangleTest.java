package friedman.triangle;

import org.junit.Assert;
import org.junit.Test;

public class TriangleTest {

	@Test
	public void testToString() {
		Triangle tri = new Triangle(3);
		
		String printed = tri.toString();
		Assert.assertEquals("  *\n"
						  + " * *\n"
						  + "*****", printed);
	}

}
