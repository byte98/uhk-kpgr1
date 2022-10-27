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

import cz.uhk.fim.skodaji1.kpgr1.hw01.model.Line;
import java.awt.Color;

/**
 * Class abstracting classes with ability to rasterize line
 */
public abstract class LineRasterizer
{
    /**
     * Raster to which line will be rasterized
     */
    Raster raster;
    
    /**
     * Color of line
     */
    Color color;

    /**
     * Creates new line rasterizer
     * @param raster Raster to which line will be rasterized
     */
    public LineRasterizer(Raster raster)
    {
        this.raster = raster;
    }

    /**
     * Sets color of line
     * @param color New color of line
     */
    public void setColor(Color color)
    {
        this.color = color;
    }

    /**
     * Sets color of line
     * @param color New color of line
     */
    public void setColor(int color) {
        this.color = new Color(color);
    }

    /**
     * Performs rasterization of line
     * @param line Line which will be rasterized
     */
    public void rasterize(Line line)
    {
        this.rasterize(line.getStart().x, line.getStart().y,
                line.getEnd().x, line.getEnd().y, line.getColor());
    }

    /**
     * Performs rasterization of line
     * @param x1 Coordinate on X axis of starting point
     * @param y1 Coordinate on Y axis of starting point
     * @param x2 Coordinate on X axis of ending point
     * @param y2 Coordinate on Y axis of ending point
     * @param color Color of line
     */;
    public void rasterize(int x1, int y1, int x2, int y2, Color color)
    {
        this.setColor(color);
        this.drawLine(x1, y1, x2, y2);
    }

    /**
     * Draws line
     * @param x1 Coordinate on X axis of starting point
     * @param y1 Coordinate on Y axis of starting point
     * @param x2 Coordinate on X axis of ending point
     * @param y2 Coordinate on Y axis of ending point
     */
    protected abstract void drawLine(int x1, int y1, int x2, int y2);
    
    /**
     * Sets, whether line should be dashed
     * @param dashed Flag, whether line should be dashed
     */
    public abstract void setDashed(boolean dashed);
}
