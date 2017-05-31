import org.junit.*;
import static org.junit.Assert.*;

/*
 * Test class HW2
 */
public class HW2Tester {
  
  /*
   * Tests the replaceFirstK method
   */
  @Test
  public void testReplaceFirstK() {
    assertEquals("Replacing when limit is zero", "abcd", HW2.replaceFirstK("abcd", 'a', 'I', 0)); // Test zero
    assertEquals("Not replacing first char", "Abcd", HW2.replaceFirstK("abcd", 'a', 'A', 1)); //Test one, first
    assertEquals("Not replacing char", "aBcd", HW2.replaceFirstK("abcd", 'b', 'B', 1)); // Test one, middle
    assertEquals("Not replacing last char", "abcD", HW2.replaceFirstK("abcd", 'd', 'D', 1)); // Test one, last
    assertEquals("Not replacing multiple chars", "aBcdB", HW2.replaceFirstK("abcdb", 'b', 'B', 2)); // Test many
    assertEquals("Not limiting replacement appropriately", "aBcdBb", HW2.replaceFirstK("abcdbb", 'b', 'B', 2)); // Test many, limited
    assertEquals("Breaking when limit is greater than number of characters to replace", "aBcdBB", HW2.replaceFirstK("abcdbb", 'b', 'B', 4)); // Test many, underlimited
  }
  
  /*
   * Tests the allChars method
   */
  @Test
  public void testAllChars() {
    assertEquals("Listing inputs or chars between inputs when there are no chars between inputs", "", HW2.allChars('b', 'a')); // Test zero
    assertEquals("Listing chars between identical inputs", "d", HW2.allChars('d', 'd')); // Test one
    assertEquals("Not listing chars between two lowercase inputs", "defghijklm", HW2.allChars('d', 'm')); // Test many
  }
  
  /*
   * Tests the showCharOfString method
   */
  @Test
  public void testShowCharOfString() {
    assertEquals("Not replacing unspecified chars with '_'", "______________", HW2.showCharOfString("Missouri River", "")); // Test zero
    assertEquals("Not keeping specified chars while replacing unspecified ones", "M_____________", HW2.showCharOfString("Missouri River", "M")); // Test one, first
    assertEquals("Not keeping specified chars while replacing unspecified ones", "_________R____", HW2.showCharOfString("Missouri River", "R")); // Test one, middle
    assertEquals("Not keeping specified chars while replacing unspecified ones", "______________*", HW2.showCharOfString("Missouri River*", "*")); // Test one, last
    assertEquals("Not keeping specified chars", "Missouri River", HW2.showCharOfString("Missouri River", "Misour Rve")); // Test many
    assertEquals("Appending chars when first input string is empty", "", HW2.showCharOfString("", "i")); // Test zero body string
  }
  
  /*
   * Tests the hiddenInString method
   */
  @Test
  public void testHiddenInString() {
    assertFalse("Returning true when k is zero", HW2.hiddenInString("abracadabra", "cad", 0)); // Test zero
    assertTrue("Not finding search string at beginning of body string", HW2.hiddenInString("abracadabra", "a", 1)); // Test one, first, one search string
    assertTrue("Not finding search string in body string", HW2.hiddenInString("abracadabra", "cad", 1)); // Test one, middle, many search string
    assertTrue("Not finding search string at end of body string", HW2.hiddenInString("abracadabra", "a", 1)); // Test one, last
    assertTrue("Not finding strings spaced by k", HW2.hiddenInString("abracadabra", "baaa", 2)); // Test many
    assertTrue("Not correctly searching backwards when k is negative", HW2.hiddenInString("abracadabra", "bar", -3)); // Test negative
    assertFalse("Returning true when search string is empty", HW2.hiddenInString("abracadabra", "", 2)); // Test zero search string
    assertFalse("Finding search string in empty body string", HW2.hiddenInString("", "cad", 1)); // Test zero body string
    assertFalse("Searching for strings without spacing them by k", HW2.hiddenInString("abracadabra", "cad", 2)); // Search string not in body spaced by k
  }
  
  /*
   * Tests the capitalizeWords method
   */
  @Test
  public void testCapitalizeWords() {
    assertEquals("Appending chars when input string is empty", "", HW2.capitalizeWords("")); // Test zero
    assertEquals("Not keeping work without capitals uncapitalized", "and", HW2.capitalizeWords("and")); // Test one
    assertEquals("Not capitalizing words with capital chars", "AND", HW2.capitalizeWords("And")); // Test one, capital
    assertEquals("Not keeping muli-word uncapitalized string uncapitalized", "tom and i", HW2.capitalizeWords("tom and i")); // Test many
    assertEquals("Not capitalizing first word appropriately", "TOM and i", HW2.capitalizeWords("Tom and i")); // Test many, first
    assertEquals("Not capitalizing middle word appropriately", "tom AND i", HW2.capitalizeWords("tom aNd i")); // Test many, middle
    assertEquals("Not capitalizing last word appropriately", "tom and I", HW2.capitalizeWords("tom and I")); // Test many, last
    assertEquals("Doesn't address all cases at once", "GUESS what??  THERE are TWENTY-SIX letters in the ENGLISH ALPHABET!", HW2.capitalizeWords("Guess what??  There are twenty-sIx letters in the English alphABEt!")); // Test many, first, middle, last
  }
}
