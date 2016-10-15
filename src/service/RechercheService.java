/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javax.swing.JTable;
import model.ABMultiValue;
import model.Message;
import model.TableauModel;

/**
 *
 * @author rojo
 */
public class RechercheService {
    public static JTable getSmsRecu(String condition) throws Exception{
        TableauModel tm=new TableauModel(new Message()," and is_from_me = '0'");
        JTable tableau = new JTable(tm.getDonnees(), tm.getEntete());
        return tableau;
    }
    public static JTable getSmsEnvoyee(String condition) throws Exception{
        TableauModel tm=new TableauModel(new Message()," and is_from_me = '1'");
        JTable tableau = new JTable(tm.getDonnees(), tm.getEntete());
        return tableau;
    }
    public static JTable getSmsAll(String condition) throws Exception{
        TableauModel tm=new TableauModel(new Message(),"");
        JTable tableau = new JTable(tm.getDonnees(), tm.getEntete());
        return tableau;
    }
    public static JTable getContactAll(String condition) throws Exception{
        TableauModel tm=new TableauModel(new ABMultiValue(),"");
        JTable tableau = new JTable(tm.getDonnees(), tm.getEntete());
        return tableau;
    }
    
    public static JTable getIMessageRecu(String condition) throws Exception{
        TableauModel tm=new TableauModel(new Message()," and is_from_me = '0'");
        JTable tableau = new JTable(tm.getDonnees(), tm.getEntete());
        return tableau;
    }
    public static JTable getIMessageEnvoyee(String condition) throws Exception{
        TableauModel tm=new TableauModel(new Message()," and is_from_me = '1'");
        JTable tableau = new JTable(tm.getDonnees(), tm.getEntete());
        return tableau;
    }
    public static JTable getIMessageAll(String condition) throws Exception{
        TableauModel tm=new TableauModel(new Message(),"");
        JTable tableau = new JTable(tm.getDonnees(), tm.getEntete());
        return tableau;
    }
}
