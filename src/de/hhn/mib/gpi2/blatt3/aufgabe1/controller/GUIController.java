package de.hhn.mib.gpi2.blatt3.aufgabe1.controller;

import de.hhn.mib.gpi2.blatt3.aufgabe1.model.Order;
import de.hhn.mib.gpi2.blatt3.aufgabe1.model.Pizza;
import de.hhn.mib.gpi2.blatt3.aufgabe1.view.ButtonPanel;
import de.hhn.mib.gpi2.blatt3.aufgabe1.view.MyMenuBar;
import de.hhn.mib.gpi2.blatt3.aufgabe1.view.PizzaConfigPanel;
import de.hhn.mib.gpi2.blatt3.aufgabe1.view.Window;
import de.hhn.mib.gpi2.blatt4.aufgabe1.io.DataStorage;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class creates the controller actions
 * @author ALexandra Stratulat
 * @version 1.1
 */
public class GUIController {

    private Window mainWindow;
    private Pizza pizza;
    private Order order;
    private PizzaConfigPanel pizzaConfigPanel;
    private DataStorage dataStorage = new DataStorage();

    /**
     * Constructor
     */
    public GUIController() {
        Window window = new Window();
        pizzaConfigPanel = window.getPizzaConfigPanel();

        //lambda expression action listener for buttons
        ButtonPanel buttonPanel = window.getButtonPanel();
        buttonPanel.addActionDoneBtn(event -> done());
        buttonPanel.addActionCloseBtn(event -> close());

        //lambda expression action listener for menubar
        MyMenuBar menuBar = window.getMyMenuBar();
        menuBar.addActionCloseItem(event -> close());
        menuBar.addActionSaveItem(event -> save());
        menuBar.addActionReadItem(event -> read());
        menuBar.addActionHelpItem(event -> help());
    }

    /**
     * This method creates the pizza that the user selected.
     * If the user configured his pizza he can press yes and will see
     * his order details.
     */
    private void done(){
        this.pizza = new Pizza(this.pizzaConfigPanel.getPrice(), this.pizzaConfigPanel.getPizzaSize(), this.pizzaConfigPanel.getPizzaTopping());
        List<Pizza> pizzas = new ArrayList<>();
        if(selected()){
            int confirmed = JOptionPane.showConfirmDialog(mainWindow, "Total amount to pay: " + this.pizzaConfigPanel.getPrice() + ". Do you want to order this?",
                    "Order", JOptionPane.YES_NO_OPTION);
            if(confirmed == JOptionPane.YES_OPTION){
                pizzas.add(pizza);
                this.order = new Order(pizzas);
                JOptionPane.showMessageDialog(mainWindow, "This is your order! " + this.order.toString().
                        replace("[","").
                        replace("]","").
                        replace("_", " "));
            }
        }
    }

    /**
     * If the user presses on close button/ or close menu item he will be asked
     * if he want to cancel the program. If his answer is yes, then the program closes.
     */
    public void close(){
        int confirmed = JOptionPane.showConfirmDialog(mainWindow, "Are you sure you want to cancel your order?", "Exit Program",
                JOptionPane.YES_NO_OPTION);
        if(confirmed == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }

    /**
     * If the user presses on help menu item, he can write a message that will go on the
     * console.
     */
    public void help(){
       String message = JOptionPane.showInputDialog(mainWindow, "Need help? Write us a message.");
       System.out.println("Someone needs help. Here is the message: '" + message + "'");

   }

    /**
     *This method writes the order in a file if the pizza was configured.
     */
    private void save() {
       if(selected()){
           dataStorage.writeOrder(this.order);
       }
   }

    /**
     * This method reads the order from the file and shows the order in a dialog and in the console.
     */
    private void read() {
        if(selected()) {
           Order order = dataStorage.readOrder();
           System.out.println(order.toString());
           JOptionPane.showMessageDialog(mainWindow, "Thank you for your order! Your order has been received and will be processed shortly. \n" +
                   "Order Number: " + order.getOrderId() + "\n" + pizza.print());
       }
   }

    /**
     * If the user press the done button/read/write order, but they haven't selected anything,
     * they see a dialog that reminds them that they haven't configured the pizza.
     * @return true if pizza is configured
     */
    private boolean selected(){
       if(this.getPizzaConfigPanel().getPrice() == 0) {
           JOptionPane.showMessageDialog(mainWindow, "You haven't selected anything! Please try again!");
           return false;
       }
       return true;
   }
    /**
     * getter
     * @return pizza config panel/pizza/order
     */
    private PizzaConfigPanel getPizzaConfigPanel() {
        return pizzaConfigPanel;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public Object getOrder() {
        return order;
    }
}
