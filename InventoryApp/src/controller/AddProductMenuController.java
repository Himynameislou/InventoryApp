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

/**
 * Controller class that is responsible for all new products added to inventory. Gives user functionality to add product name, inventory, price, max, and min.
 * @author luisvegerano
 */
public class AddProductMenuController implements Initializable {
    Stage stage;
    Parent scene;
    /**
     * List of associated parts
     */
    private ObservableList<Part> assParts =FXCollections.observableArrayList();

    /**
     * Component Part Search Text Field
     */
    @FXML
    private TextField compPartSearch;

    /**
     * Product ID Text Field
     */
    @FXML
    private TextField productIDTextField;

    /**
     * Product Name Text Field
     */
    @FXML
    private TextField productNameTextField;

    /**
     * Product Inventory Text Field
     */
    @FXML
    private TextField productInventoryTextField;

    /**
     * Product Price Text Field
     */
    @FXML
    private TextField productPriceTextField;

    /**
     * Product Inventory Text Field - Max
     */
    @FXML
    private TextField productMaxTextField;

    /**
     * Product Inventory Text Field - Min
     */
    @FXML
    private TextField productMinTextField;

    /**
     * Part TableView
     */
    @FXML
    private TableView<Part> partTableView;

    /**
     * Part ID Column
     */
    @FXML
    private TableColumn<Part, Integer> partIDCol;

    /**
     * Part Name Column
     */
    @FXML
    private TableColumn<Part, String> partNameCol;

    /**
     * Part Inventory Column
     */
    @FXML
    private TableColumn<Part, Integer> partInventoryCol;

    /**
     * Part Cost Column
     */
    @FXML
    private TableColumn<Part, Double> partCostCol;

    /**
     * Associated Part TableView
     */
    @FXML
    private TableView<Part> componentProductParts;

    /**
     * Associated Part ID Column
     */
    @FXML
    private TableColumn<Part, Integer> compPartIDCol;

    /**
     * Associated Part Name Column
     */
    @FXML
    private TableColumn<Part, String> compPartNameCol;

    /**
     * Associated Part Inventory Column
     */
    @FXML
    private TableColumn<Part, Integer> compPartInventoryCol;

    /**
     * Associated Part Price Column
     */
    @FXML
    private TableColumn<Part, Double> compPartPriceCol;

    /**
     * Adds selected part from Parts TableView and adds it to the Associated Parts TableView
     * @param event Add Button Clicked
     */
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

    /**
     * Cancels out of Add Product Menu
     * @param event Cancel Button Clicked
     * @throws IOException FXMLLoader
     */
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Removes associated part from associated parts tableview. Warns user about removing items.
     * @param event Remove Associated Part Button clicked
     */
    @FXML
    void onActionDeleteAssocPart(ActionEvent event) {
        Part associatedPartToRemove = componentProductParts.getSelectionModel().getSelectedItem();
        if(associatedPartToRemove == null){
            alertMessageType(6);
        }else if (associatedPartToRemove != null){
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Warning");
            confirmationAlert.setHeaderText("Warning: Part Removal");
            confirmationAlert.setContentText("Are you sure you want to remove associated part?");
            Optional<ButtonType> removeOption = confirmationAlert.showAndWait();

            if(removeOption.isPresent() && removeOption.get() == ButtonType.OK){
                assParts.remove(associatedPartToRemove);
                componentProductParts.setItems(assParts);
            }
        }
    }

    /**
     * Saves new Product and associated parts to array then populates product tableview on main screen
     * @param event Save Button Clicked
     * @throws IOException FXMLLoader
     */
    @FXML
    void onActionSave(ActionEvent event) throws IOException{
        /**
         * For Loop that sets up and assigns unique ID for new parts
         */
        try {
            int id = 0;
            for (Product product : DataProvider.getAllProducts()) {
                if (product.getProductID() > id)
                    id = (product.getProductID());
                id = ++id;
            }

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
                onActionCancel(event);
            }
        } catch (Exception e){
            alertMessageType(5);
        }
    }

    /**
     * Method is used to call different warnings and alerts for user to confirm changes
     * @param alertID Message Type Selector
     */
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
                alert.setContentText("Cannot have blank or invalid fields");
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

    /**
     * Initializes Controller.
     * Parts and Associated Parts populated in TableViews. Search field set up to work here.
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

    }
}
