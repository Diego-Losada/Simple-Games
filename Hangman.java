import java.util.ArrayList;

public class Hangman {
    /**
     * The result of a game.
     * LOSE: 6 or more incorrect guesses
     * WIN: guess all letters in target word
     * NONE: all other cases
     */

    public static void main(String[] args) {
        Hangman hangman = new Hangman();
        hangman.setPhrase("Mission' IMPOSSIBLE");
        hangman.guess('S');
        System.out.println(hangman.toString());
    }
    public enum Result { LOSE, WIN, NONE };
    
    private ArrayList<Character> guess;
    private ArrayList<Character> letters;
    private String phrase;
    private int misses;
    //======================================================
    // You will need to add private instance variables as
    // needed to help you solve this problem. As long as
    // anything you add is private, you may add whatever you
    // prefer.
    //======================================================

    
    
    // Question 1a
    /**
     * This constructor initializes the puzzle phrase to the empty
     * string, along with any other variables you may need.
     */
    public Hangman() {
        phrase = "";
        guess = new ArrayList<Character>();
        letters = new ArrayList<Character>();
        misses = 0;
    }
    
    
    // Question 1b
    /**
     * This sets the current puzzle phrase. It also reinitializes
     * any other variables you may need (since using a new phrase 
     * would likely make no sense for their current values).
     * A phrase must contain only alphabetic (a-z, A-Z) characters
     * (all of which should be converted to uppercase before use),
     * along with spaces and apostrophes. So, for example, the
     * phrase "Adam's rIB" would be acceptable and would be set
     * as "ADAM'S RIB", but "Mission: IMPOSSIBLE" would not (and
     * the current puzzle phrase would not be changed).
     */
    public void setPhrase(String phrase) {
        String sentence = "";
        String saved_string = this.phrase;
        Character character = ' ';
        String ap = "'";
        for(int i=0; i< phrase.length(); i++) {
            character = phrase.charAt(i);
            if (Character.isLetter(character)) {
                if(Character.isUpperCase(character)){
                    sentence = sentence + character;
                } else{
                    sentence = sentence + Character.toUpperCase(character);
                }
            }
            this.phrase = sentence;
            if (!Character.isLetter(character)) {
                if (character.equals(' ')) {
                    sentence = sentence + character;
                } else if (character.equals(ap.charAt(0))) {
                    sentence = sentence + character;
                } else {
                    this.phrase = saved_string;
                    break;
                }
            }
        }
        
        System.out.println(this.phrase);
    }
    
    
    // Question 2
    /**
     * This returns true if the character is available
     * to be guessed (meaning that it has not already
     * been guessed previously). Otherwise, it returns false.
     * Note that only alphabetic (a-z, A-Z) characters are
     * available, and any such character must be converted
     * to uppercase before it is used.
     */
    public boolean available(char c) {
        if (guess.contains(c)) {
            return true;
        }
        return false;
    }
    
    
    // Question 3
    /**
     * The same requirements about character sets and case apply
     * here as in Question 2.
     * 
     * This returns the number of times (0 or more) that
     * the character appears in the word, if it is legal and has
     * not been guessed previously. Also, it marks that character
     * as guessed. Finally, any new, correct guess should update
     * the puzzle while any new, incorrect guess should update the
     * number of misses.
     * 
     * If the character is illegal or already guessed, this makes
     * no changes and returns -1.
     */
    public int guess(char c) {
        if (guess.contains(c) || !Character.isLetter(c)) {
            return -1;
        }
        int count = 0;
        for (int i=0; i< phrase.length(); i++) {
            if (phrase.charAt(i) == c) {
                count = count + 1;
            }
        }
        if (count == 0) {
            misses = misses + 1;
        } else if (count > 0) {
            guess.add(c);
        }
        return count;
    }
    
    
    // Question 4
    /**
     * This returns the current result of the game 
     * (WIN, LOSE, NONE). It should return LOSE if there have
     * already been 6 or more incorrect guesses. It should
     * return WIN if all of the letters in the puzzle word 
     * have been guessed. And it should return NONE otherwise.
     */
    public Result getResult() {
        if (misses >= 6) {
            return Result.LOSE;
        }
        return Result.NONE;
    }
    
    
    // Question 5
    /**
     * This returns a String representing the current state
     * of the puzzle, the letters that have been used, and
     * the number of misses. The string should be of the 
     * following form:
     * 
     * PUZZLE  [USED LETTERS]  [MISSES]
     * 
     * The PUZZLE should be the current state of the puzzle
     * phrase, where hyphens are used to represent letters that
     * have not yet been guessed. (Any spaces or apostrophes in
     * the puzzle are kept in their places.) The USED LETTERS should
     * be a string of all letters that have been guessed, in the
     * order that they were guessed (earliest to latest). The
     * MISSES is a non-negative integer representing the misses.
     * All characters should be in uppercase.
     * 
     * For instance, if the puzzle were the word "EXUBERANCE",
     * and the guesses were M, B, E, T, P, and N, in that order,
     * then this method would return
     * 
     * "E--BE--N-E  [MBETPN]  [3]"
     * 
     * If the puzzle were the phrase "ADAM'S RIB" at the beginning
     * (i.e., no guesses have been made), then this method would
     * return
     * 
     * "----'- ---"  []  [0]
     * 
     */
    public String toString() {
        String puzzle = phrase;
        Boolean change = true;
        for(int i =0; i<puzzle.length(); i++) {
            if(Character.isLetter(puzzle.charAt(i))) {
            }
            for(int j=0; j<guess.size(); j++) {
                if (phrase.charAt(i) == guess.get(j)) {
                    change = false;
                }
            }
            if (change) {
                if (Character.isLetter(puzzle.charAt(i))) {
                    puzzle = puzzle.replace(puzzle.charAt(i), '-');
                }
            }
            change = true;
        }
        System.out.println(guess.toString());
        String c = "[";
      return c+ puzzle + "]"+ "  "+ guess.toString() + "  "+  "["+ Integer.toString(misses)+ "]";
    }
    
}

