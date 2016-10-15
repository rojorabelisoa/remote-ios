package utilitaire;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
/**
 * @author Rojo RABELISOA 
 */
public class UtilitaireDate 
{
    public static Date stringToDate(String date) throws ParseException
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd"); 
        java.util.Date dateStr = formatter.parse(date);
        java.sql.Date dateDB = new java.sql.Date(dateStr.getTime());
        return dateDB;
    }
    
    
    /**
     * Fonction extraire le Jour dans une date en fonction du masque de la source.
     * Le masque peut �tre de la forme "jj/mm/aa", "jj/mm/aaaa" ou "aaaa/mm/jj", "aa/mm/jj"
     * @param source la date en format String
     * @param masque le masque correspondant � la date
     * @param sep le separateur correspondant au masque
     * @return la valeur du jour
     * @throws Exception 
     */
    public static int extractJour(String source,String masque,char sep) throws Exception
    {
        char[]date=masque.toCharArray();
        int debut=0;
        int i=0;
        int n=0;
        for (i=0;i<date.length;i++)
        {
            if (date[i]=='j'||date[i]=='J'||date[i]=='d'||date[i]=='D')
            {
                n=UtilitaireLettre.countchar(date[i], masque);
                debut=i;
                int test=UtilitaireLettre.countchar(i, source,sep);
                /*System.out.println("isan="+n);
                System.out.println("i="+i);*/
                if (test!=n)
                {
                    throw new Exception ("Fonction extractJour : le jour ne correspond pas au masque");
                }
                break;
            }
        }
        if (i==date.length||n>2||n<2)
        {
            throw new Exception ("fonction extractJour : le masque ne correspond pas � la date");
        }
        //System.out.println("soutce="+source);
        String j=source.substring(debut, debut+2);
        Integer jj=Integer.parseInt(j);
        int day=jj.intValue();
        if (day>31)
        {
            throw new Exception ("fonction extractJour : Jour invalide");
        }
        return day;
    }
    
    /**
     * Fonction extraire le Mois dans une date en fonction du masque de la source.
     * Le masque peut �tre de la forme "jj/mm/aa", "jj/mm/aaaa" ou "aaaa/mm/jj", "aa/mm/jj"
     * @param source la date en format String
     * @param masque le masque correspondant � la date
     * @param sep le separateur correspondant au masque
     * @return la valeur du mois
     * @throws Exception 
     */
    public static int extractMois(String source,String masque,char sep) throws Exception
    {
        char[]date=masque.toCharArray();
        int debut=0;
        int i=0;
        int n=0;
        for (i=0;i<date.length;i++)
        {
            if (date[i]=='m'||date[i]=='M')
            {
                n=UtilitaireLettre.countchar(date[i], masque);
                debut=i;
                int test=UtilitaireLettre.countchar(i, source,sep);
                /*System.out.println("isan="+n);
                System.out.println("i="+i);*/
                if (test!=n)
                {
                    throw new Exception ("Fonction extractMois : le mois ne correspond pas au masque");
                }
                break;
            }
        }
        if (i==date.length||n>2||n<2)
        {
            throw new Exception ("fonction extractMois : le masque ne correspond pas � la date");
        }
        String j=source.substring(debut, debut+2);
        Integer jj=Integer.parseInt(j);
        int mon=jj.intValue();
        if (mon>12)
        {
            throw new Exception ("fonction extractMois : Mois invalide");
        }
        return mon;
    }
    
    /**
     * Fonction extraire l'Ann�e dans une date en fonction du masque de la source.
     * Le masque peut �tre de la forme "jj/mm/aa", "jj/mm/aaaa" ou "aaaa/mm/jj", "aa/mm/jj"
     * @param source la date en format String
     * @param masque le masque correspondant � la date
     * @param sep le separateur correspondant au masque
     * @return la valeur de l'ann�e
     * @throws Exception 
     */
    public static int extractAnnee(String source,String masque,char sep) throws Exception
    {
        char[]date=masque.toCharArray();
        int debut=0;
        int isaann=0;
        int i=0;
        for (i=0;i<date.length;i++)
        {
            if (date[i]=='a'||date[i]=='A'||date[i]=='y'||date[i]=='Y')
            {
                isaann=UtilitaireLettre.countchar(date[i], masque);
                debut=i;
                int test=UtilitaireLettre.countchar(i, source,sep);
                /*System.out.println("isan="+isaann);
                System.out.println("i="+i);*/
                if (test!=isaann)
                {
                    throw new Exception ("Fonction extract annee : l'annee ne correspond pas au masque");
                }
                break;
            }
        }
        /*System.out.println("isan="+isaann);
        System.out.println("i="+i);
        System.out.println("debut="+debut);
        System.out.println("date length="+date.length);*/
        if (i==date.length||4<isaann||2>isaann)
        {
            throw new Exception ("fonction extractAnnee : le masque ne correspond pas � la date");
        }
        String y=source.substring(debut, debut+isaann);
        Integer yy=Integer.parseInt(y);
        int year=yy.intValue();
        if (isaann==2)
        {
            if (year>40)
            {
                year=year+1900;
            }
            else
            {
                year=year+2000;
            }
        }
        return year-1900;
    }
    
    /**
     * Fonction de v�rification si une ann�e est bissextile ou non
     * @param annee l'ann�e � v�rifier
     * @return true si l'ann�e est bissextile
     */
    public static boolean bissextile(int annee) 
    {
        if (annee%4==0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Fonction retournant la fin d'un mois dans une ann�e
     * @param mois le mois
     * @param annee l'ann�e du mois
     * @return la fin du mois en fonction de l'ann�e
     */
    public static int finDuMois (int mois,int annee)
    {
        int finMois=0;
        if (mois==2)
        {
            if(UtilitaireDate.bissextile(annee)==true)
            {
                finMois=29;
            }
            else
            {
                finMois=28;
            }
            return finMois;
        }
        if (mois<=7)
        {
            if (mois%2!=0)
            {
                finMois=31;
            }
            else
            {
                finMois=30;
            }
            return finMois;
        }
        if (mois>=8)
        {
            if (mois%2!=0)
            {
                finMois=30;
            }
            else
            {
                finMois=31;
            }
            return finMois;
        }
        return finMois;
    }
    
    /**
     * Teste la validit� d'une date d�j� s�par�e. Cette fonction ne propose pas une date valide dans le cas ou celle-ci n'existe pas
     * @param jour le jour
     * @param mois le mois
     * @param annee l'ann�e
     * @return true si la date est valide
     * @throws Exception 
     */
    public static boolean validiteDate(int jour,int mois,int annee) throws Exception
    {
        if (mois>12)
        {
            throw new Exception ("Fonction validiteDate : mois impossible");
        }
        else
        {
            
            if (jour>UtilitaireDate.finDuMois(mois, annee))
            {
                throw new Exception ("Fonction validiteDate : jour invalide pour le mois donnee");
            }
            else
            {
                return true;
            }
        }
    }
    
    
    /**
     * Transforme un java.sql.Date en String
     * @param source la date � transformer
     * @param formatsortie le format en sortie
     * @return la date en String
     * @throws Exception 
     */
    public static String sqlDatetoString (Date source, String formatsortie) throws Exception
    {
        String date=source.toString();
        int jour=UtilitaireDate.extractJour(date, "aaaa-mm-jj", '-');
        //System.out.println("jour ato="+jour);
        int mois=UtilitaireDate.extractMois(date, "aaaa-mm-jj", '-');
        //System.out.println("mois ato="+mois);
        int annee=UtilitaireDate.extractAnnee(date, "aaaa-mm-jj", '-')+1900;
        //System.out.println("annee ato="+annee);
        String d=Integer.toString(jour);
        String m=null;
        if (mois<10)
        {
            m="0"+Integer.toString(mois);
        }
        else
        {
            m=Integer.toString(mois);
        }
        String a=Integer.toString(annee);
        char[]day=d.toCharArray();
        char[]month=m.toCharArray();
        char[]year=a.toCharArray();
        char[]format=formatsortie.toCharArray();
        char[]ret=new char[format.length];
        int i=0,j=0,k=0,l=0;
        while (i<ret.length)
        {
            if (format[i]=='/'||format[i]=='-')
            {
                ret[i]=format[i];
            }
            if (format[i]=='j'||format[i]=='J')
            {
                ret[i]=day[j];
                j++;
            }
            if (format[i]=='m'||format[i]=='M')
            {
                ret[i]=month[k];
                k++;
            }
            if (format[i]=='a'||format[i]=='A')
            {
                ret[i]=year[l];
                l++;
            }
            //System.out.println(ret[i]);
            i++;
        }
        String retour=String.valueOf(ret);
        return retour;
    }
    
    /**
     * ajouter 'n' jour � une date
     * @param source la date � modifier
     * @param jour le nombre de jour � ajouter
     * @return nouvelle date
     */
    public static Date ajouterjour (Date source,int jour)
    {
        Date retour=null;
        int d=source.getDate();
        int m=source.getMonth();
        //System.out.println("mont="+m);
        int save=m;
        int y=source.getYear()+1900;
        //System.out.println("y: "+y);
        int newday=d+jour;
        retour=new Date(y-1900,m,newday);
        return retour;
    }
    
    /**
     * Soustrait une date de 'n' jour
     * @param source la date � soustraire
     * @param jour le nombre de jour � soustraire
     * @return nouvelle date
     * @throws Exception 
     */
    public static Date soustrairejour (Date source,int jour)
    {
        Date retour=null;
        int d=source.getDate();
        int m=source.getMonth();
        //System.out.println("mont="+m);
        int save=m;
        int y=source.getYear()+1900;
        //System.out.println("y: "+y);
        int newday=d-jour;
        retour=new Date(y-1900,m,newday);
        return retour;
    }
    
    /**
     * Ajoute 'n' mois � une date
     * @param source date dans laquelle ajouter les 'n' mois
     * @param mois le nombre de mois � ajouter
     * @return nouvelle date
     * @throws Exception 
     */
    public static Date ajoutermois (Date source,int mois) throws Exception
    {
        Date retour=null;
        int d=source.getDate();
        int m=source.getMonth();
        //System.out.println("mont="+m);
        int save=m;
        int y=source.getYear()+1900;
        //System.out.println("y: "+y);
        int newmonth=m+mois;
        //System.out.println("newmonth: "+newmonth);
        if (newmonth>11)
        {
            while(newmonth>11)
            {
                newmonth=newmonth-12;
                y=y+1;
            }
        }
        retour=new Date(y-1900,newmonth,d);
        return retour;
    }
    
    /**
     * Soustrait une date de 'n' mois
     * @param source la date � soustraire
     * @param mois le nombre de mois � soustraire
     * @return nouvelle date
     * @throws Exception 
     */
    public static Date soustrairemois (Date source,int mois) throws Exception
    {
        Date retour=null;
        int d=source.getDate();
        int m=source.getMonth();
        //System.out.println("mont="+m);
        int save=m;
        int y=source.getYear()+1900;
        //System.out.println("y: "+y);
        int newmonth=m-mois;
        if (newmonth==0)
        {
            newmonth=12;
        }
        if (newmonth<0)
        {
            while (newmonth<0)
            {
                newmonth=12+newmonth;
                y=y-1;
            }
        }
        //System.out.println("newmonth: "+newmonth);
        retour=new Date(y-1900,newmonth,d);
        return retour;
    }
    
    /**
     * Donne la diff�rence entre 2 dates, en ne tenant pas compte de l'ordre des dates dans les arguments
     * @param one date1
     * @param two date2
     * @return la diff�rence entre les 2 dates
     */
    public static int difference2Date (Date one, Date two)
    {
        int retour;
        Calendar c = Calendar.getInstance();
        c.setTime(one);
        int nbdone=c.get(6);
        //System.out.println("nbdone="+nbdone);
        c.setTime(two);
        int nbdtwo=c.get(6);
        //System.out.println("nbdtwo="+nbdtwo);
        if (nbdtwo>nbdone)
        {
            retour=nbdtwo-nbdone;
        }
        else
        {
            retour=nbdone-nbdtwo;
        }
        //System.out.println("retour="+retour);
        return retour;
    }

}
