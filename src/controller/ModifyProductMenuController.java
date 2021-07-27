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
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static model.DataProvider.*;

public class ModifyProductMenuController implements Initializable{
    Stage stage;
    Parent scene;
    /**
     * List of associated parts
     */
    private ObservableList<Part> assParts =FXCollections.observableArrayList();

    @FXML
    private TextField compPartSearch;

    @FXML
    private TextField productIDTextField;

    @FXML
    private TextField productNameTextField;

    @FXML
    private TextField productInventoryTextField;

    @FXML
    private TextField productPriceTextField;

    @FXML
    private TextField productMaxTextField;

    @FXML
    private TextField productMinTextField;

    @FXML
    private TableView<Part> partTableView;

    @FXML
    private TableColumn<Part, Integer> partIDCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, Integer> partInventoryCol;

    @FXML
    private TableColumn<Part, Double> partCostCol;

    @FXML
    private TableView<Part> componentProductParts;

    @FXML
    private TableColumn<Part, Integer> compPartIDCol;

    @FXML
    private TableColumn<Part, String> compPartNameCol;

    @FXML
    private TableColumn<Part, Integer> compPartInventoryCol;

    @FXML
    private TableColumn<Part, Double> compPartPriceCol;

    Product productToModify;
    int selectedProductIndex;

    @FXML
    void onActionAddPartToProduct(ActionEvent event) {
        Part selectAssociatedPart = partTableView.getSelectionModel().getSelectedItem();
        if(selectAssociatedPart == null){
            alertMessageType(1);
        } else {
            assParts.add(selectAssociatedPart);
            componentProductParts.setItems(assParts);
        }
    }

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        Alert warning = new Alert(Alert.AlertType.CONFIRMATION, "Warning, edits are about to be saved. Do you wish to continue?");
        Optional<ButtonType> clickOkToCancel = warning.showAndWait();
        if(clickOkToCancel.isPresent() && clickOkToCancel.get() == ButtonType.OK){
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
        }
    }

    @FXML
    void onActionDeleteAssocPart(ActionEvent event) {
        Part associatedPartToRemove = componentProductParts.getSelectionModel().getSelectedItem();
        if(associatedPartToRemove == null){
            alertMessageType(6);
        } else {
            assParts.remove(associatedPartToRemove);
            componentProductParts.setItems(assParts);
        }
    }

    @FXML
    void onActionSave(ActionEvent event) throws IOException{
        /**
         * For Loop that sets up and assigns unique ID for new parts
         */
        try {
            int id = productToModify.getProductID();

            String productName = productNameTextField.getText();
            int inventory = Integer.parseInt(productInventoryTextField.getText());
            Double productPrice = Double.parseDouble(productPriceTextField.getText());
            int productMax = Integer.parseInt(productMaxTextField.getText());
            int productMin = Integer.parseInt(productMinTextField.getText());
            String compName = null;


            if (productName.isEmpty()) {
                alertMessageType(2);
            } else if ((productMin < 0) || (productMin > productMax)) {
                alertMessageType(3);
            } else if (!((productMin <= inventory) && (productMax >= inventory))) {
                alertMessageType(4);
            } else {
                Product newProduct = new Product(id, productName, productPrice, inventory, productMin, productMax, compName);
                for(Part part: assParts){
                    newProduct.addProductPart(part);
                }
                DataProvider.addProduct(newProduct);
                DataProvider.swapProductModifyMenu(productToModify);
                onActionCancel(event);
            }
        } catch (Exception e){
            alertMessageType(5);
        }
    }



    private void alertMessageType(int alertID) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        switch (alertID) {
            case 1:
                alert.setTitle("Error");
                alert.setHeaderText("Error: Part Addition");
                alert.setContentText("Must select part from parts table");
                alert.showAndWait();
                break;
            case 2:
                alert.setTitle("Error");
                alert.setHeaderText("Error: Product Name");
                alert.setContentText("Product name field cannot remain blank");
                alert.showAndWait();
                break;
            case 3:
                alert.setTitle("Error");
                alert.setHeaderText("Error: Min/Max Value");
                alert.setContentText("Min value must be greater than 0 and less than max value");
                alert.showAndWait();
                break;
            case 4:
                alert.setTitle("Error");
                alert.setHeaderText("Error: Invalid Inventory number");
                alert.setContentText("Inventory must be between min and max values");
                alert.showAndWait();
                break;
            case 5:
                alert.setTitle("Error");
                alert.setHeaderText("Error: Blank fields");
                alert.setContentText("Cannot have blank fields");
                alert.showAndWait();
                break;
            case 6:
                alert.setTitle("Error");
                alert.setHeaderText("Error: Part Removal");
                alert.setContentText("Must select part from associated parts table");
                alert.showAndWait();
                break;
        }
    }



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
        partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        /**
         * Setting associated parts to table view.
         */
        //componentProductParts.setItems(getAssociatedProductParts());
        compPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        compPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        compPartInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        compPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        /**
         * Searching through partTableView without the need of a search button.
         */
        //Wrapping list in a filtered list
        FilteredList<Part> filteredPartList = new FilteredList<>(getAllParts(), b -> true);
        //Setting filter predicate for when filter changes
        compPartSearch.textProperty().addListener((observable, oldValue, newValue) -> {
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
         * Populating all necessary fields for product modification
         */

        productToModify = MainScreenController.getProductSelected();
        assParts = productToModify.getAssociatedProductParts();
        componentProductParts.setItems(assParts);

        productIDTextField.setText(String.valueOf(productToModify.getProductID()));
        productNameTextField.setText(productToModify.getProductName());
        productInventoryTextField.setText(String.valueOf(productToModify.getProductStock()));
        productPriceTextField.setText(String.valueOf(productToModify.getProductPrice()));
        productMaxTextField.setText(String.valueOf(productToModify.getProductMax()));
        productMinTextField.setText(String.valueOf(productToModify.getProductMin()));
    }
}
