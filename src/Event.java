import java.util.HashMap;
import java.util.Hashtable;
import java.util.Set;

/**
 * Describes an event. Contains a unique ID, the cheapest
 * ticket attached to this event and a HashMap of all tickets for the event.
 *
 * Also stored all created events in a HashTable.
 * @author George Andrews
 * @version 1.0
 */
public class Event {
    //Variables
    private static int nextId = 0;
    private static final Hashtable<Integer, Event> allEvents = new Hashtable<>();
    private final int id;
    private int x;
    private int y;
    private Ticket cheapestTicket;
    private final HashMap<Integer, Ticket> tickets;

    /**
     * Constructor. Creates an event and assigns it a
     * unique ID.
     */
    public Event() {
        id = nextId;
        nextId++;
        tickets = new HashMap<>();
        allEvents.put(id, this);
    }

    /**
     * Returns the event with the given ID
     *
     * @param id The ID of event to return
     * @return The event with the given ID
     */
    public static Event getEvent(int id) {
        return allEvents.get(id);
    }


    /**
     * Adds a ticket to the event. For purposes of simplicity,
     * Event does not keep track of all tickets, only the cheapest.
     * @param ticket
     * The ticket to add.
     */
    public void addTicket(Ticket ticket){
        tickets.put(ticket.getId(), ticket);
        if(cheapestTicket == null || ticket.getPrice() < cheapestTicket.getPrice()){
            cheapestTicket = ticket;
        }
    }

    /**
     * @return
     * The cheapest ticket for this event.
     */
    public Ticket getCheapestTicket() {
        return cheapestTicket;
    }

    /**
     * @return
     * The HashMap of all tickets for this event.
     */
    public HashMap<Integer, Ticket> getTickets() {
        return tickets;
    }

    /**
     * Searches through all the tickets and sets cheapestTicket to
     * the ticket with the lowest price that is not sold.
     */
    public void updateTickets() {
        Ticket cheapest = null;
        Set<Integer> ids = tickets.keySet();
        for (Integer id : ids) {
            Ticket ticket = tickets.get(id);
            if ((cheapest == null || cheapest.getPrice() > ticket.getPrice()) && !ticket.isSold()) {
                cheapest = ticket;
            }
        }
        cheapestTicket = cheapest;
    }

    /**
     * @return
     * Returns the events X coordinates.
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the events X coordinates.
     * @param x
     * The X coordinate of the event.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return
     * Returns the events Y coordinates
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the events Y coordinates.
     * @param y
     * The Y coordinate of the event.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return
     * The Event name in the form of "Event [ID]"
     */
    public String toString(){
        return String.format("Event %03d", id);
    }
}
