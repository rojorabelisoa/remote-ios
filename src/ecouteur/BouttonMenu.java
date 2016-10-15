/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecouteur;

import iphone.Bienvenue;
import iphone.IphoneSms;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import service.IphoneService;

/**
 *
 * @author rojo
 */
public class BouttonMenu implements ActionListener{
    private Bienvenue b;
    public BouttonMenu(Bienvenue aThis) {
        this.b=aThis;
    }

    public BouttonMenu() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            //IphoneService.getSmsDb() ;
            IphoneSms s1=new IphoneSms();
            s1.setVisible(true);
            b.setVisible(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
