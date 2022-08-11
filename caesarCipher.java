import java.util.Scanner;

public class caesarCipher {

    public static final String alphabets = "abcdefghijklmnopqrstuvwxyz";        //Stating all the english alphabets

    public static String encrypt(int k, String plainText) {
        plainText = plainText.toLowerCase();
        char[] textArray = plainText.toCharArray();
        for (int i = 0; i < textArray.length; i++) {
            if (Character.isLetter(textArray[i])) {
                // Only runs if there is an actual alphabet
                int C = (alphabets.indexOf(textArray[i]) + k) % 26;
                textArray[i] = alphabets.charAt(C);
            } else {
                //Ignore these characters
            }
        }
        return String.copyValueOf(textArray);       //returns encrypted text
    }

    public static String decrypt(int k, String encryptedText) {
        char[] textArray = encryptedText.toCharArray();
        for (int i = 0; i < textArray.length; i++) {
            if (Character.isLetter(textArray[i])) {
                //   Runs if the character is an alphabet
                int p = (alphabets.indexOf(textArray[i]) - k) % 26;
                if (p < 0) {
                    // Specific case where p goes less than zero
                    p = alphabets.length() + p;
                }
                textArray[i] = alphabets.charAt(p);
            } else {
                //   Ignore these characters
            }
        }
        return String.copyValueOf(textArray);       //returns decrypted text
    }


    public static void main(String[] args) {
        //Driver
        Scanner sc = new Scanner(System.in);

        System.out.println("**** Caesar Cipher **** \n" +
                "**** Enter the text to encrypt ****");
        String text = sc.nextLine();
        System.out.println("**** Enter Shift ****");
        int shift = sc.nextInt();
        System.out.println("**** Encrypting Text ****");
        String encryptedText = encrypt(shift, text);
        System.out.println("**** Encrypted text is - " + encryptedText + " ****");
        System.out.println("**** Decrypting the text ****");
        System.out.println("**** Decrypted text is - " + decrypt(shift, encryptedText) + " *****");
    }
}