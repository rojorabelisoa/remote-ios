/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import bean.ClassMAPTable;
import java.util.Date;

/**
 *
 * @author rojo
 */
public class IMessage extends ClassMAPTable{ 
    private int rowid;
    private String date;
    private String phoneNumber;
    private String service;
    private String type;
    private String dateRead;
    private String text;
    
    
    public IMessage() {
        String requete="SELECT \n"
                    + "  m.rowid as RowID, \n"
                    + "  DATETIME(date + 978307200, 'unixepoch', 'localtime') as Date, \n"
                    + "  h.id as \"PhoneNumber\", m.service as Service, \n"
                    + "  CASE is_from_me \n"
                    + "    WHEN 0 THEN \"Received\" \n"
                    + "    WHEN 1 THEN \"Sent\" \n"
                    + "    ELSE \"Unknown\" \n"
                    + "  END as Type, \n"
                    + "  CASE \n"
                    + "    WHEN date_read > 0 then DATETIME(date_read + 978307200, 'unixepoch')\n"
                    + "    WHEN date_delivered > 0 THEN DATETIME(date_delivered + 978307200, 'unixepoch') \n"
                    + "    ELSE NULL END as \"DateRead\", \n"
                    + "  text as Text \n"
                    + "FROM message m, handle h \n"
                    + "WHERE h.rowid = m.handle_id and m.service='iMessage'\n"
                    + " ";
        this.setRequete(requete);
        String path="/Users/rojo/NetBeansProjects/iphone/sms.db";
        this.setPathDB(path);
    }

    public IMessage(int rowid, String date, String phoneNumber, String service, String type, String dateRead, String text) {
        this.rowid = rowid;
        this.date = date;
        this.phoneNumber = phoneNumber;
        this.service = service;
        this.type = type;
        this.dateRead = dateRead;
        this.text = text;
    }
     
    
    /**
     * @return the rowid
     */
    public int getRowid() {
        return rowid;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @return the service
     */
    public String getService() {
        return service;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the dateRead
     */
    public String getDateRead() {
        return dateRead;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param rowid the rowid to set
     */
    public void setRowid(int rowid) {
        this.rowid = rowid;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @param service the service to set
     */
    public void setService(String service) {
        this.service = service;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @param dateRead the dateRead to set
     */
    public void setDateRead(String dateRead) {
        this.dateRead = dateRead;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

}
