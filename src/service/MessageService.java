/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import database.ClassDeRecherche;
import database.DbConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import model.Message;

/**
 *
 * @author rojo
 */
public class MessageService {
    public static Message[] getMessage(String condition) throws Exception{
        return ClassDeRecherche.rechercher(condition, null);
        //throw new Exception("mijanona aloha");
    }

    public static void supprimerMessage(String id) throws SQLException {
        int capteur=0;
        Connection con = null;
        String condition=" where RowId="+id;
        DbConnection db= new DbConnection();
        try{
            con=db.GetConn();
            String sql="";
            sql +="DELETE from Message ";
            sql += condition;
            System.out.println(" *** Requete supression : "+sql+"***");
            Statement s=con.createStatement();
            s.executeUpdate(sql);            
            System.out.println(" *** Suppression Réussi ***");
        }
        catch(Exception e)
        {
            System.out.println(" *** erreur suppression. Detail: "+e.getMessage()+"***");
        }
        finally {
                con.close();
                        
        }
    }
    
}
