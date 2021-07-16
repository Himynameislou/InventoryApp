package MainApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.InHouse;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/MainScreen.fxml"));
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(new Scene(root, 1000, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        InHouse keyboardPart1 = new InHouse(1,"Key Caps",30.00,12,4,15,101);
        InHouse keyboardPart2 = new InHouse(2,"Switches",24.99,10,4,40,102);
        InHouse keyboardPart3 = new InHouse(3,"Case",50.99,5,2,10,103);
        InHouse keyboardPart4 = new InHouse(4,"Plate",20.33,2,1,10,104);
        InHouse keyboardPart5 = new InHouse(5,"PCB",75.00,5,4,15,105);

        launch(args);
    }


}
