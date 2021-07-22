package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.DataProvider;
import model.InHouse;
import model.OutSourced;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddPartMenuController implements Initializable {
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
    void onActionSavePartBtn(ActionEvent event) throws IOException{
        int id = 0;

        String partName = partNameTextField.getText();

        int inventory = Integer.parseInt(partInventoryTextField.getText());
        String inventoryCheck = partInventoryTextField.getText();

        Double partPrice = Double.parseDouble(partPriceCostTextField.getText());
        String priceCheck = partPriceCostTextField.getText();

        int partMax = Integer.parseInt(partMaxTextField.getText());
        String maxCheck = partMaxTextField.getText();

        int partMin = Integer.parseInt(partMinTextField.getText());
        String minCheck = partMinTextField.getText();

        int partMachineID;
        String machineCheck = partMachineIDTextField.getText();

        String compName;

        boolean partAdded = false;

        if(partName.isEmpty() || inventoryCheck.isEmpty() || priceCheck.isEmpty() || maxCheck.isEmpty() || minCheck.isEmpty() || machineCheck.isEmpty())
        {
            alertMessage(1);
        } else
        {
            if((partMin < 0) || (partMin > partMax)){
                alertMessage(2);
            }
        } else{
            if((partMin < partMax) && (partMin <= inventory) && (inventory <= partMax)){
                if(inHouseRB.isSelected()){
                    partMachineID = Integer.parseInt(partMachineIDTextField.getText());
                    InHouse newInHousePartAdd = new InHouse(id, partName, partPrice, inventory, partMin, partMax, partMachineID);
                    newInHousePartAdd.setId(DataProvider.getUniquePartID());
                    DataProvider.addPart(newInHousePartAdd);
                    partAdded = true;
                }
                if(outsourcedRB.isSelected()){
                    compName = partMachineIDTextField.getText();
                    OutSourced newOutSourcePartAdd = new OutSourced(id, partName, partPrice, inventory, partMin, partMax, compName);
                    newOutSourcePartAdd.setId(DataProvider.getUniquePartID());
                    DataProvider.addPart(newOutSourcePartAdd);
                    partAdded = true;
                }
                if(partAdded){
                    onActionCancelBtn(event);
                }
        }
    }


    private void alertMessage(int alertID){
        Alert alert = new Alert(Alert.AlertType.ERROR);

        switch (alertID)
        {
            case 1:
                alert.setTitle("Error");
                alert.setHeaderText("Error: Part Addition");
                alert.setContentText("Cannot have blank fields");
                alert.showAndWait();
                break;
            case 2:
                alert.setTitle("Error");
                alert.setHeaderText("Error: Invalid Inventory number");
                alert.setContentText("Inventory must be between min and max values");
                alert.showAndWait();
                break;
            case 3:
                alert.setTitle("Error");
                alert.setHeaderText("Error: Min Value");
                alert.setContentText("Min value must be greater than 0 and less than max value");
                alert.showAndWait();
                break;
            case 5:
                alert.setTitle("Error");
                alert.setHeaderText("Error: Max Value");
                alert.setContentText("Max value must be greater than Inventory value and min value");
                alert.showAndWait();
                break;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
