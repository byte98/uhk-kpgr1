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
import cz.uhk.fim.skodaji1.kpgr1.hw01.graphics.Raster;
import cz.uhk.fim.skodaji1.kpgr1.hw01.model.Point;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Objects;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
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
    public static final int WIDTH = 800;
    
    /**
     * Default height of window
     */
    public static final int HEIGHT = 600;
    
    /**
     * Reference to controller of this window
     */
    private MainWindowController controller;
    
    /**
     * Reference to main window
     */
    private MainWindow ref;
    
    //<editor-fold defaultstate="collapsed" desc="UI components">
    
    /**
     * Toolbar
     */
    private JToolBar toolBar;
    
    /**
     * Cursor button from toolbar
     */
    private ToggleButton buttonToolCursor;
    
    /**
     * Hand button from toolbar
     */
    private ToggleButton buttonToolHand;
    
    /**
     * Viewer of selected background color
     */
    private ColorDisplay backgroundColor;
    
    /**
     * Viewer of selected foreground color
     */
    private ColorDisplay foregroundColor;
    
    /**
     * Canvas for drawing
     */
    private Canvas canvas;
    
    /**
     * Line button from toolbar
     */
    private ToggleButton buttonModeLine;
    
    /**
     * Triangle button from toolbar
     */
    private ToggleButton buttonModeTriangle;
    
    /**
     * Polygon button from toolbar
     */
    private ToggleButton buttonModePolygon;
    //</editor-fold>
    
    private boolean controlsEnabled = true;
    
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
        this.ref = this;
    }
    
    /**
     * Initializes components of window
     */
    private void initializeComponents()
    {
        //<editor-fold defaultstate="collapsed" desc="Top toolbar">
        this.toolBar = new JToolBar();
        this.toolBar.setFloatable(true);
            //<editor-fold defaultstate="collapsed" desc="Cursor button">
            this.buttonToolCursor = new ToggleButton(Icons.CURSOR);
            this.toolBar.add(this.buttonToolCursor);
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
            this.toolBar.add(this.buttonToolHand);
            this.buttonToolHand.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    controller.toolChanged(MainWindowController.Tools.HAND);
                }
            });
            //</editor-fold>
            this.toolBar.addSeparator();
            //<editor-fold defaultstate="collapsed" desc="Background color section">
            this.toolBar.add(new Label(Icons.BACKGROUND));
            this.backgroundColor = new ColorDisplay(Color.BLACK);
            this.toolBar.add(this.backgroundColor);
            Button colorButton = new Button(Icons.PALETTE);
            colorButton.addActionListener(new ActionListener(){
            @Override
                public void actionPerformed(ActionEvent e)
                {
                    Color c = JColorChooser.showDialog(ref, "Vyberte barvu pozadí", backgroundColor.getColor());
                    if (Objects.nonNull(c))
                    {
                        controller.backgroundColorChanged(c);
                    }
                }
            
            });
            this.toolBar.add(colorButton);
            //</editor-fold>
            this.toolBar.addSeparator();
            //<editor-fold defaultstate="collapsed" desc="Foreground color section">
            this.toolBar.add(new Label(Icons.FOREGROUND));
            this.foregroundColor = new ColorDisplay(Color.BLACK);
            this.toolBar.add(this.foregroundColor);
            Button cButton = new Button(Icons.PALETTE);
            cButton.addActionListener(new ActionListener(){
            @Override
                public void actionPerformed(ActionEvent e)
                {
                    Color c = JColorChooser.showDialog(ref, "Vyberte barvu popředí", foregroundColor.getColor());
                    if (Objects.nonNull(c))
                    {
                        controller.foregroundColorChanged(c);
                    }
                }
            
            });
            this.toolBar.add(cButton);
            //</editor-fold>
            this.toolBar.addSeparator();
            //<editor-fold defaultstate="collapsed" desc="Mode selection">
                //<editor-fold defaultstate="collapsed" desc="Line">
                this.buttonModeLine = new ToggleButton(Icons.LINE);                
                toolBar.add(this.buttonModeLine);
                this.buttonModeLine.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        controller.modeChanged(MainWindowController.Modes.LINE);
                    }                    
                });
                //</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="Triangle">
                this.buttonModeTriangle = new ToggleButton(Icons.TRIANGLE);
                this.toolBar.add(this.buttonModeTriangle);
                this.buttonModeTriangle.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        controller.modeChanged(MainWindowController.Modes.TRIANGLE);
                    }                    
                });
                //</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="Polygon">
                this.buttonModePolygon = new ToggleButton(Icons.POLYGON);
                this.toolBar.add(this.buttonModePolygon);
                this.buttonModePolygon.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        controller.modeChanged(MainWindowController.Modes.POLYGON);
                    }                    
                });
                //</editor-fold>
            //</editor-fold>
            this.toolBar.addSeparator();
            //<editor-fold defaultstate="collapsed" desc="Clean canvas">
            Button cleanButton = new Button(Icons.ERASER);
            cleanButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    controller.cleanClicked();
                }            
            });
            this.toolBar.add(cleanButton);
            //</editor-fold>
        super.add(this.toolBar, BorderLayout.NORTH);
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Canvas">
        this.canvas = new Canvas();
        MouseAdapter mouseAdapter = new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e)
            {
                if (e.getButton() == MouseEvent.BUTTON1)
                {
                    controller.handleMouseDragStart(new Point(e.getX(), e.getY()));
                }
            }
            
            @Override
            public void mouseReleased(MouseEvent e)
            {
                if (e.getButton() == MouseEvent.BUTTON1)
                {
                    controller.handleMouseDragStop(new Point(e.getX(), e.getY()));
                }
            }
            
            @Override
            public void mouseDragged(MouseEvent e)
            {
                controller.handleMouseDrag(new Point(e.getX(), e.getY()));
            }
        };
        this.canvas.addMouseListener(mouseAdapter);
        this.canvas.addMouseMotionListener(mouseAdapter);
        super.add(this.canvas, BorderLayout.CENTER);
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
            case HAND:   this.buttonToolHand.setSelected(true);   break;
        }
    }
    
    /**
     * Shows selected mode
     * @param mode Mode which will be displayed as selected
     */
    public void selectMode(MainWindowController.Modes mode)
    {
        this.buttonModeLine.setSelected(false);
        this.buttonModeTriangle.setSelected(false);
        this.buttonModePolygon.setSelected(false);
        switch(mode)
        {
            case LINE:     this.buttonModeLine.setSelected(true);     break;
            case TRIANGLE: this.buttonModeTriangle.setSelected(true); break;
            case POLYGON:  this.buttonModePolygon.setSelected(true);  break;
        }
    }
    
    /**
     * Displays actually selected background color
     * @param c Background color which will be displayed
     */
    public void displayBackgroundColor(Color c)
    {
        this.backgroundColor.setColor(c);
    }
    
     /**
     * Displays actually selected foreground color
     * @param c Foreground color which will be displayed
     */
    public void displayForegroundColor(Color c)
    {
        this.foregroundColor.setColor(c);
    }
    
    /**
     * Gets actually used raster
     * @return Actual raster
     */
    public Raster getRaster()
    {
        return this.canvas.getRaster();
    }
    
    /**
     * Redraws whole canvas
     */
    public void redraw()
    {
        this.canvas.redraw();
    }
    
    /**
     * Enables controls
     */
    public void setControlsEnabled()
    {
        this.controlsEnabled = true;
        this.toolBar.setEnabled(this.controlsEnabled);
        for (Component c: this.toolBar.getComponents())
        {
            c.setEnabled(this.controlsEnabled);
        }
    }
    
    /**
     * Disables controls
     */
    public void setControlsDisabled()
    {
        this.controlsEnabled = false;
        this.toolBar.setEnabled(this.controlsEnabled);
        for (Component c: this.toolBar.getComponents())
        {
            c.setEnabled(this.controlsEnabled);
        }
    }
}
