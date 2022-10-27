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

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Class implementing raster using buffered image
 */
public class RasterBufferedImage implements Raster
{
    private static int i = 0;
    
    /**
     * Image which will be edited
     */
    private final BufferedImage image;
    
    /**
     * Color of image
     */
    private int color;
    
    /**
     * Creates new raster which uses buffered image
     * @param width Width of raster
     * @param height Height of raster
     */
    public RasterBufferedImage(int width, int height)
    {
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }
    
    /**
     * Repaints whole image
     * @param graphics Graphics to which image will be painted into
     */
    public void repaint(Graphics graphics)
    {
        graphics.drawImage(this.image, 0, 0, null);
    }
    
    /**
     * Draws content of raster into image
     * @param raster
     */
    public void draw(RasterBufferedImage raster)
    {
        Graphics graphics = this.getGraphics();
        graphics.setColor(new Color(this.color));
        graphics.drawRect(0, 0, this.getWidth(), this.getHeight());
        graphics.drawImage(raster.image, 0, 0, null);
    }
    
    /**
     * Gets images graphics
     * @return Images graphics
     */
    public Graphics getGraphics()
    {
        return this.image.getGraphics();
    }

    @Override
    public int getPixel(int x, int y)
    {
        return this.image.getRGB(x, y);
    }

    @Override
    public void setPixel(int x, int y, int color)
    {
           this.image.setRGB(x, y, color);
    }

    @Override
    public void clear()
    {
        for (int x = 0; x < this.image.getWidth(); x++)
        {
            for (int y = 0; y < this.image.getHeight(); y++)
            {
                this.setPixel(x, y, this.color);
            }
        }
    }

    @Override
    public void setClearColor(int color)
    {
        this.color = color;
    }

    @Override
    public int getClearColor()
    {
        return this.color;
    }
    
    @Override
    public int getWidth()
    {
        return this.image.getWidth();
    }

    @Override
    public int getHeight()
    {
        return this.image.getHeight();
    }
}
