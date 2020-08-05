package de.hhn.mib.gpi2.blatt3.aufgabe1.model;

/**
 * Enum with toppings of the pizza
 * @author ALexandra Stratulat
 * @version 1.0
 */
public enum PizzaTopping {
    TOMATO("Tomato", 50),
    CHEESE("Cheese", 50),
    SALAMI("Salami", 50),
    HAM("Ham", 50),
    ANANAS("Ananas", 50),
    VEGETABLES("Vegetables", 50),
    SEAFOOD("Seafood", 50),
    NUTELLA("Nutella", 50),
    SOUR_CREAM("Sour_Cream", 50);

    private String toppings;
    private int price;

    PizzaTopping(String toppings, int price) {
        this.toppings = toppings;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return toppings;
    }
}
