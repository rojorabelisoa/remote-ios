package utilitaire;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSetMetaData;
/**
 * @author Rojo RABELISOA 
 */

public class Utilitaire 
{
        public static String supFirstCharacter(String maString)
        {
            return maString = maString.substring(0,1).toUpperCase() + maString.substring(1).toLowerCase();
        }
        public static String getValueInSession(String name)
        {
            return name;
        }
    /**
     * V�rifie l'existence, dans une liste de m�thode, d'un getter pour un attribut
     * @param f l'attribut
     * @param m liste de m�thodes
     * @return l'indice o� se trouve le getter s'il existe sinon -1
     * @throws Exception
     */
    public static int verifgetter (Field f,Method[]m) throws Exception //fonction pour verifier la pr�sence de get de l'attributs
    {
        String field=UtilitaireLettre.firstUpper(f.getName());
        String get="get";
        int i=0;
        int statut=-1;
        while (i<m.length)
        {
            if((get+field).compareToIgnoreCase(m[i].getName())==0)
            {
                statut=i;
                break;
            }
            i++;
        }
        if (i==m.length&&statut==-1)
        {
            throw new Exception("fonction verifgetter: la fonction "+get+field+" n'existe pas");
        }
        return statut;
    }
    
    /**
     * V�rifie l'existence, dans une liste de m�thode, d'un setter pour un attribut
     * @param f l'attribut
     * @param m liste de m�thodes
     * @return l'indice o� se trouve le setter s'il existe sinon -1
     * @throws Exception
     */
    public static int verifsetter (Field f,Method[]m) throws Exception
    {
        String field=f.getName();
        String set="set";
        int i=0;
        int statut=-1;
        //System.out.println("set+field="+set+field);
        while (i<m.length)
        {
            if((set+field).compareToIgnoreCase(m[i].getName())==0)
            {
                statut=i;
                break;
            }
            i++;
        }
        if (i==m.length&&statut==-1)
        {
            throw new Exception("fonction verifsetter: la fonction "+set+field+" n'existe pas");
        }
        return statut;
    }
    
    /**
     * V�rifie la correspondance d'un attribut avec un champ dans la base de donn�e 
     * @param f l'attribut
     * @param rsmd metadata tir� � partir d'un ResultSet et 
     * qui permettra d'avoir la liste des colonnes dans la base de donn�e
     * @return l'indice � laquel se trouve la colonne dans la base
     * @throws Exception
     */
    public static int verifAttribChamp (Field f,ResultSetMetaData rsmd) throws Exception
    {
        String field=f.getName();
        int taille=rsmd.getColumnCount();
        int i=1;
        int statut=-1;
        while (i<=taille)
        {
            if (field.compareToIgnoreCase(rsmd.getColumnName(i))==0)
            {
                statut=i;
                //System.out.println("rsmd:"+rsmd.getColumnName(i)+" � l'indice "+i);
                break;
            }
            i++;
        }
        if (i==taille&&statut==-1)
        {
            return statut;
        }
        return statut;
    }
    
    /**
     * Cette fonction donne le type de valeur qu'un attribut peut contenir
     * @param f l'attribut
     * @return le type de valeur que l'attribut peut contenir
     * @throws Exception
     */
    public static String getTypeChamp (Field f) throws Exception
    {
        Class c=f.getType();
        String retour=null;
        String name=c.getName();
        if (name.compareToIgnoreCase("java.lang.String")==0)
            retour="String";
        if (name.compareToIgnoreCase("double")==0)
            retour="Double";
        if (name.compareToIgnoreCase("int")==0)
            retour="int";
        if (name.compareToIgnoreCase("java.util.date")==0)
            retour="Date";
        if (name.compareToIgnoreCase("java.sql.date")==0)
            retour="Date";
        if (name.compareToIgnoreCase("float")==0)
            retour="float";
        if (name.compareToIgnoreCase("long")==0)
            retour="long";
        if (retour==null)
        {
            throw new Exception ("fonction getTypeChamp: type de variable non repertorier");
        }
        return retour;
    }
}

