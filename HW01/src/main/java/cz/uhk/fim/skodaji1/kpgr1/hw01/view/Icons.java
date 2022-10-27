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

import cz.uhk.fim.skodaji1.kpgr1.hw01.Main;
import java.util.Objects;
import javax.swing.ImageIcon;

/**
 * Class holding information about all available icons
 * Author of icons: https://www.icons8.com
 * @author Jiri Skoda <skodaji1@uhk.cz>
 */
public class Icons
{
    /**
     * Cursor icon
     */
    public static ImageIcon CURSOR;
    
    /**
     * Hand cursor icon
     */
    public static ImageIcon HAND;
    
    /**
     * Eraser icon
     */
    public static ImageIcon ERASER;
    
    /**
     * Palette icon
     */
    public static ImageIcon PALETTE;
    
    /**
     * Background icon
     */
    public static ImageIcon BACKGROUND;
    
    /**
     * Foreground icon
     */
    public static ImageIcon FOREGROUND;
    
    /**
     * Line icon
     */
    public static ImageIcon LINE;
    
    /**
     * Triangle icon
     */
    public static ImageIcon TRIANGLE;
    
    /**
     * Polygon icon
     */
    public static ImageIcon POLYGON;
    
    /**
     * Reference to icons class
     */
    private static Icons ref;
    
    /**
     * Initializes icons
     */
    private Icons()
    {
        Icons.CURSOR = new ImageIcon(Main.class.getClassLoader().getResource("cursor.png"));
        Icons.HAND = new ImageIcon(Main.class.getClassLoader().getResource("hand.png"));
        Icons.ERASER = new ImageIcon(Main.class.getClassLoader().getResource("eraser.png"));
        Icons.PALETTE = new ImageIcon(Main.class.getClassLoader().getResource("palette.png"));
        Icons.BACKGROUND = new ImageIcon(Main.class.getClassLoader().getResource("background.png"));
        Icons.FOREGROUND = new ImageIcon(Main.class.getClassLoader().getResource("foreground.png"));
        Icons.LINE = new ImageIcon(Main.class.getClassLoader().getResource("line.png"));
        Icons.TRIANGLE = new ImageIcon(Main.class.getClassLoader().getResource("triangle.png"));
        Icons.POLYGON = new ImageIcon(Main.class.getClassLoader().getResource("polygon.png"));
    }
    
    /**
     * Performs initialization of icons
     */
    public static void init()
    {
        if (Objects.isNull(Icons.ref))
        {
            Icons.ref = new Icons();
        }
    }
    
}
