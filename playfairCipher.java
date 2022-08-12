import java.util.ArrayList;
import java.util.Scanner;

class playfairCipher {

    public static ArrayList<String> createDigraphArray(String Text) {
        // Method to split the string to substrings of two characters each
        char[] letters = Text.toCharArray();
        for (char letter: letters) {
            // If j is present in the given text
            if (letter == 'j') {
                letter = 'i';
            }
        }
        String text = "";
        for (char letter: letters) {
            // To remove space from the text
            if (Character.isLetter(letter)) {
                text = text.concat(String.valueOf(letter));
            }
        }
        ArrayList<String> digraphs = new ArrayList<>();
        if (text.length() % 2 != 0) {
            // In case of odd number of characters in the string
            text = text.concat("x");
        }
        text = text.toLowerCase();          //Converting the String to lowercase
        for (int i = 0; i < text.length(); i = i+2) {
            digraphs.add(text.substring(i, i+2));
        }
        return digraphs;
    }

    public static char[][] matrixCreator(String keyword) {
        // Method to create the matrix
        String alphabets = "abcdefghiklmnopqrstuvwxyz";           // Stating the 25 required characters to make the matrix.
        char[] a = alphabets.toCharArray();
        char[] k = keyword.toCharArray();
        String letters = "";
        // Creating a string which has keyword letters then other english alphabet letters
        for (char letter: k) {
            letters = letters.concat(String.valueOf(letter));
        }
        for (char letter: a) {
            if (!letters.contains(String.valueOf(letter))) {            // Checking if the letter is already in letters String
                letters = letters.concat(String.valueOf(letter));
            } else {
                // Since that letter is already there in the String do not insert it in.
            }
        }
        char[] l = letters.toCharArray();
        char[][] matrix = new char[5][5];
        for (int i = 0; i < 5; i++) {
            // To create the matrix
            System.arraycopy(l, 5*i, matrix[i], 0, 5);
        }
        return matrix;
    }

    public static String encrypt(String plainText, char[][] matrix) {
        ArrayList<String> digraphs = createDigraphArray(plainText);
        String encryptedText = "";
        int[][] pos = new int[2][2];
        for (String digraph: digraphs) {
            // Getting the letter position
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    for (int k = 0; k < 2; k++) {
                        if (matrix[i][j] == digraph.charAt(k)) {
                            pos[k][0] = i;
                            pos[k][1] = j;
                        }
                    }
                }
            }
            // Shifting letters
            if (pos[0][0] == pos[1][0]) {
                // If letters are in same row
                pos[0][1] = (pos[0][1] + 1) % 5;
                pos[1][1] = (pos[1][1] + 1) % 5;
            } else if (pos[0][1] == pos[1][1]) {
                // If letters are in same column
                pos[0][0] = (pos[0][0] + 1) % 5;
                pos[1][0] = (pos[1][0] + 1) % 5;
            } else {
                // Letters are neither in the same row nor same column.
                int temp;
                temp = pos[0][1];
                pos[0][1] = pos[1][1];
                pos[1][1] = temp;
            }
            encryptedText = encryptedText.concat(String.valueOf(matrix[pos[0][0]][pos[0][1]]) + String.valueOf(matrix[pos[1][0]][pos[1][1]]));
        }
        return encryptedText;
    }

    public static String decrypt(String encryptedText, char[][] matrix) {
        ArrayList<String> digraphs = createDigraphArray(encryptedText);
        String plainText = "";
        int[][] pos = new int[2][2];
        for (String digraph: digraphs) {
            // Getting the letter position
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    for (int k = 0; k < 2; k++) {
                        if (matrix[i][j] == digraph.charAt(k)) {
                            pos[k][0] = i;
                            pos[k][1] = j;
                        }
                    }
                }
            }
            // Shifting letters
            if (pos[0][0] == pos[1][0]) {
                // If letters are in same row
                pos[0][1] = (pos[0][1] - 1) % 5;
                if (pos[0][1] < 0) {
                    // If pos = -1
                    pos[0][1] += 5;
                }
                pos[1][1] = (pos[1][1] - 1) % 5;
                if (pos[1][1] < 0) {
                    // If pos = -1
                    pos[1][1] += 5;
                }
            } else if (pos[0][1] == pos[1][1]) {
                // If letters are in same column
                pos[0][0] = (pos[0][0] - 1) % 5;
                if (pos[0][0] < 0) {
                    // If pos = -1
                    pos[0][0] += 5;
                }
                pos[1][0] = (pos[1][0] - 1) % 5;
                if (pos[1][0] < 0) {
                    // If pos = -1
                    pos[1][0] += 5;
                }
            } else {
                // Letters are neither in the same row nor same column.
                int temp;
                temp = pos[0][1];
                pos[0][1] = pos[1][1];
                pos[1][1] = temp;
            }
            plainText = plainText.concat(String.valueOf(matrix[pos[0][0]][pos[0][1]]) + String.valueOf(matrix[pos[1][0]][pos[1][1]]));
        }
        return plainText;
    }

    public static void main(String[] args) {
        // Driver
        Scanner sc = new Scanner(System.in);

        System.out.println("**** Playfair Cipher **** \n" +
                "**** Enter text to encrypt ****");
        String text = sc.nextLine();
        System.out.println("**** Enter the keyword ****");
        String keyword = sc.nextLine();
        char[][] matrix = matrixCreator(keyword);
        System.out.println("**** Encrypting Text ****");
        String encryptedText = encrypt(text, matrix);
        System.out.println("**** Encrypted text is - " + encryptedText + " ****");
        System.out.println("**** Decrypting the text ****");
        String decryptedText = decrypt(encryptedText, matrix);
        System.out.println("**** Decrypted text is - " + decryptedText + " ****");
    }
}
