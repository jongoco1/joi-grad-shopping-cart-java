package com.thoughtworks.codepairing.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCart {
    private List<Product> products;
    private Customer customer;

    public ShoppingCart(Customer customer, List<Product> products) {
        this.customer = customer;
        this.products = products;
    }

    public List<Product> getProducts() { return products; }
    public Customer getCustomer() { return customer; }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> removeProduct(String name) {
        int index = 0;
        List<Product> revisedList = new ArrayList<Product>(products);
        for (Product product : products) {
            // case: item does NOT exist in shopping cart
            if (index >= products.size()) {
                System.out.println("Item " + name + " no found in shopping cart");
            }
            // case: item exists in shopping cart
            if (product.getName().equals(name)) {
                revisedList.remove(index);
                System.out.println("Item " + name + " removed shopping cart");
            }
            else { index++; }
        }
        products = revisedList;
        return revisedList;
    }

    public Order checkout() {
        double totalPrice = 0;

        int loyaltyPointsEarned = 0;
        System.out.println("In ShoppingCart.checkout(),\n"
            + "products array size: " + products.size());
        for (Product product : products) {
            double discount = 0;
            if (product.getProductCode().startsWith("DIS_10")) {
                discount = (product.getPrice() * 0.1);
                loyaltyPointsEarned += (product.getPrice() / 10);
            } else if (product.getProductCode().startsWith("DIS_15")) {
                discount = (product.getPrice() * 0.15);
                loyaltyPointsEarned += (product.getPrice() / 15);
            } else {
                loyaltyPointsEarned += (product.getPrice() / 5);
            }

            totalPrice += product.getPrice() - discount;
        }

        return new Order(totalPrice, loyaltyPointsEarned);
    }

    @Override
    public String toString() {
        return "Customer: " + customer.getName() + "\n" + "Bought:  \n" + products.stream().map(p -> "- " + p.getName()+ ", "+p.getPrice()).collect(Collectors.joining("\n"));
    }
}
