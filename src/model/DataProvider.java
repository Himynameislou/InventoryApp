package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/*
* This class holds all of the inventory. Both Parts and Products. It also holds methods for editing/adding parts/products to the inventory.
* */
public class DataProvider {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
}
