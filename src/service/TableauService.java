/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javax.swing.JTable;
import model.ABMultiValue;
import model.*;
import model.TableauModel;

/**
 *
 * @author rojo
 */
public class TableauService {
    public static JTable getSmsRecu() throws Exception{
        TableauModel tm=new TableauModel(new Message()," and m.service='SMS' and is_from_me = '0'");
        JTable tableau = new JTable(tm.getDonnees(), tm.getEntete());
        return tableau;
    }
    public static JTable getSmsEnvoyee() throws Exception{
        TableauModel tm=new TableauModel(new Message()," and m.service='SMS' and is_from_me = '1'");
        JTable tableau = new JTable(tm.getDonnees(), tm.getEntete());
        return tableau;
    }
    public static JTable getSmsAll() throws Exception{
        TableauModel tm=new TableauModel(new Message()," and m.service='SMS'");
        JTable tableau = new JTable(tm.getDonnees(), tm.getEntete());
        return tableau;
    }
    public static JTable getContactAll() throws Exception{
        TableauModel tm=new TableauModel(new ABMultiValue(),"");
        JTable tableau = new JTable(tm.getDonnees(), tm.getEntete());
        return tableau;
    }
    
    public static JTable getIMessageRecu() throws Exception{
        TableauModel tm=new TableauModel(new Message()," and m.service='iMessage' and is_from_me = '0'");
        JTable tableau = new JTable(tm.getDonnees(), tm.getEntete());
        return tableau;
    }
    public static JTable getIMessageEnvoyee() throws Exception{
        TableauModel tm=new TableauModel(new Message()," and m.service='iMessage' and is_from_me = '1'");
        JTable tableau = new JTable(tm.getDonnees(), tm.getEntete());
        return tableau;
    }
    public static JTable getIMessageAll() throws Exception{
        TableauModel tm=new TableauModel(new Message()," and m.service='iMessage'");
        JTable tableau = new JTable(tm.getDonnees(), tm.getEntete());
        return tableau;
    }
}
