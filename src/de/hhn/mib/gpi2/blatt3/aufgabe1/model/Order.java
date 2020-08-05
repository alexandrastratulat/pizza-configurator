package de.hhn.mib.gpi2.blatt3.aufgabe1.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * This class creates the order
 * @author ALexandra Stratulat
 * @version 1.0
 */
public class Order implements Serializable {
    //order number
    private long orderId;
    //pizzas ordered
    private List<Pizza> pizzas;

    //Constructor
    public Order(List<Pizza> pizzas) {
        //random for order id
        Random randomGenerator = new Random();
        this.orderId = (1 + randomGenerator.nextInt(8192)) * 1000L;
        this.pizzas = pizzas;
    }

    //get and set
    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    private List<Pizza> getPizzas() {
        return this.pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    //add pizza
    public void addPizza(Pizza pizza){
        this.pizzas.add(pizza);
    }

    //remove pizza
    public void removePizza(Pizza pizza){
        this.pizzas.remove(pizza);
    }

    //to string
    @Override
    public String toString() {
        return getOrderId() + ";" + getPizzas();
    }

    //equals and hash code
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId &&
                Objects.equals(pizzas, order.pizzas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, pizzas);
    }
}
