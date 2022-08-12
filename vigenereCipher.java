import java.util.ArrayList;
import java.util.Scanner;

public class vigenereCipher {

    public static final String alphabets = "abcdefghijklmnopqrstuvwxyz";            // Stating all the english alphabets

    public static String encrypt(String plainText, String keyword) {
        plainText = plainText.toLowerCase();            // Converting the plain text to low caps
        keyword = keyword.toLowerCase();
        char[] keywordArray = keyword.toCharArray();
        // Creating an array that will contain the value of all shifts
        ArrayList<Integer> shifts = new ArrayList<>();
        for (char letter: keywordArray) {
            shifts.add(alphabets.indexOf(letter));
        }
        char[] plainTextArray = plainText.toCharArray();
        // Shifting the letters
        for (int i = 0; i < plainTextArray.length; i++) {
            if (Character.isLetter(plainTextArray[i])) {
                plainTextArray[i] = alphabets.charAt((alphabets.indexOf(plainTextArray[i]) + shifts.get(i % shifts.size())) % alphabets.length());
            }
        }
        return String.valueOf(plainTextArray);
    }

    public static String decrypt(String encryptedText, String keyword) {
        encryptedText = encryptedText.toLowerCase();            // Converting the plain text to low caps
        keyword = keyword.toLowerCase();
        char[] keywordArray = keyword.toCharArray();
        // Creating an array that will contain the value of all shifts
        ArrayList<Integer> shifts = new ArrayList<>();
        for (char letter: keywordArray) {
            shifts.add(alphabets.indexOf(letter));
        }
        char[] encryptedTextArray = encryptedText.toCharArray();
        // Shifting the letters
        for (int i = 0; i < encryptedTextArray.length; i++) {
            if (Character.isLetter(encryptedTextArray[i])) {
                int requiredIndex = (alphabets.indexOf(encryptedTextArray[i]) - shifts.get(i % shifts.size())) % alphabets.length();
                if (requiredIndex < 0) {
                    requiredIndex = requiredIndex + alphabets.length();
                }
                encryptedTextArray[i] = alphabets.charAt(requiredIndex);
            }
        }
        return String.valueOf(encryptedTextArray);
    }

    public static void main(String[] args) {
        //Driver
        Scanner sc = new Scanner(System.in);

        System.out.println("**** Vigenere Cipher **** \n" +
                "**** Enter the text to encrypt ****");
        String text = sc.nextLine();
        System.out.println("**** Enter Keyword ****");
        String keyword = sc.nextLine();
        System.out.println("**** Encrypting Text ****");
        String encryptedText = encrypt(text, keyword);
        System.out.println("**** Encrypted text is - " + encryptedText + " ****");
        System.out.println("**** Decrypting the text ****");
        System.out.println("**** Decrypted text is - " + decrypt(encryptedText, keyword) + " *****");
    }
}
