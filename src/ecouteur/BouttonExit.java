/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecouteur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author rojo
 */
public class BouttonExit implements ActionListener{
    

    @Override
    public void actionPerformed(ActionEvent e) {
         System.exit(0); //To change body of generated methods, choose Tools | Templates.
    }
    
}
