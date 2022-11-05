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

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Class representing button used for UI
 * @author Jiri Skoda <jiri.skoda@student.upce.cz>
 */
public class Button extends JButton
{
    /**
     * Icon which will be displayed in button
     */
    private ImageIcon icon;
    
    /**
     * Creates new button
     * @param icon Icon which will be displayed in button
     */
    public Button(ImageIcon icon)
    {
        super(icon);
        this.icon = icon;
    }
    
    /**
     * Creates new button
     * @param icon Icon which will be displayed in button
     * @param toolTipHeader Header of tool tip
     * @param toolTipBody Body of tool tip
     */
    public Button(ImageIcon icon, String toolTipHeader, String toolTipBody)
    {
        this(icon);
        super.setToolTipText(String.format("<html><strong>%s</strong><br>%s</html>",toolTipHeader, toolTipBody));
    }
    
    /**
     * Creates new button
     * @param icon Icon which will be displayed in button
     * @param toolTipHeader Header of tool tip
     * @param toolTipBody Body of tool tip
     * @param toolTipKey Tip for keyboard shortcut
     */
    public Button(ImageIcon icon, String toolTipHeader, String toolTipBody, String toolTipKey)
    {
        this(icon);
        StringBuilder key = new StringBuilder();
        String[] parts = toolTipKey.split("\\+");
        for (int i = 0; i < parts.length; i++)
        {
            key.append("<kbd>");
            key.append(parts[i].trim());
            key.append("</kbd>");
            if (i < parts.length- 1)
            {
                key.append(" + ");
            }
        }
        super.setToolTipText(String.format("<html><strong>%s</strong><br>%s<br><i>Klávesová zkratka: %s</i></html>",toolTipHeader, toolTipBody, key.toString()));
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHints(rh);
        g2.drawImage(this.icon.getImage(),
                (this.getWidth() / 2) - (this.icon.getIconWidth() / 2),
                (this.getHeight() / 2) - (this.icon.getIconHeight() / 2),null);
        super.paintComponent(g);
    }
}
