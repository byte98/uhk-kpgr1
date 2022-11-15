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
import java.util.Comparator;
import java.util.List;

/**
 * Class abstracting all planimetry objects
 * @author Jiri Skoda <jiri.skoda@student.upce.cz>
 */
public abstract class Shape implements Cloneable
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
     * Flag, whether shape exists
     */
    protected boolean exists = true;
    
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
        this.points.add(point);
    }
    
    /**
     * Adds point between already existing points
     * @param point Point which will be added to shape
     * @param previous Previous point
     * @param next Next point
     */
    public void addPoint(Point point, Point previous, Point next)
    {
        if (this.points.contains(previous) && this.points.contains(next))
        {
            int idxPrev = this.points.indexOf(previous);
            int idxNext = this.points.indexOf(next);
            int idx = Math.max(this.points.indexOf(previous), this.points.indexOf(next));
            if (Math.min(idxPrev, idxNext) == 0 && Math.max(idxPrev, idxNext) == this.points.size() - 1)
            {
                idx = 0;
            }
            this.points.add(idx, point);
        }
        else
        {
            this.addPoint(point);
        }
    }
    
    /**
     * Creates copy of shape
     * @return Copy of shape
     */
    public abstract Shape clone();
    
    /**
     * Removes point from shape
     * @param p Point which will be removed
     */
    public abstract void removePoint(Point p);
    
    /**
     * Gets flag, whether shape exists
     * @return TRUE if shape exists, FALSE otherwise
     */
    public boolean exists()
    {
        return this.exists;
    }
    
    /**
     * Gets points sorted by its distance to defined point
     * @param p Point to which distance will be compared
     * @return Array with sorted points
     */
    public Point[] getNearestPoints(Point p)
    {
        Point[] reti = new Point[this.points.size()];
        reti = this.points.toArray(reti);
        Arrays.sort(reti, new Comparator<Point>(){
            @Override
            public int compare(Point o1, Point o2)
            {
                int reti = 0;
                if (o1.distanceTo(p) < o2.distanceTo(p))
                {
                    reti = -1;
                }
                else if (o1.distanceTo(p) > o2.distanceTo(p))
                {
                    reti = 1;
                }
                return reti;
            }
        });
        return reti;
    }
    
    /**
     * Gets number of points
     * @return Number of points
     */
    public int countPoints()
    {
        return this.points.size();
    }
}