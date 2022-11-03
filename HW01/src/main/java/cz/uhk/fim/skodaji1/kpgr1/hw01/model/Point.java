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

/**
 * Class representing point in 2D coordinates
 * @author Jiri Skoda <jiri.skoda@student.upce.cz>
 */
public class Point
{
    /**
     * Coordinate on X axis
     */
    public int x;
    
    /**
     * Coordinate on Y axis
     */
    public int y;
    
    /**
     * Creates new point
     * @param x Coordinate on X axis
     * @param y Coordinate on Y axis
     */
    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Creates new point
     * @param x Coordinate on X axis
     * @param y Coordinate on Y axis
     */
    public Point(double x, double y)
    {
        this.x = (int) Math.round(x);
        this.y = (int) Math.round(y);
    }
    
    /**
     * Computes distance to another point
     * @param p Point to which distance will be computed
     * @return Distance between points
     */
    public double distanceTo(Point p)
    {
        return Math.sqrt(Math.pow(p.x - this.x, 2) + Math.pow(p.y - this.y, 2));
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 67 * hash + this.x;
        hash = 67 * hash + this.y;
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Point other = (Point) obj;
        if (this.x != other.x)
        {
            return false;
        }
        return this.y == other.y;
    }
    
    
}
