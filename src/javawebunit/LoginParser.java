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

import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class LoginParser implements Runnable {
    
    private final WebPage page;
    private final JFrame window = new JFrame("Login");
    private final SpringLayout springLayout = new SpringLayout();
    private final ImageIcon logoImage;
    private final ImageIcon loadingImage;
    
    private final JLabel logo = new JLabel();
    private final JTextField usernameField = new JTextField();
    private final JPasswordField passwordField = new JPasswordField();
    private final JButton loginButton = new JButton("Login");
    private final JLabel usernameLabel = new JLabel("Username:"),
                         passwordLabel = new JLabel("Password:");

    public Thread parseLogin = new Thread(this);
    public boolean running = false;
    
    // -------------------------------------------------------------------------
    
    /*
     * Constructor
     * 
     */
    public LoginParser(final WebPage webPage) {
        this.page = webPage;
        this.logoImage = new ImageIcon(this.getClass().getResource("resources/logo.png"));
        this.loadingImage = new ImageIcon(this.getClass().getResource("resources/circle.gif"));
        this.showFrame();
    }
    
    /*
     * GetLoginButton
     * 
     * Get JButton refference
     * 
     * @access  public
     * @return  JButton
     */
    public final JButton getLoginButton() {
        return this.loginButton;
    }
    
    /*
     * GetWindow
     * 
     * Get JFrame refference
     * 
     * @access  public
     * @return  JFrame
     */
    public final JFrame getWindow() {
        return this.window;
    }
    
    /*
     * HideFrame
     * 
     * hide login frame
     * 
     * @access  public
     */
    public final void hideFrame() {
        this.window.setVisible(false);
        this.loginButton.removeMouseListener(this.page.mouseListener);
    }

    /*
     * Run
     * 
     * Runnable Thread
     */
    @Override
    public void run() {
        this.running = true;
        try {
            this.logo.setIcon(this.loadingImage);
            this.page.setPage("https://sms7.schoolsoft.se/nti/jsp/Login.jsp");

            final HtmlForm form = this.page.htmlPage.getFormByName("userForm");
            final HtmlSubmitInput submitButton = form.getInputByName("button");
            final HtmlSelect selection = form.getSelectByName("usertype");
            final HtmlTextInput usernameInput = form.getInputByName("ssusername");
            final HtmlPasswordInput passwordInput = form.getInputByName("sspassword");
            
            selection.getOption(1).setSelected(true);
            usernameInput.setText(this.usernameField.getText());
            passwordInput.setText(this.passwordField.getText());

            this.page.htmlPage = submitButton.click();
            if (this.page.htmlPage.asText().contains("Varning")) {
                JOptionPane.showMessageDialog(this.window, "Invalid login information!", "Error", JOptionPane.WARNING_MESSAGE);
                this.logo.setIcon(this.logoImage);
            } else {
                this.hideFrame();
                final Timeline timeline = new Timeline(this.page);
            }
        } catch (IOException ex) {
            System.out.println("Exception caught while logging in! "+ex.getMessage());
            JOptionPane.showMessageDialog(this.window, "Failed to login; please try again!", "Error", JOptionPane.WARNING_MESSAGE);
        } finally {
            this.running = false;
        }
    }
    
    public final void showFrame() {
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.setSize(300, 300);
        this.window.setResizable(false);
        this.window.setLocationRelativeTo(null);
        
        this.logo.setIcon(this.logoImage);
        
        final Dimension dimension = new Dimension();
        dimension.setSize(120, 20);
        this.usernameField.setPreferredSize(dimension);
        this.passwordField.setPreferredSize(dimension);
        this.usernameLabel.setForeground(Color.WHITE);
        this.passwordLabel.setForeground(Color.WHITE);
        
        this.loginButton.setPreferredSize(new Dimension(70,20));
        this.loginButton.setFocusable(false);
        this.loginButton.addMouseListener(this.page.mouseListener);
        
        final Container container = this.window.getContentPane();
        container.setBackground(new Color(93, 93, 93));
        container.setLayout(this.springLayout);
        container.add(this.logo);
        container.add(this.usernameField);
        container.add(this.passwordField);
        container.add(this.usernameLabel);
        container.add(this.passwordLabel);
        container.add(this.loginButton);
        
        this.springLayout.putConstraint(SpringLayout.WEST, this.logo, 125, SpringLayout.WEST, container);
        this.springLayout.putConstraint(SpringLayout.NORTH, this.logo, 50, SpringLayout.NORTH, container);
        this.springLayout.putConstraint(SpringLayout.SOUTH, this.usernameField, -100, SpringLayout.SOUTH, container);
        this.springLayout.putConstraint(SpringLayout.EAST, this.usernameField, -50, SpringLayout.EAST, container);
        this.springLayout.putConstraint(SpringLayout.SOUTH, this.passwordField, -70, SpringLayout.SOUTH, container);
        this.springLayout.putConstraint(SpringLayout.EAST, this.passwordField, -50, SpringLayout.EAST, container);
        this.springLayout.putConstraint(SpringLayout.SOUTH, this.usernameLabel, -3, SpringLayout.SOUTH, this.usernameField);
        this.springLayout.putConstraint(SpringLayout.WEST, this.usernameLabel, 50, SpringLayout.WEST, container);
        this.springLayout.putConstraint(SpringLayout.SOUTH, this.passwordLabel, -3, SpringLayout.SOUTH, this.passwordField);
        this.springLayout.putConstraint(SpringLayout.WEST, this.passwordLabel, 50, SpringLayout.WEST, container);
        this.springLayout.putConstraint(SpringLayout.SOUTH, this.loginButton, -40, SpringLayout.SOUTH, container);
        this.springLayout.putConstraint(SpringLayout.EAST, this.loginButton, -50, SpringLayout.EAST, this.passwordField);
        window.setVisible(true);
    }
}
