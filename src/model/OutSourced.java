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
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     * @param companyName
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
