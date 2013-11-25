package flybooking.GUI;

import flybooking.Plane;
import flybooking.Seat;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
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
     *
     * @param planeToDraw
     *
     * @return
     */
    public PlaneGraphicsComponent paintPlaneSeats(Plane planeToDraw)
    {
        return new PlaneGraphicsComponent(planeToDraw);
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

        private int rows, cols;
        private Plane planeToDraw;

        public PlaneGraphicsComponent(Plane planeToDraw)
        {
            this.planeToDraw = planeToDraw;
            rows = planeToDraw.getRows();
            cols = planeToDraw.getColumns();

        }

        @Override
        public void paint(Graphics g)
        {
            g.drawRect(0, 0, cols * unit + 49, rows * unit + 49);

            for (int i = 0; i < cols; i++)
            {
                for (int j = 0; j < rows; j++)
                {
                    if (planeToDraw.getSeatAvailability(planeToDraw.SeatIDGenerator(i, j)))
                    {
                        g.setColor(Color.GREEN);
                    }
                    else
                    {
                        g.setColor(Color.RED);
                    }

                    g.fillRect((i * unit) + padding + 25, (j * unit) + padding + 25, unit - padding, unit - padding);
                    g.setColor(Color.BLACK);
                    g.drawRect((i * unit) + padding + 25, (j * unit) + padding + 25, unit - padding, unit - padding);
                }
            }
        }

        @Override
        public Dimension getPreferredSize()
        {
            return new Dimension(cols * unit + 50, rows * unit + 50);
        }

        @Override
        public Dimension getMinimumSize()
        {
            return getPreferredSize();
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
            g.drawRect(0, 0, width, height+20);
            Font headerFont = new Font("Monospaced", Font.BOLD, 16);
            g.setColor(Color.BLACK);
            g.setFont(headerFont);
            g.drawString("AND AND OG FORUP FLY", width-300, 35);
        }

        @Override
        public Dimension getPreferredSize()
        {
            return new Dimension(width+1, height+21);
        }

        @Override
        public Dimension getMinimumSize()
        {
            return getPreferredSize();
        }
    }

}
