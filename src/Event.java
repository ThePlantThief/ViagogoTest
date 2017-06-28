/**
 * Describes an event. Contains a unique ID, and the cheapest
 * ticket attached to this event.
 * @author George Andrews
 * @version 1.0
 */
public class Event {
    //Variables
    private static int nextId = 0;
    private int id;
    private Ticket cheapestTicket;

    /**
     * Constructor. Creates an event and assigns it a
     * unique ID.
     */
    public Event() {
        id = nextId;
        nextId++;
    }

    /**
     * @return
     * Returns the ID of the Event
     */
    public int getId() {
        return id;
    }

    /**
     * Adds a ticket to the event. For purposes of simplicity,
     * Event does not keep track of all tickets, only the cheapest.
     * @param ticket
     * The ticket to add.
     */
    public void addTicket(Ticket ticket){
        if(cheapestTicket == null || ticket.getPrice() < cheapestTicket.getPrice()){
            cheapestTicket = ticket;
        }
    }

    /**
     * @return
     * Returns the price of the cheapest ticket in US dollars
     */
    public String cheapestTicketPrice(){
        if(cheapestTicket == null){
            return "No Tickets";
        } else {
            return String.format("$%.2f", cheapestTicket.getPrice());
        }
    }

    /**
     * @return
     * The Event name in the form of "Event [ID]"
     */
    public String toString(){
        return String.format("Event %03d", id);
    }
}
