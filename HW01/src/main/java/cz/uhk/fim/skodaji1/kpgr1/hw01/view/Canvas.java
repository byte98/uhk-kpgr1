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
package cz.uhk.fim.skodaji1.kpgr1.hw01.view;

import cz.uhk.fim.skodaji1.kpgr1.hw01.graphics.Raster;
import cz.uhk.fim.skodaji1.kpgr1.hw01.graphics.RasterBufferedImage;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Objects;
import javax.swing.JPanel;

/**
 * Class representing canvas with ability for drawing
 */
public final class Canvas extends JPanel
{
    
    /**
     * Raster which will be drawn
     */
    private RasterBufferedImage raster;
    
    /**
     * Creates new canvas
     */
    public Canvas()
    {
        super();
        this.raster = new RasterBufferedImage(MainWindow.WIDTH, MainWindow.HEIGHT);
        this.addComponentListener(new ComponentAdapter(){
            @Override
            public void componentResized(ComponentEvent e)
            {
                if (getWidth()<1 || getHeight()<1)
                        return;
                if (Objects.nonNull(raster) && getWidth()<=raster.getWidth() && getHeight()<=raster.getHeight()) //no resize if new one is smaller
                        return;
                RasterBufferedImage newRaster = new RasterBufferedImage(getWidth(), getHeight());
                newRaster.setClearColor(raster.getClearColor());
                newRaster.clear();
                if (Objects.nonNull(raster))
                {
                    newRaster.draw(raster);
                }                
                raster = newRaster;
            }
        });
    }
    
    /**
     * Redraws whole canvas
     */
    public void redraw()
    {
        this.repaint();
    }
    
    /**
     * Gets actually used raster
     * @return Actually used raster
     */
    public Raster getRaster()
    {
        return this.raster;
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (Objects.nonNull(this.raster))
        {
            this.raster.repaint(g);
        }        
    }
}
