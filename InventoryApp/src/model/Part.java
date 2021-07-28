package model;
/**
* Supplied class Part.java 
 */

/**
 * Class for Parts that extends to In House and Outsourced parts
 * @author Luis Vegerano
 */
public abstract class Part {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     * Constructor for Part
     * @param id Part ID
     * @param name Part Name
     * @param price Part Price
     * @param stock Part Stock
     * @param min Part Stock - Min
     * @param max Part Stock - Max
     */
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**Gets Part ID
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**Sets the Part ID
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**Gets the Part Name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**Sets the Part Name
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**Gets the Part Price
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**Sets the Part Price
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    /**Gets the Part Stock
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**Sets the Part Stock
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**Gets the Part Stock Min
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**Sets the Part Stock Min
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**Gets the Part Stock Max
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**Sets the Part Stock Max
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }
    
}