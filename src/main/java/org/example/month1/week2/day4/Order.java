package org.example.month1.week2.day4;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private long id;
    private String status;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private List<Product> products;
    private Customer customer;

    public Order(long id, String status, LocalDate orderDate, List<Product> products, Customer customer){
        this.id=id;
        this.status=status;
        this.orderDate=orderDate;
        this.deliveryDate=orderDate.plusDays(2);
        this.products=products;
        this.customer=customer;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", orderDate=" + orderDate +
                ", deliveryDate=" + deliveryDate +
                ", products=" + products +
                ", customer=" + customer +
                '}';
    }
}
