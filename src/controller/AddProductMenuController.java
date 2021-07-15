package controller;

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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    private TableView<?> partTableView;

    @FXML
    private TableColumn<?, ?> partIDCol;

    @FXML
    private TableColumn<?, ?> partNameCol;

    @FXML
    private TableColumn<?, ?> partInventoryCol;

    @FXML
    private TableColumn<?, ?> partCostCol;

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

    }
}
