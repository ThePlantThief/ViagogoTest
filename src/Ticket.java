/**
 * This class describes a tickets. Usually attached to an Event.
 * @see Event
 * @author George Andrews
 * @version 1.0
 */
public class Ticket {
    //Variables
    private float price;

    //Methods

    /**
     * Constructor. Builds the tickets.
     * @param price
     * Price is in US dollars and must be greater than 0.
     */
    public Ticket(float price){
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero");
        } else {
            this.price = price;
        }
    }

    /**
     * @return
     * Returns the price of the ticket.
     */
    public float getPrice() {
        return price;
    }
}
