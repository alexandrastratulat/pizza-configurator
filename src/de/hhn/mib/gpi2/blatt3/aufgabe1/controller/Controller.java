package de.hhn.mib.gpi2.blatt3.aufgabe1.controller;

import javax.swing.*;

/**
 * This class shows the gui8
 * @author ALexandra Stratulat
 * @version 1.0
 */
public class Controller {
    public static void main(String[] args) {

        //Swing theme
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Exception e) {
            System.out.println("Look and Feel not set");
        }

        GUIController controller = new GUIController();

    }

}
