import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Manages a Grid of Events and allows users to search for
 * the cheapest tickets of the closest events.
 * @see Grid
 * @see Event
 * @author George Andrews
 * @version 1.0
 */
public class EventManager {

    /**
     * Outputs the grid to the command line. Asks the user for
     * their coordinates and returns the closest 5 events and their
     * cheapest ticket price.
     * @param args
     */
    public static void main(String[] args) {
        Grid grid = new Grid(-10,10,-10,10);
        SeedData.generateData(grid,25);
        System.out.println("##### Event Manager #####\n" +
                "See below the events grid:");
        System.out.println(grid.toString());
        boolean validInput = false;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int x = 0;
        int y = 0;
        System.out.println("\n##### Find An Event #####");
        //Waits for a valid input
        while (!validInput){
            System.out.print("Enter your coordinates (x,y): ");
            try {
                String in = reader.readLine();
                String[] splitIn = in.split(",");
                if(splitIn.length == 2){
                    x = Integer.valueOf(splitIn[0].replace("(",""));
                    y = Integer.valueOf(splitIn[1].replace(")",""));
                    if(grid.validCords(x,y)) {
                        validInput = true;
                    } else {
                        System.out.println("Coordinates are out of bounds!");
                    }
                } else {
                    System.out.println("Incorrect Format!");
                }
            } catch (IOException e){
                System.err.println("Serious error from IO... Shutting down");
                System.exit(0);
            } catch (NumberFormatException e){
                System.out.println("Incorrect Format!");
            }
        }

        //Outputs events
        System.out.println("Closest Events to (" + x + "," + y + "):\n" +
                grid.findEvents(x,y,5));
    }
}
