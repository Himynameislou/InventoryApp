package controller;

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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static model.DataProvider.*;

public class AddProductMenuController implements Initializable {
    Stage stage;
    Parent scene;

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
    private TableView<?> componentProductParts;

    @FXML
    private TableColumn<?, ?> compPartIDCol;

    @FXML
    private TableColumn<?, ?> compPartNameCol;

    @FXML
    private TableColumn<?, ?> compPartInventoryCol;

    @FXML
    private TableColumn<?, ?> compPartPriceCol;

    @FXML
    void onActionAddPartToProduct(ActionEvent event) {

    }

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionDeleteAssocPart(ActionEvent event) {

    }

    @FXML
    void onActionSave(ActionEvent event) {

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
