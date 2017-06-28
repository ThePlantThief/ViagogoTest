# Viagogo Developer Test

NOTE: This is the expanded version. It covers the requirements and allows sale of tickets.

To view the simple version that ONLY covers the requirements go to the master branch.

Language: Java

To Compile:
Navigate to the folder containing the .java files.
Call the command "javac *.java"

To Run:
Call the command "java EventManager"

# Assumptions
-	Each event is only attached to one grid.

This assumption allows events to store their own coordinates in there assoicated grid.
# Evaluation


To support multiple events at the same location I could create a new class defining a location. This class would contain a list of all events that occur at the location and would be quite easy to implement.

When working with a larger world size I would consider using a database to store all the event details to enable better querying and searching. Presuming a larger world size would mean more users, using a database would also help prevent synchronization errors such as selling the same ticket twice.
