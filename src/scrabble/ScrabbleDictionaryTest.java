package friedman.scrabble;

import org.junit.Assert;
import org.junit.Test;

public class ScrabbleDictionaryTest{

	 @Test
	 public void testContainsTrue() {
		ScrabbleDictionary dictionary = new ScrabbleDictionary();
	  
		Assert.assertTrue(dictionary.contains("HELLO"));
		Assert.assertTrue(dictionary.contains("Hello"));
	}
	 @Test
	 public void testContainsFalse() {
		 ScrabbleDictionary dictionary = new ScrabbleDictionary();
		  
		 Assert.assertFalse(dictionary.contains("DFGLKDJFG"));
		 Assert.assertFalse(dictionary.contains("eirutierut"));
	 }
	 @Test
	 public void testContainsNull() {
		 ScrabbleDictionary dictionary = new ScrabbleDictionary();
  
		 Assert.assertFalse(dictionary.contains(null));
	 }
}
