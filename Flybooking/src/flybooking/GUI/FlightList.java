
package flybooking.GUI;

import flybooking.*;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListModel;

/**
 * Create a custom list of flights as strings.
 * @author Anders Wind Steffensen, Christoffer Forup & Anders Fischer-Nielsen
 */
public class FlightList extends JList {
    //The array of flights to show in the list.
    private ArrayList<Flight> flights;
    //The listmodel to show in the list.
    private DefaultListModel listModel;
    
    /**
     * Create a new flight list, containing a given array of flights.
     * @param flights The flights to show in the list.
     */
    public FlightList(ArrayList<Flight> flights)
    {
        this.flights = flights;
        listModel = new DefaultListModel();
        setFlights(flights);
        drawList();
    }
    
    /**
     * Draw the list. 
     */
    public void drawList() {
        this.setVisibleRowCount(4);
        this.setFixedCellHeight(50);
        this.setFixedCellWidth(400);
        this.repaint();
    }
    
    /**
     * Put the flights in the array, adding each one to the list as a string.
     * @param flights The array to add to the list.
     */
    public void setFlights(ArrayList<Flight> flights) {
        this.flights = flights;
        
        //Go through the list of flights, adding each one as a string.
        for (Flight f : flights) {
            listModel.addElement(convertToString(f));
        }
        
        this.setModel(listModel);
        this.repaint();
    }
    
    /**
     * Convert a Plane object to a string to put in the list. (Ugly code. Don't 
     * look)
     * @param flight The plane object to convert.
     * @return A string with the relevant data for the list.
     */
    public String convertToString(Flight flight) {
        String convertedString = "Invalid flight";
        
        convertedString = flight.getStartDate().getHours() + 
                ":" + flight.getStartDate().getMinutes() + 
                "             " + 
                flight.getStartAirport().getName() + " > " + 
                flight.getEndAirport().getName() + 
                "             " + flight.getPlane().getID() + 
                "\n" + flight.getEndDate().getHours() + 
                ":" + flight.getEndDate().getMinutes() + 
                "             " + flight.getStartDate().getMonth() + "/" + 
                flight.getStartDate().getDay() + "-" + 
                flight.getStartDate().getYear() + 
                "             " + flight.getPrice() + ",-";
        
        return convertedString;
    }
}