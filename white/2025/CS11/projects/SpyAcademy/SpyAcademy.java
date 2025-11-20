import java.util.Scanner;

/**
 * Spy Academy: Code Breaker Training
 * AP Computer Science A - Arrays and Strings Lab
 * 
 * In this lab, you'll implement cipher methods used by spies to encode and decode messages.
 * The lab has two phases:
 *   Phase 1: Single word encoding/decoding
 *   Phase 2: Phrase encoding/decoding (with spaces and punctuation)
 * 
 * You'll play against a partner - one person encodes, the other tries to decode!
 */

public class SpyAcademy {
    
    // ==================== PHASE 1: SINGLE WORD METHODS ====================
    
    /**
     * Reverses a string
     * Example: "AGENT" returns "TNEGА"
     * 
     * @param word the string to reverse
     * @return the reversed string
     */
    public static String reverseString(String word) {
        // TODO: Implement this method
        // Hint: Build a new string by going through word backwards
        
        return ""; // Replace this
    }
    
    /**
     * Encodes a single word using Caesar cipher (shifts each letter)
     * Only shifts letters A-Z (uppercase). Assumes input is uppercase with no spaces.
     * Example: caesarShift("HELLO", 3) returns "KHOOR"
     * 
     * @param word the word to encode (uppercase letters only)
     * @param shift how many positions to shift each letter (1-25)
     * @return the encoded word
     */
    public static String caesarShift(String word, int shift) {
        // TODO: Implement this method
        // Hint: Loop through each character
        // Use charAt() to get each letter
        // Convert to numbers (A=0, B=1, ... Z=25), shift, then convert back
        // Remember: (char)('A' + number) converts a number back to a letter
        
        return ""; // Replace this
    }
    
    /**
     * Decodes a single word that was encoded with Caesar cipher
     * Example: caesarDecrypt("KHOOR", 3) returns "HELLO"
     * 
     * @param encoded the encoded word
     * @param shift the shift amount used to encode
     * @return the decoded word
     */
    public static String caesarDecrypt(String encoded, int shift) {
        // TODO: Implement this method
        // Hint: This is the opposite of caesarShift
        // If caesarShift adds the shift, what should caesarDecrypt do?
        
        return ""; // Replace this
    }
    
    /**
     * Counts the number of vowels (A, E, I, O, U) in a string
     * Example: countVowels("HELLO") returns 2
     * 
     * @param text the text to analyze
     * @return the number of vowels found
     */
    public static int countVowels(String text) {
        // TODO: Implement this method
        // Hint: Loop through the string and check if each character is a vowel
        // Use || (or) to check multiple conditions
        
        return 0; // Replace this
    }
    
    
    // ==================== PHASE 2: PHRASE METHODS ====================
    
    /**
     * Encodes a phrase using Caesar cipher
     * Shifts letters but KEEPS spaces, punctuation, and numbers unchanged
     * Example: caesarShiftPhrase("MEET AT NOON!", 3) returns "PHHW DW QRRQ!"
     * 
     * @param message the phrase to encode
     * @param shift how many positions to shift each letter
     * @return the encoded phrase
     */
    public static String caesarShiftPhrase(String message, int shift) {
        // TODO: Implement this method
        // Hint: Similar to caesarShift, but check if each character is a letter first
        // If it's a letter, shift it. If not, keep it the same.
        // Use Character.isLetter(ch) to check if a character is a letter
        
        return ""; // Replace this
    }
    
    /**
     * Decodes a phrase that was encoded with Caesar cipher
     * Example: caesarDecryptPhrase("PHHW DW QRRQ!", 3) returns "MEET AT NOON!"
     * 
     * @param encoded the encoded phrase
     * @param shift the shift amount used to encode
     * @return the decoded phrase
     */
    public static String caesarDecryptPhrase(String encoded, int shift) {
        // TODO: Implement this method
        // Hint: This is the opposite of caesarShiftPhrase
        
        return ""; // Replace this
    }
    
    
    // ==================== GAME HELPER METHODS ====================
    
    /**
     * Checks if a decoded message contains at least one keyword from the list
     * Useful for validating if a decode attempt looks correct
     * 
     * @param decoded the decoded message to check
     * @param keywords array of keywords to look for
     * @return true if decoded contains any keyword, false otherwise
     */
    public static boolean isValidDecode(String decoded, String[] keywords) {
        // TODO (OPTIONAL): Implement this method
        // Hint: Loop through the keywords array
        // Use decoded.contains(keyword) or decoded.indexOf(keyword) 
        
        return false; // Replace this
    }
    
    
    // ==================== GAME CODE (PROVIDED) ====================
    
