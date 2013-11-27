package flybooking;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author Anders Fischer-Nielsen
 */
public class Calculator
{

    private static Random randomGen = new Random();

    /**
     * Add two numbers and get the result.
     *
     * @param n1 The first number to add.
     * @param n2 The second number to add.
     *
     * @return The sum of the two numbers.
     */
    public static int add(int n1, int n2)
    {
        return n1 + n2;
    }

    /**
     * Subtract two numbers and get the result.
     *
     * @param n1 The first number to subtract.
     * @param n2 The second number to subtract.
     *
     * @return The sum of the two numbers.
     */
    public static int subtract(int n1, int n2)
    {
        return n1 - n2;
    }

    /**
     * Multiply two numbers and get the result.
     *
     * @param n1 The first number to multiply.
     * @param n2 The second number to multiply.
     *
     * @return The sum of the two numbers.
     */
    public static int multiply(int n1, int n2)
    {
        return n1 * n2;
    }

    /**
     * Convert a given date into a string.
     *
     * @param date The date to parse.
     *
     * @return The parsed Date as a string.
     */
    public static String convertDateToString(Date date)
    {
        return new SimpleDateFormat("dd/MM-yyyy").format(date);
    }

    public static Date convertStringToDate(String string) throws ParseException
    {
        return new SimpleDateFormat("dd/MM-yyyy").parse(string);
    }

    String createPersonID() throws SQLException
    {
        while (true)
        {
            String personID = "" + randomGen.nextInt(99999);
            if (Database.getInstance().checkForID(personID))
            {
                return personID;
            }
        }
    }

    String createReservationID() throws SQLException
    {
        while (true)
        {
            String personID = "" + randomGen.nextInt(9999);
            if (Database.getInstance().checkForID(personID))
            {
                return personID;
            }
        }
    }
}
