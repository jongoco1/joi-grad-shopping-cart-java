package com.thoughtworks.codepairing.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;

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

    // function: removeProduct
    // purpose: remove an item from the shopping cart
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

    // function: X for V sales (X=number of an item, V=value of one set of an item)
    // purpose: helper function to allow for a different type of sale (i.e. BOGO, etc.)
    public Order setSales(int numItemsInSet, int numFreeItems) {
        Map<String, Integer> counter = new HashMap<>();
        for (Product product : products) {
            // parse thru shopping cart
            // if item does not exist in hashmap, create new key w product name & value=1
            // else add 1 to the current value held by key
            String prodName = product.getName();
            if (counter.containsKey(prodName)) {
                counter.put(prodName, counter.get(prodName) + 1);
            }
            else {
                counter.put(prodName, 1);
            }
        }
        // calculate price based on numItemsInSet and numFreeItems
        // use division and mod operators
        // case: cannot be combined with any other offers
        // case: combined with other offers (much more difficult)

        // placeholder
        return null;
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
