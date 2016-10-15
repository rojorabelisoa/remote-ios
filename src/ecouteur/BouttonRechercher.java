/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecouteur;

import iphone.IphoneSms;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import model.ABMultiValue;
import model.Message;
import model.TableauModel;
import service.TableauService;

/**
 *
 * @author rojo
 */
public class BouttonRechercher implements ActionListener {

    private String text;
    private IphoneSms s;
    TableRowSorter<TableModel> rowSorter;

    @Override
    public void actionPerformed(ActionEvent e) {
        System.err.println("page now"+s.getPageActuelle());
        try {
            if(s.getPageActuelle()=="AllSms")
                rowSorter = new TableRowSorter<>(TableauService.getSmsAll().getModel());
            else if(s.getPageActuelle()=="AllIMessage")
                rowSorter = new TableRowSorter<>(TableauService.getIMessageAll().getModel());
            else if(s.getPageActuelle()=="ReceivedIMessage")
                rowSorter = new TableRowSorter<>(TableauService.getIMessageRecu().getModel());
            else if(s.getPageActuelle()=="SendIMessage")
                rowSorter = new TableRowSorter<>(TableauService.getIMessageEnvoyee().getModel());
            else if(s.getPageActuelle()=="ReceivedSms")
                rowSorter = new TableRowSorter<>(TableauService.getSmsRecu().getModel());
            else if(s.getPageActuelle()=="SendSms")
                rowSorter = new TableRowSorter<>(TableauService.getSmsEnvoyee().getModel());
            else 
                rowSorter = new TableRowSorter<>(TableauService.getContactAll().getModel());
            s.getjTable1().setRowSorter(rowSorter);
            s.getjTable1().removeAll();
            String text = s.getRecherche().getText();
            if (text.length() == 0) {
                rowSorter.setRowFilter(null);
            } else {
                rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" +text));
            }
            s.getjTable1().repaint();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public BouttonRechercher(IphoneSms s) {
        this.s = s;
    }

}
