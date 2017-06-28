# Viagogo Developer Test

NOTE: This is the simple version. It covers ONLY the requirements.

To view the expanded version that expands on the requirements go to the expanded branch.

Language: Java

To Compile:
Navigate to the folder containing the .java files.
Call the command "javac *.java"

To Run:
Call the command "java EventManager"

# Assumptions
-	Tickets cannot be sold, only viewed.
-	Tickets once added to an event are never removed.

These assumptions have been made to reduce memory usage as only the cheapest ticket needs to be stored. The current system would fail if tickets were able to be removed or bought as users could buy unavailable tickets. To expand functionality to enable buying and removing tickets I would implement the following systems:

-	The event class would store all tickets to the event.
-	Tickets would keep track of what event they are attached to.
-	When a ticket is sold or removed it would be flagged as sold, stopping users buying them.
-	If the cheapest ticket is sold it would called a method on the event to search through the remaining tickets and find the next cheapest unsold ticket.

This new system would keep the benefit of speed when browsing as events do not have to keep searching for their cheapest ticket but enable buying of tickets, updating the cheapest ticket that is produced from searches. This has been done on the expanded branch.

# Evaluation


To support multiple events at the same location I could create a new class defining a location. This class would contain a list of all events that occur at the location and would be quite easy to implement.


When working with a larger world size I would consider using a database to store all the event details to enable better querying and searching. Presuming a larger world size would mean more users, using a database would also help prevent synchronization errors such as selling the same ticket twice.
