import java.util.ArrayList;

/**
 * Defines a 2D grid in which events can be added at specified
 * Coordinates. Note, only one event can be stored at each location.
 *
 * @author George Andrews
 * @version 1.0
 * @see Event
 */
public class Grid {
    //Variables
    private final int minX;
    private final int maxX;
    private final int minY;
    private final int maxY;
    private final int range;
    private final Event[][] grid;

    /**
     * Constructor. Sets up the grid.
     *
     * @param minX The minimum x coordinate of the grid.
     * @param maxX The maximum x coordinate of the grid.
     * @param minY The minimum y coordinate of the grid.
     * @param maxY The maximum y coordinate of the grid.
     */
    public Grid(int minX, int maxX, int minY, int maxY) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        int xHeight = Math.abs(maxX - minX);
        int yHeight = Math.abs(maxY - minY);
        range = Math.max(xHeight, yHeight);
        grid = new Event[xHeight + 1][yHeight + 1];
    }

    /**
     * Adds an event to the grid.
     *
     * @param event The event to add.
     * @param xCord The x coordinate of the event.
     * @param yCord The y coordinate of the event.
     * @return Returns True if the event was added successfully, else
     * False.
     */
    public boolean addEvent(Event event, int xCord, int yCord) {
        //Checks if cords are valid
        if (xCord > maxX || xCord < minX) {
            throw new IndexOutOfBoundsException("xCord is outside the grid space (" + minX + " - " + maxX + ")");
        } else if (yCord > maxY || yCord < minY) {
            throw new IndexOutOfBoundsException("yCord is outside the grid space (" + minY + " - " + maxY + ")");
        } else if (getEvent(xCord, yCord) == null) {
            //Places the event at the cords if empty
            grid[xCord - minX][yCord - minY] = event;
            event.setX(xCord);
            event.setY(yCord);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets the event at the given coordinates.
     *
     * @param xCord The x coordinate of the event.
     * @param yCord The y coordinate of the event.
     * @return The Event, or null if no Event was found.
     */
    private Event getEvent(int xCord, int yCord) {
        if (!validCords(xCord, yCord)) {
            return null;
        } else {
            return grid[xCord - minX][yCord - minY];
        }
    }

    /**
     * Finds the cheapest ticket prices for a number of events
     * closest to the given coordinates.
     *
     * @param xCord The x coordinate to start searching from.
     * @param yCord The y coordinate to start searching from.
     * @param limit The number of events to find.
     * @return Returns an ArrayList of the grid.
     */
    public ArrayList<Event> findEvents(int xCord, int yCord, int limit) {
        if (!validCords(xCord, yCord)) {
            throw new IllegalArgumentException("Coordinates are out of bounds");
        } else {
            ArrayList<Event> closestEvents = new ArrayList<>();
            //Checks the event at the location.
            Event event = getEvent(xCord, yCord);
            if (event != null) {
                closestEvents.add(event);
                if (closestEvents.size() >= limit) {
                    return closestEvents;
                }
            }

            //Searching around the location at increasing distances
            for (int searchDistance = 1; searchDistance < range; searchDistance++) {
                ArrayList<Event> events = new ArrayList<>();
                for (int i = 0; i < searchDistance + 1; i++) {
                    int x1 = xCord - searchDistance + i;
                    int y1 = yCord - i;
                    int x2 = xCord + searchDistance - i;
                    int y2 = yCord + i;
                    events.add(getEvent(x1, y1));
                    events.add(getEvent(x2, y2));
                }

                for (int i = 1; i < searchDistance; i++) {
                    int x1 = xCord + searchDistance - i;
                    int y1 = yCord - i;
                    int x2 = xCord - i;
                    int y2 = yCord + searchDistance - i;
                    events.add(getEvent(x1, y1));
                    events.add(getEvent(x2, y2));
                }

                for (Event event1 : events) {
                    if (event1 != null) {
                        closestEvents.add(event1);
                        if (closestEvents.size() >= limit) {
                            return closestEvents;
                        }
                    }
                }

            }
            return closestEvents;
        }
    }

    /**
     * Returns the Manhattan distance between the point and an event in the grid.
     *
     * @param x1    The x coordinate of the first point.
     * @param y1    The y coordinate of the first point.
     * @param event The event to find the distance to.
     * @return The Manhattan distance if the coordinates are valid.
     * Otherwise returns -1.
     */
    public int distance(int x1, int y1, Event event) {
        int x2 = event.getX();
        int y2 = event.getY();
        if (validCords(x1, y1) && validCords(x2, y2)) {
            return Math.abs(x1 - x2) + Math.abs(y1 - y2);
        } else {
            return -1;
        }
    }

    /**
     * Checks if the given coordinates are contained in the grid-space.
     *
     * @param xCord The x coordinate.
     * @param yCord The y coordinate.
     * @return True if valid, else false.
     */
    public boolean validCords(int xCord, int yCord) {
        return !(xCord < minX || xCord > maxX || yCord < minY || yCord > maxY);
    }

    /**
     * @return The lowest x coordinate.
     */
    public int getMinX() {
        return minX;
    }

    /**
     * @return The greatest x coordinate.
     */
    public int getMaxX() {
        return maxX;
    }

    /**
     * @return The lowest y coordinate.
     */
    public int getMinY() {
        return minY;
    }

    /**
     * @return The greatest y coordinate.
     */
    public int getMaxY() {
        return maxY;
    }

    /**
     * @return Returns the grid as a string. NOTE, tabs are used so the
     * grid can be easily copied into a spreadsheet to check distances during
     * testing.
     */
    public String toString() {
        StringBuilder output = new StringBuilder("\t");
        for (int x = minX; x <= maxX; x++) {
            output.append(x).append("\t");
        }
        for (int y = maxY; y >= minY; y--) {
            output.append("\n");
            output.append(y).append("\t");
            for (int x = minX; x <= maxX; x++) {
                Event event = getEvent(x, y);
                if (event != null) {
                    output.append(event.toString()).append("\t");
                } else {
                    output.append("None\t");
                }
            }
        }
        return output.toString();
    }
}
