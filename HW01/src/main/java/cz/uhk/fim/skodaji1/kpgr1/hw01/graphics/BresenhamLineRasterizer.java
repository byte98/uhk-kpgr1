/*
 * Copyright (C) 2022 Jiri Skoda <jiri.skoda@student.upce.cz>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package cz.uhk.fim.skodaji1.kpgr1.hw01.graphics;

import cz.uhk.fim.skodaji1.kpgr1.hw01.model.Point;

/**
 * Line rasterizer using Bresenhams algorithm
 * @author Jiri Skoda <jiri.skoda@student.upce.cz>
 */
public class BresenhamLineRasterizer extends LineRasterizer
{    
    /**
     * Flag, whether line should be dashed
     */
    private boolean dashed = false;
    
    /**
     * Size for spaces when drawing dashed line
     */
    private static final int DASH_SIZE = 5;
    
    /**
     * Counter of drawn pixels
     */
    private int pixelCounter;
        
    /**
     * Creates new line rasterizer which uses Bresenham algorithm
     * @param raster Raster to which line will be drawn
     */
    public BresenhamLineRasterizer(Raster raster)
    {
        super(raster);
    }
    
    @Override
    public void setDashed(boolean dashed)
    {
        this.dashed = dashed;
    }

    @Override
    protected void drawLine(int x1, int y1, int x2, int y2)
    {
        this.draw(x1, y1, x2, y2);
    }
    
    /**
     * Puts pixel into raster
     * @param x Coordinate on X axis
     * @param y Coordinate on Y axis
     */
    private void putPixel(int x, int y)
    {
        if (x >= 0 &&
            x < this.raster.getWidth() &&
            y >= 0 &&
            y < this.raster.getHeight())
        {
            if ((this.dashed && (this.pixelCounter / BresenhamLineRasterizer.DASH_SIZE) % 2 == 1)
                    || this.dashed == false)
            {
                this.raster.setPixel(x, y, this.color.getRGB());
            }            
        }
        this.pixelCounter++;
    }
        
    /**
     * Draws line
     * @param x1 Coordinate on X axis of starting point
     * @param y1 Coordinate on Y axis of starting point
     * @param x2 Coordinate on X axis of ending point
     * @param y2 Coordinate on Y axis of ending point
     * @param dx Delta of values on X axis
     * @param dy Delta of values on Y axis
     */
    private void draw(int x1, int y1, int x2, int y2)
    {
        if (Math.abs(y2 - y1) < Math.abs(x2 - x1))  // Grows faster on X axis
        {                                           // (slope is less than pi/2)
            if (x1 > x2) // Line has been defined from right to left
            {
                this.drawLow(x2, y2, x1, y1);
            }
            else        // Line has been defined from left to right
            {
                this.drawLow(x1, y1, x2, y2);
            }
        }
        else // Grows faster on Y axis
        {    // (slope is more than (or equals) pi/2)
            if (y1 > y2) // Line has been defined from top to bottom
            {
                this.drawHigh(x2, y2, x1, y1);
            }
            else // Line has been defined from bottom to top
            {
                this.drawHigh(x1, y1, x2, y2);
            }
        }
    }
    
    /**
     * Draw line for slope less than pi/2
     * @param x1 Coordinate on X axis of starting point
     * @param y1 Coordinate on Y axis of starting point
     * @param x2 Coordinate on X axis of ending point
     * @param y2 Coordinate on Y axis of ending point
     */
    private void drawLow(int x1, int y1, int x2, int y2)
    {
        int dx = x2 - x1;
        int dy = y2 - y1;
        int yi = 1;
        if (dy < 0)
        {
            yi = -1;
            dy = dy * (-1);
        }
        int p = (2 * dy) - dx;
        int y = y1;
        for (int x = x1; x <= x2; x++)        
        {
            this.putPixel(x, y);
            if (p > 0)
            {
                y = y + yi;
                p = p + (2 * (dy - dx));
            }
            else
            {
                 p = p + (2 * dy);
            }
        }
    }
    
    /**
     * Draw line for slope more than pi/2
     * @param x1 Coordinate on X axis of starting point
     * @param y1 Coordinate on Y axis of starting point
     * @param x2 Coordinate on X axis of ending point
     * @param y2 Coordinate on Y axis of ending point
     */
    private void drawHigh(int x1, int y1, int x2, int y2)
    {
        int dx = x2 - x1;
        int dy = y2 - y1;
        int xi = 1;
        if (dx < 0)
        {
            xi = -1;
            dx = dx * (-1);
        }
        int p = (2 * dx) - dy;
        int x = x1;
        for (int y = y1; y <= y2; y++)
        {
            this.putPixel(x, y);
            if (p > 0)
            {
                x = x + xi;
                p = p + (2 * (dx - dy));
            }
            else
            {
                p = p + (2 * dx);
            }
        }
    }
    
}
