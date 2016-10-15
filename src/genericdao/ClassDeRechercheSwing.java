
package genericdao;

import bean.ClassMAPTable;
import database.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import java.io.*;
import java.sql.PreparedStatement;
import model.Message;
/**
 * @author Rojo Rabelisoa
 */
public class ClassDeRechercheSwing implements Serializable{
    
    public static String majuscule(String s){
        return Character.toUpperCase(s.charAt(0))+s.substring(1);
    }
    
    
    protected static void setFieldValue(Object obj,Field champ,Object valeur) throws Exception
    {
        String nomMethode="set"+utilitaire.UtilitaireLettre.firtToUpperCase(champ.getName());
        Class[] type_de_l_attribut = { champ.getType() };
        Object[] args={valeur};
        if (valeur != null){((obj.getClass()).getDeclaredMethod(nomMethode,type_de_l_attribut)).invoke(obj,args);}
    }
    public static Object[][] rechercher(ClassMAPTable o,String condition,Connection con) throws Exception
    {
        String sql="";
        int capteur=0;
        ResultSet rset=null;
        Statement st=null;
        Object[][] res;
        try
        {
            if(con==null){DbConnection db=new DbConnection();con=db.GetConn(o.getPathDB());capteur=1;}
            sql = o.getRequete();
            sql+=condition;
            System.out.println("votre requete : "+sql);
            st=con.createStatement();
            rset=st.executeQuery(sql);   
            res  = ResultsetToObj(rset, o,con);
            return res;
        }   
        catch(Exception e)
        {
            System.out.println(" *** Erreur Recherchecdscd. Detail : "+e.getMessage()+" *** ");
            e.printStackTrace();
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
    
    
    private static  Object[][]  ResultsetToObj(ResultSet rset,bean.ClassMAPTable maptable,Connection con) throws Exception
    {
        String sql = "select count(*) from "+maptable.getClass().getSimpleName()+"";
        PreparedStatement ps =  con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        int rowCount = 0;
        while(rs.next()) {
            rowCount = Integer.parseInt(rs.getString("count(*)"));
        }
        Field[] lf = maptable.getClass().getDeclaredFields();
        Object[][]res=new Object[rowCount][lf.length];
        int hor=0;
        int vert=0;
        while (rset.next())
        {
            int i=0;
            
            Object obj=(ClassMAPTable)(Class.forName(maptable.getClass().getName()).newInstance());
            ClassMAPTable tmp=(ClassMAPTable) obj;
            while(i<lf.length)
            {
                Object resultatcolonne = rset.getObject(lf[i].getName());
                setFieldValue(tmp,lf[i],resultatcolonne);
                res[vert][hor]=resultatcolonne;
                hor++;
                i++;
            }
            vert++;hor=0;
        }
        return res; 
    }
    
    
    /*
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
    }*/
}
