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
import cz.uhk.fim.skodaji1.kpgr1.hw01.graphics.BresenhamLineRasterizer;
import cz.uhk.fim.skodaji1.kpgr1.hw01.graphics.LineRasterizer;
import cz.uhk.fim.skodaji1.kpgr1.hw01.graphics.Raster;
import cz.uhk.fim.skodaji1.kpgr1.hw01.model.Line;
import cz.uhk.fim.skodaji1.kpgr1.hw01.model.Point;
import cz.uhk.fim.skodaji1.kpgr1.hw01.model.Shape;
import cz.uhk.fim.skodaji1.kpgr1.hw01.view.Icons;
import cz.uhk.fim.skodaji1.kpgr1.hw01.view.MainWindow;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
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
     * Class representing snapshot of actual state of main window
     */
    private static class Snapshot
    {
        /**
         * Previous state of main window
         */
        private Snapshot previous;
        
        /**
         * Next state of main window
         */
        private Snapshot next;
        
        /**
         * Background color of window
         */
        private Color background;
        
        /**
         * Foreground color of window
         */
        private Color foreground;
        
        /**
         * Shapes drawn in window
         */
        private List<Shape> shapes;
        
        /**
         * Creates new snapshot
         * @param shapes Shapes drawn in window
         * @param background Background color of window
         * @param foreground Foreground color of window
         */
        public Snapshot(List<Shape> shapes, Color background, Color foreground)
        {
            this.shapes = new ArrayList<>();
            for (Shape s: shapes)
            {
                this.shapes.add(s.clone());
            }
            this.previous = null;
            this.next = null;
            this.background = background;
            this.foreground = foreground;
        }
        
        //<editor-fold defaultstate="collapsed" desc="Getters & setters">
        public Snapshot getPrevious()
        {
            return previous;
        }

        public void setPrevious(Snapshot previous)
        {
            this.previous = previous;
        }

        public Snapshot getNext()
        {
            return next;
        }

        public void setNext(Snapshot next)
        {
            this.next = next;
        }

        public Color getBackground()
        {
            return background;
        }

        public void setBackground(Color background)
        {
            this.background = background;
        }

        public Color getForeground()
        {
            return foreground;
        }

        public void setForeground(Color foreground)
        {
            this.foreground = foreground;
        }

        public List<Shape> getShapes()
        {
            return shapes;
        }

        public void setShapes(List<Shape> shapes) 
        {
            this.shapes = shapes;
        }
        //</editor-fold>
    }
    
    /**
     * Radius from points to edit them
     */
    private static final int RADIUS = 10;
    
    /**
     * Enumeration of all available tools
     */
    public enum Tools
    {
        CURSOR, HAND
    }
    
    /**
     * Enumeration of all available modes of application
     */
    public enum Modes
    {
        LINE, TRIANGLE, POLYGON
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
     * Actually used tool
     */
    private Tools tool = Tools.CURSOR;
    
    /**
     * Mode of program
     */
    private Modes mode = Modes.LINE;
    
    /**
     * Creates new controller of main window
     */
    public MainWindowController()
    {
        this.reference = this;
        this.shapes = new ArrayList<>();
        this.actualState = new Snapshot(this.shapes, this.backgroundColor, this.foregroundColor);
    }
    
    /**
     * Actually selected color of background
     */
    private Color backgroundColor = Color.BLACK;
    
    /**
     * Actually selected color of foreground
     */
    private Color foregroundColor = Color.WHITE;
    
    /**
     * Rasterizer of lines
     */
    private LineRasterizer lineRasterizer;
    
    /**
     * Shape which is actually drawn
     */
    private Shape actual = null;
    
    /**
     * Actually processed point
     */
    private Point point = null;
        
    
    /**
     * List of all inserted shapes
     */
    private List<Shape> shapes;
    
    /**
     * Actual state of window
     */
    private Snapshot actualState;
    
    /**
     * Generates new line rasterizer.
     * Here is the place where default rasterizer is set.
     * @param raster Object, to which line will be drawn
     * @return Object which can rasterize line
     */
    private LineRasterizer lineRasterizer(Raster raster)
    {
        return new BresenhamLineRasterizer(raster);
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
                    mainWindow.selectTool(tool);
                    mainWindow.selectMode(mode);
                    mainWindow.displayBackgroundColor(backgroundColor);
                    mainWindow.displayForegroundColor(foregroundColor);
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
        this.tool = tool;
        this.mainWindow.selectTool(tool);
        this.mainWindow.setModesEnabled(this.tool == Tools.CURSOR);
        this.mainWindow.setCursor(
                this.tool == Tools.CURSOR
                ? Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)
                : Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );
    }
    
    /**
     * Handles change of mode
     * @param mode Actually selected mode
     */
    public void modeChanged(Modes mode)
    {
        this.mode = mode;
        this.mainWindow.selectMode(mode);
    }
    
    /**
     * Handles change of background color
     * @param c New background color
     */
    public void backgroundColorChanged(Color c)
    {
        this.backgroundColor = c;
        this.mainWindow.displayBackgroundColor(c);
        this.mainWindow.getRaster().setClearColor(c.getRGB());
    }
    
    /**
     * Handles change of foreground color
     * @param c New foreground color
     */
    public void foregroundColorChanged(Color c)
    {
        this.foregroundColor = c;
        this.mainWindow.displayForegroundColor(c);
    }
    
    /**
     * Handles click on clean button
     */
    public void cleanClicked()
    {
        this.mainWindow.getRaster().clear();
        this.shapes.clear();
        this.mainWindow.redraw();
        this.createSnapshot();
    }
    
    
    /**
     * Handles mouse drag start
     * @param position Position of mouse cursor
     */
    public void handleMouseDragStart(Point position)
    {
        this.handleMouseEvent(position, MouseEvent.MOUSE_PRESSED);
    }
    
    
    /**
     * Handles mouse drag
     * @param position Position of mouse cursor
     */
    public void handleMouseDrag(Point position)
    {
        this.handleMouseEvent(position, MouseEvent.MOUSE_DRAGGED);
    }
            
    
    /**
     * Handles mouse drag stop
     * @param position Position of mouse cursor
     */
    public void handleMouseDragStop(Point position)
    {
        this.handleMouseEvent(position, MouseEvent.MOUSE_RELEASED);
    }
    
    /**
     * Handles any mouse event
     * @param position Position of cursor
     * @param mouseEvent Type of mouse action
     */
    private void handleMouseEvent(Point position, int mouseAction)
    {
        if (this.tool == Tools.CURSOR && this.mode == Modes.LINE)
        {
            this.handleLine(position, mouseAction);
        }
        else if (this.tool == Tools.HAND)
        {
            this.handleHandTool(position, mouseAction);
        }
        
    }
    
    /**
     * Handles handle tool
     * @param position Position of cursor
     * @param mouseAction Type of mouse action
     */
    private void handleHandTool(Point position, int mouseAction)
    {
        if (mouseAction == MouseEvent.MOUSE_PRESSED)
        {
            this.actual = null;
            this.point = null;
            
            // Find nearest point from all shapes
            Point point = null;
            Shape shape = null;
            double dist = Double.MAX_VALUE;
            for (Shape s: this.shapes)
            {
                Point p = s.getNearestPoint(position);
                if (p.distanceTo(position) < dist)
                {
                    dist = p.distanceTo(position);
                    point = p;
                    shape = s;
                }
            }
            // Check if found something and if is in allowed distance
            if (Objects.nonNull(point) && Objects.nonNull(shape) && dist < MainWindowController.RADIUS)
            {
                this.actual = shape;
                this.point = point;
                this.actual.setEditing(true);
            }
        }
        else if (mouseAction == MouseEvent.MOUSE_DRAGGED && Objects.nonNull(this.actual) && Objects.nonNull(this.point))
        {
            this.actual.setPoint(this.point, position);
            this.point = position;
            this.redraw();
        }
        else if (mouseAction == MouseEvent.MOUSE_RELEASED && Objects.nonNull(this.actual) && Objects.nonNull(this.point))
        {
            this.actual.setEditing(false);
            this.actual = null;
            this.point = null;
            this.redraw();
            this.createSnapshot();
        }
    }
    
    /**
     * Handles mouse events connected with line
     * @param position Position of mouse cursor
     * @param mouseAction Type of mouse action
     */
    private void handleLine(Point position, int mouseAction)
    {
        if (mouseAction == MouseEvent.MOUSE_PRESSED)
        {
           this.lineRasterizer = this.lineRasterizer(this.mainWindow.getRaster());
           this.point = position;
           this.lineRasterizer.setDashed(true);
           this.actual = new Line(this.foregroundColor);
           this.shapes.add(this.actual);
           this.actual.addPoint(this.point);
           this.actual.addPoint(this.point);
           this.actual.setEditing(true);
        }
        else if (mouseAction == MouseEvent.MOUSE_DRAGGED)
        {
            this.lineRasterizer.setDashed(true);
            this.actual.setPoint(this.point, position);
            this.point = position;
            this.redraw();
        }
        else if (mouseAction == MouseEvent.MOUSE_RELEASED)
        {
            this.actual.setPoint(this.point, position);
            this.actual.setEditing(false);
            this.redraw();
            this.createSnapshot();
        }
    }
    
    
    
    /**
     * Redraws canvas
     */
    private void redraw()
    {
        this.mainWindow.getRaster().clear();
        // Draw lines
        for(Shape s: this.shapes)
        {
            this.lineRasterizer.setDashed(s.getEditing());
            for (Line l: s.getLines())
            {
                this.lineRasterizer.rasterize(l);
            }
        }
        this.mainWindow.redraw();
    }
    
    /**
     * Creates snapshot of actual state of window
     */
    private void createSnapshot()
    {
        Snapshot s = new Snapshot(this.shapes, this.backgroundColor, this.foregroundColor);
        this.actualState.setNext(s);
        s.setPrevious(this.actualState);
        this.mainWindow.setUndoEnabled(Objects.nonNull(this.actualState.getPrevious()));
        this.mainWindow.setRedoEnabled(Objects.nonNull(this.actualState.getNext()));
        this.actualState = s;
    }
    
    /**
     * Reloads actual state of application
     */
    private void reloadState()
    {
        this.shapes = this.actualState.getShapes();
        this.backgroundColor = this.actualState.getBackground();
        this.foregroundColor = this.actualState.getForeground();
        this.mainWindow.displayBackgroundColor(this.backgroundColor);
        this.mainWindow.getRaster().setClearColor(this.backgroundColor.getRGB());
        this.mainWindow.displayForegroundColor(this.foregroundColor);
        this.redraw();
    }
    
    /**
     * Handles click on undo button
     */
    public void undoClicked()
    {
        if (Objects.nonNull(this.actualState.getPrevious()))
        {
            this.actualState = this.actualState.getPrevious();
            this.reloadState();
            this.mainWindow.setUndoEnabled(Objects.nonNull(this.actualState.getPrevious()));
            this.mainWindow.setRedoEnabled(Objects.nonNull(this.actualState.getNext()));
        }
    }
    
    /**
     * Handles click on redo button
     */
    public void redoClicked()
    {
        if (Objects.nonNull(this.actualState.getNext()))
        {
            this.actualState = this.actualState.getNext();
            this.reloadState();
            this.mainWindow.setUndoEnabled(Objects.nonNull(this.actualState.getPrevious()));
            this.mainWindow.setRedoEnabled(Objects.nonNull(this.actualState.getNext()));
        }
    }
}
