import java.util.ArrayList;
import java.util.Scanner;

// HSHAHAHAHA WARNING SANITY--
// SORRY IF DI KO MAEXPLAIN NANG MAAYOS ;-;

public class PlayFairCipher {

    // TABLE 5X5 for encryption
    public static int[][] table =  new int[5][5];
    // ARRAY LIST FOR PAIRING
    public static ArrayList<String> groupedText = new ArrayList<String>();
    // ENCRYPTED BY PAIR
    public static ArrayList<String> encryptedText = new ArrayList<String>();

    /*
       OPTION IS FOR THE TWO DIFFERENT TYPES OF PLAY FAIR CIPHER
       - ONE IS A TYPE WHERE IN YOU REMOVE/OMIT Q
       - SECOND IS A TYPE WHERE IN YOU REPLACE ALL THE LETTER J WITH I
     */
    public static String option;

    // FOR REMOVING DUPLICATES OF THE KEYWORD
    public static String removeDups(String keyword){
        keyword=keyword.toLowerCase();
        String keywordfixed="", output="";

        // REMOVE NUMBERS, SYMBOLS, AND SPACES
        for (int g=0; g<keyword.length();g++){
            char character=keyword.charAt(g);
            if((character>=65 && character<=90)||(character>=97 && character<=122))keywordfixed+=character;
        }

        // CHECKING IF THEIR ARE DUPLICATES IN THE KEYWORD ENTERED
        for(int i=0; i<keywordfixed.length();i++){

            boolean condition=false;
            for(int j=0; j<output.length();j++){
                if(keywordfixed.charAt(i)==output.charAt(j)) condition=true;
            }

            if(!condition){
                output+=keywordfixed.charAt(i);
            }

        }
        return output;
    }

    /*
     CREATING AND ARRANGING THE 5X5 TABLE FOR ENCRYPTION
     HAHA MENTAL BOOM
     */
    public static void tableCypher(String keyword){
        int column=0, row=0;

        // LETTING THE KEYWORD BE PUT IN THE ARRAY TABLE FIRST BEFORE THE OTHER LETTERS.
        int i=0;
       while(i< keyword.length()){
           column=i/5;
           row=i%5;
           table[column][row]=keyword.charAt(i);
           i++;
     }

        i--;
       // NUMBER SERVES AS THE COUNT OF HOW MANY REMAINING LETTERS ARE NEEDED TO BE PUT IN THE ARRAY TABLE
       int number=0;
       // LOOPING FROM A TO Z TO CHECK IF WHAT LETTERS ARE NOT IN THE ARRAY TABLE
       for(int j=1; j<=26; j++){

           char alpha= (char)(96+j);

                 // ADD Q TO THE TABLE IF THEY CHOOSE TO REPLACE J WITH I OPTION

                 boolean condition=true;
                 // LET ALL THE LETTERS EXCEPT THE LETTERS FROM THE KEYWORD TO BE PUT IN THE TABLE ARRAY.
                 for (int k=0; k< keyword.length(); k++){
                     if(alpha==keyword.charAt(k)){
                         condition=false;
                     }
                 }

                // CONDITION FOR THE OPTION MODIFICATION
                if (alpha==113 && (option.equals("a") || option.equals("A"))) condition=false;
                    else if(alpha==106 && (option.equals("b") || option.equals("B"))) condition=false;

                 // IF ALL THE RESTRICTION HAS BEEN PASSED, THEN CONTINUE PUTTING THE LETTERS INSIDE THE TABLE ARRAY
                 if(condition){
                     number++;
                     column=(i+number)/5;
                     row=(i+number)%5;
                     table[column][row]=alpha;
                 }

       }

        // FOR PRINTING 5X5 TABLE
        // JUST FOR CHECKING
        /*
        for(int z = 0; z < 5; z++) {
            for(int j = 0; j < 5; j++) {
                char a = (char) table[z][j];
                System.out.print(a+" ");
            }
            System.out.println();
        }
         */

    }

