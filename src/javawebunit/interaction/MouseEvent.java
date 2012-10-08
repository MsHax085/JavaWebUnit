/*
    * JavaWebUnit, Show schoolsoft scheme.
    * Copyright (C) 2012 Richard Dahlgren
    * 
    * This program is free software; you can redistribute it and/or modify
    * it under the terms of the GNU General Public License as published by
    * the Free Software Foundation; either version 3 of the License, or
    * (at your option) any later version.
    * 
    * This program is distributed in the hope that it will be useful,
    * but WITHOUT ANY WARRANTY; without even the implied warranty of
    * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    * GNU General Public License for more details.
    * 
    * You should have received a copy of the GNU General Public License
    * along with this program; if not, write to the Free Software Foundation,
    * Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
 */

package javawebunit.interaction;

import java.awt.event.MouseListener;
import javawebunit.LoginParser;
import javawebunit.WebPage;

/*
 * MouseEvent Class
 * 
 * @package     javawebunit.interaction
 * @category    Event Listening
 * @author      Richard Dahlgren
 */
public class MouseEvent implements MouseListener {
    
    private final WebPage page;
    
    // -------------------------------------------------------------------------
    
    /*
     * Constructor
     * 
     */
    public MouseEvent(final WebPage webPage) {
        this.page = webPage;
    }

    /*
     * MousePressed
     * 
     * Mouse Press Event Parser
     * 
     * @access  public
     * @param   MouseEvent
     */
    @Override
    public final void mousePressed(final java.awt.event.MouseEvent e) {
        final Object source = e.getSource();
        final LoginParser parser = this.page.loginParser;
        if (source == parser.getLoginButton()) {
            if (!parser.running) {
                parser.parseLogin = new Thread(parser);
                parser.parseLogin.start();
            }
        }
    }
    
    /*
     * Unused functions
     * 
     */
    @Override public void mouseClicked(java.awt.event.MouseEvent e) {}
    @Override public void mouseReleased(java.awt.event.MouseEvent e) {}
    @Override public void mouseEntered(java.awt.event.MouseEvent e) {}
    @Override public void mouseExited(java.awt.event.MouseEvent e) {}
}
