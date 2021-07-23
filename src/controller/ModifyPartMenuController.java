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
import model.Part;

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

    private Part partToModify;

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
        try {
            int id = partToModify.getId();
            String partName = partNameTextField.getText();
            int inventory = Integer.parseInt(partInventoryTextField.getText());
            Double partPrice = Double.parseDouble(partPriceCostTextField.getText());
            int partMax = Integer.parseInt(partMaxTextField.getText());
            int partMin = Integer.parseInt(partMinTextField.getText());
            int partMachineID;
            String compName;

            boolean partAdded = false;

            if (partName.isEmpty()) {
                alertMessageType(1);
            } else if ((partMin < 0) || (partMin > partMax)) {
                alertMessageType(3);
            } else if (!((partMin <= inventory) && (partMax >= inventory))) {
                alertMessageType(2);
            } else {


                try{
                    if (inHouseRB.isSelected()) {
                        partMachineID = Integer.parseInt(partMachineIDTextField.getText());
                        InHouse newInHousePartAdd = new InHouse(id, partName, partPrice, inventory, partMin, partMax, partMachineID);
                        //newInHousePartAdd.setId(DataProvider.getUniquePartID());
                        DataProvider.addPart(newInHousePartAdd);
                        partAdded = true;
                    }
                }catch (Exception e){
                    alertMessageType(6);
                }

                if (outsourcedRB.isSelected()) {

                    compName = partMachineIDTextField.getText();
                    OutSourced newOutSourcePartAdd = new OutSourced(id, partName, partPrice, inventory, partMin, partMax, compName);
                    //newOutSourcePartAdd.setId(DataProvider.getUniquePartID());
                    DataProvider.addPart(newOutSourcePartAdd);
                    partAdded = true;
                }

                if (partAdded) {
                    DataProvider.swapPartModifyMenu(partToModify);
                    onActionCancelBtn(event);
                }
            }
        } catch (Exception e){
            alertMessageType(5);
        }
    }



    private void alertMessageType(int alertID) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        switch (alertID)
        {
            case 1:
                alert.setTitle("Error");
                alert.setHeaderText("Error: Part Modify");
                alert.setContentText("Cannot have blank name field");
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
                alert.setHeaderText("Error: Min/Max Value");
                alert.setContentText("Min value must be greater than 0 and less than max value");
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
                alert.setHeaderText("Error: Machine ID");
                alert.setContentText("Invalid entry. Must be integers only");
                alert.showAndWait();
                break;

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /**
         * Populating fields with selected item from main screen.
         */
        partToModify = MainScreenController.selectPartModify();

        if(partToModify instanceof InHouse){
            inHouseRB.setSelected(true);
            partTypeLabel.setText("Machine ID");
            partMachineIDTextField.setText(String.valueOf(((InHouse) partToModify).getMachineID()));
        }
        if(partToModify instanceof OutSourced){
            outsourcedRB.setSelected(true);
            partTypeLabel.setText("Company Name");
            partMachineIDTextField.setText(((OutSourced) partToModify).getCompanyName());
        }

        partIDTextField.setText(String.valueOf(partToModify.getId()));
        partNameTextField.setText(partToModify.getName());
        partInventoryTextField.setText(String.valueOf(partToModify.getStock()));
        partPriceCostTextField.setText(String.valueOf(partToModify.getPrice()));
        partMaxTextField.setText(String.valueOf(partToModify.getMax()));
        partMinTextField.setText(String.valueOf(partToModify.getMin()));

    }
}