    // ENCRYPTION PROCESS
    public static String encryption(String textEntered) {
        groupedText.clear();
        encryptedText.clear();
        String text0 = textEntered.replaceAll("\\s", "");
        String encryptText="";

            // IF ONLY OMIT Q OPTION IS SELECTED
        if (option.equals("a") || option.equals("A")) text0 = text0.replaceAll("q", "");
            // IF REPLACE J WITH I OPTION IS SELECTED
        else if(option.equals("b") || option.equals("B")) text0 = text0.replaceAll("j", "i");

        String text = "";
        for (int g=0; g<text0.length();g++){
            char character=text0.charAt(g);
            if((character>=65 && character<=90) || (character>=97 && character<=122) || character==32)text+=character;
        }

        String text1 = "";

        // CHECKING REPETITION AND REARRANGING IT
        while (true) {
            int counter = 0;
            boolean norep = true;
            text1 = "";

            for (int a = 0; a < text.length(); a++) {
                if (a / 2 != text.length() / 2) {
                    if (a % 2 == 0 && text.charAt(a) == text.charAt(a + 1)) {
                        text1 += text.charAt(a);

                        counter = a + 1;
                        while (counter < text.length()) {
                            if (counter == a + 1) text1 += 'x';
                            else text1 += text.charAt(counter - 1);

                            counter++;
                        }

                        a = text.length();
                        norep = false;
                    } else text1 += text.charAt(a);

                }
            }
            if (text.length() % 2 != 0 && norep == false) text1 += text.charAt(counter - 1);
            else if (text.length() % 2 != 0 && norep == true) text1 += text.charAt(text.length() - 1);
            text = text1;

            if (norep == true) break;
        }

        // TESTING REPETITION
        //System.out.println(text1);

        if (text.length() == 1) groupedText.add(String.valueOf(text.charAt(0)) + "x");

        for (int i = 0; i < (text.length() / 2); i++) {
            groupedText.add(String.valueOf(text.charAt(i * 2)) + String.valueOf(text.charAt((i * 2) + 1)));
            if ((text.length() / 2) == i + 1 && !(text.length() / 2 == 0) && text.length() % 2 == 1)
                groupedText.add(String.valueOf(text.charAt((i + 1) * 2) + "x"));
        }

        // TESTING GROUPED TEXT
        //System.out.println(groupedText);

        // ENCRYPTION PROCESS
        for (int i = 0; i < groupedText.size(); i++) {

            // FINDING THE ROWS AND COLUMN OF THE PAIRED CHARACTERS TO BE COMPARED
            char first = groupedText.get(i).charAt(0);
            char second = groupedText.get(i).charAt(1);

            int row1 = 0, row2 = 0;
            int column1 = 0, column2 = 0;

                for (int k = 0; k < 5; k++) {
                    for (int l = 0; l < 5; l++) {
                        if (first == table[k][l]) {
                            row1 = k;
                            column1 = l;
                        }
                        if (second == table[k][l]) {
                            row2 = k;
                            column2 = l;
                        }
                    }
                }


            // ENCRYPTING BY PAIR
            String newPair = "";
            if (column1 == column2) {

                if (row1 == 4) newPair = String.valueOf((char) table[0][column1]);
                    else newPair = String.valueOf((char) table[row1 + 1][column1]);
                if (row2 == 4) newPair += String.valueOf((char) table[0][column2]);
                 else newPair += String.valueOf((char) table[row2 + 1][column2]);

            } else if (row1 == row2) {

                if (column1 == 4) newPair = String.valueOf((char) table[row1][0]);
                    else newPair = String.valueOf((char) table[row1][column1 + 1]);
                if (column2 == 4) newPair += String.valueOf((char) table[row2][0]);
                    else newPair += String.valueOf((char) table[row2][column2 + 1]);

            } else {
                newPair = String.valueOf((char) table[row1][column2]);
                newPair += String.valueOf((char) table[row2][column1]);
            }
            encryptedText.add(newPair);
            encryptText+=newPair+" ";

        }

        // RETURN ENCRYPTED TEXT
        return encryptText;

    }

