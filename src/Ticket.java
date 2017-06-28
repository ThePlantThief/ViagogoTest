import java.util.Hashtable;

/**
 * This class describes a ticket. Tickets are attached to one event,
 * and have a price, a unique id and can be sold.
 * @see Event
 * @author George Andrews
 * @version 1.0
 */
public class Ticket {
    //Variables
    private static int nextId = 0;
    private static final Hashtable<Integer, Ticket> allTickets = new Hashtable<>();
    private int id;
    private Event event;
    private float price;
    private boolean sold;

    //Methods

    /**
     * Constructor. Builds the tickets.
     * @param price
     * Price is in US dollars and must be greater than 0.
     */
    public Ticket(float price, Event event) {
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero");
        } else {
            id = nextId;
            nextId++;
            sold = false;
            this.price = price;
            this.event = event;
            event.addTicket(this);
            allTickets.put(id, this);
        }
    }

    /**
     * @return
     * Returns the price of the ticket.
     */
    public float getPrice() {
        return price;
    }

    /**
     * @return Whether or not the ticket has been sold.
     */
    public boolean isSold() {
        return sold;
    }

    /**
     * @return
     * The unique ID of the ticket.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ticket as sold and tells the event to
     * update its cheapest ticket if this ticket is the
     * current cheapest.
     * @return
     * True if the ticket is bought, false if it cannot be.
     */
    public boolean buyTicket() {
        if (!sold) {
            sold = true;
            if (this == event.getCheapestTicket()) {
                event.updateTickets();
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the ticket with the given id.
     * @param id
     * The ID of the ticket.
     * @return
     * The Ticket with the given ID.
     */
    public static Ticket getTicket(int id) {
        return allTickets.get(id);
    }

    /**
     * @return
     * The ticket in the form "Ticket ###" where the number
     * is the ID.
     */
    public String toString() {
        return String.format("Ticket %03d $%.2f", id, price);
    }
}
