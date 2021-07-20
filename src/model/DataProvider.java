package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
* This class holds all of the inventory. Both Parts and Products. It also holds methods for editing/adding parts/products to the inventory.
* */
public class DataProvider {
    /**
    Observable list Array for parts
     */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    /**
     * Observable list for FILTERED parts objects
     */
    private static ObservableList<Part> filteredParts = FXCollections.observableArrayList();

    /**
    Observable list Array for products
     */
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
    Method for adding parts
     */
    public static void addPart(Part part)
    {
        allParts.add(part);
    }

    /**
    Method for adding products
     */
    public static void addProduct(Product product)
    {
        allProducts.add(product);
    }

    /**
    Getting all parts
     */
    public static ObservableList<Part> getAllParts()
    {
        return allParts;
    }

    /**
     * Get all filtered part objects
     */
    public static ObservableList<Part> getFilteredParts() {
        return filteredParts;
    }

    /**
    Getting all products
     */
    public static ObservableList<Product> getAllProducts()
    {
        return allProducts;
    }
}