    public static String decryption(String cipherText){

        String text = cipherText.replaceAll("\\s", "");

        if(text.length()%2!=0) text+="x";

        String decrypted="";

        for (int i = 0; i < text.length(); i+=2) {

            // FINDING THE ROWS AND COLUMN OF THE PAIRED CHARACTERS TO BE COMPARED
            char first = text.charAt(i);
            char second = text.charAt(i+1);

            int row1 = 0, row2 = 0;
            int column1 = 0, column2 = 0;

            // LOOPING TO GET THE ROWS AND COLUMN OF THE PAIRS
                for (int k = 0; k < 5; k++) {
                    for (int l = 0; l < 5; l++) {
                        if (first == table[k][l]) {
                            row1 = k;
                            column1 = l;
                        }
                        if (second == table[k][l]) {
                            row2 = k;
                            column2 = l;
                        }
                    }
                }


            // ENCRYPTING BY PAIR
            String newPair = "";
            if (column1 == column2) {

                if (row1 == 0) newPair = String.valueOf((char) table[4][column1]);
                else newPair = String.valueOf((char) table[row1 - 1][column1]);
                if (row2 == 0) newPair += String.valueOf((char) table[4][column2]);
                else newPair += String.valueOf((char) table[row2 - 1][column2]);

            } else if (row1 == row2) {

                if (column1 == 0) newPair = String.valueOf((char) table[row1][4]);
                else newPair = String.valueOf((char) table[row1][column1 - 1]);
                if (column2 == 0) newPair += String.valueOf((char) table[row2][4]);
                else newPair += String.valueOf((char) table[row2][column2 - 1]);

            } else {
                newPair = String.valueOf((char) table[row1][column2]);
                newPair += String.valueOf((char) table[row2][column1]);
            }
           decrypted+=newPair;

        }

        // RETURN DECRYPTED TEXT
        return decrypted;

    }

    // MAIN METHOD
    public static void main(String[] args) {

        // SCANNER VARIABLE
        Scanner in = new Scanner(System.in);

        // ENTERING KEYWORD
        System.out.print("Enter Keyword: ");
        String keyword = in.nextLine();

        // CALLING OUT THE METHOD TO REMOVE DUPLICATES
        String nodups = removeDups(keyword);

        option="";
        System.out.println("\nOPTIMIZATION: ");
        System.out.println("[A] OMIT Q");
        System.out.println("[B] REPLACE J WITH I");
        while(true) {
            System.out.print("Enter your desired input: ");
            option= in.nextLine();

            if(option.equals("a") || option.equals("A") || option.equals("b") || option.equals("B")) break;
                else System.out.println("INVALID INPUT.\n");
        }
        System.out.print("\n");

        // PRINTING NODUPLICATE KEYWORD
        // System.out.println(nodups);

        // CREATING THE TABLE FOR ENCRYPTION
        tableCypher(nodups);

        boolean loop=true;
        while(loop) {
            System.out.print("""
            MODES
            [A] ENCRYPT
            [B] DECRYPT
            [C] EXIT
            Enter your desired input:ㅤ""");
            String mode = in.nextLine().stripLeading();

            String textEntered = "";
            // SWITCH CONDITION FOR THE OPTION ABOVE.
            switch(mode){
                case "A": case "a":
                    System.out.print("\nENCRYPT TEXT\nEnter a plain text: ");
                    textEntered = in.nextLine();
                    System.out.print("Cipher text: ");
                    System.out.println(encryption(textEntered)+"\n");
                    break;
                case "B": case "b":
                    System.out.print("\nDECRYPT TEXT\nEnter a cipher text: ");
                    textEntered = in.nextLine();
                    System.out.print("Decipher text: ");
                    System.out.println(decryption(textEntered)+"\n");
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
