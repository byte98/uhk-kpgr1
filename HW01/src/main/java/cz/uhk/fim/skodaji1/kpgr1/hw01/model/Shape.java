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
import java.util.List;

/**
 * Class abstracting all planimetry objects
 * @author Jiri Skoda <jiri.skoda@student.upce.cz>
 */
public abstract class Shape
{
    /**
     * Color of shape
     */
    protected Color color;
    
    /**
     * List of points representing shape
     */
    protected List<Point> points;
    
    /**
     * Flag, whether shape should be drawn in editing style
     */
    protected boolean editing = false;
    
    /**
     * Creates new shape
     * @param c Color of shape
     */
    public Shape(Color c)
    {
        this.color = c;
        this.points = new ArrayList<>();
    }
    
    /**
     * Sets flag, whether shape should be drawn in editing style
     * @param editing Flag, whether shape should be drawn in editing style
     */
    public void setEditing(boolean editing)
    {
        this.editing = editing;
    }
    
    /**
     * Gets flag, whether shape should be drawn in editing style
     * @return TRUE if shape should be drawn in editing style,
     *         FALSE otherwise
     */
    public boolean getEditing()
    {
        return this.editing;
    }
    
    /**
     * Sets color of shape
     * @param c 
     */
    public void setColor(Color c)
    {
        this.color = c;
    }
    
    /**
     * Gets color of shape
     * @return 
     */
    public Color getColor()
    {
        return this.color;
    }
    
    /**
     * Gets line representation of shape
     * @return List of lines representing shape
     */
    public abstract List<Line> getLines();
    
    /**
     * Gets nearest point to another point
     * @param p Point which nearest point will be returned
     * @return Nearest point to another point
     */
    public Point getNearestPoint(Point p)
    {
        Point reti = null;
        double dist = Double.MAX_VALUE;
        for(Point pt: this.points)
        {
            double d = p.distanceTo(pt);
            if (d < dist)
            {
                reti = pt;
                dist = d;
            }
        }
        return reti;
    }
    
    /**
     * Sets coordinates of point
     * @param oldPoint Old coordinates of point
     * @param newPoint New coordinates of point
     */
    public void setPoint(Point oldPoint, Point newPoint)
    {
        /*
        System.out.println(String.format("[%03d;%03d] -> [%03d;%03d]", oldPoint.x, oldPoint.y, newPoint.x, newPoint.y));
        for (Point p: this.points)
        {
            System.out.println(String.format("    [%03d; %03d]", p.x, p.y));
            if (p.equals(oldPoint))
            {
                System.out.println("^^^");
                p.x = newPoint.x;
                p.y = newPoint.y;
                System.out.println(String.format("   => [%03d; %03d]", p.x, p.y));
                break;
            }
        }
        for(Point p: this.points)
        {
            System.out.println(String.format("   -- [%03d; %03d]", p.x, p.y));
        }*/
        if (this.points.contains(oldPoint))
        {
            this.points.set(this.points.indexOf(oldPoint), newPoint);
        }
    }
    
    /**
     * Adds point to shape
     * @param point Point which will be added to shape
     */
    public void addPoint(Point point)
    {
        // Add point between nearest ones
        if (this.points.size() < 3)
        {
            this.points.add(point);
        }
        else
        {
            Point nearest = this.getNearestPoint(point);
            int idx = this.points.indexOf(nearest);
            this.points.add(idx, point);
        }
        
    }
}
