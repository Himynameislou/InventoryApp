package model;

/**
 * Class for Outsourced parts
 */
public class OutSourced extends Part{

    /**
     * Company Name
     */
    private String companyName;

    /**
     * Constructor for Outsourced parts
     * @param id Outsourced Part ID
     * @param name Outsourced Part Name
     * @param price Outsourced Part Price
     * @param stock Outsourced Part Stock
     * @param min Outsourced Part Stock - Min
     * @param max Outsourced Part Stock - Max
     * @param companyName Outsourced Part Company Name
     */
    public OutSourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * Gets company name
     * @return company name
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Sets Company Name
     * @param companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