    /**
     * Runs Phase 1: Single Word Game
     */
    public static void playPhase1(Scanner scanner) {
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║  PHASE 1: SINGLE WORD TRAINING       ║");
        System.out.println("╚═══════════════════════════════════════╝");
        System.out.println("Practice encoding single words (no spaces or punctuation)\n");
        
        // Player 1: Enter secret word
        System.out.println("PLAYER 1 (SPY):");
        System.out.print("Enter a secret word (uppercase letters only): ");
        String secretWord = scanner.nextLine().toUpperCase();
        
        System.out.print("Enter shift amount (1-25): ");
        int shift = scanner.nextInt();
        
        System.out.print("How many guesses should Player 2 get? (default 3): ");
        int maxGuesses = scanner.nextInt();
        if (maxGuesses <= 0) maxGuesses = 3;
        scanner.nextLine(); // consume newline
        
        // Encode the message
        String encoded = caesarShift(secretWord, shift);
        
        // Clear screen (simulate)
        for (int i = 0; i < 3; i++) System.out.println("\n\n\n\n\n\n\n\n");
        
        // Player 2: Try to decode
        System.out.println("\n\nPLAYER 2 (CODE BREAKER):");
        System.out.println("Intercepted message: " + encoded);
        System.out.println("You have " + maxGuesses + " guesses to crack the code!\n");
        
        boolean success = false;
        for (int attempt = 1; attempt <= maxGuesses; attempt++) {
            System.out.print("Guess #" + attempt + " - Enter shift amount (1-25): ");
            int guess = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            String decoded = caesarDecrypt(encoded, guess);
            System.out.println("Decoded message: " + decoded);
            
            if (decoded.equals(secretWord)) {
                System.out.println("🎉 SUCCESS! You cracked the code!");
                success = true;
                break;
            } else {
                if (attempt < maxGuesses) {
                    System.out.println("❌ Incorrect. Try again!\n");
                }
            }
        }
        
        if (!success) {
            System.out.println("\n💥 Mission failed! The correct shift was: " + shift);
            System.out.println("The secret word was: " + secretWord);
        }
    }
    
    /**
     * Runs Phase 2: Phrase Game
     */
    public static void playPhase2(Scanner scanner) {
        System.out.println("\n\n╔═══════════════════════════════════════╗");
        System.out.println("║  PHASE 2: FULL MISSION MESSAGES      ║");
        System.out.println("╚═══════════════════════════════════════╝");
        System.out.println("Now encode full phrases with spaces and punctuation!\n");
        
        // Player 1: Enter secret message
        System.out.println("PLAYER 1 (SPY):");
        System.out.print("Enter a secret message: ");
        String secretMessage = scanner.nextLine().toUpperCase();
        
        System.out.print("Enter shift amount (1-25): ");
        int shift = scanner.nextInt();
        
        System.out.print("How many guesses should Player 2 get? (default 3): ");
        int maxGuesses = scanner.nextInt();
        if (maxGuesses <= 0) maxGuesses = 3;
        scanner.nextLine(); // consume newline
        
        // Encode the message
        String encoded = caesarShiftPhrase(secretMessage, shift);
        
        // Clear screen (simulate)
        for (int i = 0; i < 3; i++) System.out.println("\n\n\n\n\n\n\n\n");
        
        // Player 2: Try to decode
        System.out.println("\n\nPLAYER 2 (CODE BREAKER):");
        System.out.println("Intercepted message: " + encoded);
        System.out.println("You have " + maxGuesses + " guesses to crack the code!\n");
        
        boolean success = false;
        for (int attempt = 1; attempt <= maxGuesses; attempt++) {
            System.out.print("Guess #" + attempt + " - Enter shift amount (1-25): ");
            int guess = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            String decoded = caesarDecryptPhrase(encoded, guess);
            System.out.println("Decoded message: " + decoded);
            
            if (decoded.equals(secretMessage)) {
                System.out.println("🎉 SUCCESS! You cracked the code!");
                success = true;
                break;
            } else {
                if (attempt < maxGuesses) {
                    System.out.println("❌ Incorrect. Try again!\n");
                }
            }
        }
        
        if (!success) {
            System.out.println("\n💥 Mission failed! The correct shift was: " + shift);
            System.out.println("The secret message was: " + secretMessage);
        }
    }
    
    /**
     * Main method - runs the game
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║   SPY ACADEMY: CODE BREAKER TRAINING  ║");
        System.out.println("╚═══════════════════════════════════════╝");
        System.out.println("\nWelcome, Agent! You'll learn to encode and");
        System.out.println("decode secret messages using Caesar cipher.\n");
        
        // Phase 1
        System.out.print("Ready for Phase 1? (yes/no): ");
        String ready = scanner.nextLine();
        if (ready.toLowerCase().startsWith("y")) {
            playPhase1(scanner);
        }
        
        // Phase 2
        System.out.print("\nReady for Phase 2? (yes/no): ");
        ready = scanner.nextLine();
        if (ready.toLowerCase().startsWith("y")) {
            playPhase2(scanner);
        }
        
        System.out.println("\n\nThank you for training at Spy Academy! 🕵️");
        scanner.close();
    }
}
