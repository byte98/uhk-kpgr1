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
     * Computes distance between line and point
     * @param p Point to which distance will be computed
     * @return Computed distance between line and defined point
     */
    public double distanceTo(Point p)
    {
        double reti = Double.NaN;
        
        // At first, we must define general equation of line.
        // So we start with directional vector and normal vector
        double vectX = this.getEnd().x - this.getStart().x;
        double vectY = this.getEnd().y - this.getEnd().y;
        double normX = vectY;
        double normY = (-1) * vectX;
        
        // General equation of line is: ax + by + c = 0.
        // Coefficients a and be are known in this time (coordinates of
        // normal vector). So its time to compute coefficient c.
        // ax + by + c = 0           / - ax
        //      by + c = 0 - ax      / - by
        //           c = 0 - ax - by 
        double c = 0 - (normX * this.getStart().x) - (normY * this.getStart().y);
        
        // Now, when we have general equation of line, we can compute distance
        // of point from line.
        //            |a*x0 + b*y0 + c|
        // distance = ----------------
        //            (a^2 + b^2)^(1/2)
        
        reti = Math.abs((normX * p.x) + (normY * p.y) + c) /
               Math.sqrt(Math.pow(normX, 2) + Math.pow(normY, 2));
        
        return reti;
    }
}
