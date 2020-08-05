package de.hhn.mib.gpi2.blatt3.aufgabe1.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * This class creates the pizza
 * @author ALexandra Stratulat
 * @version 1.1
 */
public class Pizza implements Serializable {

    //price of the pizza
    private int price;
    //size of the pizza
    private PizzaSize size;
    //toppings
    private List<PizzaTopping> pizzaToppings;

    //Constructor
    public Pizza(int price, PizzaSize size, List<PizzaTopping> pizzaToppings) {
        this.price = price;
        this.size = size;
        this.pizzaToppings = pizzaToppings;
    }

    //get and set
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public PizzaSize getSize() {
        return size;
    }

    public void setSize(PizzaSize size) {
        this.size = size;
    }

    public List<PizzaTopping> getToppings() {
        return pizzaToppings;
    }

    public void setToppings(List<PizzaTopping> pizzaToppings) {
        this.pizzaToppings = pizzaToppings;
    }

    /**
     * This method prints the pizza details
     * @return pizza details
     */
    public String print(){
        return "Pizza Size: " + this.size.toString().replace("_", " ") + "\n" +
                "Toppings: " + this.pizzaToppings.toString().replace("[", "").replace("]", "").replace("_", " ") +
                "\n" + "----------------------------------------------------------------------------------" + "\n" +
                "Total price: " + this.price +
                "\n" + "----------------------------------------------------------------------------------";
    }

    //to string
    @Override
    public String toString() {
        return getPrice() + ";" + getSize() + ";" + getToppings();
    }

    //equals and hash code
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizza pizza = (Pizza) o;
        return price == pizza.price &&
                size == pizza.size &&
                Objects.equals(pizzaToppings, pizza.pizzaToppings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, size, pizzaToppings);
    }
}
