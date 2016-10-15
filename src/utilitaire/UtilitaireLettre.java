package utilitaire;
import java.util.Vector;
/**
 * @author Rojo RABELISOA 
 */
public class UtilitaireLettre 
{
    public static String firtToUpperCase(String text)
    {
        String valiny;
        valiny = Character.toUpperCase(text.charAt(0)) + text.substring(1);
        return valiny;
    }
    /**
     * Compte le nombre de caract�re entre un indice de d�part jusqu'� un point d'arr�t
     * @param n l'indice de d�part
     * @param m le mot � analyser
     * @param stop le caract�re d'arr�t
     * @return le nombre de char
     */
    public static int countchar(int n,String m,char stop)
    {
        int retour=0;
        char[]mot=m.toCharArray();
        //System.out.println("mot="+mot.length);
        for(int i=n;i<mot.length&&mot[i]!=stop;i++)
        {
            //System.out.println("depart a="+i);
            retour++;
        }
        return retour;
    }
    
    /**
     * Compte le nombre de caract�re � partir d'un indice
     * @param n l'indice de d�part
     * @param m le mot � analyser
     * @return le nombre de char
     */
    public static int countchar(int n,String m)
    {
        int retour=0;
        char[]mot=m.toCharArray();
        System.out.println("mot="+mot.length);
        for(int i=n;i<mot.length;i++)
        {
            System.out.println("depart a="+i);
            retour++;
        }
        return retour;
    }
    
    /**
     * Compte le nombre d'occurrence d'un caract�re dans un mot (ne tient pas compte des successions)
     * @param c le caract�re � compter
     * @param m le mot o� compter le caract�re
     * @return le nombre d'occurrence du caract�re
     */
    public static int countchar(char c,String m)
    {
        int n=0;
        char[]text=m.toCharArray();
        for (int i=0;i<m.length();i++)
        {
            if(c==text[i])
            {
                n++;
            }
        }
        return n;
    }
    
    
    /**
     * fonction pour compter le nombre d'occurence d'un caract�re en tenant compte des successions
     * @param c char
     * @param m le mot
     * @return le nombre d'occurence
     */
    public static int countcharSansSuccession(char c,String m)
    {
        int n=0;
        char[]text=m.toCharArray();
        System.out.println("taille d tab==="+m.length());
        int i;
        for (i=0;i<m.length();i++)
        {
            if(c==text[i])
            {
                if(i+1<m.length() && c!=text[i+1])
                {
                    n++;
                }
            }
        }
        if(c==text[i-1])
        {
            n++;
        }
        return n;
    }
    
    //supprimer  des espaces  devant une phrase s'ils existent
    public static String deleteSpaceDevant(String phrase,char aSupprimer)
    {
            char[] c=phrase.toCharArray();
            int cpt=0;
            for (int i=0;i<c.length;i++)
            {
                    if (c[i]!=aSupprimer)
                            break;
                    cpt++;
            }
            char[] c2=new char[c.length-cpt];
            int cpt2=0;
            for (int j=cpt;j<c.length;j++)
            {
                    c2[cpt2]=c[j];
                    cpt2++;
            }
            String ret=new String (c2);
            return ret;
    }

    //supprimer des espaces apres une phrase
    public static String deleteSpaceAfter(String phrase, char aSupprimer) throws Exception
    {
            char[] c=phrase.toCharArray();
            int cpt=0;
            for(int i=(c.length-1);i>=0;i--)
            {
                    if (c[i]!=aSupprimer)
                            break;
                    cpt++;
            }
            char[] c2=new char[c.length-cpt];
            int cpt2=0;
            for (int j=0;j<c.length-cpt;j++)
            {
                    c2[cpt2]=c[j];
                    cpt2++;
            }
            String ret=new String (c2);
            return ret;
    }
    
