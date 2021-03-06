package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.DataProvider;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.Optional;
import java.util.ResourceBundle;

import static model.DataProvider.*;

/**
 * Controller class that users interact with in order to Add, Modify, or Delete Parts and Products.
 * Run time error occurs if no part or product is selected.
 * @author luisvegerano
 */
public class MainScreenController implements Initializable {
    Stage stage;
    Parent scene;

    /**
     * Text field for part search
     */
    @FXML
    private TextField partSearchField;

    /**
     * Parts TableView
     */
    @FXML
    private TableView<Part> partTableView;

    /**
     * Id column for partTableView
     */
    @FXML
    private TableColumn<Part, Integer> partIDCol;

    /**
     * Name column for partTableView
     */
    @FXML
    private TableColumn<Part, String> partNameCol;

    /**
     * Inventory column for partTableView
     */
    @FXML
    private TableColumn<Part, Integer> partInvCol;

    /**
     * Cost column for partTableView
     */
    @FXML
    private TableColumn<Part, Double> partCostCol;

    /**
     * Text field for product search
     */
    @FXML
    private TextField productSearchField;

    /**
     * Product tableView
     */
    @FXML
    private TableView<Product> productTableView;

    /**
     * Id column for productTableView
     */
    @FXML
    private TableColumn<Product, Integer> productIDCol;

    /**
     * Name column for productTableView
     */
    @FXML
    private TableColumn<Product, String> productNameCol;

    /**
     * Inventory column for productTableView
     */
    @FXML
    private TableColumn<Product, Integer> productInvCol;

    /**
     * Price column for productTableView
     */
    @FXML
    private TableColumn<Product, Double> productPriceCol;

    /**
     * Part object created to allow for clicking of part in tableView
     */
    private static Part partSelected;

    /**
     * Product object created to allow for clicking of product in tableView
     */
    private static Product productSelected;

    /**
     * Gets the product selected by user
     * @return a Product object
     */
    public static Product getProductSelected() {
        return productSelected;
    }

