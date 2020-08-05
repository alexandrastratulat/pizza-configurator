package de.hhn.mib.gpi2.blatt3.aufgabe1.view;

import de.hhn.mib.gpi2.blatt3.aufgabe1.model.PizzaTopping;

import javax.swing.*;

/**
 * This class creates checkbox
 * @author ALexandra Stratulat
 * @version 1.0
 */
public class ToppingsCheckBox extends JCheckBox {
    //topping
    private final PizzaTopping topping;


    //enum topping to string
    public ToppingsCheckBox(PizzaTopping topping) {
        super(topping.toString());
        this.topping = topping;
    }

    PizzaTopping getTopping(){
        return topping;
    }

    public int getPrice(){
       return topping.getPrice();
    }
}
