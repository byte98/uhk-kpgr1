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

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 * Class representing UI element which can display color
 * @author Jiri Skoda <jiri.skoda@student.upce.cz>
 */
public class ColorDisplay extends JLabel
{
    /**
     * Actually displayed color
     */
    private Color color;
    
    /**
     * Creates new element for displaying color
     * @param c Color which will be displayed
     */
    public ColorDisplay(Color c)
    {
        super();
        this.setOpaque(true);
        this.setFont(new Font("Consolas", Font.BOLD, 14));
        this.color = c;
        this.displayColor();
    }
    
    /**
     * Sets new color which will be displayed
     * @param c New color which will be displayed
     */
    public void setColor(Color c)
    {
        this.color = c;
        this.displayColor();
        this.setBorder(BorderFactory.createLineBorder(this.color, 5, true));
    }
    
    /**
     * Gets actually displayed color
     * @return Actually displayed color
     */
    public Color getColor()
    {
        return this.color;
    }
    
    /**
     * Displays actually set color
     */
    private void displayColor()
    {
        this.setText(this.getColorString());
        this.setForeground(this.getFontColor());
        this.setBackground(this.color);
    }
    
    /**
     * Gets textual representation of actual color
     * @return Textual representation of actual color
     */
    private String getColorString()
    {
        return String.format("#%02x%02x%02x", this.color.getRed(), this.color.getGreen(), this.color.getBlue());  
    }
    
    /**
     * Selects font color according to actual color
     * @return Font color depending on actual color
     */
    private Color getFontColor()
    {
        Color reti = Color.BLACK;
        if ((this.color.getRed() + this.color.getBlue() + this.color.getGreen()) < (255 * 3) / 2)
            // Select white color for dark colors
        {
            reti = Color.WHITE;
        }
        return reti;
    }
}
