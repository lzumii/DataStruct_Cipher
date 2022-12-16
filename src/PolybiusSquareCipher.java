import java.util.ArrayList;
import java.util.Scanner;

public class PolybiusSquareCipher {

    // TABLE 5X5 TO BE USED A REFERENCE FOR ENCRYPTION
    public static char[][] table =  {
            {'a','b','c','d','e'},
            {'f','g','h','i','k'},
            {'l','m','n','o','p'},
            {'q','r','s','t','u'},
            {'v','w','x','y','z'}
    };

    // ENCRYPTION PART
    public static String encryption(String textEntered){

                        // REPLACES all "j" into "i" for us to encrypt the text properly.
        String text = textEntered.toLowerCase().replaceAll("j", "i");

        String textfixed=""; // TO CLEAN THE TEXT INPUTTED FIRST BEFORE ENCRYPTION SUCH AS REMOVING NUMBERS AND SYMBOLS.
        String encrypted=""; // ENCRYPTED TEXT

        // REMOVING NUMBERS AND SYMBOLS
        for (int g=0; g<text.length();g++){
            char character=text.charAt(g);
            if((character>=65 && character<=90) || (character>=97 && character<=122) || character==32)textfixed+=character;
        }

        // TEXT PRINTING FOR TESTING IT OUT
        //System.out.println(textfixed);

        // LOOP THAT COMPARES ALL THE CHARACTERS IN THE TABLE TO THE CHARACTERS OF THE TEXT INPUTTED.
        // IF IT MATCHES, IT RETURNS THE ROW AND COLUMN FROM THE TABLE ABOVE.
        for(int i=0; i<textfixed.length(); i++){
            for (int k = 0; k < 5; k++) {
                for (int l = 0; l < 5; l++) {
                    // IF IT MATCHES, IT ADDS THE ROWS AND COLUMN OF THE CHARACTER IN THE TABLE THAT MATCHES THE CHARACTER FROM THE TEXT INPUTTED TO THE ENCRYPTED TEXT
                    if(textfixed.charAt(i)==table[k][l]) encrypted += String.valueOf(k + 1) + String.valueOf(l + 1);
                }
            }

            // IF THE CHARACTER IS SPACE, NO CHANGES.
            if(textfixed.charAt(i)==32) encrypted+=" ";
        }

        // RETURNS THE ENCRYPTED TEXT.
        return encrypted;
    }

    // DECRYPTION PART
    public static String decryption(String textEntered){

        String textFixed=""; // JUST LIKE IN ENCRYPTION, IT FIXES THE TEXT BEFORE DECRYPTING
        String decrypted=""; // DECRYPTED TEXT
        ArrayList<Integer> spaces = new ArrayList<Integer>();
        // ARRAY LIST THAT STORES THE INDEX OF THE SPACES IN THE TEXT INPUTTED.

        // ACCEPTS ONLY 1-5 NUMBERS AND SPACES
        for(int g=0; g<textEntered.length();g++){
            char character=textEntered.charAt(g);
            if((character>=49 && character<=53)|| character==32) textFixed+=character;
        }
        //System.out.println(textFixed);

        // LOOPING TO IDENTIFY WHICH INDEXES ARE SPACES
        for(int h=0; h< textFixed.length();h++){
            if(textFixed.charAt(h)==32) spaces.add(h);
        }

        int wordCount=1; // AS A GUIDE, IT SERVES AS A WAY TO KNOW IF THERE IS PAIR
        int row=0, column=0; // ROWS AND COLUMN THAT U GET FROM THE TEXT INPUTTED
        for(int i=0; i<textFixed.length(); i++){

            // CONDITION THAT IF THE WORD COUNT IS ODD AND THE CURRENT SELECTED CHARACTER IS NOT A SPACE.
            if(wordCount%2==0 && textFixed.charAt(i)!=32){
                row=Character.getNumericValue(textFixed.charAt((i-1)))-1;
                column=Character.getNumericValue(textFixed.charAt((i)))-1;

                decrypted+=table[row][column];
            }
            wordCount++;

            // IF THE CURRENT SELECTED CHARACTER IS
            if(textFixed.charAt(i)==32){
                decrypted+=" ";
                wordCount=1;
            }

        }

        // RETURNS THE DECRYPTED TEXT
        return decrypted;
    }

    public static void main(String[] args) {

        // SCANNER VARIABLE
        Scanner in = new Scanner(System.in);

        // LOOP UNTIL USER INPUTTED [C] TO EXIT.
        boolean loop=true;
        while(true) {
            System.out.print("""
            MODES
            [A] ENCRYPT
            [B] DECRYPT
            [C] EXIT
            Enter your desired input:ㅤ""");
            String mode = in.nextLine().stripLeading();

            String output="", textEntered ="";
            // SWITCH CASE FOR THE OPTIONS ABOVE.
            switch(mode){
                // ENCRYPTON CASE
                case "A": case "a":
                    System.out.print("\nENCRYPT TEXT\nEnter a plain text: ");
                    textEntered = in.nextLine();
                    System.out.print("Cipher text: ");
                    output=encryption(textEntered);
                    System.out.println(output+"\n");
                    break;
                // DECRYPTION CASE
                case "B": case "b":
                    System.out.print("\nDECRYPT TEXT\nEnter a cipher text: ");
                    textEntered = in.nextLine();
                    System.out.print("Decipher text: ");
                    output=decryption(textEntered);
                    System.out.println(output+"\n");
                    break;
                // EXIT LOOP
                case "C": case "c":
                    loop=false;
                    break;
                // INVALID INPUT
                default:
                    System.out.println("Enter a valid input!\n");
                    break;

            }

        }
    }
}