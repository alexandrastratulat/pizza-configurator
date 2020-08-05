package de.hhn.mib.gpi2.blatt3.aufgabe1.model;

/**
 * Enum with sizes of the pizza
 * @author ALexandra Stratulat
 * @version 1.0
 */
public enum PizzaSize {
    SMALL("Small", 500),
    MEDIUM("Medium", 600),
    LARGE("Large", 800),
    EXTRA_LARGE("Extra_Large", 1100);

    private String size;
    private int price;

    PizzaSize(String size, int price) {
        this.size = size;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return size;
    }
}
        

