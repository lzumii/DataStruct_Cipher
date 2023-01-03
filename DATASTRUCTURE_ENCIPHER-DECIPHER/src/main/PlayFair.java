package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import static java.awt.Font.*;

public class PlayFair implements ActionListener {
    public static String keyword, text, dtext;
    public static int rbtn = 1;
    // TABLE 5X5 for encryption
    public static int[][] table =  new int[5][5];
    // ARRAY LIST FOR PAIRING
    public static ArrayList<String> groupedText = new ArrayList<String>();
    // ENCRYPTED BY PAIR
    public static ArrayList<String> encryptedText = new ArrayList<String>();

    public static JLabel frame;
    public static ButtonGroup group;
    public static JRadioButton rb1;

    // REMOVE DUPLICATES ON THE KEYWORD
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

    // CREATING A TABLE FOR ENCRYPTION
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
            if (alpha==113 && (rbtn==1))condition=false;
            else if(alpha==106 && (rbtn==2)) condition=false;

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

    // ENCRYPTION PART
    public static String encryption(String textEntered) {
        groupedText.clear();
        encryptedText.clear();
        String text0 = textEntered.toLowerCase().replaceAll("\\s", "");
        String encryptText="";

        // IF ONLY OMIT Q OPTION IS SELECTED
        if (rbtn== 1) text0 = text0.replaceAll("q", "");
            // IF REPLACE J WITH I OPTION IS SELECTED
        else if(rbtn==2) text0 = text0.replaceAll("j", "i");

        text = "";
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

    // DECRYPTION PART
    public static String decryption(String cipherText){

        String text = cipherText.toLowerCase().replaceAll("\\s", "");

        if(text.length()%2!=0) text+="x";
        dtext = "";

        for(int a = 0; a<text.length(); a++ ){
            dtext+=text.charAt(a);
            if(a%2==1){dtext+=" ";}
        }
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

    // EVENTS OF THE FRAMES
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        String textentered, nodups, encriptedtext, decryptedtext;

        if(actionEvent.getSource()==rb1){rbtn = 1;}
        else if (actionEvent.getSource()==rb2){rbtn = 2;}

        //ENCIPHER
        if (actionEvent.getSource()==encipher) {
            keyword = keytxt.getText();
            textentered = entxt.getText();
            //OMIT Q ENCIPHER PROCESS
            nodups = removeDups(keyword);
            tableCypher(nodups);
            encriptedtext = encryption(textentered);
            entxt.setText(text);
            detxt.setText(encriptedtext);
        }

        //DECIPHER
        else if (actionEvent.getSource()==decipher){
            keyword = keytxt.getText();
            textentered = detxt.getText();
            //OMIT Q ENCIPHER PROCESS
            nodups = removeDups(keyword);
            tableCypher(nodups);
            decryptedtext = decryption(textentered);
            entxt.setText(decryptedtext);
            detxt.setText(dtext);

        }

    }

// DECLARATIONS
    JLabel label, labelExit1, labelExitHover1; // FOR LABEL ON TOP
    main.ButtonCustom encipher, decipher; // FOR ENCIPHER AND DECIPHER BUTTONS
    JTextArea entxt, detxt;
    JTextField keytxt;
    JRadioButton rb2;

    PlayFair() {

        // EXIT BUTTON
        labelExit1 = new JLabel();
        labelExit1.setIcon(new ImageIcon(main.logoExit.getImage().getScaledInstance(22, 22, Image.SCALE_SMOOTH)));
        labelExit1.setBounds(672,4,550,22);
        labelExit1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelExitHover1.setVisible(true);
            }
        });

        labelExitHover1 = new JLabel();
        labelExitHover1.setIcon(new ImageIcon(main.logoExitHover.getImage().getScaledInstance(22, 22, Image.SCALE_SMOOTH)));
        labelExitHover1.setBounds(672,4,550,22);
        labelExitHover1.setVisible(false);
        labelExitHover1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
               frame.setVisible(false);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelExitHover1.setVisible(false);
            }
        });

        //ENCIPHER TEXT AREA
        entxt = new JTextArea();
        entxt.setBounds(50, 225, 250, 150);
        entxt.setBackground(new Color(251,209,150));
        entxt.setBorder(BorderFactory.createEmptyBorder(4,6,4,6));
        entxt.setLineWrap(true);

        //BUTTONS ---> ENCIPHER
        encipher = new main.ButtonCustom("ENCIPHER");
        encipher.setBounds(50, 400, 100, 50);
        encipher.setBackground(new Color(88,27,88));
        encipher.setForeground(Color.WHITE);
        encipher.setFont(new Font("Monospaced", PLAIN, 12));
        encipher.setFocusable(false); //remove text border in ENCIPHER button
        encipher.setHoverBackgroundColor(new Color(102, 52, 103));
        encipher.setPressedBackgroundColor(new Color(56, 18, 57));
        encipher.addActionListener(this);

        // ENCIPHER TEXT LABEL
        JLabel enbel = new JLabel();
        enbel.setText(" PLAIN TEXT:");
        enbel.setFont(new Font("Monospaced", PLAIN, 14));
        enbel.setForeground(Color.WHITE);
        enbel.setBounds(50, 175, 250,50);
        enbel.setBackground(new Color(21, 38, 68));
        enbel.setOpaque(true); //setting JLabel background visible

        //DECIPHER TEXT AREA
        detxt = new JTextArea();
        detxt.setBounds(380, 225, 250, 150);
        detxt.setBackground(new Color(251,209,150));
        detxt.setBorder(BorderFactory.createEmptyBorder(4,6,4,6));
        detxt.setLineWrap(true);

        //BUTTONS ---> DECIPHER
        decipher = new main.ButtonCustom("DECIPHER");
        decipher.setBounds(380, 400, 100, 50);
        decipher.setBackground(new Color(88,27,88));
        decipher.setForeground(Color.WHITE);
        decipher.setFont(new Font("Monospaced", PLAIN, 12));
        decipher.setFocusable(false); //remove text border in DECIPHER button
        decipher.setHoverBackgroundColor(new Color(102, 52, 103));
        decipher.setPressedBackgroundColor(new Color(56, 18, 57));
        decipher.addActionListener(this);

        //DECIPHER TEXT LABEL
        JLabel debel = new JLabel();
        debel.setText(" CIPHER TEXT:");
        debel.setFont(new Font("Monospaced", PLAIN, 14));
        debel.setForeground(Color.WHITE);
        debel.setBounds(380, 175, 250,50);
        debel.setBackground(new Color(21, 38, 68));
        debel.setOpaque(true); //setting JLabel background visible

        // SETTING UP LABEL ON THE TOP OF THE FRAME
        label = new JLabel("PLAYFAIR CIPHER");
        label.setFont(new Font("Monospaced", BOLD, 30));
        label.setSize(700, 100);
        label.setForeground(new Color(255,255,255));
        label.setBackground(new Color(0,25,40, 175));
        label.setOpaque(true); //setting JLabel background visible
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        //KEYWORD LABEL
        JLabel keyword = new JLabel();
        keyword.setText("KEYWORD:");
        keyword.setFont(new Font("Monospaced", BOLD, 16));
        keyword.setForeground(Color.WHITE);
        keyword.setBounds(50,110, 90, 25 );
        keyword.setBackground(new Color(88,27,88));
        keyword.setOpaque(false);

        //KEYWORD TEXT AREA

        keytxt = new JTextField("Keyword");
        keytxt.setFont(new Font("Monospaced", BOLD, 12));
        keytxt.setBounds(140,110, 90,25);
        keytxt.setBackground(new Color(251,209,150));
        keytxt.setBorder(BorderFactory.createLineBorder(new Color(70, 50,170), 1));
        keytxt.setBorder(BorderFactory.createCompoundBorder(keytxt.getBorder(),BorderFactory.createEmptyBorder(2,4,2,4)));
        // LINE 88: ADDS AN EMPTY BORDER INSIDE THE EXISTING BORDER

        //RADIO BUTTONS
        rb1 = new JRadioButton("OMIT Q");
        rb1.setBackground(new Color(0,0,0,0));
        rb1.setFont(new Font("Monospaced", BOLD, 14));
        rb1.setForeground(Color.WHITE);
        rb1.setFocusable(false);
        rb1.setOpaque(false);
        rb1.addActionListener(this);

        rb2 = new JRadioButton("REPLACE J WITH I");
        rb2.setBackground(new Color(0,0,0,0));
        rb2.setFont(new Font("Monospaced", BOLD, 14));
        rb2.setForeground(Color.WHITE);
        rb2.setFocusable(false);
        rb2.setOpaque(false);
        rb2.addActionListener(this);

        group = new ButtonGroup();
        group.add(rb1);
        group.add(rb2);
        group.setSelected(rb1.getModel(), true);

        //PANEL FOR BUTTONS
        JPanel btnp1 = new JPanel();
        btnp1.setLayout(new GridLayout(1,1));
        btnp1.setBounds(380, 110, 80, 30);
        btnp1.setOpaque(false);
        btnp1.add(rb1);

        JPanel btnp2 = new JPanel();
        btnp2.setLayout(new GridLayout(1,1));
        btnp2.setBounds(470, 110, 160, 30);
        btnp2.setOpaque(false);
        btnp2.add(rb2);

        //MAIN FRAME
        frame = new JLabel();
        JLabel bg = new JLabel();
        bg.setIcon(new ImageIcon(main.bgImg.getImage().getScaledInstance(914, 918, Image.SCALE_SMOOTH)));
        bg.setBounds(0,0,700,500);

        frame.setSize(700, 500);
        frame.add(labelExitHover1);
        frame.add(labelExit1);
        frame.add(enbel);
        frame.add(entxt);
        frame.add(encipher);
        frame.add(debel);
        frame.add(detxt);
        frame.add(decipher);
        frame.add(label);
        frame.add(keyword);
        frame.add(keytxt);
        frame.add(btnp1);
        frame.add(btnp2);
        frame.add(bg);
        frame.setLayout(null);
    }

    // MAIN METHOD
    public static void main() {
         new PlayFair();
    }


}
