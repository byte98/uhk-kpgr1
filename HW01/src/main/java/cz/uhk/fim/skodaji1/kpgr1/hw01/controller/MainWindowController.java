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
package cz.uhk.fim.skodaji1.kpgr1.hw01.controller;

import com.formdev.flatlaf.FlatDarkLaf;
import cz.uhk.fim.skodaji1.kpgr1.hw01.view.Icons;
import cz.uhk.fim.skodaji1.kpgr1.hw01.view.MainWindow;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Controller of behavior of main window
 * @author Jiri Skoda <skodaji1@uhk.cz>
 */
public class MainWindowController
{
    /**
     * Enumeration of all available tools
     */
    public enum Tools
    {
        CURSOR, HAND
    }
    
    /**
     * Reference to this main window controller
     */
    private MainWindowController reference;
    
    /**
     * Main window of application
     */
    private MainWindow mainWindow;
    
    /**
     * Creates new controller of main window
     */
    public MainWindowController()
    {
        this.reference = this;
    }
    
    /**
     * Shows main window
     */
    public void show()
    {
        SwingUtilities.invokeLater
        (
            new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        UIManager.setLookAndFeel( new FlatDarkLaf() );
                    } catch (UnsupportedLookAndFeelException ex)
                    {
                        Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Icons.init();
                    mainWindow = new MainWindow(reference);
                    mainWindow.setVisible(true);
                    mainWindow.selectTool(Tools.CURSOR);
                }
            }
        );
    }
    
    /**
     * Handles change of tool
     * @param tool Actually selected tool
     */
    public void toolChanged(Tools tool)
    {
        mainWindow.selectTool(tool);
    }
}
