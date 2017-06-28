import java.util.ArrayList;

/**
 * Defines a 2D grid in which events can be added at specified
 * Coordinates. Note, only one event can be stored at each location.
 * @see Event
 * @author George Andrews
 * @version 1.0
 */
public class Grid {
    //Variables
    private int minX;
    private int maxX;
    private int minY;
    private int maxY;
    private int range;
    private Event[][] events;

    /**
     * Constructor. Sets up the grid.
     * @param minX
     * The minimum x coordinate of the grid.
     * @param maxX
     * The maximum x coordinate of the grid.
     * @param minY
     * The minimum y coordinate of the grid.
     * @param maxY
     * The maximum y coordinate of the grid.
     */
    public Grid(int minX, int maxX, int minY, int maxY) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        int xHeight = Math.abs(maxX - minX);
        int yHeight = Math.abs(maxY - minY);
        range = Math.max(xHeight, yHeight);
        events = new Event[xHeight + 1][yHeight + 1];
    }

    /**
     * Adds an event to the grid.
     * @param event
     * The event to add.
     * @param xCord
     * The x coordinate of the event.
     * @param yCord
     * The y coordinate of the event.
     * @return
     * Returns True if the event was added successfully, else
     * False.
     */
    public boolean addEvent(Event event, int xCord, int yCord){
        //Checks if cords are valid
        if(xCord > maxX || xCord < minX){
            throw new IndexOutOfBoundsException("xCord is outside the grid space (" + minX + " - " + maxX + ")");
        } else if (yCord > maxY || yCord < minY){
            throw new IndexOutOfBoundsException("yCord is outside the grid space (" + minY + " - " + maxY + ")");
        } else if (getEvent(xCord,yCord) == null){
            //Places the event at the cords if empty
            events[xCord - minX][yCord - minY] = event;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets the event at the given coordinates.
     * @param xCord
     * The x coordinate of the event.
     * @param yCord
     * The y coordinate of the event.
     * @return
     * The Event, or null if no Event was found.
     */
    private Event getEvent(int xCord, int yCord){
        if(xCord > maxX || xCord < minX || yCord > maxY || yCord < minY){
            return null;
        } else {
            return events[xCord - minX][yCord - minY];
        }
    }

    /**
     * Finds the cheapest ticket price for a number of events
     * closest to the given coordinates in a String.
     * @param xCord
     * The x coordinate to start searching from.
     * @param yCord
     * The y coordinate to start searching from.
     * @param limit
     * The number of events to find.
     * @return
     * Returns a String of the event ids, price and distance.
     */
    public String findEvents(int xCord, int yCord, int limit) {
        if (!validCords(xCord, yCord)) {
            throw new IllegalArgumentException("Coordinates are out of bounds");
        } else {
            StringBuilder output = new StringBuilder();
            int count = 0;
            //Checks the event at the location.
            Event event = getEvent(xCord, yCord);
            if (event != null) {
                output.append(event.toString()).append(" - ").append(event.cheapestTicketPrice()).append(", Distance ").append("0").append("\n");
                count++;
                if (count >= limit) {
                    return output.toString();
                }
            }

            //Searching around the location at increasing distances
            for (int searchDistance = 1; searchDistance < range; searchDistance++) {
                ArrayList<Event> foundEvents = new ArrayList<>();
                for (int i = 0; i < searchDistance + 1; i++) {
                    int x1 = xCord - searchDistance + i;
                    int y1 = yCord - i;
                    int x2 = xCord + searchDistance - i;
                    int y2 = yCord + i;
                    foundEvents.add(getEvent(x1, y1));
                    foundEvents.add(getEvent(x2, y2));
                }

                for (int i = 1; i < searchDistance; i++) {
                    int x1 = xCord + searchDistance - i;
                    int y1 = yCord - i;
                    int x2 = xCord - i;
                    int y2 = yCord + searchDistance - i;
                    foundEvents.add(getEvent(x1, y1));
                    foundEvents.add(getEvent(x2, y2));
                }

                for (Event event1 : foundEvents) {
                    if (event1 != null) {
                        count++;
                        output.append(event1.toString()).append(" - ").append(event1.cheapestTicketPrice()).append(", Distance ").append(searchDistance).append("\n");
                        if (count >= limit) {
                            return output.toString();
                        }
                    }
                }
            }
            return output.toString();
        }
    }

    /**
     * Returns the Manhattan distance between two points in the grid.
     * @param x1
     * The x coordinate of the first point.
     * @param y1
     * The y coordinate of the first point.
     * @param x2
     * The x coordinate of the second point.
     * @param y2
     * The y coordinate of the second point.
     * @return
     * The Manhattan distance if the coordinate are valid.
     * Otherwise returns -1.
     */
    public int distance(int x1, int y1, int x2, int y2){
        if(validCords(x1,y1) && validCords(x2,y2)){
            return Math.abs(x1 - x2) + Math.abs(y1 - y2);
        } else {
            return -1;
        }
    }

    /**
     * Checks if the given coordinates are contained in the gridspace.
     * @param xCord
     * The x coordinate.
     * @param yCord
     * The y coordinate.
     * @return
     * True if valid, else false.
     */
    public boolean validCords(int xCord, int yCord){
        return !(xCord < minX || xCord > maxX  || yCord < minY  || yCord > maxY);
    }

    /**
     * @return
     * The lowest x coordinate.
     */
    public int getMinX() {
        return minX;
    }

    /**
     * @return
     * The greatest x coordinate.
     */
    public int getMaxX() {
        return maxX;
    }

    /**
     * @return
     * The lowest y coordinate.
     */
    public int getMinY() {
        return minY;
    }

    /**
     * @return
     * The greatest y coordinate.
     */
    public int getMaxY() {
        return maxY;
    }

    /**
     * @return
     * Returns the grid as a string.
     */
    public String toString(){
        String output = " \t";
        for(int x = minX; x <= maxX; x++){
            output = output + x + "\t";
        }
        for(int y = maxY; y >= minY; y --){
            output = output + "\n";
            output = output + y + "\t";
            for(int x = minX; x <= maxX; x++){
                Event event = getEvent(x,y);
                if(event != null){
                    output = output + event.toString() + "\t";
                } else {
                    output = output + "None\t";
                }
            }
        }
        return output;
    }
}
