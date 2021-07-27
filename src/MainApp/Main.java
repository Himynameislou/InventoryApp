package MainApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DataProvider;
import model.InHouse;
import model.OutSourced;
import model.Product;

/**
 * Inventory Management application allows user to create a list of parts and products. Products also have associated parts within them.
 *
 * A future feature that would be helpful would be the ability to re-index the part/product IDs.
 *
 * @author luisvegerano
 */

/**
 * JavaDoc located in folder JavaDoc within the InventoryApp project folder. InventoryApp>JavaDoc
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/MainScreen.fxml"));
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(new Scene(root, 1000, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        /**
        Pre-populated part (InHouse & Outsourced) objects for App.
         */
        InHouse keyboardPart1 = new InHouse(1,"Key Caps",30.00,12,4,15,101);
        InHouse keyboardPart2 = new InHouse(2,"Switches",24.99,10,4,40,102);
        InHouse keyboardPart3 = new InHouse(3,"Case",50.99,5,2,10,103);
        InHouse keyboardPart4 = new InHouse(4,"PCB",75.00,5,4,15,105);
        OutSourced keyboardPart5 = new OutSourced(5,"Artisan Caps",79.99,3,2,5,"Drop");

        /**
        Pre-populated Products for App
         */
        Product kB1 = new Product(1,"K2",79.99,5,3,5,"Keychron");
        Product kB2 = new Product(2,"K4",79.99,5,3,5,"Keychron");
        Product kB3 = new Product(3,"ALT",149.00,2,1,3,"Drop");
        Product kB4 = new Product(4,"G413",69.99,4,2,6,"Logitech");


        /**
        Adding parts to Observable List via DataProvider class and addPart static method
         */
        DataProvider.addPart(keyboardPart1);
        DataProvider.addPart(keyboardPart2);
        DataProvider.addPart(keyboardPart3);
        DataProvider.addPart(keyboardPart4);
        DataProvider.addPart(keyboardPart5);

        /**
         * Adding products to Observable List via DataProvider class and addProduct static method
         */
        DataProvider.addProduct(kB1);
        DataProvider.addProduct(kB2);
        DataProvider.addProduct(kB3);
        DataProvider.addProduct(kB4);

        launch(args);
    }
}
