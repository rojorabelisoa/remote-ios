
package database;


import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import java.io.*;
import model.Message;
/**
 * @author Rojo
 */
public class ClassDeRecherche implements Serializable{
    
    protected static void setFieldValue(Object obj,Field champ,Object valeur) throws Exception
    {
        String nomMethode="set"+utilitaire.UtilitaireLettre.firtToUpperCase(champ.getName());
        Class[] type_de_l_attribut = { champ.getType() };
        Object[] args={valeur};
        if (valeur != null){((obj.getClass()).getDeclaredMethod(nomMethode,type_de_l_attribut)).invoke(obj,args);}
    }
    public static Message[] rechercher(String condition,Connection con) throws Exception
    {
        Message o= new Message();
        String sql="";
        int capteur=0;
        ResultSet rset=null;
        Statement st=null;
        try
        {
            if(con==null){DbConnection db=new DbConnection();con=db.GetConn(o.getPathDB());capteur=1;}
            sql = "SELECT \n"
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
                    + "WHERE h.rowid = m.handle_id \n"
                    + " ";
            sql+=condition;
            st=con.createStatement();
            rset=st.executeQuery(sql);   
            Message[] res  = ResultsetToMessage(rset, o);
            return res;
        }   
        catch(Exception e)
        {
            System.out.println(" *** Erreur Recherche. Detail : "+e.getMessage()+" *** ");
            throw e;
        }
        finally
        {
            rset.close();
            st.close();
            if(capteur==1) {
                con.close();
            }
        }
    }
    
    private static  Message[]  ResultsetToMessage(ResultSet rset,Message maptable) throws Exception
    {
        Vector liste =new Vector();
        Field[] lf = maptable.getClass().getDeclaredFields();
        int taille = 0;
        while (rset.next())
        {
            int i=0;
            
            Object obj=(Message)(Class.forName(maptable.getClass().getName()).newInstance());
            Message tmp=(Message) obj;
            while(i<lf.length)
            {
                Object resultatcolonne = rset.getObject(lf[i].getName());
                setFieldValue(tmp,lf[i],resultatcolonne);
                i++;
            }
            liste.add(tmp);i=0;
            taille++;
        }
        Message[] a=(Message[]) Array.newInstance(maptable.getClass(), taille);
        liste.copyInto(a);
        return (Message[])a;  
    }
}
