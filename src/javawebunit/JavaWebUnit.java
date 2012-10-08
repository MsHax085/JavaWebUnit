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

import com.gargoylesoftware.htmlunit.WebClient;

/*
 * JavaWebUnit Class
 * 
 * @package     javawebunit
 * @category    Application Startup
 * @author      Richard Dahlgren
 */
public class JavaWebUnit {
    
    private final WebClient webClient = new WebClient();
    
    // -------------------------------------------------------------------------
    
    /*
     * Constructor
     * 
     */
    public static void main(String[] args) {
        final JavaWebUnit javaWebUnit = new JavaWebUnit();
    }
    
    /*
     * Constructor - part
     */
    public JavaWebUnit() {
        this.webClient.setCssEnabled(false);
        this.webClient.setJavaScriptEnabled(true);
        this.webClient.setRedirectEnabled(true);
        final WebPage webPage = new WebPage(this);
    }
    
    /*
     * Get Web Client
     * 
     * Fetch WebClient Refference
     * 
     * @access  public
     */
    public final WebClient getWebClient() {
        return this.webClient;
    }
    
    /*
     * CloseClient
     * 
     * Close the WebClient
     * 
     * @access  public
     */
    public final void closeClient() {
        if (this.webClient == null) {
            this.webClient.closeAllWindows();
        }
    }
}
