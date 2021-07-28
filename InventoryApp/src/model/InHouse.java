package model;

/**
 * Class for In House part
 * @author luisvegerano
 *
 */
public class InHouse extends Part{

    /**
     * Machine ID
     */
    private int machineID;

    /**
     * Constructor for In House Part
     * @param id In House Part ID
     * @param name In House Part Name
     * @param price In House Part Price
     * @param stock In House Part Stock
     * @param min In House Part Stock - Min
     * @param max In House Part Stock - Max
     * @param machineID In House Part Machine ID
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineID) {
        super(id, name, price, stock, min, max);
        this.machineID = machineID;
    }

    /**
     * Gets machine id
     * @return machine id
     */
    public int getMachineID() {
        return machineID;
    }

    /**
     * Sets Machine ID
     * @param machineID
     */
    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
}
