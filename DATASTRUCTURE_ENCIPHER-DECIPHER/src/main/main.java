package main;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class main implements ActionListener {

    public static Border Border_White = BorderFactory.createLineBorder(new Color(194, 203, 208) ,1);

    public static ImageIcon logoExit = new ImageIcon("EXIT-HOVER.png");
    public static ImageIcon logoExitHover = new ImageIcon("EXIT-RED.png");
    public static ImageIcon bgImg = new ImageIcon("bg.jpg"); //BG IMPORT
    public static ImageIcon logo = new ImageIcon("LOGO.png");

    public static ButtonCustom CC, PF, PS;

    public static class ButtonCustom extends JButton {
        private Color hoverBackgroundColor;
        private Color pressedBackgroundColor;

        public ButtonCustom() {
            this(null);
        }
        public ButtonCustom(String text) {
            super(text);
            super.setContentAreaFilled(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            if (getModel().isPressed()) {
                g.setColor(pressedBackgroundColor);
            } else if (getModel().isRollover()) {
                g.setColor(hoverBackgroundColor);
            } else {
                g.setColor(getBackground());
            }
            g.fillRect(0, 0, getWidth(), getHeight());
            super.paintComponent(g);
        }

        @Override
        public void setContentAreaFilled(boolean b) {}
        public Color getHoverBackgroundColor() {
            return hoverBackgroundColor;
        }
        public void setHoverBackgroundColor(Color hoverBackgroundColor) {
            this.hoverBackgroundColor = hoverBackgroundColor;
        }
        public Color getPressedBackgroundColor() {
            return pressedBackgroundColor;
        }
        public void setPressedBackgroundColor(Color pressedBackgroundColor) {
            this.pressedBackgroundColor = pressedBackgroundColor;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == CC) {
            Caesar.main();
        }
        else if (e.getSource() == PF) {
            PlayFair.main();
        }
        else if (e.getSource() == PS) {
            Polybius.main();
        }
    }

    JFrame f;
    JLabel background, logoLabel, label, label1, labelExit, labelExitHover;
    JPanel upPanel,upperBar;

    main() {

        ImageIcon bgImg = new ImageIcon("bg.jpg");

        f = new JFrame();

        labelExit = new JLabel();
        labelExit.setIcon(new ImageIcon(logoExit.getImage().getScaledInstance(22, 22, Image.SCALE_SMOOTH)));
        labelExit.setBounds(672,4,550,22);
        labelExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelExitHover.setVisible(true);
            }
        });

        labelExitHover = new JLabel();
        labelExitHover.setIcon(new ImageIcon(logoExitHover.getImage().getScaledInstance(22, 22, Image.SCALE_SMOOTH)));
        labelExitHover.setBounds(672,4,550,22);
        labelExitHover.setVisible(false);
        labelExitHover.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.exit(0);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelExitHover.setVisible(false);
            }
        });

        upperBar = new JPanel();
        upperBar.setBackground(new Color(0,25,40,255));
        upperBar.setBounds(0, 0, 700, 30);
        upperBar.add(labelExitHover);
        upperBar.add(labelExit);
        upperBar.setLayout(null);

        // BACKGROUND
        background=new JLabel();
        background.setIcon(new ImageIcon(bgImg.getImage().getScaledInstance(914, 918,  Image.SCALE_SMOOTH)));
        background.setBounds(0,0,700,500);

        // CAESAR BUTTON
        CC = new ButtonCustom("CAESAR CIPHER");
        CC.setForeground(new Color(255,255,255));
        CC.setFont(new Font("Monospaced", Font.BOLD, 12));
        CC.setBackground(new Color(88, 27, 88));
        CC.setHoverBackgroundColor(new Color(102, 52, 103));
        CC.setPressedBackgroundColor(new Color(56, 18, 57));
        CC.setBounds(50, 350, 160, 50);
        CC.setBorder(Border_White);
        CC.setFocusable(false);
        CC.addActionListener(this);

        // PLAYFAIR BUTTON
        PF = new  ButtonCustom("PLAYFAIR CIPHER");
        PF.setForeground(new Color(255,255,255));
        PF.setFont(new Font("Monospaced", Font.BOLD, 12));
        PF.setBounds(265, 350, 160, 50);
        PF.setBackground(new Color(88, 27, 88));
        PF.setHoverBackgroundColor(new Color(102, 52, 103));
        PF.setPressedBackgroundColor(new Color(56, 18, 57));
        PF.setBorder(Border_White);
        PF.setFocusable(false);
        PF.addActionListener(this);

        // PLAYFAIR BUTTON
        PS = new  ButtonCustom("POLYBIUS SQ CIPHER");
        PS.setForeground(new Color(255,255,255));
        PS.setFont(new Font("Monospaced", Font.BOLD, 12));
        PS.setBounds(490, 350, 160, 50);
        PS.setBackground(new Color(88, 27, 88));
        PS.setHoverBackgroundColor(new Color(102, 52, 103));
        PS.setPressedBackgroundColor(new Color(56, 18, 57));
        PS.setBorder(Border_White);
        PS.setFocusable(false);
        PS.addActionListener(this);

        // UPPER
        label = new JLabel("DATA STRUCTURE");
        label.setFont(new Font("Monospaced", Font.BOLD, 62));
        label.setForeground(new Color(255,255,255));
        label.setBounds(130, 90, 700, 500);
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.LEFT);

        label1 = new JLabel("Encipher and Decipher");
        label1.setFont(new Font("Monospaced", Font.BOLD, 22));
        label1.setForeground(new Color(255,255,255));
        label1.setBounds(130, 160, 700, 500);
        label1.setVerticalAlignment(JLabel.TOP);
        label1.setHorizontalAlignment(JLabel.LEFT);

        logoLabel=new JLabel();
        logoLabel.setIcon(new ImageIcon(logo.getImage().getScaledInstance(100, 100,  Image.SCALE_SMOOTH)));
        logoLabel.setBounds(20,100,700,500);
        logoLabel.setVerticalAlignment(JLabel.TOP);

        upPanel = new JPanel();
        upPanel.setBackground(new Color(0,25,40, 105));
        upPanel.setBounds(0, 0, 700, 500);
        upPanel.add(label1);
        upPanel.add(label);
        upPanel.setLayout(null);

        f.setUndecorated(true);
        f.setSize(700, 500);
        f.add(logoLabel);
        f.add(CC);
        f.add(PF);
        f.add(PS);
        f.add(upperBar);
        f.add(upPanel);
        f.add(background);
        f.getRootPane().setBorder(BorderFactory.createLineBorder(new Color(70, 50,170), 2));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setLayout(null);
        f.setVisible(true);
    }

    public static void main(String[]args){
        new main();
    }
}

