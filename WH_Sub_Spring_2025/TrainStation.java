/** A simple paradigm of a linkable object: a train station on a train line */

public class TrainStation {

    private String name;
    private TrainStation next;

    /** Basic constructor. By design, we leave the pointer to the next station null. We will have a separate method to change the next pointer when we want. */
    public TrainStation(String name) {
        this.name = name;
        this.next = null;
    } // basic constructor

    /** Setter for next. It changes the pointer of the current station the station we specify. */
    public void setNext(TrainStation next) {
        this.next = next;
    } // method setNext
    
    /** Predicate accessor for next. Tells if there is station after this station.*/
    public boolean hasNext() {
        return this.next != null;
    } // method hasNext

    /** Plain accessor for next. Returns the station object after the present station. */
    public TrainStation getNext() {
        return this.next;
    } // method getNext

    /** Plain accessor for station name */
    public String getName() {
        return this.name;
    } // method getName

} // class TrainStation