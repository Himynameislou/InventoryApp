package controller;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.DataProvider;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static model.DataProvider.*;

public class MainScreenController implements Initializable {
    Stage stage;
    Parent scene;

    @FXML
    private TextField partSearchField;

    @FXML
    private TableView<Part> partTableView;

    @FXML
    private TableColumn<Part, Integer> partIDCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, Integer> partInvCol;

    @FXML
    private TableColumn<Part, Double> partCostCol;

    @FXML
    private TextField productSearchField;

    @FXML
    private TableView<Product> productTableView;

    @FXML
    private TableColumn<Product, Integer> productIDCol;

    @FXML
    private TableColumn<Product, String> productNameCol;

    @FXML
    private TableColumn<Product, Integer> productInvCol;

    @FXML
    private TableColumn<Product, Double> productPriceCol;

    @FXML
    void onActionAddPart(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddPartMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionAddProduct(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddProductMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionDeletePart(ActionEvent event) {

    }

    @FXML
    void onActionDeleteProduct(ActionEvent event) {

    }

    @FXML
    void onActionExitApp(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void onActionModifyPart(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ModifyPartMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionModifyProduct(ActionEvent event) {

    }

    /**
     * Next few methods deal with adding, updating, removing objects as well as searching.
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
