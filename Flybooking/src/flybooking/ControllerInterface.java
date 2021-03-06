package flybooking;

import java.util.ArrayList;
import java.util.Date;

/**
 * An interface for creating reservations and controlling the booking system in
 * general.
 *
 * @author Anders Wind Steffensen, Christoffer Forup & Anders Fischer-Nielsen
 */
public interface ControllerInterface
{
    /**
     * Begin the process of creating a new reservation.
     */
    void createReservation();

    /**
     * Save the reservation when finished.
     *
     * @return Whether the reservation saved successfully or not.
     */
    boolean saveReservation();

    /**
     * Get a a list of reservations matching the given ID and CPR.
     *
     * @param reservationID         The reservation ID to search for.
     * @param CPR                   The CPR to search for.
     * @param startDate             The start date to search for.
     * @param endDate               The end date to search for.
     * @param startDestination      The start destination the search for.
     * @param endDestination        The end destination to search for.
     *
     * @return A list of matching reservations.
     */
    ArrayList<ReservationInterface> getReservations(String reservationID, 
                                                        String CPR, 
                                                        Date startDate,
                                                        Date endDate,
                                                        String startDestination, 
                                                        String endDestination);

    /**
     * Delete a reservation.
     *
     * @param reservationID
     */
    void deleteReservation(String reservationID);

    /**
     * Get the number of destinations from the database.
     *
     * @return The number of destinations in the database.
     */
    int getNumberOfDestinations();

    /**
     * Get all destination cities from the database as strings.
     *
     * @return A string array of cities in the database.
     *
     */
    String[] getDestinationsAsStrings();

    /**
     * Check whether an ID already exists in the database.
     *
     * @param ID The ID to check for in the database.
     * @return Whether the ID exists in the database.
     */
    boolean checkForID(int ID);

    /**
     * Saves the seatIDs which are booked on this reservation.
     *
     * @param seatIDs the seatIds to book on this reservation
     */
    void bookSeats(ArrayList<String> seatIDs);

    /**
     * Get the reservation the system is currently working on.
     *
     * @return A Reservation object.
     */
    ReservationInterface getWorkingOnReservation();

    /**
     * Set the Reservation we're currently working on.
     *
     * @param reservation
     */
    void setWorkingOnReservation(ReservationInterface reservation);

    /**
     * return booked seats for this reservations flight.
     *
     * @return an arrayList of seatIDs
     */
    public ArrayList<String> getBookedSeats();

    /**
     * returns the booked seats on the controllers reservation.
     *
     * @return
     */
    public ArrayList<String> getBookedThisResSeats();

    /**
     * Returns the controller's reservations booked persons.
     *
     * @return a list of Person objects
     */
    public ArrayList<Person> getBookedPersons();

    /**
     * Resets the controller, short for delete the current reservation and make
     * a new one.
     */
    public void resetController();

    /**
     * Get the reservationID which will later be appointed to the reservation.
     *
     * @return
     */
    public String getReservationIDToCome();

    /**
     * Set the reservationID which will later be appointed to the reservation.
     * @param reservationIDToCome The reservation to set.
     */
    public void setReservationIDToCome(String reservationIDToCome);

    /**
     * Find a list of flights matching the specified parameters in the database.
     * @param startDate The date to depart.
     * @param endDate   The date to arrive.
     * @param chosenStartDestination The chosen start destination.
     * @param chosenEndDestination The chosen end destination.
     * @return A list of flights matching the specified parameters.
     */
    public ArrayList<FlightInterface> getFlightList(Date startDate, Date endDate, String chosenStartDestination, String chosenEndDestination);

    /**
     * Checks if the database has any ID matching the IDToCheck.
     * @param IDToCheck the ID to check for in the database.
     * @return false if it is taken and true if its free.
     */
    public boolean checkIDInDataBase(int IDToCheck);
    
}
