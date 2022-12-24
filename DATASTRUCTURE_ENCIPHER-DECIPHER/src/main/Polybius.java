package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Polybius implements ActionListener {

    public static JTextArea Encipher,Decipher;
    public static main.ButtonCustom En, De;
    public static String enTextFixed,deTextFixed;
    public static JLabel f;

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
        String text = textEntered.toLowerCase();

        String textfixed=""; // TO CLEAN THE TEXT INPUTTED FIRST BEFORE ENCRYPTION SUCH AS REMOVING NUMBERS AND SYMBOLS.
        String encrypted=""; // ENCRYPTED TEXT

        // REMOVING NUMBERS AND SYMBOLS
        for (int g=0; g<text.length();g++){
            char character=text.charAt(g);
            if((character>=65 && character<=90) || (character>=97 && character<=122) || character==32)textfixed+=character;
        }
        enTextFixed=textfixed;
        textfixed=textfixed.replaceAll("j", "i");
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
        deTextFixed=textFixed;
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
            if(wordCount%2==0 && textFixed.charAt(i)==32)decrypted+=" ";
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

    // ENCRYPTION and DECRYPTION
    @Override
    public void actionPerformed(ActionEvent e) {
        String  Encrypt, Decrypt;

        // ENCIPHER
        if (e.getSource() == En) {
            Encrypt=encryption(Encipher.getText());
            Encipher.setText(enTextFixed);
            Decipher.setText(Encrypt);
        }
        // DECIPHER
        else if (e.getSource() == De) {
            Decrypt=decryption(Decipher.getText());
            Encipher.setText(Decrypt);
            Decipher.setText(deTextFixed);
        }
    }

    JLabel background, label, EnLabel, DeLabel, labelExit1, labelExitHover1;
    JPanel encipherPanel, decipherPanel, upPanel;

    Polybius() {

        f = new JLabel();

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
                f.setVisible(false);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelExitHover1.setVisible(false);
            }
        });

        // BACKGROUND
        background=new JLabel();
        background.setIcon(new ImageIcon(main.bgImg.getImage().getScaledInstance(914, 918,  Image.SCALE_SMOOTH)));
        background.setBounds(0,0,700,500);

        // TEXT AREA FOR ENCIPHER
        Encipher = new JTextArea();
        Encipher.setBounds(50, 210, 250, 150);
        Encipher.setBackground(new Color(251,209,150));
        Encipher.setBorder(BorderFactory.createEmptyBorder(4,6,4,6));

        EnLabel = new JLabel(" PLAIN TEXT:", SwingConstants.LEFT);
        EnLabel.setForeground(new Color(255,255,255));
        EnLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        EnLabel.setBounds(0,0,180,50);
        EnLabel.setBackground(new Color(29, 84, 123));

        // ENCIPHER PANEL
        encipherPanel = new JPanel();
        encipherPanel.setBackground(new Color(21, 38, 68));
        encipherPanel.setBounds(50, 160, 250, 48);
        encipherPanel.add(EnLabel);
        encipherPanel.setLayout(null);

        // ENCIPHER BUTTON
        En = new main.ButtonCustom("ENCIPHER");
        En.setForeground(new Color(255,255,255));
        En.setFont(new Font("Monospaced", Font.PLAIN, 12));
        En.setBackground(new Color(88, 27, 88));
        En.setHoverBackgroundColor(new Color(102, 52, 103));
        En.setPressedBackgroundColor(new Color(56, 18, 57));
        En.setBounds(50, 400, 100, 50);
        En.addActionListener(this);

        // TEXT AREA FOR DECIPHER
        Decipher = new JTextArea();
        Decipher.setBounds(380, 210, 250, 150);
        Decipher.setBackground(new Color(251,209,150));
        Decipher.setBorder(BorderFactory.createEmptyBorder(4,6,4,6));

        DeLabel = new JLabel(" CIPHER TEXT:", SwingConstants.LEFT);
        DeLabel.setForeground(new Color(255,255,255));
        DeLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        DeLabel.setBackground(new Color(29, 84, 123));
        DeLabel.setBounds(0,0,200,50);

        // DECIPHER PANEL
        decipherPanel = new JPanel();
        decipherPanel.setBackground(new Color(21, 38, 68));
        decipherPanel.setBounds(380, 160, 250, 48);
        decipherPanel.add(DeLabel);
        decipherPanel.setLayout(null);

        // DECIPHER BUTTON
        De = new main.ButtonCustom("DECIPHER");
        De.setForeground(new Color(255,255,255));
        De.setFont(new Font("Monospaced", Font.PLAIN, 12));
        De.setBounds(380, 400, 100, 50);
        De.setBackground(new Color(88, 27, 88));
        De.setHoverBackgroundColor(new Color(102, 52, 103));
        De.setPressedBackgroundColor(new Color(56, 18, 57));
        De.addActionListener(this);

        // UPPER
        label = new JLabel("POLYBIUS SQUARE CIPHER");
        label.setFont(new Font("Monospaced", Font.BOLD, 30));
        label.setForeground(new Color(255,255,255));
        label.setBounds(0, 30, 700, 500);
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setHorizontalTextPosition(JLabel.CENTER);

        upPanel = new JPanel();
        upPanel.setBackground(new Color(0,25,40, 175));
        upPanel.setBounds(0, 0, 700, 100);
        upPanel.add(label);
        upPanel.setLayout(null);

        f.setSize(700, 500);
        f.add(labelExitHover1);
        f.add(labelExit1);
        f.add(upPanel);
        f.add(Encipher);
        f.add(encipherPanel);
        f.add(decipherPanel);
        f.add(En);
        f.add(De);
        f.add(Decipher);
        f.add(background);
        f.setLayout(null);

    }

    public static void main(){
        new Polybius();
    }
}

