/**
 * Generates random events for a grid.
 * @see Grid
 * @see Event
 * @author George Andrews
 * @version 1.0
 */
public class SeedData {

    /**
     * Populates the given grid with a given number of events
     * @param grid
     * The Grid to populate.
     * @param numberOfEvents
     * The number of events to generate.
     */
    public static void generateData(Grid grid, int numberOfEvents){
        int minX = grid.getMinX();
        int rangeX = Math.abs(grid.getMaxX() - minX);
        int minY = grid.getMinY();
        int rangeY = Math.abs(grid.getMaxY() - minY);
        for(int i = 0; i < numberOfEvents; i++){
            //Make Event
            Event event = new Event();
            for(int j = 0; j <  Math.round((float) (Math.random()*10)); j++){
                float price = 1 + (float) (Math.random()*100 + 1);
                Ticket ticket = new Ticket(price);
                event.addTicket(ticket);
            }

            //Assign Location
            int xCord;
            int yCord;
            do {
                xCord = Math.round((float) (minX + (rangeX * Math.random())));
                yCord = Math.round((float) (minY + (rangeY * Math.random())));
            } while (!grid.addEvent(event,xCord,yCord));
        }
    }
}
