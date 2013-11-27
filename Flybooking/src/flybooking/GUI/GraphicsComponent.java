package flybooking.GUI;

import flybooking.Plane;
import flybooking.Seat;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComponent;

/**
 * A custom graphics component that can draw available seats on a plane.
 *
 * @author Anders
 */
public class GraphicsComponent
{

    private final int unit = 24, padding = 10; // for seats.

    public GraphicsComponent()
    {
        // do nothing so far
    }

    /**
     * The paintPlaneSeats() method constructs and returns a new
     * PlaneGraphicsComponent, which is an inner class in graphics component.
     *
     * @param planeToDraw
     *
     * @return a component who draws planeToDraw
     */
    public PlaneGraphicsComponent paintPlaneSeats(Plane planeToDraw)
    {
        return new PlaneGraphicsComponent(planeToDraw);
    }

    public PlaneGraphicsComponent paintPlaneSeats(Plane planeToDraw, int X, int Y)
    {
        PlaneGraphicsComponent planeToWork = new PlaneGraphicsComponent(planeToDraw);
        planeToWork.setSeatAvailability(X, Y);
        return planeToWork;
    }

    /**
     * Paints a blue header with a height and width.
     *
     * @param height
     * @param width
     */
    public HeaderGraphicsComponent paintHeader(int height, int width)
    {
        return new HeaderGraphicsComponent(height, width);
    }

    /**
     * PlaneGraphicsComponent, used for drawing a plane's seat
     */
    private class PlaneGraphicsComponent extends JComponent
    {

        private Plane planeToDraw;
        private int rows, cols;
        int fixdistanceX = 25, fixdistanceY = 25;

        public PlaneGraphicsComponent(Plane planeToDraw)
        {
            this.planeToDraw = planeToDraw;
            rows = planeToDraw.getRows();
            cols = planeToDraw.getColumns();

        }

        /**
         * paints the plane.
         *
         * @param g
         */
        @Override
        public void paint(Graphics g)
        {
            int fixdistanceX = 25;
            int fixdistanceY = 25;
            // draws a rect around the seats.
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, cols * unit + 79, rows * unit + 64);
            g.setColor(Color.BLACK);
            g.drawRect(0, 0, cols * unit + 79, rows * unit + 64);

            for (int i = 0; i < cols; i++)
            {
                /**
                 * If the seat is after the first 1/10 seats then add more
                 * distance to the rest of the seats (making a visual indicator
                 * of where the firstclass seats are.)
                 */
                if (i == cols / 10)
                {
                    fixdistanceY = 40;
                }
                /**
                 * same as before just for the business class which is between
                 * 1/10 and 1/4.
                 */
                if (i == cols / 4)
                {
                    fixdistanceY = 55;
                }
                for (int j = 0; j < rows; j++)
                {
                    /**
                     * Creates the illusion of a isle in the middle of the
                     * plane.
                     */
                    if (j == rows / 2)
                    {
                        fixdistanceX = 40;
                    }
                    // checks the availability of the seat. and change the color
                    if (planeToDraw.getSeatAvailability(planeToDraw.SeatIDGenerator(i, j)))
                    {
                        g.setColor(Color.GREEN);
                    }
                    else
                    {
                        g.setColor(Color.RED);
                    }

                    // Draw the seats.
                    g.fillRect((i * unit) + padding + fixdistanceY, (j * unit) + padding + fixdistanceX, unit - padding, unit - padding);
                    g.setColor(Color.BLACK);
                    g.drawRect((i * unit) + padding + fixdistanceY, (j * unit) + padding + fixdistanceX, unit - padding, unit - padding);
                }
                fixdistanceX = 25;
            }
            g.setColor(Color.BLACK);
            g.drawString("First class     Business class                              Economy class", 20, 20);

        }

        @Override
        public Dimension getPreferredSize()
        {
            return new Dimension(cols * unit + 80, rows * unit + 65);
        }

        @Override
        public Dimension getMinimumSize()
        {
            return getPreferredSize();
        }

        public void setSeatAvailability(int mouseX, int mouseY)
        {
            int firstCol, firstRow;
            int mouseCol, mouseRow;
            //Start of the column
            firstCol = (mouseX - padding - fixdistanceX) / (unit);
            mouseCol = firstCol;
            if (firstCol >= cols / 10 && !(firstCol >= cols / 4))
            {
                // after the first class
                fixdistanceX = 40;
                mouseCol = (mouseX - padding - fixdistanceX) / (unit);
            }
            if (firstCol >= cols / 4)
            {
                // after the business class
                fixdistanceX = 55;
                mouseCol = (mouseX - padding - fixdistanceX) / (unit);
            }

            // start of the row
            firstRow = (mouseY - 35) / (24);
            mouseRow = firstRow;
            if (firstRow >= rows / 2)
            {
                // after the isle
                fixdistanceY = 40;
                mouseRow = (mouseY - padding - fixdistanceY) / (unit);
            }

            // checks if it returns a valid seat position.
            if (mouseCol < 0 || mouseCol > cols || mouseRow < 0 || mouseRow > rows)
            {
                fixdistanceX = 25;
                fixdistanceY = 25;
                return;
            }

            // checks if the click is within the seat boxes.
            if (mouseY > (mouseRow * unit) + padding + fixdistanceY
                    && mouseY < (mouseRow * unit) + padding + fixdistanceY + unit
                    - padding)
            {
                if (mouseX > (mouseCol * unit) + padding
                        + fixdistanceX && mouseX < (mouseCol * unit) + padding
                        + fixdistanceX + unit - padding)
                {
                    planeToDraw.setSeatAvailability(planeToDraw.SeatIDGenerator(mouseCol,mouseRow));
                    System.out.println(Plane.SeatIDGenerator(mouseCol,mouseRow));
                    
                }
            }

            // return the fixdistances to the original state.
            fixdistanceX = 25;
            fixdistanceY = 25;
        }
    }

    /**
     * The header graphics component. Used for drawing a nice blue filled rect.
     */
    private class HeaderGraphicsComponent extends JComponent
    {

        private int height, width;

        public HeaderGraphicsComponent(int height, int width)
        {
            this.height = height;
            this.width = width;
        }

        @Override
        public void paint(Graphics g)
        {
            g.setColor(Color.BLUE);
            g.fillRect(0, 0, width, height);
            g.setColor(Color.GRAY);
            g.drawRect(0, 0, width, height + 20);
            Font headerFont = new Font("Monospaced", Font.BOLD, 16);
            g.setColor(Color.BLACK);
            g.setFont(headerFont);
            g.drawString("AND AND & FORUP PLANE BOOKING", width - 350, 35);
        }

        @Override
        public Dimension getPreferredSize()
        {
            return new Dimension(width + 1, height + 21);
        }

        @Override
        public Dimension getMinimumSize()
        {
            return getPreferredSize();
        }
    }
}
