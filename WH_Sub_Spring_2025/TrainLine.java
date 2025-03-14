/**
 * An every day linked list paradigm.
 */

public class TrainLine {

    /**
     * The trainline is represented only by its first station. Every station after
     * than can be discovered by traveling down the line. That's the nature of
     * sequential data structures.
     */
    TrainStation head;

    /**
     * Add a new station. By convention, new stations are added at the end of the
     * trainline.
     * 
     * @param name String with name of new station to add
     */
    public void add(String name) {
        // Create the new station object to add
        TrainStation newStation = new TrainStation(name);
        // If there are no stations in this line, ie if even the head station does not
        // exist, make the new station and head of the line
        if (this.head == null) {
            // Line is empty! New station becomes head station
            this.head = newStation;
        } else {
            // Line is not empty. We must find its last station and add the new station
            // after it. To find the last station we travel down the line until we arrive at
            // a station that does not have a next station. Traveling is done by a "moving
            // station" that we call traveler or cursor or current. It's just a pointer to
            // the train stations as we traverse the line seeking its last station.
            TrainStation cursor = this.head;
            while (cursor.hasNext()) {
                cursor = cursor.getNext();
            }
            // The while loop ends when we find the last station of the line. We recognize
            // the last station because its next pointer is null. Now that we are at the
            // last station, we change its next pointer to connect to the new station. And
            // since the new station was created with an null next pointer, by definition it
            // is now the last station of the line.
            cursor.setNext(newStation);
        }
    } // method add
}
