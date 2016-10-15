/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecouteur;

import iphone.SendSms;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author rojo
 */
public class BouttonSendSms implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        SendSms s1=new SendSms();
        s1.setVisible(true);
    }
    
}
