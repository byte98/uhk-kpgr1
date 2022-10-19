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

/**
 * Interface defining work with raster
 */
public interface Raster
{
    /**
     * Clears canvas
     */
    public abstract void clear();
    
    /**
     * Sets clear color
     * @param color Clear color
     */
    public abstract void setClearColor(int color);
    
    /**
     * Gets horizontal size
     * @return Width
     */
    public abstract int getWidth();
    
    /**
     * Gets vertical size
     * @return Height
     */
    public abstract int getHeight();
    
    /**
     * Gets pixel color at [x, y] position
     * @param x Horizontal coordinate
     * @param y Vertical coordinate
     * @return Pixel color
     */
    public abstract int getPixel(int x, int y);
    
    /**
     * Sets pixel color at [x, y] position
     * @param x Horizontal coordinate
     * @param y Vertical coordinate
     * @param color Pixel color
     */
    public abstract void setPixel(int x, int y, int color);
}