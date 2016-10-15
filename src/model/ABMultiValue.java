/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import bean.ClassMAPTable;

/**
 *
 * @author rojo
 */
public class ABMultiValue extends ClassMAPTable {
    private int rowID;
    private String first;
    private String last;
    private String value;

    public ABMultiValue() {
        String requete="select ABPerson.ROWID,ABPerson.first,ABPerson.last, ABMultiValue.value from ABPerson,ABMultiValue where ABMultiValue.record_id=ABPerson.ROWID";
        this.setRequete(requete);
        String path="/Users/rojo/NetBeansProjects/iphone/AddressBook.sqlitedb";
        this.setPathDB(path);
    }

    public ABMultiValue(int rowID, String first, String last, String value) {
        this.rowID = rowID;
        this.first = first;
        this.last = last;
        this.value = value;
    }
    

    

    /**
     * @return the first
     */
    public String getFirst() {
        return first;
    }

    /**
     * @return the last
     */
    public String getLast() {
        return last;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    

    /**
     * @param first the first to set
     */
    public void setFirst(String first) {
        this.first = first;
    }

    /**
     * @param last the last to set
     */
    public void setLast(String last) {
        this.last = last;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the rowID
     */
    public int getRowID() {
        return rowID;
    }

    /**
     * @param rowID the rowID to set
     */
    public void setRowID(int rowID) {
        this.rowID = rowID;
    }

    
}