    /**
     * Loads Add Part enu
     * Add method to add new part to array and tableView
     * @param event Add button clicked
     * @throws IOException FXMLLoader
     */
    @FXML
    void onActionAddPart(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddPartMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Loads Add Product menu
     * Add method to add new product to array and tableView
     * @param event Add product button clicked
     * @throws IOException FXMLLoader
     */
    @FXML
    void onActionAddProduct(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddProductMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Method deletes part from array and tableView
     * Warns user if no part is selected and confirms if a part should be deleted
     * @param event Delete part button clicked
     */
    @FXML
    void onActionDeletePart(ActionEvent event) {
        partSelected = partTableView.getSelectionModel().getSelectedItem();
        if(partSelected == null) {
            alertMessageType(1);
        } else {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Warning");
            confirmationAlert.setHeaderText("Warning: Part Deletion");
            confirmationAlert.setContentText("Are you sure you want to delete selected part?");
            Optional<ButtonType> cancelOption = confirmationAlert.showAndWait();

            if(cancelOption.isPresent() && cancelOption.get() == ButtonType.OK){
            DataProvider.deletePartMain(selectPartModify());
            }
        }
    }

    /**
     * Method deletes product from array and tableView
     * Warns user that a product has associated parts that need to be removed first before deleting product
     * @param event Delete product button clicked
     */
    @FXML
    void onActionDeleteProduct(ActionEvent event) {
        Product selectProductToDelete = productTableView.getSelectionModel().getSelectedItem();
        if(selectProductToDelete == null){
            alertMessageType(2);
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Do you want to delete the selected product?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                ObservableList<Part> assParts = selectProductToDelete.getAssociatedProductParts();
                if(assParts.size() >=1){
                    alertMessageType(3);
                } else {
                    DataProvider.deleteProduct(selectProductToDelete);
                }
            }
        }
    }

    /**
     * Method shuts down application
     * @param event Exit button clicked
     */
    @FXML
    void onActionExitApp(ActionEvent event) {
        System.exit(0);
    }

    /**
     * Loads Modify Part menu
     * If no part is selected a warning is sent to the user.
     * @param event Modify button clicked
     * @throws IOException FXMLLoader
     */
    @FXML
    void onActionModifyPart(ActionEvent event) throws IOException {
        partSelected = partTableView.getSelectionModel().getSelectedItem();
        if(partSelected == null) {
            alertMessageType(1);
        } else {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/ModifyPartMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /**
     * Loads Modify Product menu
     * If no product is selected a warning is sent to the user.
     * @param event Modify product clicked
     * @throws IOException FXMLLoader
     */
    @FXML
    void onActionModifyProduct(ActionEvent event) throws IOException {
        productSelected = productTableView.getSelectionModel().getSelectedItem();
        if (productSelected == null){
            alertMessageType(2);
        } else{
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ModifyProductMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();}
    }

    /**
     * Part object created to help with selection methods
     * @return partSelected
     */
    public static Part selectPartModify(){
        return partSelected;
    }

    /**
     * Method is used to call different warnings and alerts for user to confirm changes
     * @param alertID Message type selector
     */
    public static void alertMessageType(int alertID) {
        Alert alert = new Alert(Alert.AlertType.ERROR);


        switch (alertID)
        {
            case 1:
                alert.setTitle("Error");
                alert.setHeaderText("Error: Part Addition");
                alert.setContentText("Part must be selected");
                alert.showAndWait();
                break;
            case 2:
                alert.setTitle("Error");
                alert.setHeaderText("Error: Product Modification");
                alert.setContentText("Product must be selected");
                alert.showAndWait();
                break;
            case 3:
                alert.setTitle("Error");
                alert.setHeaderText("Error: Parts Associated");
                alert.setContentText("All associated parts parts must be removed first before product can be deleted");
                alert.showAndWait();
                break;
        }
    }





    /**
     * Initializes controller
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        /**
        Setting Cells for Parts TableView
         */
        partTableView.setItems(DataProvider.getAllParts());

        /**
        Setting column and row data for parts
         */
        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        /**
         * Searching through partTableView without the need of a search button.
         */

        //Wrapping list in a filtered list
        FilteredList<Part> filteredPartList = new FilteredList<>(getAllParts(), b -> true);
        //Setting filter predicate for when filter changes
        partSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredPartList.setPredicate(part -> {


                //if text field is empty then display all data
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (part.getName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                }
                else if (String.valueOf(part.getId()).indexOf(lowerCaseFilter)!=-1)
                    return true;
                else
                    return false; // Does not match.

            });
        });
        //wrapping filtered list in a sorted list
        SortedList<Part> sortedPartData = new SortedList<>(filteredPartList);
        //Bind the SortedList comparator to the TableView comparator. So that sorting fully works
        sortedPartData.comparatorProperty().bind(partTableView.comparatorProperty());
        //Adding sorted and filtered data to the table.
        partTableView.setItems(sortedPartData);



        /**
         * Setting Cells for Products TableView
         */
        productTableView.setItems(DataProvider.getAllProducts());

        /**
         * Setting column and row data for products
         */
        productIDCol.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
        productInvCol.setCellValueFactory(new PropertyValueFactory<>("productStock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("productPrice"));

        /**
         * Searching through partTableView without the need of a search button.
         */
        //Wrapping list in a filtered list
        FilteredList<Product> filteredProductList = new FilteredList<>(getAllProducts(), b -> true);
        //Setting filter predicate for when filter changes
        productSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredProductList.setPredicate(product -> {
                //if text field is empty then display all data
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (product.getProductName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                }
                else if (String.valueOf(product.getProductID()).indexOf(lowerCaseFilter)!=-1)
                    return true;
                else
                    return false; // Does not match.

            });
        });
        //wrapping filtered list in a sorted list
        SortedList<Product> sortedProductData = new SortedList<>(filteredProductList);
        //Bind the SortedList comparator to the TableView comparator. So that sorting fully works
        sortedProductData.comparatorProperty().bind(productTableView.comparatorProperty());
        //Adding sorted and filtered data to the table.
        productTableView.setItems(sortedProductData);
    }
}
