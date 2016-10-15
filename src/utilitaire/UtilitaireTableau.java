package utilitaire;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;
/**
 * @author Rojo RABELISOA 
 */
public class UtilitaireTableau 
{
    /**
     * ajoute plusieurs �l�ments � un tableau
     * @param toadd le tableau source
     * @param tableau le tableau de destination
     * @return un nouveau tableau
     */
    public static Object addelementstableau(Object[]toadd,Object[]tableau)
    {
        int i=0;int j=0;int a=toadd.length;int b=tableau.length;int t=a+b;
        Object[]retour=new Object[t];
        while (i<b)
        {
            retour[i]=tableau[i];
            i++;
        }
        while (j<a)
        {
            retour[i]=toadd[j];
            j++;
            i++;
        }
        return retour;
    }
    
    /**
     * supprime un �l�ment d'un tableau � l'indice n
     * @param n la n-i�me place
     * @param objet le tableau
     * @return un nouveau tableau sans l'�l�ment supprim�
     * @throws Exception 
     */
    public static Object[]delelementtableau(int n,Object[]objet) throws Exception
    {
        n=n-1;
		Object[]ret=new Object[objet.length-1];
		int i=0;int j=0;int t=objet.length;
		if (n<t)
		{
			while(j<t-1)
			{
				if(i==n)
				{
					i++;
				}
				else
				{
					ret[j]=objet[i];
					i++;
					j++;
				}
			}
		}
		else
		{
			throw new Exception("indice � supprimer n'existe pas");
		}
		return ret;
	}
    
    /**
     * ajoute un �l�ment � l'indice n d'un tableau
     * @param o l'Objet
     * @param n la n-i�me place o� ins�rer l'Objet
     * @param tableau le tableau o� ins�rer l'�l�ment, si "null" alors la fonction cr�era un tableau
     * @return un nouveau tableau avec le nouvel �l�ments
     * @throws Exception 
     */
    public static Object[] addelementtableau(Object o,int n,Object[]tableau)throws Exception
    {
        n=n-1;
        if (n>tableau.length)
        {
            throw new Exception("addelementtableau:indice invalide");
        }
	if (tableau==null)
	{
		tableau=new Object[1];
		tableau[0]=o;
                return tableau;
	}
	else
	{
            Object[]ret=new Object[tableau.length+1];
            int i=0;int t=tableau.length+1;
            {
                int j=0;   
                while (i<t)
                {
                    if (i==n)
                    {
                        ret[i]=o;
                    }
                    else
                    {
                        ret[i]=tableau[j];
                        j++;
                    }
                    i++;
                }
            }
            return ret;
	}
    }
    
    /**
     * met un objet � l'indice n dans un tableau
     * @param n l'indice o� mettre le nouvel �l�ment
     * @param o l'Objet
     * @param dest le tableau de destination
     */
    private static void settableau(int n,Object o,Object[]dest)
    {
	dest[n]=o;
    }
    
    /**
     * fonction calculer somme d'un attribut donn�e dans un tableau d'objet
     * @param param
     * @param objet
     * @return la somme des attributs
     * @throws Exception 
     */
    public static double somme (String param,Object[]objet) throws Exception
    {
        double retour=0;
        try
        {
            Field champ=objet[0].getClass().getDeclaredField(param);
            String methodchamp="get"+champ.getName();
            Method m=objet[0].getClass().getMethod(methodchamp, (Class<?>[]) null);
            int i=0;int t=objet.length;
            double temp;
            while (i<t)
            {
                temp=(Double)m.invoke(objet[i], null);
                retour=retour+temp;
                i++;
            }
        }
        catch (NoSuchFieldException e) 
        {
           throw new Exception("fonction somme:le champ '"+param+"' n'a pas �t� trouv�");
        }
        catch (SecurityException e)
        {
            throw new Exception("fonction somme:le champ "+param+" n'est pas accessible");
        }
        catch (NoSuchMethodException e)
        {
            throw new Exception("fonction somme:la methode get"+param+" n'existe pas");
        }
        return retour;
    }
    
