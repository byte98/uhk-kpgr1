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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class representing line in 2D graphics
 * @author Jiri Skoda <jiri.skoda@student.upce.cz>
 */
public class Line extends Shape
{
    /**
     * Multiplier of axis and parallel line on X axis
     */
    private static final int AXIS_X_MULTIPLIER = 15360; // twice 8K resolution
    
    
    /**
     * Multiplier of axis and parallel line on Y axis
     */
    private static final int AXIS_Y_MULTIPLIER = 8640; // twice 8K resolution
    
    /**
     * Creates new line
     * @param color Color of line
     */
    public Line(Color color)
    {
        super(color);
    }
    
    /**
     * Gets starting point of line
     * @return Starting point of line
     */
    public Point getStart()
    {
        Point reti = null;
        if (this.points.size() > 0)
        {
            reti = this.points.get(0);
        }
        return reti;
    }
    
    /**
     * Gets middle point of line
     * @return Middle point of line
     */
    public Point getMiddle()
    {
        return new Point(
                (this.getStart().x + this.getEnd().x) / 2,
                (this.getStart().y + this.getEnd().y) / 2
        );
    }
    
    /**
     * Gets ending point of line
     * @return Ending point of line
     */
    public Point getEnd()
    {
        Point reti = null;
        if (this.points.size() > 1)
        {
            reti = this.points.get(1);
        }
        return reti;
    }

    @Override
    public List<Line> getLines()
    {
        return Arrays.asList(this);
    }
    
    @Override
    public Line clone()
    {
        Line reti = new Line(this.color);
        for (Point p: this.points)
        {
            reti.addPoint(new Point(p.x, p.y));
        }
        reti.setEditing(this.editing);
        return reti;
    }
    
    @Override
    public void removePoint(Point p)
    {
        if (this.points.contains(p))
        {
            this.exists = false;
            this.points.clear();
        }
    }
    
    /**
     * Computes distance from point to the line using principles explained
     * on {@link https://en.wikipedia.org/wiki/Distance_from_a_point_to_a_line}
     * @param p Point to which distance will be computed
     * @return Distance from point to the line
     */
    public double distanceTo(Point p)
    {
        double x0 = p.x;
        double y0 = p.y;
        double x1 = this.getStart().x;
        double y1 = this.getStart().y;
        double x2 = this.getEnd().x;
        double y2 = this.getEnd().y;
        return (((x2 - x1)*(y1 - y0)) - ((x1 - x0)*(y2 - y1)))
                /
                (Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
    }
}
