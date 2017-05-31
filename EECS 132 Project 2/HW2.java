/* Thomass Lerner
 * 
 * This class contains some methods that manipulate strings using loops and stringbuilders
 */

public class HW2 {
  
  /* Replaces the first k instances of something with something else */
  public static String replaceFirstK(String s, char replaceThis, char withThis, int k) {
    StringBuilder sb = new StringBuilder();
    int index = 0;
    /* Appends characters to a stringbuilder, making k amounts of replacements when necessary */
    while(index < s.length()) {
      /* Makes replacements where appropriate */
      if((s.charAt(index) == replaceThis) && (k > 0)) {
        sb.append(withThis);
        k--;
        index++;
      }
      /* Appends characters that don't need to be replaced */
      else {
        sb.append(s.charAt(index));
        index++;
      }
    }
    return sb.toString();
  }
  
  /* Lists all characters between two input characters alphabetically */
  public static String allChars(char start, char stop) {
    StringBuilder sb = new StringBuilder();
    int i = (int)start;
    /* Appends characters, starting from the start character and stopping with the stop character, to a stringbuilder */
    while(i <= (int)stop) {
      sb.append((char)i);
      i++;
    }
    return sb.toString();
  }
  
  /* Replaces characters from a string wtih underscores if those characters are not contained within another string */
  public static String showCharOfString(String a, String b) {
    StringBuilder sb = new StringBuilder();
    int indexA = 0;
    int indexB = 0;
    boolean foundChar = false;
    /* Cycles through b for each character in a, checking if that character is contained within b and appending it if it is
     * Otherwise, appends an underscore */
    while(indexA < a.length()) {
      if(indexB < b.length() && foundChar == false) {
        while(indexB < b.length() && foundChar == false) {
          if(a.charAt(indexA) == b.charAt(indexB)) {
            sb.append(a.charAt(indexA));
            foundChar = true;
          }
          else {
            indexB++;
          }
        }
      }
      else if(indexB >= b.length()) {
        sb.append('_');
        indexB = 0;
        indexA++;
      }
      else {
        foundChar = false;
        indexB = 0;
        indexA++;
      }
    }
    return sb.toString();
  }
  
  /* This method is somewhat inefficient, but I'm too scared to change anything... I will not be using while loops very often anymore */
  /* Initiates a game of hangman, filling in correct guesses and ending the game if the word is guessed or too many bad guesses are made */
  public static boolean hangman(String s, int limit) {
    StringBuilder sb = new StringBuilder();
    int badGuesses = 0;
    int index = 0;
    boolean foundChar = false;
    boolean done = false;
    /* Allows for user input
     * Compares input to s, filling letters guessed correctly or marking a bad guess */
    while(badGuesses < limit && done == false) {                                                                                                                                                                         // Please work
      System.out.println (showCharOfString(s, sb.toString()) + " missed guesses: " + badGuesses);
      String prompt = javax.swing.JOptionPane.showInputDialog("input guess:");
      /* Searches through the string for the input character and flips a boolean value if found */
      while(index < s.length() && foundChar == false) {
        if(prompt.length() != 0 && prompt.charAt(0) == s.charAt(index)) {
          sb.append(s.charAt(index));
          foundChar = true;
        }
        else {
          index++;
        }
      }
      if(foundChar == false) {
        badGuesses++;
      }
      /* Local variables thar are necessary to be inside the loop */
      foundChar = false;
      boolean flag = false;
      index = 0;
      /* Checks if puzzle is solved by searching for underscores using the showCharOfString method */
      while(index < s.length() && flag == false) {
        if(showCharOfString(s, sb.toString()).charAt(index) == '_') {
          flag = true;
        }
        index++;
      }
      /* We must convert these booleans in this way because they would not be able to work
       * if they were declared outside the loop
       * and because we cannot manually break loops, even with return statements */
      if(flag == false) {
        done = true;
      } else {
        done = false;
      }
      index = 0;
    }
    if(done == true) {
      return true;
    } 
    return false;
  }
  
  /* Searches through a string completely for a second string interspersed every k characters */
  public static boolean hiddenInString(String body, String searchFor, int k) {
    int index2 = 0;
    /* Negative and 0 inputs were jamming the program up, so at the cost of writing more lines the cases were handled separately */
    if(k > 0 && searchFor.length() != 0) {
      /* This loop searches for the first letter of the string we are searching for in the body (searched) string
       * If it finds that letter, it sees if the letter after the next k letters contains the next letter 
       * If it doesn't or no letter is found in the first place, it resets to searching for the first letter and continues on searching each letter of the body one by one */
      for(int index = 0; index < body.length() && index2 < searchFor.length(); index++) {
        if(body.charAt(index) == searchFor.charAt(index2)) {
          index2++;
          index += (k - 1);
        }
        else {
          index2 = 0;
        }
      }
      /* If we advanced through every letter in the string we are searching for, then we have found the string in the body successfully */
      if(index2 == searchFor.length()) {
        return true;
      }
      else {
        return false;
      }
    }
    else if(searchFor.length() != 0) {
      /* Same as above loop, but with inputs sanitized for negative numbers
       * Also handles 0 by returning false in all cases */
      for(int index = body.length() - 1; index > 0 && index2 < searchFor.length(); index--) {
        if(body.charAt(index) == searchFor.charAt(index2)) {
          index2++;
          index += (k + 1);
        }
        else {
          index2 = 0;
        }
      }
      if(index2 == searchFor.length()) {
        return true;
      }
      else {
        return false;
      }
    }
    else {
      return false;
    }
  }
  
  /* Capitalizes and word wtih a capital letter in it */
  public static String capitalizeWords(String s) {
    StringBuilder sb = new StringBuilder();
    String detectCaps = showCharOfString(s, "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    String detectLowers = showCharOfString(s, "abcdefghijklmnopqrstuvwxyz");
    int lettersInWord = 0;
    boolean capitalInWord = false;
    /* Runs through the string, running loops to append characters appropriately */
    for(int index = 0; index < s.length(); index++) {
      if(detectCaps.charAt(index) != '_') {
        capitalInWord = true;
      }
      if(s.charAt(index) == ' ' || index + 1 == s.length()) {
        if(capitalInWord == true && s.charAt(index) == ' ') {
          /* Capitalizes all letters of a word with any capital letters in it if that word is not at the end of the string */
          for(int index2 = index - lettersInWord; index2 < index; index2++) {
            if(detectLowers.charAt(index2) != '_') {
              sb.append((char)((int)s.charAt(index2) - 32));
            }
            else {
              sb.append(s.charAt(index2));
            }
          }
          sb.append(s.charAt(index));
        }
        else if(capitalInWord == true) {
          /* Capitalizes all letters of a word with any capital letters in it if that word is at the end of the string */
          for(int index2 = index - lettersInWord; index2 <= index; index2++) {
            if(detectLowers.charAt(index2) != '_') {
              sb.append((char)((int)s.charAt(index2) - 32));
            }
            else {
              sb.append(s.charAt(index2));
            }
          }
        }
        else {
          /* Appends characters without capitalization if there were no capitals in word */
          for(int index2 = index - lettersInWord; index2 <= index; index2++) {
            sb.append(s.charAt(index2));
          }
        }
        capitalInWord = false;
        lettersInWord = -1;
      }
      lettersInWord++;
    }
    return sb.toString();
  }
}