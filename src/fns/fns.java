/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fns;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import ui.welcomeFrame;

/**
 *
 * @author HP
 */
public class fns {
     public static void main(String[] args) {
        // TODO code application logic here
       welcomeFrame wf= new welcomeFrame();
       Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
       wf.setVisible(true);
       wf.setSize(d);    
}
}


