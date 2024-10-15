package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
public class Playmain  {
    public static void main(String[]args)
    {
        JFrame frame = new JFrame("breakout ball game");
        frame.setSize(700,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setBackground(Color.gray);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        Gamepanel panel = new Gamepanel();
        frame.add(panel);
    }
}
