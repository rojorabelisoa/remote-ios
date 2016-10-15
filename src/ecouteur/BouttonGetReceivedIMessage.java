/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecouteur;

import iphone.IphoneSms;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import service.TableauService;

/**
 *
 * @author rojo
 */
public class BouttonGetReceivedIMessage  implements ActionListener {
    private IphoneSms s;

    public BouttonGetReceivedIMessage(IphoneSms aThis) {
        this.s=aThis;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            s.setPageActuelle("ReceivedIMessage");
            s.getRecherche().setText("");
            s.getTitre().setText("Liste des iMessages Recus");
            s.getjTable1().removeAll();
            JTable tableau = TableauService.getIMessageRecu();
            s.getjTable1().setModel(tableau.getModel());
            RowSorter<TableModel> sort=new TableRowSorter<>(tableau.getModel());
            s.getjTable1().setRowSorter(sort);
            s.getjTable1().repaint();
            s.getjTable1().setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
}
