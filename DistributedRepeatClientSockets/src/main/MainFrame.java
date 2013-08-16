/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Niall Traynor
 */
public class MainFrame extends JFrame{
    
    public static final String SELECTCHAT = "selectChat";
    public static final String CHATWINDOW = "chatWindow";
    public static final String MENU = "menu";
    
    private CardLayout cardLayout = new CardLayout();
    private JPanel cards = new JPanel(cardLayout);
}
    
