package model;
/**
* Supplied class Part.java 
 */

/**
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
     * @return the productID
     */
    public int getProductID() {
        return productID;
    }

    /**
     * @param productID the id to set
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the name to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the productPrice
     */
    public double getProductPrice() {
        return productPrice;
    }

    /**
     * @param productPrice the price to set
     */
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * @return the productStock
     */
    public int getProductStock() {
        return productStock;
    }

    /**
     * @param productStock the stock to set
     */
    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }

    /**
     * @return the min
     */
    public int getProductMin() {
        return productMin;
    }

    /**
     * @param productMin the min to set
     */
    public void setProductMin(int productMin) {
        this.productMin = productMin;
    }

    /**
     * @return the max
     */
    public int getProductMax() {
        return productMax;
    }

    /**
     * @param productMax the max to set
     */
    public void setProductMax(int productMax) {
        this.productMax = productMax;
    }

    /**
    * @return productCompanyName
     */
    public String getProductCompanyName() {
        return productCompanyName;
    }

    /**
     *
     * @param productCompanyName
     */
    public void setProductCompanyName(String productCompanyName) {
        this.productCompanyName = productCompanyName;
    }
}
