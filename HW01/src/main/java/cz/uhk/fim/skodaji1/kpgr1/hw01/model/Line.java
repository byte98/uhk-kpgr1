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
}
