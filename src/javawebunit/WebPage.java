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

package javawebunit;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import javawebunit.interaction.MouseEvent;
import javax.swing.JOptionPane;

/*
 * WebPage class
 * 
 * @package     javawebunit
 * @category    WebPage handler
 * @author      Richard Dahlgren
 */
public class WebPage {
    
    public final JavaWebUnit jwu;
    public final MouseEvent mouseListener = new MouseEvent(this);
    public final LoginParser loginParser = new LoginParser(this);
    public HtmlPage htmlPage;
    
    // -------------------------------------------------------------------------
    
    /*
     * Constructor
     * 
     */
    public WebPage(final JavaWebUnit javaWebUnit) {
        this.jwu = javaWebUnit;
    }
    
    /*
     * SetPage
     * 
     * Set new webpage
     * 
     * @access  public
     * @param   String
     */
    public final void setPage(final String url) {
        try {
            this.htmlPage = this.jwu.getWebClient().getPage(url);
        } catch (Exception ex) {
            this.closeApplication("Failed to retrive webpage!", ex.getMessage());
        }
    }
    
    /*
     * CloseApplication
     * 
     * Close due to error exception
     * 
     * @access  private
     * @param   String
     * @param   String
     */
    private void closeApplication(final String message, final String error) {
        System.out.println(message + " " + error);
        JOptionPane.showMessageDialog(this.loginParser.getWindow(), message, "Error", JOptionPane.WARNING_MESSAGE);
        System.exit(0);
    }
}
