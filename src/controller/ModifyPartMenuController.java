package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyPartMenuController implements Initializable {
    Stage stage;
    Parent scene;

    @FXML
    private RadioButton inHouseRB;

    @FXML
    private ToggleGroup tgPartSource;

    @FXML
    private RadioButton outsourcedRB;

    @FXML
    private Label partTypeLabel;

    @FXML
    private TextField partIDTextField;

    @FXML
    private TextField partNameTextField;

    @FXML
    private TextField partInventoryTextField;

    @FXML
    private TextField partPriceCostTextField;

    @FXML
    private TextField partMaxTextField;

    @FXML
    private TextField partMachineIDTextField;

    @FXML
    private TextField partMinTextField;

    @FXML
    void onActionCancelBtn(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionInHouseRB(ActionEvent event) {
        partTypeLabel.setText("Machine ID");
    }

    @FXML
    void onActionOutsourcedRB(ActionEvent event) {
        partTypeLabel.setText("Company Name");
    }

    @FXML
    void onActionSaveModifyPartBtn(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
