/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecouteur;

import iphone.SendSms;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.IphoneService;

/**
 *
 * @author rojo
 */
public class BouttonConfirmSend implements ActionListener{
    private String numero;
    private String text;
    private SendSms sms;

    public BouttonConfirmSend() {
    }
    
    public BouttonConfirmSend(SendSms sms) {
        this.sms=sms;
        this.numero=sms.getNumberTextField().getText();
        this.text=sms.getTextTextField().getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            System.out.println("num = "+sms.getNumberTextField().getText());
            System.out.println("text = "+sms.getTextTextField().getText());
            IphoneService.sendSMS(sms.getNumberTextField().getText(), sms.getTextTextField().getText());
            sms.dispose();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
}
