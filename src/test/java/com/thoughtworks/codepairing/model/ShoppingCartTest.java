package com.thoughtworks.codepairing.model;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class ShoppingCartTest {

    public static final int PRICE = 100;
    public static final String PRODUCT = "Product";

    Customer customer;

    @Before
    public void setUp() throws Exception {
        customer = new Customer("test");
    }

    @Test
    public void shouldCalculatePriceWithNoDiscount() {
        System.out.println("test: calculate PRICE w/ NO discount");
        List<Product> products = asList(new Product(PRICE, "", PRODUCT));
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(100.0, order.getTotalPrice(), 0.0);
        System.out.println("test end: calculate PRICE w/ NO discount");
    }

    @Test
    public void shouldCalculateLoyaltyPointsWithNoDiscount() {
        System.out.println("test: calculate POINTS w/ NO discount");
        List<Product> products = asList(new Product(PRICE, "", PRODUCT));
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(20, order.getLoyaltyPoints());
        System.out.println("test end: calculate POINTS w/ NO discount");
    }

    @Test
    public void shouldCalculatePriceFor10PercentDiscount() {
        System.out.println("test: calculate PRICE w/ 10% discount");
        List<Product> products = asList(new Product(PRICE, "DIS_10_ABCD", PRODUCT));
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(90.0, order.getTotalPrice(), 0.0);
        System.out.println("test end: calculate PRICE w/ 10% discount");
    }

    @Test
    public void shouldCalculateLoyaltyPointsFor10PercentDiscount() {
        System.out.println("test: calculate POINTS w/ 10% discount");
        List<Product> products = asList(new Product(PRICE, "DIS_10_ABCD", PRODUCT));
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(10, order.getLoyaltyPoints());
        System.out.println("test end: calculate POINTS w/ 10% discount");
    }

    @Test
    public void shouldCalculatePriceFor15PercentDiscount() {
        System.out.println("test: calculate PRICE w/ 15% discount");
        List<Product> products = asList(new Product(PRICE, "DIS_15_ABCD", PRODUCT));
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(85.0, order.getTotalPrice(), 0.0);
        System.out.println("test end: calculate PRICE w/ 15% discount");
    }

    @Test
    public void shouldCalculateLoyaltyPointsFor15PercentDiscount() {
        System.out.println("test: calculate POINTS w/ 15% discount");
        List<Product> products = asList(new Product(PRICE, "DIS_15_ABCD", PRODUCT));
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(6, order.getLoyaltyPoints());
        System.out.println("test end: calculate POINTS w/ 15% discount");
    }

    @Test
    public void shouldRemoveExistingItemFromList() {
        System.out.println("test: remove existing item from list");
        List<Product> products = asList(new Product(PRICE, "DIS_15_ABCD", PRODUCT));
        ShoppingCart cart = new ShoppingCart(customer, products);
        cart.removeProduct(PRODUCT);
        Order order = cart.checkout();

        assertEquals(0, order.getLoyaltyPoints());
        System.out.println("test end: remove existing item from list");
    }

//    @Test
//    public void tryRemovingNonExistingItemFromList() {
//        System.out.println("test: try removing non-existing item from list");
//        List<Product> products = asList(new Product(PRICE, "DIS_15_ABCD", PRODUCT));
//        ShoppingCart cart = new ShoppingCart(customer, products);
//        cart.removeProduct("item");
//        Order order = cart.checkout();
//
//        assertEquals(0, order.getLoyaltyPoints());
//        System.out.println("test end: try removing non-existing item from list");
//    }
}
