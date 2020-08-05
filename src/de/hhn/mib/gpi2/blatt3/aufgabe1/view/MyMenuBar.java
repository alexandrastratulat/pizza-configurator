package de.hhn.mib.gpi2.blatt3.aufgabe1.view;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * This class creates the menu bar
 * @author ALexandra Stratulat
 * @version 1.1
 */
public class MyMenuBar extends JMenuBar {

    // menu
    private JMenu file, order, help;
    //menu items
    private JMenuItem close, save, read, needHelp;

    /**
     * Constructor creates menu bar
     */
    public MyMenuBar() {
        //menu items
        close = new JMenuItem("Close");
        save = new JMenuItem("Save order");
        read = new JMenuItem("Read order");
        needHelp = new JMenuItem("ContactUs");

        //menu files
        file = new JMenu("File");
        order = new JMenu("Order");
        help = new JMenu("Help");

        //add item to menu
        file.add(close);
        order.add(save);
        order.add(read);
        help.add(needHelp);

        //add menu to menu bar
        this.add(file);
        this.add(order);
        this.add(help);

        //set visible
        this.setVisible(true);

    }

    /**
     * This method adds Action Listener to "Close" item
     * @param listener ActionListener
     */
    public void addActionCloseItem(ActionListener listener){
        close.addActionListener(listener);
    }

    /**
     * This method adds Action Listener to "Save order" item
     * @param listener ActionListener
     */
    public void addActionSaveItem(ActionListener listener){
        save.addActionListener(listener);
    }

    /**
     * This method adds Action Listener to "Read order" item
     * @param listener ActionListener
     */
    public void addActionReadItem(ActionListener listener){
        read.addActionListener(listener);
    }

    /**
     * This method adds Action Listener to "ContactUs" item
     * @param listener ActionListener
     */
    public void addActionHelpItem(ActionListener listener){
        needHelp.addActionListener(listener);
    }

}