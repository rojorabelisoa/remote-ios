package database;

import java.sql.Connection;
import java.io.*;
import java.sql.DriverManager;
/**
 * @author Rojo
 */
public class DbConnection implements Serializable
{
    Connection conn;
    public DbConnection(){conn = null;}

    public Connection GetConn() throws Exception
    {
      try{ 
          String sDriverName = "org.sqlite.JDBC";
            Class.forName(sDriverName);
          //conn = DriverManager.getConnection("jdbc:sqlite:sms.db");
          //conn = DriverManager.getConnection("jdbc:sqlite:/Users/rojo/NetBeansProjects/iphone/sms.db");
          conn = DriverManager.getConnection("jdbc:sqlite:/Users/rojo/NetBeansProjects/iphone/AddressBook.sqlitedb");
          return conn;
      } 
      catch (Exception e){			
        e.printStackTrace();
      }			
      return null;
    }
    public Connection GetConn(String path) throws Exception
    {
      try{ 
          String sDriverName = "org.sqlite.JDBC";
            Class.forName(sDriverName);
          //conn = DriverManager.getConnection("jdbc:sqlite:sms.db");
          //conn = DriverManager.getConnection("jdbc:sqlite:/Users/rojo/NetBeansProjects/iphone/sms.db");
          conn = DriverManager.getConnection("jdbc:sqlite:"+path);
          return conn;
      } 
      catch (Exception e){			
        e.printStackTrace();
      }			
      return null;
    }
    
    
    

    public void commitON()
    {
      try{conn.setAutoCommit(true);}
      catch(Exception e){System.out.println(" ** Erreur ouverture transaction: ".concat(String.valueOf(String.valueOf(e.getMessage()))));}
    }

    public void commitOFF()
    {
      try{conn.setAutoCommit(false);}
      catch(Exception e){System.out.println(" ** Erreur fermeture transaction: ".concat(String.valueOf(String.valueOf(e.getMessage()))));}
    }

    public void close_connection()
    {
      try{if(conn != null) {
              conn.close();
          }}
      catch(Exception e){System.out.println(" ** Erreur fermeture connection: ".concat(String.valueOf(String.valueOf(e.getMessage()))));}
    }

    public void valider()
    {
      try{conn.commit();}
      catch(Exception e){System.out.println(" ** Erreur validation Transaction: ".concat(String.valueOf(String.valueOf(e.getMessage()))));}
    }

    public void annuler()
    {
      try{conn.rollback();}
      catch(Exception e){System.out.println(" ** Erreur annulation Transaction: ".concat(String.valueOf(String.valueOf(e.getMessage()))));}
    }  
}