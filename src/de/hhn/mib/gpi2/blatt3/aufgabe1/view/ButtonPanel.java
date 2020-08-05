package de.hhn.mib.gpi2.blatt3.aufgabe1.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * This class adds the buttons to panel
 * @author ALexandra Stratulat
 * @version 1.0
 */
public class ButtonPanel extends JPanel{

    //buttons
    private JButton doneBtn = new JButton("Done");
    private JButton closeBtn = new JButton("Close");

    public ButtonPanel(){
        this.setLayout(new FlowLayout());
        this.add(doneBtn);
        this.add(closeBtn);
    }

    /**
     * This method adds Action Listener to "Done " Button
     * @param listener ActionListener
     */
    public void addActionDoneBtn(ActionListener listener){
        doneBtn.addActionListener(listener);
    }

    /**
     * This method adds Action Listener to "Close" Button
     * @param listener ActionListener
     */
    public void addActionCloseBtn(ActionListener listener){
        closeBtn.addActionListener(listener);
    }

}
