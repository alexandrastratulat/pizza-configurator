package de.hhn.mib.gpi2.blatt3.aufgabe1.view;

import de.hhn.mib.gpi2.blatt3.aufgabe1.model.PizzaSize;
import de.hhn.mib.gpi2.blatt3.aufgabe1.model.PizzaTopping;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * This class creates the pizza configurator panel
 * @author ALexandra Stratulat
 * @version 1.0
 */
public class PizzaConfigPanel extends JPanel {

    //panes
    private JPanel eastPanel = new JPanel();
    private JPanel centerPanel = new JPanel();
    private JPanel northPanel = new JPanel();
    private JPanel southPanel = new JPanel();

    //title
    private JLabel label;

    //enum values to -> combo box
    private PizzaSize[] sizes = PizzaSize.values();
    private JComboBox<PizzaSize> comboBox = new JComboBox<>(sizes);

    //enum values to -> check box
    private List<ToppingsCheckBox> list = new ArrayList<>(PizzaTopping.values().length);

    //price text
    private JTextField priceText;

    //prices
    private int totalPrice = 0;
    private int sizePrice = 0;
    private int toppingPrice = 0;

    //listener
    private ActionListener listener = new PriceListener();


    /**
     * Constructor PizzaPanel, adds the components
     * (pizza size, toppings, total price, image [test])
     */
    public PizzaConfigPanel() {
        //layout of the panel
        this.setLayout(new GridBagLayout());

        //creates the other components
        createTitle();
        createComboBoxPanel();
        createCheckBoxPanel();
        createPricePanel();
        createImagePanel();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = constraints.weighty = 1; //request vertical/horizontal space
        constraints.fill = GridBagConstraints.HORIZONTAL;

        //title
        constraints.insets = new Insets(20,20,10,0);
        constraints.gridx = 0;//column
        constraints.gridy = 0;//row
        this.add(label, constraints);

        //adds the panels to the big panel
       // combobox
        constraints.insets = new Insets(-60,20,10,250);
        constraints.gridx = 0;//column
        constraints.gridy = 1;//row
        this.add(northPanel, constraints);

       // checkbox
        constraints.insets = new Insets(-70,20,10,10);
        constraints.gridx = 0;
        constraints.gridy = 2;
        this.add(centerPanel, constraints);

        //img
        constraints.insets = new Insets(50,20,-50,40);
        constraints.gridx = 2;
        constraints.gridy = 1;
        this.add(eastPanel, constraints);

       // total price
        constraints.ipady = 0;       //reset to default
        constraints.anchor = GridBagConstraints.FIRST_LINE_END; //bottom of space
        constraints.insets = new Insets(10,0,20,0);  //padding
        constraints.gridx = 2;       //second column
        constraints.gridwidth = 2;   //2 columns wide
        constraints.gridy = 3;       //third row
        this.add(southPanel, constraints);

        //set visible
        this.setVisible(true);
    }

    /**
     * This method creates the title
     */
    private void createTitle(){
        label = new JLabel("Welcome to Pizza Configurator!");
        label.setFont(new Font("Monospaced", Font.BOLD, 26));
        label.setForeground(Color.decode("#cc2f1d"));
    }


    /**
     * This method adds in combobox the sizes of the pizza
     */
    private void createComboBoxPanel(){

        comboBox.setModel(new DefaultComboBoxModel<>(PizzaSize.values()));
        comboBox.addActionListener(listener);

        northPanel.setLayout(new BorderLayout());
        northPanel.add(new JLabel("Choose size of your pizza:  "), BorderLayout.WEST);
        northPanel.add(comboBox);
    }

    /**
     * This method adds to checkbox the enums pizza toppings
     */
    private void createCheckBoxPanel(){
        centerPanel.setLayout(new GridLayout(10,1));
        centerPanel.add(new JLabel("Choose toppings for your pizza:  "), BorderLayout.NORTH);

        for(PizzaTopping pizzaTopping: PizzaTopping.values()){
            ToppingsCheckBox checkBox = new ToppingsCheckBox(pizzaTopping);
            list.add(checkBox);
            centerPanel.add(checkBox);
            checkBox.addActionListener(listener);
        }

    }

    /**
     * This method creates the text for the price
     */
    private void createPricePanel(){
        southPanel.add(new JLabel("Total price: "));
        priceText = new JTextField(7);
        priceText.setFont(new Font("Monospaced", Font.BOLD, 16));
        priceText.setEditable(false);
        priceText.setForeground(Color.red);
        priceText.setBackground(southPanel.getBackground());
        priceText.setDisabledTextColor(Color.red);
        priceText.setHorizontalAlignment(SwingConstants.CENTER);
        priceText.setText(" ");
        southPanel.add(priceText);
    }

    /**
     * This method adds image
     * its a test so i can see how it will look like
     */
    private void createImagePanel(){
        ImageIcon imageIcon = new ImageIcon("src\\de\\hhn\\mib\\gpi2\\blatt3\\aufgabe1\\img\\pizza.png");
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);
        eastPanel.add(new JLabel("", imageIcon, SwingConstants.LEFT));
    }

    /**
     * This method returns the total price
     * @return totalPrice
     */
    public int getPrice(){
        return totalPrice;
    }

    /**
     * This method returns the selected pizza size
     * @return pizza size
     */
    public PizzaSize getPizzaSize(){
        return sizes[comboBox.getSelectedIndex()];
    }

    /**
     * This method returns the selected toppings
     * @return pizza toppings
     */
    public List<PizzaTopping> getPizzaTopping(){
        List<PizzaTopping> toppings = new ArrayList<>();
        for(ToppingsCheckBox t: list){
            if(t.isSelected()) {
                toppings.add(t.getTopping());
            }
        }
        return toppings;
    }

    /**
     * Method that returns the amount in cents as formatted amount
     *
     * @param amount amount of cents
     * @return amount of cents in euros.cents
     */
    private String getFormattedAmount(int amount) {
        int cents = amount % 100;
        int euro = (amount - cents) / 100;

        return "$" + euro + "." + cents;
    }

    /**
     * Class for action listener
     */
    public class PriceListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            toppingPrice = 0;

            //converts the selected item from combobox to string
            String size = comboBox.getSelectedItem().toString();


            //gives the correspond price of the pizza size
            switch (size) {
                case "Small":
                    sizePrice = PizzaSize.SMALL.getPrice();
                    break;
                case "Medium":
                    sizePrice = PizzaSize.MEDIUM.getPrice();
                    break;
                case "Large":
                    sizePrice = PizzaSize.LARGE.getPrice();
                    break;
                case "Extra_Large":
                    sizePrice = PizzaSize.EXTRA_LARGE.getPrice();
                    break;
            }

            //add topping price for the selected toppings
            for(ToppingsCheckBox t: list){
                if(t.isSelected()) {
                    toppingPrice += t.getPrice();
                }
            }

            EventQueue.invokeLater(() -> {
                //calculates the total price
                totalPrice = sizePrice + toppingPrice;
                //formatting from cents to euro
                priceText.setText(getFormattedAmount(totalPrice));

            });
        }
    }
}