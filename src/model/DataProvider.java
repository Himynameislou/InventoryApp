package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
* This class holds all of the inventory. Both Parts and Products are included. It also holds methods for editing/adding parts/products to the inventory.
 * @author luisvegerano
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

    /**
     * Initial partID set in order to create other unique IDs.
     */
    private static int partID = 0;

    /**
     * Method Generates new unique ID.
     */
    public static int getUniquePartID()
    {
        return ++partID;
    }
    public static boolean swapPartModifyMenu(Part selectedPart){
        if(allParts.contains(selectedPart)){
            allParts.remove(selectedPart);
            return true;
        } else {
            return false;
        }
    }


    public static boolean swapProductModifyMenu(Product selectedProduct){
        if(allProducts.contains(selectedProduct)){
            allProducts.remove(selectedProduct);
            return true;
        } else {
            return false;
        }
    }

    public static boolean deletePartMain(Part partToDelete){
        if(allParts.contains(partToDelete)){
            allParts.remove(partToDelete);
            return true;
        } else {
            return false;
        }
    }

    public static boolean deleteProduct(Product selectedProdToDel){
        if(allProducts.contains(selectedProdToDel)){
            allProducts.remove(selectedProdToDel);
            return true;
        }else {
            return false;
        }
    }
}
