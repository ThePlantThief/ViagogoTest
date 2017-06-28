import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Manages a Grid of Events and allows users to search for
 * the cheapest tickets of the closest events.
 *
 * @author George Andrews
 * @version 1.0
 * @see Grid
 * @see Event
 */
public class EventManager {
    //Variables
    private final Grid grid;

    /**
     * Constructor. Assigns this event manager to the given grid
     *
     * @param grid The grid to manage.
     */
    private EventManager(Grid grid) {
        this.grid = grid;
    }

    /**
     * Prints a list of a given number of events that are closest to
     * the given point.
     *
     * @param x     The x coordinate to start the search from.
     * @param y     The y coordinate to start the search from.
     * @param limit The number of events to find/print.
     */
    private void printClosestEvents(int x, int y, int limit) {
        ArrayList<Event> events = grid.findEvents(x, y, limit);
        for (Event event : events) {
            Ticket ticket = event.getCheapestTicket();
            if (ticket != null) {
                System.out.println(event.toString() + " - " + ticket.toString() + ", Distance " + grid.distance(x, y, event));
            } else {
                System.out.println(event.toString() + " - No Tickets, Distance " + grid.distance(x, y, event));
            }
        }
    }

    /**
     * Outputs the grid to the command line. Asks the user for
     * their coordinates and returns the closest 5 events and their
     * cheapest ticket price.
     *
     * @param args
     * Extra arguments have no effect.
     */
    public static void main(String[] args) {
        //Sets up event manager and IO
        Grid grid = new Grid(-10, 10, -10, 10);
        SeedData.generateData(grid, 25);
        EventManager manager = new EventManager(grid);
        boolean run = true;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String helpMessage = "Input Options:\n" +
                "- Type your coordinates \"(x,y)\" to find a ticket \n" +
                "- Type \"view\" to view the map\n" +
                "- Type \"event\" to view an events tickets\n" +
                "- Type \"buy\" to buy a ticket\n" +
                "- Type \"help\" to view this list again\n" +
                "- Type \"exit\" to exit\n";
        System.out.println(helpMessage);
        //Gets user input
        while (run) {
            System.out.println("\nEnter your input: ");
            try {
                String in = reader.readLine();
                switch (in) {
                    case "view":
                        System.out.println("\n" + grid.toString());
                        break;

                    case "exit":
                        System.out.println("Thanks for using this event manager!");
                        run = false;
                        break;

                    case "buy":
                        System.out.println("Enter the id of the ticket:");
                        in = reader.readLine();
                        int ticketId = Integer.valueOf(in);
                        Ticket ticket = Ticket.getTicket(ticketId);
                        if (ticket != null && ticket.buyTicket()) {
                            System.out.println("PURCHASED TICKET " + ticketId + "!");
                        } else {
                            System.out.println("Ticket unavailable!");
                        }
                        break;

                    case "event":
                        System.out.println("Enter the id of the event:");
                        in = reader.readLine();
                        int eventId = Integer.valueOf(in);
                        Event event = Event.getEvent(eventId);
                        if (event != null) {
                            System.out.println("Tickets for " + event.toString() + ":");
                            HashMap<Integer, Ticket> tickets = event.getTickets();
                            Set<Integer> ids = tickets.keySet();
                            for (int id : ids) {
                                System.out.println("- " + tickets.get(id).toString());
                            }
                        } else {
                            System.out.println("Ticket unavailable!");
                        }
                        break;

                    case "help":
                        System.out.println(helpMessage);
                        break;

                    default:
                        String[] splitIn = in.split(",");
                        if (splitIn.length == 2) {
                            int x = Integer.valueOf(splitIn[0].replace("(", ""));
                            int y = Integer.valueOf(splitIn[1].replace(")", ""));
                            if (grid.validCords(x, y)) {
                                //Outputs closest events and tickets
                                System.out.println("Closest Events to (" + x + "," + y + "):");
                                manager.printClosestEvents(x, y, 5);
                            } else {
                                System.out.println("Coordinates are out of bounds!");
                            }
                        } else {
                            System.out.println("Incorrect Input!");
                        }
                }

            } catch (IOException e) {
                System.err.println("Serious error from IO... Shutting down");
                System.exit(0);
            } catch (NumberFormatException e) {
                System.out.println("Incorrect Input!");
            }
        }
    }


}
