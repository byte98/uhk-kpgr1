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

import cz.uhk.fim.skodaji1.kpgr1.hw01.controller.MainWindowController;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;

/**
 * Class representing main window of application
 * @author Jiri Skoda <skodaji1@uhk.cz>
 */
public class MainWindow extends JFrame
{
    /**
     * Title of window
     */
    private static final String TITLE = "UHK FIM KPGR1: Uloha 1 - Jiri Skoda";
    
    /**
     * Default width of window
     */
    private static final int WIDTH = 800;
    
    /**
     * Default height of window
     */
    private static final int HEIGHT = 600;
    
    /**
     * Reference to controller of this window
     */
    private MainWindowController controller;
    
    //<editor-fold defaultstate="collapsed" desc="UI components">
    /**
     * Cursor button from toolbar
     */
    private ToggleButton buttonToolCursor;
    
    /**
     * Hand button from toolbar
     */
    private ToggleButton buttonToolHand;
    //</editor-fold>
    
    /**
     * Creates new main window of application
     * @param controller Controller of behavior of main window
     */
    public MainWindow(MainWindowController controller)
    {
        super.setTitle(MainWindow.TITLE);
        super.setSize(MainWindow.WIDTH, MainWindow.HEIGHT);
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        super.setLayout(new BorderLayout(10, 10));
        this.initializeComponents();
        this.controller = controller;
    }
    
    /**
     * Initializes components of window
     */
    private void initializeComponents()
    {
        //<editor-fold defaultstate="collapsed" desc="Top toolbar">
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(true);
            //<editor-fold defaultstate="collapsed" desc="Cursor button">
            this.buttonToolCursor = new ToggleButton(Icons.CURSOR);
            toolBar.add(this.buttonToolCursor);
            this.buttonToolCursor.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    controller.toolChanged(MainWindowController.Tools.CURSOR);
                }
            });
            //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="Hand button">
            this.buttonToolHand = new ToggleButton(Icons.HAND);
            toolBar.add(this.buttonToolHand);
            this.buttonToolHand.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    controller.toolChanged(MainWindowController.Tools.HAND);
                }
            });
            //</editor-fold>
            toolBar.addSeparator();
        super.add(toolBar, BorderLayout.NORTH);
        //</editor-fold>
    }
    
    /**
     * Shows selected tool
     * @param tool Tool which will be displayed as selected
     */
    public void selectTool(MainWindowController.Tools tool)
    {
        this.buttonToolCursor.setSelected(false);
        this.buttonToolHand.setSelected(false);
        switch(tool)
        {
            case CURSOR: this.buttonToolCursor.setSelected(true); break;
            case HAND:   this.buttonToolHand.setSelected(true);  break;
        }
    }
}
