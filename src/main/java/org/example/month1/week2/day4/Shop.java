package org.example.month1.week2.day4;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
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
        LocalDate ld2=LocalDate.of(2021, Month.FEBRUARY,5);

        Order o1=new Order(3001,"Pending",ld1,babyList,c2);
        Order o2=new Order(3002,"Pending",ld2,bookList,c1);
        Order o3=new Order(3002,"Pending",ld1,bookList,c1);

        List<Order> orderList=List.of(o1,o2,o3);

        List<Order> olderOrder=getFebruaryOrder(orderList);
        //Map<Customer,Order> orderMap= new HashMap<>();


        Map<Customer, List<Order>> orderMap=orderList.stream()
                .collect(Collectors.groupingBy(Order::getCustomer));

        Map<Customer,Double> importMap=orderList.stream()
                .collect(Collectors.groupingBy(Order::getCustomer,Collectors.summingDouble(Order::getTotal)));

        List<Product> mostExpensive= prod.stream().sorted(Comparator.comparingDouble(Product::getPrice).reversed())
                .limit(3)
                .collect(Collectors.toList());

        Map<Customer,Double> averageMap=orderList.stream()
                .collect(Collectors.groupingBy(Order::getCustomer,Collectors.averagingDouble(Order::getTotal)));

        Map<Category,Double> CategoryTotal= prod.stream()
                .collect(Collectors.groupingBy(Product::getCategory,
                        Collectors.summingDouble(Product::getPrice)));

        File txtFile=new File("createdFile/products.txt");
        //createFile(txtFile,prod);
        try{
            List<String> lista=FileUtils.readLines(txtFile, Charset.defaultCharset());
            System.out.println(lista);
            System.out.println(transformToProductList(lista));

        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
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
    private static void createFile(File txtFile,List<Product> prod){
        prod.forEach((element)->{
            String text=element.getId()+"@"+
                    element.getName()+"@"+
                    element.getCategory()+"@"+
                    element.getPrice()+"\n";
            try{
                FileUtils.writeStringToFile(txtFile,text, Charset.defaultCharset(),true);
            }catch(IOException ex){
                System.out.println(ex.getMessage());
            }
        });
    }

    private static List<Product>transformToProductList(List<String> lista){
        List<Product> products=new ArrayList<>();
        lista.forEach(element->{
            String[] toArray= element.split("@");
            long id=Long.parseLong(toArray[0]);
            String name=toArray[1];
            Category category=Product.FindCategory(toArray[2]);
            double price=Double.parseDouble(toArray[3]);
            products.add(new Product(id,name,category,price));
        });
        return products;
    }
}