    /**
     * fonction calculer moyenne d'un attribut donnee dans un tableau d'objet
     * @param param
     * @param objet
     * @return la moyenne des attributs
     * @throws Exception 
     */
    public static double moyenne (String param, Object[]objet) throws Exception
    {
        double s=UtilitaireTableau.somme(param,objet);
        double moyenne=s/objet.length;
        return moyenne;
    }
    
    /**
     * fonction pour convertir un Vector vers un tableau d'Objet
     * @param v Vector � convertir
     * @return un tableau d'Objet
     */
    public static Object[] vectortotab (Vector v)//fonction convertir un Vector en tableau d'Object
    {
        int n=v.size();
        Object[]retour=new Object[n];
        int i=0;
        while (i<n)
        {
            retour[i]=v.get(i);
            i++;
        }
        return retour;
    }
    
    public static Object[][]data2d(Object[]o) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        Field[]f=o[1].getClass().getDeclaredFields();
        /*for(int k=0;k<f.length;k++)
        {
            //System.out.println("champ["+k+"] === "+f[k].getName());
        }*/
        int nbrAttribut=f.length;
        Object[][]retour=new Object[o.length][nbrAttribut];
        Method m;
        int i=0;
        int j=0;
        while(i<o.length)
        {
            while(j<nbrAttribut)
            {
                String name="get"+UtilitaireLettre.firstUpper(f[j].getName());
                //System.out.println("method name=="+name);
                m=o[0].getClass().getDeclaredMethod(name, null);
                retour[i][j]=m.invoke(o[i],null);
                //System.out.println("reponse etetetete ==="+retour[i][j]);
                j++;
            }
            j=0;
            i++;
        }
        return retour;
    }
    
    /**
     * permet de faire le tri d'un tableau d'Objet selon un crit�re
     * @param type le type de tri � effectuer (1 pour le tri croissant, 0 pour le tri d�croissant)
     * @param param le param�tre de tri
     * @param tableau le tableau � trier
     * @throws Exception 
     */
    public static void tri (int type,String param, Object[]tableau) throws Exception //si type=1 =>tri croissant; si type=0 =>tri decroissant
    {
	Method[]m=tableau[0].getClass().getMethods(); 	//atao ao anaty tableau de m�thode daolo izay m�thode ao @ ilay obj[0] zany oe l� tableau
	int i=0;
	String method; 	//contiendra le nom de la m�thode
	Method hita=null;	//contiendra la m�thode lorsqu'elle sera trouv�e
	while (i<m.length)	//mitety an'ilay tableau de Method
	{
		method=m[i].getName();
		if (method.compareToIgnoreCase("get"+param)==0)	//dans le cas o� hitany ilay Method mifanaraka @ ilay param�tre
		{
			hita=m[i];
			break;
		}
		if (i+1==m.length)	//raha oatra ka tonga any @ farany ka mbola tsy hita ilay Method
		{
			System.out.println("erreur");
			break;
		}
		i++;
	}
	int k=0;
        int z=tableau.length;
	if (type==1)
	{
            int j=0;
	
            Object temp;
		//System.out.println(hita.getReturnType().getName());
			while (j<z)
			{
				while (k<z-1)
				{
					if (hita.getReturnType().getName().compareTo("java.lang.String")==0)
					{
                                             
						String temp1=(String)hita.invoke(tableau[k],null);
						String temp2=(String)hita.invoke(tableau[k+1], null);
						if (temp1.compareToIgnoreCase(temp2)>0)
						{
                                                   
							temp=tableau[k];
							UtilitaireTableau.settableau(k,tableau[k+1],tableau);
							UtilitaireTableau.settableau(k+1,temp,tableau);
						}
					}
					if(hita.getReturnType().isPrimitive()==true)
					{
						Double a=(Double)hita.invoke(tableau[k], null);
						Double b=(Double)hita.invoke(tableau[k+1], null);
						double temp1=a.doubleValue();
						double temp2=b.doubleValue();
						if (temp1>temp2)
						{
							temp=tableau[k];
							UtilitaireTableau.settableau(k,tableau[k+1],tableau);
							UtilitaireTableau.settableau(k+1,temp,tableau);
						}
					}
				k++;
				}
			k=0;
			j++;
			}
			
		}
		if (type==0)
		{
			int j=0;
			Object temp;
			while (j<z)
			{
				while (k<z-1)
				{
					/*if (hita.getReturnType().getName().compareTo("java.util.Date")==0)
					{
						Date temp1=(Date)hita.invoke(tableau[k],null);
						Date temp2=(Date)hita.invoke(tableau[k+1],null);
						if (temp1<temp2)
						{
							temp=tableau[k+1];
							this.setObject(k+1,tableau[k]);
							this.setObject(k,temp);
						}
					}*/
					if (hita.getReturnType().getName().compareTo("java.lang.String")==0)
					{
						String temp1=(String)hita.invoke(tableau[k],null);
						String temp2=(String)hita.invoke(tableau[k+1], null);
						if (temp1.compareToIgnoreCase(temp2)<0)
						{
							temp=tableau[k+1];
							UtilitaireTableau.settableau(k+1,tableau[k],tableau);
							UtilitaireTableau.settableau(k,temp,tableau);
						}
					}
					if(hita.getReturnType().isPrimitive()==true)
					{
						Double a=(Double)hita.invoke(tableau[k], null);
						Double b=(Double)hita.invoke(tableau[k+1], null);
						double temp1=a.doubleValue();
						double temp2=b.doubleValue();
						if (temp1<temp2)
						{
							temp=tableau[k+1];
							UtilitaireTableau.settableau(k+1,tableau[k],tableau);
							UtilitaireTableau.settableau(k,temp,tableau);
						}
					}
				k++;
				}
			k=0;
			j++;
			}
			
		}
    }
    
    /**
     * Fonction pour trier des �l�ments selon plusieurs crit�re. Si un param�tre a la m�me valeur pour 2 objets alors, la fonction triera les 2 objets selon le param�tre suivant
     * @param type croissant ou d�croissant
     * @param param liste des param�tres de tri
     * @param tableau liste des objets � trier
     */
    public static void triMultiCritere (int type, String[]param, Object[]tableau)
    {
        
    }
    
    /**
     * permet d'avoir l'Objet ayant la valeur maximum d'un attribut parmi une liste
     * @param param l'attribut-crit�re de la recherche
     * @param tableau le tableau o� effectu� la recherche
     * @return l'Objet trouver
     * @throws Exception 
     */
    public static Object maxvalue (String param,Object[]tableau) throws Exception //retourne l'objet ayant la plus grande valeur d'un parametre
    {
        Method[]m=tableau[0].getClass().getMethods(); 	//atao ao anaty tableau de m�thode daolo izay m�thode ao @ ilay obj[0] zany oe l� tableau
	int i=0;
	String method; 	//contiendra le nom de la m�thode
	Method hita=null;	//contiendra la m�thode lorsqu'elle sera trouv�e
	while (i<m.length)	//mitety an'ilay tableau de Method
	{
        	method=m[i].getName();
		if (method.compareToIgnoreCase("get"+param)==0)	//dans le cas o� hitany ilay Method mifanaraka @ ilay param�tre
		{
			hita=m[i];
			break;
		}
		if (i+1==m.length)	//raha oatra ka tonga any @ farany ka mbola tsy hita ilay Method
		{
			System.out.println("erreur");
			break;
		}
		i++;
	}
	int k=0;
        int z=tableau.length;
	int j=0;
        Object retour=tableau[k];
	//System.out.println(hita.getReturnType().getName());
	while (j<z)
	{
		while (k<z-1)
		{
			if (hita.getReturnType().getName().compareTo("java.lang.String")==0)
			{
                                           
				String temp1=(String)hita.invoke(retour,null);
				String temp2=(String)hita.invoke(tableau[k+1], null);
				if (temp1.compareToIgnoreCase(temp2)<0)
				{
                                    retour=tableau[k+1];
				}
			}
			if(hita.getReturnType().getName().compareTo("double")==0)
			{
				Double a=(Double)hita.invoke(retour, null);
				Double b=(Double)hita.invoke(tableau[k+1], null);
				double temp1=a.doubleValue();
				double temp2=b.doubleValue();
				if (temp1<temp2)
				{
                                    retour=tableau[k+1];
				}
			}
                        if(hita.getReturnType().getName().compareTo("int")==0)
			{
				Integer a=(Integer)hita.invoke(retour, null);
				Integer b=(Integer)hita.invoke(tableau[k+1], null);
				int temp1=a.intValue();
				int temp2=b.intValue();
				if (temp1<temp2)
				{
                                    retour=tableau[k+1];
				}
			}
			k++;
		}
            k=0;
            j++;
	}
        return retour;
    }
    
    /**
     * permet de retourner depuis une liste d'Objet, l'objet ayant la plus petite valeur d'un attribut donn�
     * @param param l'attribut-crit�re de la recherche
     * @param tableau le tableau d'objet dans lequel effectu� la recherche
     * @return l'Objet ayant la plus petite valeur pour cette attribut
     * @throws Exception 
     */
    public static Object minvalue (String param,Object[]tableau) throws Exception //retourne l'objet ayant la plus petite valeur selon un parametre
    {
        Method[]m=tableau[0].getClass().getMethods(); 	//atao ao anaty tableau de m�thode daolo izay m�thode ao @ ilay obj[0] zany oe l� tableau
	int i=0;
	String method; 	//contiendra le nom de la m�thode
	Method hita=null;	//contiendra la m�thode lorsqu'elle sera trouv�e
	while (i<m.length)	//mitety an'ilay tableau de Method
	{
		method=m[i].getName();
		if (method.compareToIgnoreCase("get"+param)==0)	//dans le cas o� hitany ilay Method mifanaraka @ ilay param�tre
		{
			hita=m[i];
			break;
		}
		if (i+1==m.length)	//raha oatra ka tonga any @ farany ka mbola tsy hita ilay Method
		{
			throw new Exception("fonction minvalue:fonction introuvable");
		}
		i++;
	}
	int k=0;
        int z=tableau.length;
	int j=0;
	Object temp;
        Object retour=tableau[k];
	//System.out.println(hita.getReturnType().getName());
	while (j<z)
	{
		while (k<z-1)
		{
			if (hita.getReturnType().getName().compareTo("java.lang.String")==0)
			{
                                           
				String temp1=(String)hita.invoke(retour,null);
				String temp2=(String)hita.invoke(tableau[k+1], null);
				if (temp1.compareToIgnoreCase(temp2)>0)
				{
                                    retour=tableau[k+1];
				}
			}
			if(hita.getReturnType().getName().compareTo("double")==0)
			{
				Double a=(Double)hita.invoke(retour, null);
				Double b=(Double)hita.invoke(tableau[k+1], null);
				double temp1=a.doubleValue();
				double temp2=b.doubleValue();
				if (temp1>temp2)
				{
                                    retour=tableau[k+1];
				}
			}
                        if(hita.getReturnType().getName().compareTo("int")==0)
			{
				Integer a=(Integer)hita.invoke(retour, null);
				Integer b=(Integer)hita.invoke(tableau[k+1], null);
				int temp1=a.intValue();
				int temp2=b.intValue();
				if (temp1>temp2)
				{
                                    retour=tableau[k+1];
				}
			}
			k++;
		}
	k=0;
	j++;
	}
        return retour;
    }
    
    /**
     * fonction permettant de rechercher dans une liste d'Objet les Objets ayant un champ avec une valeur sp�cifique. La liste peut �tre enrichit
     * @param o la liste d'Objet o� faire la recherche
     * @param fieldname le nom du champ crit�re
     * @param fieldvalue la valeur du champ
     * @return liste des Objets contenant le champ avec la valeur sp�cifi�
     * @throws Exception 
     */
    public static Object[]findObject(Object[]o,String fieldname,Object fieldvalue) throws Exception
    {
        Vector resultat=new Vector();
        int i=0;
        fieldname=fieldname.toLowerCase();
        fieldname=UtilitaireLettre.firstUpper(fieldname);
        String method="get"+fieldname;
        Method m=null;
        while (i<o.length)
        {
            m=o[i].getClass().getDeclaredMethod(method, null);
            Object e=m.invoke(o[i], null);
            if (e.getClass().getName().compareToIgnoreCase("java.lang.String")==0)
            {
                System.out.println("field name="+fieldname);
                System.out.println("field val="+e);
                if ((String)fieldvalue==(String)e)
                {
                    resultat.add(o[i]);
                }
            }
            
            i++;
        }
        return UtilitaireTableau.vectortotab(resultat);
    }
}
