package de.hhn.mib.gpi2.blatt4.aufgabe1.io;

import de.hhn.mib.gpi2.blatt3.aufgabe1.model.Order;
import de.hhn.mib.gpi2.blatt3.aufgabe1.model.Pizza;
import de.hhn.mib.gpi2.blatt3.aufgabe1.model.PizzaSize;
import de.hhn.mib.gpi2.blatt3.aufgabe1.model.PizzaTopping;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class writes the order in a file csv or bin
 * @author ALexandra Stratulat
 * @version 1.2
 */
public class DataStorage {

    //constant that can have the following values: "binary" or "text"
    private static final String STORAGE_FORMAT = "binary";

    private File fileCSV = new File("bestellung.csv");
    private File fileBin = new File("bestellung.bin");

    private int num;

    /**
     * If the constant equals binary it will call the writBin method,
     * otherwise it calls the writeCSV method
     *
     * @param order order
     */
    public void writeOrder(Order order) {
        if (STORAGE_FORMAT.equals("binary")) {
            writeOrderBin(order);
        } else if (STORAGE_FORMAT.equals("text")) {
            writeOrderCSV(order);
        }
    }

    /**
     * If the constant equals binary it will return the readBin method,
     * otherwise it returns the readCSV method
     *
     * @return readBin or readCSV
     */
    public Order readOrder() {
        if (STORAGE_FORMAT.equals("binary")) {
            return readOrderBin();
        } else if (STORAGE_FORMAT.equals("text")) {
            return readOrderCSV();
        }
        return null;
    }

    /**
     * This method creates the csv write file and writes the order.
     *
     * @param order order
     */
    private void writeOrderCSV(Order order) {

        try {
            if(!fileCSV.exists()){
                boolean success = fileCSV.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(fileCSV, true);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write(order.toString().replace("[", "").replace("]", "").replace(",", ";").replace(" ", ""));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method reads the object from the file
     *
     * @return order
     */
    private Order readOrderCSV() {
        try (FileReader fileReader = new FileReader(fileCSV);
             BufferedReader reader = new BufferedReader(fileReader)) {
            String[] split = reader.readLine().split(";");
            long orderId = Long.parseLong(split[0]);
            int price = Integer.parseInt(split[1]);
            String size = split[2];
            List<String> toppings = new ArrayList<>(Arrays.asList(split).subList(3, split.length));
            return convertStringToOrder(orderId, size, toppings, price);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method creates the bin write file; splits the order and writes the corresponding
     * variables.
     *
     * @param order order
     */
    private void writeOrderBin(Order order) {
        try {
            if(!fileBin.exists()){
                boolean success = fileBin.createNewFile();
            }
            FileOutputStream fileOut = new FileOutputStream(fileBin, true);
            DataOutputStream out = new DataOutputStream(fileOut);
            String[] split = order.toString().replace("[", "").replace("]", "").replace(" ", "").replace(",", ";").split(";");
            out.writeLong(Long.parseLong(split[0]));
            out.writeInt(Integer.parseInt(split[1]));
            out.writeUTF(split[2]);
            num = 0;
            for (int i = 3; i <= split.length-1; i++) {
                    out.writeUTF(split[i]);
                    num++;
            }
        } catch (EOFException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method reads the variables from the file.
     * Then it converts from string to the corespondent enum
     * and creates pizza and order.
     *
     * @return order
     */
    private Order readOrderBin() {
        try (FileInputStream fileIn = new FileInputStream(fileBin);
             DataInputStream in = new DataInputStream(fileIn)) {

            long orderId;
            String size;
            List<String> toppings = new ArrayList<>();
            int price;

            while (true) {
                orderId = in.readLong();
                price = in.readInt();
                size = in.readUTF();
                for (int i = 0; i < num; i++) {
                    toppings.add(in.readUTF());
                }
                return convertStringToOrder(orderId, size, toppings, price);

            }
        } catch (EOFException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method converts a String into an Order object
     * @param orderId order id
     * @param size size
     * @param toppings toppings
     * @param price price
     * @return order
     */
    private Order convertStringToOrder(long orderId, String size, List<String> toppings, int price) {

        PizzaSize pizzaSize = PizzaSize.valueOf(size.toUpperCase());

        PizzaTopping pizzaTopping;
        List<PizzaTopping> toppingList = new ArrayList<>();
        for (String text : toppings) {
            pizzaTopping = PizzaTopping.valueOf(text.toUpperCase());
            toppingList.add(pizzaTopping);
        }

        Pizza pizza = new Pizza(price, pizzaSize, toppingList);

        List<Pizza> pizzas = new ArrayList<>();
        pizzas.add(pizza);

        Order order = new Order(pizzas);
        order.setOrderId(orderId);

        return order;
    }

    public String getMessage(String msg){
        return msg;
    }

}
