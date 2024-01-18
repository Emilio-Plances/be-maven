package org.example.month1.week2.day4;

public class Customer {
    private long id;
    private String name;
    private int tier;

    public Customer(long id,String name,int tier){
        this.id=id;
        this.name=name;
        this.tier=tier;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", tier=" + tier +
                '}';
    }

    public int getTier() {
        return tier;
    }
}
