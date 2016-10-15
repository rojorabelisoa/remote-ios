package iphone;

import model.Message;
import service.IphoneService;

public class Iphone {

    public static void main(String[] arg) {
        Message[] sms;
        try {
            IphoneService.getSmsDb() ;
            /**
             * ******************************* MAKA MESSAGE
             * *****************************
             */
            /*sms=MessageService.getMessage("");
             for(int i=0;i<sms.length;i++){
             System.out.print(sms[i].getRowid()+ " ");
             System.out.print(sms[i].getDate()+ " ");
             System.out.print(sms[i].getPhoneNumber()+ " : ");
             System.out.println(sms[i].getText()+ " ");
             }*/
            Bienvenue panel = null;

            panel = new Bienvenue();
            panel.setVisible(true);
            //IphoneService.sendSMS("+261340433511", "test sms ");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