    /**
     * Fonction pour supprimer les exc�dents d'espace
     * @param word
     * @return String
     * @throws Exception 
     */
    public static String deleteSpace(String word, char aSupprimer) throws Exception
    {
        String mot=UtilitaireLettre.deleteSpaceAfter(UtilitaireLettre.deleteSpaceDevant(word,aSupprimer),aSupprimer);//supprimer des espaces avant et apres une phrase s'il existe
        char[]aa={aSupprimer};
        String sep=new String(aa);
        String [] tabWord=mot.split(sep);
        Vector temp=new Vector ();
        for (int i=0;i<tabWord.length;i++)
        {
            if ("".equals(tabWord[i]))
            {
                continue;
            }
            else
                temp.addElement(tabWord[i]);
        }
        int sizeVector=temp.size();
        String[] tabRet=new String[sizeVector];
        temp.copyInto(tabRet);
        String ret=new String();
        for (int c=0;c<tabRet.length;c++)
        {
            ret=ret+sep+tabRet[c];
        }

        return UtilitaireLettre.deleteSpaceDevant(ret,aSupprimer);
    }
    
    /**
     * Retourne l'indice des positions d'un caract�re dans un mot
     * @param c le caract�re recherch�
     * @param m le mot ou chercher le caract�re
     * @return les indices des positions du caract�res
     */
    public static int[] positionchar (char c, String m)
    {
        int n=UtilitaireLettre.countchar(c, m);
        char[]text=m.toCharArray();
        int[]retour=new int[n];
        int j=0;
        for (int i=0;i<n;i++)
        {
            if (c==text[i])
            {
                retour[j]=i;
                j++;
            }
        }
        return retour;
    }
    
    /**
     * fonction permettant de v�rifier si un string contient ou nom des chiffres
     * @param s la cha�ne � v�rifier
     * @return 0 s'il ne contient aucun chiffre sinon le nombre de chiffre qu'il contient
     */
    public static int verifnombre(String s)
    {
        int retour=0;
        char[]c=s.toCharArray();
        char[]nombre={'0','1','2','3','4','5','6','7','8','9'};
        for (int i=0;i<c.length;i++)
        {
            for (int j=0;j<nombre.length;j++)
            {
                if(c[i]==nombre[j])
                {
                    retour=retour+1;
                }
            }
        }
        return retour;
    }
    
    /**
     * fonction permettant de mettre la premi�re lettre d'un String en majuscule
     * @param s le String � modifier
     * @return un String
     */
    public static String firstUpper(String s)
    {
        char[]c=s.toCharArray();
        Character.toUpperCase(c[0]);
        String retour=new String(c);
        return retour;
    }
    
    /**
     * fonction pour remplacer un char par un autre
     */
    public static String replaceCharByChar(String mot, char toReplace, char newChar)
    {
        char[]cc=mot.toCharArray();
        for (int i=0;i<cc.length;i++)
        {
            if(cc[i]==toReplace)
            {
                cc[i]=newChar;
            }
        }
        return new String(cc);
    }
    
    /**
     * Fonction pour remplacer un caract�re par une chaine de caract�re
     * @param mot le mot dans lequel se trouve le caract�re
     * @param toReplace le caract�re � remplacer
     * @param newString la chaine de remplacement
     * @return un String
     */
    public static String replaceCharByString(String mot, char toReplace, String newString)
    {
        char[]cc=mot.toCharArray();
        char[]replacement=newString.toCharArray();
        //System.out.println("nnbre de fois ou "+toReplace+" revient =="+UtilitaireLettre.countchar(toReplace, mot));
        int t=(UtilitaireLettre.countchar(toReplace, mot)*newString.length())+mot.length();
        char[]ret=new char[t];
        //System.out.println("taille replacement =="+newString.length());
        //System.out.println("taille vaovao =="+ret.length);
        for(int i=0,k=0;i<cc.length;i++)
        {
            System.out.println("i ="+i);
            if(cc[i]==toReplace)
            {
                for(int j=0;j<replacement.length;j++)
                {
                    ret[k]=replacement[j];
                    k++;
                }
            }
            else
            {
                ret[k]=cc[i];
                k++;
            }
        }
        return new String(ret);
    }
    
    /**
     * fonction pour s�parer une chaine en plusieurs string � partir d'un s�parateur
     * @param source la chaine � s�par�e
     * @param sep le caract�re de s�paration
     * @return un tableau de string
     */
    public static String[]extractString(String source,char sep) throws Exception
    {
        String nettoyer=UtilitaireLettre.deleteSpace(source, sep);
        char[]c={sep};
        String[]retour=nettoyer.split(new String(c));
        return retour;
    }
    
}

