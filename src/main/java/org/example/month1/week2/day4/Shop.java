package org.example.month1.week2.day4;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Shop {
    public static void main(String[] args) {

        Customer c1=new Customer(2001,"Emilio",2);
        Customer c2=new Customer(2002,"Emanuele",1);

        List<Product> prod = getProductList();
        List<Product> bookList= getBooksOver100(prod);
        List<Product> babyList= filterByCategory(prod, Category.Baby).collect(Collectors.toList());
        List<Product> boysList= getBoysAndDiscount(prod);

        LocalDate ld1=LocalDate.now();
        Order o1=new Order(3001,"Pending",ld1,babyList,c2);

        LocalDate ld2=LocalDate.of(2021, Month.FEBRUARY,5);
        Order o2=new Order(3002,"Pending",ld2,bookList,c1);

        List<Order> orderList=List.of(o1,o2);

        List<Order> olderOrder=getFebruaryOrder(orderList);

    }

    private static List<Product> getProductList() {
        Product p1=new Product(1001,"Harry Potter e la pietra filosofale", Category.Book,9.0);
        Product p2=new Product(1002,"Harry Potter e la camera dei segreti - Salcazzo Edition", Category.Book,120.0);
        Product p3=new Product(1003,"T-shirt", Category.Boys,20.0);
        Product p4=new Product(1004,"Jeans", Category.Boys,12.0);
        Product p5=new Product(1005,"Biberon", Category.Baby,5.0);
        Product p6=new Product(1006,"Culla", Category.Baby,200.0);
        Product p7=new Product(1007,"Latte in polvere", Category.Baby,13.0);

        return List.of(p1,p2,p3,p4,p5,p6,p7);
    }
    private static Stream<Product> filterByCategory(List<Product> prod, Category category){
        return prod.stream().filter(element->element.getCategory()==category);
    }
    private static List<Product> getBooksOver100(List<Product> prod){
        return filterByCategory(prod, Category.Book).filter(element->element.getPrice()>100).collect(Collectors.toList());
    }
    private static List<Product> getBoysAndDiscount(List<Product> prod){
        return filterByCategory(prod, Category.Boys).peek(el->el.setPrice(el.getPrice()*0.9)).collect(Collectors.toList());
    }
    private static List<Order> getFebruaryOrder(List<Order> order){
        LocalDate minDate=LocalDate.of(2021,Month.FEBRUARY,1);
        LocalDate maxDate=LocalDate.of(2021,Month.APRIL,1);
        return order.stream().
                    filter(element->{
                        if(element.getOrderDate().isAfter(minDate) && element.getOrderDate().isBefore(maxDate)){
                            return element.getCustomer().getTier() == 2;
                        }
                        return false;
                    }).collect(Collectors.toList());
    }
}
