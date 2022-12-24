package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Caesar implements ActionListener {
    public static int interval;
    public static String textEntered=""; // USER INPUT
    public static int limit=0; // LIMIT is the minimum scope in the ASCII table for a specific group of characters. A-Z the limit is A and in ASCII table it is equivalent to 65.
    public static char select; // SELECT is the character selected in the text inputted.

    public static JLabel frame;

    // METHOD FOR ENCRYPTION
    public static String encyrpt(int interval){

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
        // RETURN THE ENCRYPTED TEXT
        return output;

    }

    // METHOD FOR DECRYPTION
    public static String decrypt(int interval){

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
        // RETURN THE DECRYPTED TEXT
        return output;
    }

    // ENCRYPTION and DECRYPTION
    @Override
    public void actionPerformed(ActionEvent e) {
        String Encrypt, Decrypt, inter="";

        if(s.getText().equals("")) s.setText("3");
          else inter=s.getText();

        for(int c=0; c<inter.length();c++) {
            if ((inter.charAt(c) >=65 && inter.charAt(c) <=90)||(inter.charAt(c) >=97 && inter.charAt(c) <=122)) {
                c=inter.length();
                inter="3";
            }
        }
        interval = Integer.parseInt(inter);
        s.setText(inter);

                // ENCIPHER
                if (e.getSource() == E) {
                    textEntered = Etext.getText();
                    Encrypt = encyrpt(interval);
                    Detext.setText(Encrypt);
                }
                // DECIPHER
                else if (e.getSource() == D) {
                    textEntered = Detext.getText();
                    Decrypt = decrypt(interval);
                    Etext.setText(Decrypt);
                }
        }


    // UI DECLARATIONS
    JLabel lbCaesar, shift, ELabel, DLabel, labelExit1, labelExitHover1;
    JPanel Cpanel, shiftpanel, Ipanel, Opanel;
    JTextArea Etext, Detext;
    JTextField s;
    main.ButtonCustom E, D;

    Caesar () {
        // MAIN FRAME
        frame = new JLabel();
        frame.setSize(700, 500);
        JLabel bg = new JLabel();
        bg.setIcon(new ImageIcon(main.bgImg.getImage().getScaledInstance(914, 918, Image.SCALE_SMOOTH)));
        bg.setBounds(0, 0, 700, 500);

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

        // "Caesar Cipher"
        lbCaesar = new JLabel("CAESAR CIPHER");
        lbCaesar.setFont(new Font("Monospaced", Font.BOLD, 36));
        lbCaesar.setForeground(Color.WHITE);
        lbCaesar.setBounds(0, 30, 700, 500);
        lbCaesar.setVerticalAlignment(JLabel.TOP);
        lbCaesar.setHorizontalAlignment(JLabel.CENTER);
        lbCaesar.setVerticalTextPosition(JLabel.TOP);
        lbCaesar.setHorizontalTextPosition(JLabel.CENTER);

        Cpanel = new JPanel();
        Cpanel.setBackground(new Color(0,25,40, 175));
        Cpanel.setBounds(0, 0, 700, 100);
        Cpanel.add(lbCaesar);
        Cpanel.setLayout(null);

        // SHIFT AREA
        shift = new JLabel(" Shift or Key Number: ", SwingConstants.LEFT);
        shift.setFont(new Font("Monospaced", Font.BOLD, 12));
        shift.setForeground(new Color(255,255,255));
        shiftpanel = new JPanel();
        shiftpanel.setBackground(new Color(21, 38, 68));
        shiftpanel.add(shift);
        shiftpanel.setBounds(50, 125, 150, 30);
        s = new JTextField();
        s.setBounds(200, 125, 45, 30);
        s.setBackground(new Color(251, 209, 150));
        s.setBorder(BorderFactory.createEmptyBorder(4, 6, 4 ,6));
        s.setText("3");

        s.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (s.getText().length() >= 2 ) // limit to 3 characters
                    e.consume();
            }
        });

        // ENCIPHER AREA
        Etext = new JTextArea();
        Etext.setBounds(50, 230, 250, 150);
        Etext.setBackground(new Color(251, 209, 150));
        Etext.setBorder(BorderFactory.createEmptyBorder(4, 6, 4 ,6));

        ELabel = new JLabel(" PLAIN TEXT:", SwingConstants.LEFT);
        ELabel.setFont(new Font("Monospaced", Font.BOLD, 14));
        ELabel.setBounds(0, 0, 180, 50);
        ELabel.setForeground(new Color(255,255,255));

        Ipanel = new JPanel();
        Ipanel.setBackground(new Color(21, 38, 68));
        Ipanel.setBounds(50, 180, 250, 48);
        Ipanel.add(ELabel);
        Ipanel.setLayout(null);

        // ENCIPHER BUTTON
        E = new main.ButtonCustom("ENCIPHER");
        E.setBounds(50, 420, 100, 50);
        E.setFont(new Font("Monospaced", Font.BOLD, 12));
        E.setForeground(new Color(255,255,255));
        E.setBackground(new Color(88, 27, 88));
        E.setHoverBackgroundColor(new Color(102, 52, 103));
        E.setPressedBackgroundColor(new Color(56, 18, 57));
        E.addActionListener(this);

        // DECIPHER AREA
        Detext = new JTextArea();
        Detext.setBounds(380, 230, 250, 150);
        Detext.setBackground(new Color(251, 209, 150));
        Detext.setBorder(BorderFactory.createEmptyBorder(4, 6, 4 ,6));

        DLabel = new JLabel(" CIPHER TEXT:", SwingConstants.LEFT);
        DLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
        DLabel.setBounds(0, 0, 200, 50);
        DLabel.setForeground(new Color(255,255,255));

        Opanel = new JPanel();
        Opanel.setBackground(new Color(21, 38, 68));
        Opanel.setBounds(380, 180, 250, 48);
        Opanel.add(DLabel);
        Opanel.setLayout(null);

        // DECIPHER BUTTON
        D = new main.ButtonCustom("DECIPHER");
        D.setBounds(380, 420, 100, 50);
        D.setFont(new Font("Monospaced", Font.BOLD, 12));
        D.setForeground(new Color(255,255,255));
        D.setBackground(new Color(88, 27, 88));
        D.setHoverBackgroundColor(new Color(102, 52, 103));
        D.setPressedBackgroundColor(new Color(56, 18, 57));
        D.addActionListener(this);

        frame.add(labelExitHover1);
        frame.add(labelExit1);
        frame.add(Cpanel);
        frame.add(shiftpanel);
        frame.add(s);
        frame.add(Etext);
        frame.add(Ipanel);
        frame.add(Detext);
        frame.add(Opanel);
        frame.add(E);
        frame.add(D);
        frame.add(bg);
        frame.setLayout(null);
    }

    //  MAIN METHOD
    public static void main() {
        new Caesar();
    }
}
