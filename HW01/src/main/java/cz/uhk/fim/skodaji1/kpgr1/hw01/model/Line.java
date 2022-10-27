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
package cz.uhk.fim.skodaji1.kpgr1.hw01.model;

import java.awt.Color;

/**
 * Class representing line in 2D graphics
 * @author Jiri Skoda <jiri.skoda@student.upce.cz>
 */
public class Line
{
    /**
     * Starting point of line
     */
    private Point start;
    
    /**
     * Ending point of line
     */
    private Point end;
    
    /**
     * Color of line
     */
    private Color color;
    
    /**
     * Creates new line
     * @param start Start of line
     * @param end End of line
     * @param color Color of line
     */
    public Line(Point start, Point end, Color color)
    {
        this.start = start;
        this.end = end;
        this.color = color;
    }
    
    /**
     * Creates new line
     * @param x1 Coordinate on X axis of starting point
     * @param y1 Coordinate on Y axis of starting point
     * @param x2 Coordinate on X axis of ending point
     * @param y2 Coordinate on Y axis of ending point
     * @param color Color of line
     */
    public Line(int x1, int y1, int x2, int y2, int color)
    {
        this(new Point(x1, x2), new Point(x2, y2), new Color(color));
    }
    
    /**
     * Gets starting point of line
     * @return Starting point of line
     */
    public Point getStart()
    {
        return this.start;
    }
    
    /**
     * Gets ending point of line
     * @return Ending point of line
     */
    public Point getEnd()
    {
        return this.end;
    }
    
    /**
     * Gets color of line
     * @return Color of line
     */
    public Color getColor()
    {
        return this.color;
    }
}
