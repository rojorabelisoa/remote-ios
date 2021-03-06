/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import bean.ClassMAPTable;
import genericdao.ClassDeRechercheSwing;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author rojo
 */
public class TableauModel extends AbstractTableModel{
    private static Object[][]donnees;
    private static String[] entete;

    /**
     * @param aDonnees the donnees to set
     */
    public static void setDonnees(Object[][] aDonnees) {
        donnees = aDonnees;
    }

    /**
     * @param aEntete the entete to set
     */
    public static void setEntete(String[] aEntete) {
        entete = aEntete;
    }

    public TableauModel(ClassMAPTable o,String condition) throws Exception {
        
            this.donnees=ClassDeRechercheSwing.rechercher(o, condition, null);
            Field[] attr = o.getClass().getDeclaredFields();
            String title[] = new String[attr.length];
            for(int m=0;m<attr.length;m++)
                title[m]=attr[m].getName();
            this.entete=title;
        
    }

    /**
     * @return the donnees
     */
    public static Object[][] getDonnees() {
        return donnees;
    }

    /**
     * @return the entete
     */
    public static String[] getEntete() {
        return entete;
    }

    /**
     * @param aDonnees the donnees to set
     */
    
    
   /* public void tableChanged(TableModelEvent e) {
        int ligneModifier=e.getLastRow();
        int colonneModifier=e.getColumn();
        Object[] o=this.getObjetLigne(ligneModifier);
        this.insererObjet(o);  // inserer
        this.updateObjet(o);  // update
    }*/

    @Override // MAKA NBRE DE LIGNE
    public int getRowCount() {
        return getDonnees().length; 
    }

    @Override // MAKA NBRE DE COLONNE
    public int getColumnCount() {
       return getDonnees()[0].length; 
    }

    @Override // MAKA ILAY OBJECT 
    public Object getValueAt(int rowIndex, int columnIndex) {
        return getDonnees()[rowIndex][columnIndex]; 
    }
    
    //MAKA ANARAN'ILAY COLONNE SELON ID
    public String getColumnName(int col){ 
      return getEntete()[col]; 
    
    } 
    
    // maka ilay ligne modifier
    public Object[] getObjetLigne(int id){
        return getDonnees()[id];
    }
    
    public void insererObjet(Object[] obj){ 
        try {
            System.out.println("\n");
            System.out.println("INSERTION");
            //ClassMAPTable u=ClassDeRecherche.ResultsetToClassMappingTable(obj, getOb());
            for(int i=0; i< getEntete().length; i++){
                //System.out.println(getEntete()[i]+" = "+u.getFieldList().toString());
            }
            System.out.println("\n");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void updateObjet(Object[] obj){ // obj: ligne modifier        
        // fonction pour inserer un objet
          System.out.println("MISE A JOUR");
          for(int i=0; i< getEntete().length; i++){
            System.out.println(getEntete()[i]+" = "+obj[i]);
          }
          
    }
}
