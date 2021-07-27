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
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     * @param machineID
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
