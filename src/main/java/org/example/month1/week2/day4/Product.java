package org.example.month1.week2.day4;

public class Product {
    private long id;
    private String name;
    private Category category;
    private double price;

    public Product(long id, String name, Category category, double price){
        this.id=id;
        this.name=name;
        this.category=category;
        this.price=price;
    }

    public Category getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Prodotto{" +
                "nome='" + name + '\'' +
                ", categoria=" + category +
                ", prezzo=" + price +"€"+
                '}';
    }
}
