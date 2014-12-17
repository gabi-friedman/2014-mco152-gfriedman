package friedman.morsecode;

import org.junit.Assert;
import org.junit.Test;

public class MorseCodeTest {

	@Test
	public void testToMorseCode(){
		MorseCode code = new MorseCode();
		String encoded = code.toMorseCode("SOS");
		Assert.assertEquals("... --- ...", encoded);
	}
	
	@Test
	public void testToPlainText(){
		MorseCode code = new MorseCode();
		String encoded = code.toPlainText("... --- ...");
		Assert.assertEquals("SOS", encoded);
	}
}
