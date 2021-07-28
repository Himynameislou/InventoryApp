package model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** Class for Product
 *
 * @author Luis Vegerano
 */
public class Product {
    private int productID;
    private String productName;
    private double productPrice;
    private int productStock;
    private int productMin;
    private int productMax;
    private String productCompanyName;

    /**
     * Constructor for Product
     * @param productID Product ID
     * @param productName Product Name
     * @param productPrice Product Price
     * @param productStock Product Stock
     * @param productMin Product Stock - Min
     * @param productMax Product Stock - Max
     * @param productCompanyName Product Company Name
     */
    public Product(int productID, String productName, double productPrice, int productStock, int productMin, int productMax, String productCompanyName) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.productMin = productMin;
        this.productMax = productMax;
        this.productCompanyName = productCompanyName;
    }
    /**
     * Observable list for associated product parts
     */
    private ObservableList<Part> associatedProductParts = FXCollections.observableArrayList();

    /**
     * Adding associated parts to product
     */
    public void addProductPart(Part part){
        associatedProductParts.add(part);
    }
    /**
     * Creates a list of all product parts
     */
    public ObservableList<Part> getAssociatedProductParts() {
        return associatedProductParts;
    }
    /**Gets Product ID
     * @return the productID
     */
    public int getProductID() {
        return productID;
    }

    /**Sets Product ID
     * @param productID the id to set
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }

    /**gets Product Name
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**Sets Product Name
     * @param productName the name to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**Gets Product Price
     * @return the productPrice
     */
    public double getProductPrice() {
        return productPrice;
    }

    /** Sets Product Price
     * @param productPrice the price to set
     */
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    /**Get Products Stock
     * @return the productStock
     */
    public int getProductStock() {
        return productStock;
    }

    /** Sets Product Stock
     * @param productStock the stock to set
     */
    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }

    /**Gets Product Min Stock
     * @return the min
     */
    public int getProductMin() {
        return productMin;
    }

    /** Sets Product Min Stock
     * @param productMin the min to set
     */
    public void setProductMin(int productMin) {
        this.productMin = productMin;
    }

    /**Gets product Max Stcok
     * @return the max
     */
    public int getProductMax() {
        return productMax;
    }

    /**Sets Product Min Stock
     * @param productMax the max to set
     */
    public void setProductMax(int productMax) {
        this.productMax = productMax;
    }

    /**Gets Product Company Name
    * @return productCompanyName
     */
    public String getProductCompanyName() {
        return productCompanyName;
    }

    /**Sets the Product Company Name
     *
     * @param productCompanyName
     */
    public void setProductCompanyName(String productCompanyName) {
        this.productCompanyName = productCompanyName;
    }


}
