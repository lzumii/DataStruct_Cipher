import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class caesar implements ActionListener {
    public static String in, out;
    public static int interval;
    public static String textEntered=""; // USER INPUT
    public static int limit=0; // LIMIT is the minimum scope in the ASCII table for a specific group of characters. A-Z the limit is A and in ASCII table it is equivalent to 65.
    public static char select; // SELECT is the character selected in the text inputted.

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
        String Encrypt, Decrypt;

        // ENCIPHER
        if (e.getSource() == E) {
            interval = Integer.parseInt(s.getText());
            textEntered = Etext.getText();
            Encrypt = encyrpt(interval);
            Etext.setText(in);
            Detext.setText(Encrypt);
        }
        // DECIPHER
        else if (e.getSource() == D) {
            interval = Integer.parseInt(s.getText());
            textEntered = Detext.getText();
            Decrypt = decrypt(interval);
            Etext.setText(Decrypt);
            Detext.setText(out);
        }

    }
    // UI DECLARATIONS
    JFrame frame;
    JLabel lbCaesar, shift, ELabel, DLabel;
    JPanel Cpanel, shiftpanel, Ipanel, Opanel;
    JTextArea Etext, Detext, s;
    ActionButton E, D;

    caesar () {
        // MAIN FRAME
        frame = new JFrame("Caesar Cipher");
        frame.setSize(700, 500);
        JLabel bg = new JLabel();
        ImageIcon bgImage = new ImageIcon("C:\\Users\\Azil\\Documents\\PROGRAMMING\\JAVA\\DATASTRU\\ceasar\\src\\bg.jpg");
        bg.setIcon(new ImageIcon(bgImage.getImage().getScaledInstance(914, 918, Image.SCALE_SMOOTH)));
        bg.setBounds(0, 0, 700, 500);

        // "Ceasar Cipher"
        lbCaesar = new JLabel("CAESAR CIPHER");
        lbCaesar.setFont(new Font("Monospaced", Font.BOLD, 36));
        lbCaesar.setForeground(Color.WHITE);
        lbCaesar.setBounds(0, 30, 700, 500);
        lbCaesar.setVerticalAlignment(JLabel.TOP);
        lbCaesar.setHorizontalAlignment(JLabel.CENTER);
        lbCaesar.setVerticalTextPosition(JLabel.TOP);
        lbCaesar.setHorizontalTextPosition(JLabel.CENTER);

        Cpanel = new JPanel();
        Cpanel.setBackground(new Color(21, 38, 68, 190));
        Cpanel.setBounds(0, 0, 700, 100);
        Cpanel.add(lbCaesar);
        Cpanel.setLayout(null);

        // SHIFT AREA
        shift = new JLabel(" Shift or Key Number: ", SwingConstants.LEFT);
        shift.setFont(new Font("Monospaced", Font.BOLD, 12));
        shift.setForeground(new Color(194, 203, 208));
        shiftpanel = new JPanel();
        shiftpanel.setBackground(new Color(21, 38, 68));
        shiftpanel.add(shift);
        shiftpanel.setBounds(50, 125, 150, 30);
        s = new JTextArea();
        s.setBounds(200, 125, 45, 30);
        s.setBackground(new Color(251, 209, 150));
        s.setBorder(BorderFactory.createEmptyBorder(4, 6, 4 ,6));

        // ENCIPHER AREA
        Etext = new JTextArea();
        Etext.setBounds(50, 230, 250, 150);
        Etext.setBackground(new Color(251, 209, 150));
        Etext.setBorder(BorderFactory.createEmptyBorder(4, 6, 4 ,6));

        ELabel = new JLabel(" Input text here:", SwingConstants.LEFT);
        ELabel.setFont(new Font("Monospaced", Font.BOLD, 14));
        ELabel.setBounds(0, 0, 180, 50);
        ELabel.setForeground(new Color(194, 203, 208));

        Ipanel = new JPanel();
        Ipanel.setBackground(new Color(21, 38, 68));
        Ipanel.setBounds(50, 180, 250, 48);
        Ipanel.add(ELabel);
        Ipanel.setLayout(null);

        // ENCIPHER BUTTON
        E = new ActionButton("ENCIPHER");
        E.setBounds(50, 420, 100, 50);
        E.setFont(new Font("Monospaced", Font.BOLD, 12));
        E.setForeground(new Color(194, 203, 208));
        E.setBackground(new Color(88, 27, 88));
        E.setHoverBGColor(new Color(102, 52, 103));
        E.setPressedBGColor(new Color(56, 18, 57));
        E.addActionListener(this);

        // DECIPHER AREA
        Detext = new JTextArea();
        Detext.setBounds(380, 230, 250, 150);
        Detext.setBackground(new Color(251, 209, 150));
        Detext.setBorder(BorderFactory.createEmptyBorder(4, 6, 4 ,6));

        DLabel = new JLabel(" Deciphered text here.", SwingConstants.LEFT);
        DLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
        DLabel.setBounds(0, 0, 200, 50);
        DLabel.setForeground(new Color(194, 203, 208));

        Opanel = new JPanel();
        Opanel.setBackground(new Color(21, 38, 68));
        Opanel.setBounds(380, 180, 250, 48);
        Opanel.add(DLabel);
        Opanel.setLayout(null);

        // DECIPHER BUTTON
        D = new ActionButton("DECIPHER");
        D.setBounds(380, 420, 100, 50);
        D.setFont(new Font("Monospaced", Font.BOLD, 12));
        D.setForeground(new Color(194, 203, 208));
        D.setBackground(new Color(88, 27, 88));
        D.setHoverBGColor(new Color(102, 52, 103));
        D.setPressedBGColor(new Color(56, 18, 57));
        D.addActionListener(this);

        // COMPONENTS
        frame.setUndecorated(true);
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }



    static class ActionButton extends JButton {
        private Color hoverBGColor;
        private Color pressedBGColor;

        public ActionButton() {
            this(null);
        }
        public ActionButton(String text) {
            super(text);
            super.setContentAreaFilled(false);
        }
        @Override
        protected void paintComponent (Graphics g) {
            if (getModel().isPressed()) {
                g.setColor(pressedBGColor);
            }
            else if (getModel().isRollover()) {
                g.setColor(hoverBGColor);
            }
            else {
                g.setColor(getBackground());
            }
            g.fillRect(0, 0, getWidth(), getHeight());
            super.paintComponent(g);
        }
        @Override
        public void setContentAreaFilled(boolean b) {
        }
        public Color getHoverBGColor() {
            return hoverBGColor;
        }
        public void setHoverBGColor(Color hoverBGColor) {
            this.hoverBGColor = hoverBGColor;
        }
        public Color getPressedBGColor() {
            return pressedBGColor;
        }
        public void setPressedBGColor(Color pressedBGColor) {
            this.pressedBGColor = pressedBGColor;
        }
    }
    public static void main(String[] args) {
        new caesar();
    }
}
