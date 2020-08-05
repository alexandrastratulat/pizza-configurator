package de.hhn.mib.gpi2.blatt3.aufgabe1.view;

import javax.swing.*;
import java.awt.*;

/**
 * This Class creates the frame and adds the components
 * @author ALexandra Stratulat
 * @version 1.0
 */
public class Window extends JFrame {

    private PizzaConfigPanel pizzaConfigPanel;
    private ButtonPanel buttonPanel;
    private MyMenuBar menuBar;

    /**
     *  Constructor creates window and adds components
     *  (button panel, pizza panel and menu bar)
     */
    public Window(){
        this.buttonPanel = new ButtonPanel();
        this.pizzaConfigPanel = new PizzaConfigPanel();
        this.menuBar = new MyMenuBar();

        this.setVisible(true);
        this.getContentPane().setLayout(new GridBagLayout());
        this.getContentPane().add(pizzaConfigPanel);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = constraints.weighty = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;


        // buttons
        constraints.insets = new Insets(10,20,20,0);
        constraints.gridx = 0;
        constraints.gridy = 3;
        this.getContentPane().add(buttonPanel, constraints);

        this.setJMenuBar(menuBar);
        this.setTitle("Pizza Configurator");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(800, 800);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    /**
     * Getter pizzaConfig
     * @return pizza Configurator panel
     */
    public PizzaConfigPanel getPizzaConfigPanel(){
        return pizzaConfigPanel;
    }

    /**
     * Getter Button
     * @return Button panel
     */
    public ButtonPanel getButtonPanel() {
        return buttonPanel;
    }

    /**
     * Getter Menu
     * @return Menu bar
     */
    public MyMenuBar getMyMenuBar() {
        return menuBar;
    }
}
