import java.util.Scanner;

public class CaesarCipher {
    public static Scanner in = new Scanner(System.in);
    public static String textEntered=""; // USER INPUT
    public static int limit=0; // LIMIT is the minimum scope in the ASCII table for a specific group of characters. A-Z the limit is A and in ASCII table it is equivalent to 65.
    public static char select; // SELECT is the character selected in the text inputted.

    // METHOD FOR ENCRYPTION
    public static String encyrpt(int interval){

        System.out.print("\nENCRYPT TEXT\nEnter a plain text: ");
        // TEXT ENTERED BY THE USER
        textEntered = in.nextLine();

        // ENCRYPTED TEXT
        String output = "";

        // LOOPING TO CHECK EVERY CHARACTER IN STRING
        for (int c = 0; c < textEntered.length(); c++) {

            // SELECTED character from the text inputted.
            select = textEntered.charAt(c);
            // CONDITION FOR ONLY A-Z AND a-z characters only
            if((select >=65 && select <=90)||(select >=97 && select <=122)) {
                // IF CONDITION TO GET THE LIMIT OF THE CURRENT CHARACTER SELECTED
                // LIMIT is the minimum scope in the ASCII table.
                // For example the value of "A" in ASCII table is 65 so therefore it is the limit.

                if(select >=65 && select <=90) limit=65;
                    else if(select >=97 && select <=122) limit=97;

                // ADDING THE ENCRYPTED CHARACTER TO THE OUTPUT USING INCREMENT.
                output += Character.toString(((select-limit + interval) % 26) + limit);
            }else {
                output += Character.toString(select);
            }
        }
        System.out.print("Cipher text: ");
        // RETURN THE ENCRYPTED TEXT
        return output;

    }

    // METHOD FOR DECRYPTION
    public static String decrypt(int interval){

        System.out.print("\nDECRYPT TEXT\nEnter a cipher text: ");
        // TEXT ENTERED BY THE USER
        textEntered = in.nextLine();

        // DECRYPTED TEXT
        String output = "";

        // LOOPING TO CHECK EVERY CHARACTER IN STRING
        for (int c = 0; c < textEntered.length(); c++) {
            // SELECTED character from the text inputted.
            select = textEntered.charAt(c);
            // CONDITION FOR ONLY A-Z AND a-z characters only
            if((select >=65 && select <=90)||(select >=97 && select <=122)) {
                // SAME AS BEFORE, ABOUT THE LIMIT AND STUFF
                if(select >=65 && select <=90) limit=65;
                 else if(select >=97 && select <=122) limit=97;

                 // ADDING THE DECRYPTED CHARACTER TO THE OUTPUT USING INCREMENT.
                output += Character.toString(((26+(select-limit - interval)) % 26) + limit);
            }else {
                output += Character.toString(select);
            }
        }
        System.out.print("Decipher text: ");
        // RETURN THE DECRYPTED TEXT
        return output;
    }

    public static void main(String[] args) {

        // INTERVAL IS THE NUMBER OF LETTERS THAT NEEDED TO SHIFT TO THE RIGHT
        int interval=0;

        // BOOLEAN VARIABLE FOR LOOPING UNTIL IT CHOOSES [C] EXIT
        while(true) {
            try {
                System.out.print("The numbers of letters to shift to the right: ");
                interval = Integer.parseInt(in.nextLine().stripLeading());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input.\n");
            }
        }
        boolean loop=true;
        while(loop) {
            System.out.print("""
            
            MODES
            [A] ENCRYPT
            [B] DECRYPT
            [C] EXIT
            Enter your desired input:ㅤ""");
            // VARIABLE FOR THE CHOOSEN OPTION.
            String mode = in.nextLine().stripLeading();

            // SWITCH CONDITION FOR THE OPTION ABOVE.
            switch(mode){
                case "A": case "a":
                    System.out.print(encyrpt(interval));
                    break;
                case "B": case "b":
                    System.out.print(decrypt(interval));
                    break;
                case "C": case "c":
                    loop=false;
                    break;
                default:
                    System.out.println("Enter a valid input!\n");
                    break;
            }

        }

    }

}
