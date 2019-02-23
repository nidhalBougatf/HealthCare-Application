/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import connexion.DataSource;
import entities.Offre;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static jdk.nashorn.internal.objects.NativeMath.round;

/**
 *
 * @author Nidhal Bougatf
 */
public class CrudOffre {
      Connection cnx= DataSource.getInstance().getConnection() ;    
Statement ste ; 
PreparedStatement pst ; 
    ResultSet rs ; 
    
    
    public ObservableList<Offre> displayAllOffre() throws SQLException{
        String requete="SELECT e.nom_es,ID_OFFRE,DESCRIPTION_O,TITRE_O, ANCIEN_PRIX, NOUVEAU_PRIX, DATEDEBUT_O, DATEFIN_O, AFFICHE_O FROM Offre o join espace e on o.id_espace=e.id_espace " ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        ObservableList<Offre> list = FXCollections.observableArrayList(); 
        while(rs.next()){
        Offre o;
            o = new Offre(rs.getString(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getFloat(5),rs.getFloat(6),rs.getDate(7).toLocalDate(),rs.getDate(8).toLocalDate(),rs.getString(9));
        list.add(o) ;
        }
        return list ;
    }
    public Offre getOffreById(Integer idd) throws SQLException{
        String requete="SELECT * from offre where ID_Offre="+idd+" " ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        Offre o=null;
        while(rs.next()){
        
            o = new Offre(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getFloat(5),rs.getFloat(6),rs.getDate(7).toLocalDate(),rs.getDate(8).toLocalDate(),rs.getString(9));
 
        }
        return o ;
    }
   
    
    public List<String> getAllEspace() throws SQLException{
        String requete="SELECT distinct(nom_es) from espace" ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        List<String> list = new ArrayList<>() ; 
        while(rs.next()){
        String p = rs.getString(1);
            list.add(p);
        }
        return list ;
    }
    public String getNomEspaceID( int idd) throws SQLException{
        String requete="SELECT nom_es from espace where id_espace="+idd+"" ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        String p="";
        while(rs.next()){
        p = rs.getString(1);
            
        }
        return p;
    }
        public int getIDNomEspace( String nome) throws SQLException{
        String req1="SELECT id_espace from espace where nom_es='"+nome+"'"; 
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(req1);
        int p=0;
        while (rs.next())
        {
           p= rs.getInt(1); 
        }
        System.out.println("nom"+nome);
        System.out.println("idd"+p);
        return p;
    }
    
    
    public void InsertOffre(Offre o) throws SQLException
    {
        String req1="SELECT id_espace from espace where nom_es='"+o.getNom_es()+"'"; 
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(req1);
        int idd=0;
        while (rs.next())
        {
           idd= rs.getInt(1); 
        }
        
        String aff=o.getAFFICHE_O();
        Date datedeb = java.sql.Date.valueOf(o.getDATEDEBUT_O());
        Date datefin = java.sql.Date.valueOf(o.getDATEFIN_O());
        String req="INSERT INTO  offre ( ID_ESPACE, DESCRIPTION_O, TITRE_O, ANCIEN_PRIX, NOUVEAU_PRIX, DATEDEBUT_O, DATEFIN_O, AFFICHE_O) VALUES (?,?,?,?,?,?,?,?)";
        
        pst=cnx.prepareStatement(req) ; 
        pst.setInt(1, idd);
        pst.setString(2, o.getDESCRIPTION_O());
        pst.setString(3,o.getTITRE_O());
        pst.setDouble(4, o.getANCIEN_PRIX());
        pst.setDouble(5, o.getNOUVEAU_PRIX());
        pst.setDate(6, datedeb);
        pst.setDate(7, datefin);
        pst.setString(8, aff);
        pst.executeUpdate(); 
        
        /*String req2="Select numtel from UTILISATEUR";
        ste=cnx.createStatement();  
        ste.executeQuery(req2); */
        long num;
        /*while (rs.next())
        {
           num= rs.getInt(1); 
           System.out.println("SMS : "+this.sendSms(o.toSMS(),num));
        }*/
        num = 24169508;
        System.out.println("SMS : "+this.sendSms(o.toSMS(),num));
        
        
        
        
    }
    
    public String sendSms(String msg,long num) {
		try {
			// Construct data
			String apiKey = "apikey=" + "5Kpn3qayaKI-S7ESsrfAyPesZlMovvIRk2XC04WGxa";
			String message = "&message=" + msg;
			String sender = "&sender=" + "HealthCare";
                        String numbers = "&numbers=" + "21624169508";
		//	String numbers = "&numbers=" + "00216"+String.valueOf(num);
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
			
			return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			return "Error "+e;
		}
	}
    
    
    
    public void UpdateOffre(Offre o) throws SQLException
    {
        
        
        String aff=o.getAFFICHE_O();
        Date datedeb = java.sql.Date.valueOf(o.getDATEDEBUT_O());
        Date datefin = java.sql.Date.valueOf(o.getDATEFIN_O());
        String req="UPDATE offre  SET ID_ESPACE=?,DESCRIPTION_O=?,TITRE_O=?,ANCIEN_PRIX=?,NOUVEAU_PRIX=?,DATEDEBUT_O=?,DATEFIN_O=?,AFFICHE_O=? WHERE ID_OFFRE=? ";
        pst=cnx.prepareStatement(req) ; 
        pst.setInt(1, o.getID_ESPACE());
        pst.setString(2, o.getDESCRIPTION_O());
        pst.setString(3,o.getTITRE_O());
        pst.setDouble(4, o.getANCIEN_PRIX());
        pst.setDouble(5, o.getNOUVEAU_PRIX());
        pst.setDate(6, datedeb);
        pst.setDate(7, datefin);
        pst.setString(8, aff);
        pst.setInt(9, o.getID_OFFRE());
        pst.executeUpdate(); 
        
    }
    
    public void DeleteOffre(int id) throws SQLException
    {
        String req="Delete from Offre where ID_OFFRE="+id+" "; 
        ste=cnx.createStatement();  
        ste.executeUpdate(req);    
    }
    /*
    public List<Offre>searchOffreByNameEspace(String nom) throws SQLException{
        String requete="SELECT * FROM Offre where ID_ESPACE= (Select ID_ESPACE from Espace where NOM_ES='"+nom+"')" ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        List<Offre> list = new ArrayList<>() ; 
        while(rs.next()){
        Offre o;
            o = new Offre(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getDouble(5),rs.getDouble(6),rs.getDate(7).toLocalDate(),rs.getDate(8).toLocalDate(),rs.getString(9));
        list.add(o) ;
        }
        return list ;
    }
    */
    public ObservableList<Offre>searchOffreByTitre(String titre) throws SQLException{
        String requete="SELECT e.nom_es,DESCRIPTION_O,TITRE_O, ANCIEN_PRIX, NOUVEAU_PRIX, DATEDEBUT_O, DATEFIN_O, AFFICHE_O FROM Offre o join espace e on o.id_espace=e.id_espace where titre_o LIKE '%"+titre+"%' OR titre_o LIKE '"+titre+"%' OR titre_o LIKE '"+titre+"%'" ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        ObservableList<Offre> list = FXCollections.observableArrayList(); 
        while(rs.next()){
            
        Offre o  = new Offre(rs.getString(1),rs.getString(2),rs.getString(3),rs.getFloat(4),rs.getFloat(5),rs.getDate(6).toLocalDate(),rs.getDate(7).toLocalDate(),rs.getString(8));
        list.add(o) ;
        }
        return list ;
    }
    
    
    public List<Offre>searchOffreMultiple(String nomesp,String etat,double prixmin,double prixmax) throws SQLException
    {
        String requete;
        if(nomesp !="Tous")
        {
            if (etat.equals("Epuise"))
            {
              requete="SELECT e.nom_es,ID_OFFRE,DESCRIPTION_O,TITRE_O, ANCIEN_PRIX, NOUVEAU_PRIX, DATEDEBUT_O, DATEFIN_O, AFFICHE_O FROM Offre o join espace e on o.id_espace=e.id_espace where o.ID_ESPACE= (Select ID_ESPACE from Espace where NOM_ES='"+nomesp+"')  AND nouveau_prix >= "+prixmin+" and nouveau_prix <="+prixmax+" AND (CURRENT_TIMESTAMP  > DATEFIN_O)   " ;
                ste=cnx.createStatement() ; 
            }
            else if (etat.equals("Disponible"))
            {
               requete="SELECT e.nom_es,ID_OFFRE,DESCRIPTION_O,TITRE_O, ANCIEN_PRIX, NOUVEAU_PRIX, DATEDEBUT_O, DATEFIN_O, AFFICHE_O FROM Offre o join espace e on o.id_espace=e.id_espace where o.ID_ESPACE= (Select ID_ESPACE from Espace where NOM_ES='"+nomesp+"')  AND nouveau_prix >= "+prixmin+" and nouveau_prix <="+prixmax+" AND (CURRENT_TIMESTAMP  < DATEFIN_O)   " ;
                ste=cnx.createStatement() ; 
            }
            else
             {
                requete="SELECT e.nom_es,ID_OFFRE,DESCRIPTION_O,TITRE_O, ANCIEN_PRIX, NOUVEAU_PRIX, DATEDEBUT_O, DATEFIN_O, AFFICHE_O FROM Offre o join espace e on o.id_espace=e.id_espace where o.ID_ESPACE= (Select ID_ESPACE from Espace where NOM_ES='"+nomesp+"')  AND nouveau_prix >= "+prixmin+" and nouveau_prix <="+prixmax+"    " ;
                ste=cnx.createStatement() ; 
            }   
        }
        else
        {
            if (etat.equals("Epuise"))
            {
              requete="SELECT e.nom_es,ID_OFFRE,DESCRIPTION_O,TITRE_O, ANCIEN_PRIX, NOUVEAU_PRIX, DATEDEBUT_O, DATEFIN_O, AFFICHE_O FROM Offre o join espace e on o.id_espace=e.id_espace where  nouveau_prix >= "+prixmin+" and nouveau_prix <="+prixmax+" AND (CURRENT_TIMESTAMP  > DATEFIN_O)   " ;
                ste=cnx.createStatement() ; 
            }
            else if (etat.equals("Disponible"))
            {
               requete="SELECT e.nom_es,ID_OFFRE,DESCRIPTION_O,TITRE_O, ANCIEN_PRIX, NOUVEAU_PRIX, DATEDEBUT_O, DATEFIN_O, AFFICHE_O FROM Offre o join espace e on o.id_espace=e.id_espace where  nouveau_prix >= "+prixmin+" and nouveau_prix <="+prixmax+" AND (CURRENT_TIMESTAMP  < DATEFIN_O)   " ;
                ste=cnx.createStatement() ; 
            }
            else
             {
                requete="SELECT e.nom_es,ID_OFFRE,DESCRIPTION_O,TITRE_O, ANCIEN_PRIX, NOUVEAU_PRIX, DATEDEBUT_O, DATEFIN_O, AFFICHE_O FROM Offre o join espace e on o.id_espace=e.id_espace where  nouveau_prix >= "+prixmin+" and nouveau_prix <="+prixmax+"   " ;
                ste=cnx.createStatement() ; 
            }
            
        }
        
        
        rs=ste.executeQuery(requete);
        List<Offre> list = new ArrayList<>() ; 
        while(rs.next()){
        Offre o  = new Offre(rs.getString(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getFloat(5),rs.getFloat(6),rs.getDate(7).toLocalDate(),rs.getDate(8).toLocalDate(),rs.getString(9));
                list.add(o) ;
        }
        return list ;
    }
    public List<Offre>searchOffreWithoutprix(String nomesp,String etat) throws SQLException{
        
        
        String requete;
        if(nomesp!="Tous")
        {
            if (etat.equals("Epuise"))
            {
              requete="SELECT e.nom_es,ID_OFFRE,DESCRIPTION_O,TITRE_O, ANCIEN_PRIX, NOUVEAU_PRIX, DATEDEBUT_O, DATEFIN_O, AFFICHE_O FROM Offre o join espace e on o.id_espace=e.id_espace where o.ID_ESPACE= (Select ID_ESPACE from Espace where NOM_ES='"+nomesp+"')  AND (CURRENT_TIMESTAMP  > DATEFIN_O)   " ;
                ste=cnx.createStatement() ; 
            }
            else if (etat.equals("Disponible"))
            {
               requete="SELECT e.nom_es,ID_OFFRE,DESCRIPTION_O,TITRE_O, ANCIEN_PRIX, NOUVEAU_PRIX, DATEDEBUT_O, DATEFIN_O, AFFICHE_O FROM Offre o join espace e on o.id_espace=e.id_espace  where o.ID_ESPACE= (Select ID_ESPACE from Espace where NOM_ES='"+nomesp+"')   AND (CURRENT_TIMESTAMP  < DATEFIN_O)   " ;
                ste=cnx.createStatement() ; 
            }
            else
             {
                requete="SELECT e.nom_es,ID_OFFRE,DESCRIPTION_O,TITRE_O, ANCIEN_PRIX, NOUVEAU_PRIX, DATEDEBUT_O, DATEFIN_O, AFFICHE_O FROM Offre o join espace e on o.id_espace=e.id_espace where o.ID_ESPACE= (Select ID_ESPACE from Espace where NOM_ES='"+nomesp+"') " ;
                ste=cnx.createStatement() ; 
            }   
        }
        else
        {
            if (etat.equals("Epuise"))
            {
              requete="SELECT e.nom_es,ID_OFFRE,DESCRIPTION_O,TITRE_O, ANCIEN_PRIX, NOUVEAU_PRIX, DATEDEBUT_O, DATEFIN_O, AFFICHE_O FROM Offre o join espace e on o.id_espace=e.id_espace where  (CURRENT_TIMESTAMP  > DATEFIN_O)   " ;
                ste=cnx.createStatement() ; 
            }
            else if (etat.equals("Disponible"))
            {
               requete="SELECT e.nom_es,ID_OFFRE,DESCRIPTION_O,TITRE_O, ANCIEN_PRIX, NOUVEAU_PRIX, DATEDEBUT_O, DATEFIN_O, AFFICHE_O FROM Offre o join espace e on o.id_espace=e.id_espace where   (CURRENT_TIMESTAMP  < DATEFIN_O)   " ;
                ste=cnx.createStatement() ; 
            }
            else
             {
                requete="SELECT e.nom_es,ID_OFFRE,DESCRIPTION_O,TITRE_O, ANCIEN_PRIX, NOUVEAU_PRIX, DATEDEBUT_O, DATEFIN_O, AFFICHE_O FROM Offre o join espace e on o.id_espace=e.id_espace  " ;
                ste=cnx.createStatement() ; 
                 System.out.println("test");
            }  
            
            
        }
        
        
        rs=ste.executeQuery(requete);
        List<Offre> list = new ArrayList<>() ; 
        while(rs.next()){
        Offre o  = new Offre(rs.getString(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getFloat(5),rs.getFloat(6),rs.getDate(7).toLocalDate(),rs.getDate(8).toLocalDate(),rs.getString(9));
        list.add(o) ;
        }
        return list ;
        }
    
    
        public void statistiquesOffre(Map<String,Double> maps) throws SQLException
        {
            
             String requete1="SELECT distinct(Type_es) from espace";
             ste=cnx.createStatement() ;
             rs=ste.executeQuery(requete1);
             List<String> types = new ArrayList<>();
             while(rs.next())
             {
                types.add(rs.getString(1));
             }

            
             int total=0;
             String requete2="Select count(ID_OFFRE) from offre  " ;
             ste=cnx.createStatement() ;
             rs=ste.executeQuery(requete2);
             while(rs.next())
             {
             total=rs.getInt(1);
             }

            
             int i=0;

             for(i=0;i<types.size();i++)
             {
                 String requete="Select count(ID_OFFRE) from offre o join espace e on e.ID_ESPACE=o.ID_ESPACE WHERE e.TYPE_ES='"+types.get(i)+"' " ;
                    ste=cnx.createStatement() ;
                    rs=ste.executeQuery(requete);
                    Double nbro=0.0;
                    String nomesp="";
                    Double moyesp=0.0;                    
                    while(rs.next())
                    {
                    nomesp=types.get(i);
                    nbro=rs.getDouble(1);
                    moyesp=(nbro / total)*100;
                    Double  b = Math.PI;
                    maps.put(nomesp,moyesp);
                    }

             }
           
        

        }
    

    
    /*
    public List<Offre>displayOffreExpire() throws SQLException{
        String requete="SELECT * FROM Offre where  CURRENT_TIMESTAMP  > DATEFIN_O " ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        List<Offre> list = new ArrayList<>() ; 
        while(rs.next()){
        Offre o = new Offre(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getFloat(5),rs.getFloat(6),rs.getDate(7).toLocalDate(),rs.getDate(8).toLocalDate(),rs.getString(9));
        list.add(o) ;
        }
        return list ;
    }
    public List<Offre>displayOffreValable() throws SQLException{
        String requete="SELECT * FROM Offre where  CURRENT_TIMESTAMP  < DATEFIN_O " ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        List<Offre> list = new ArrayList<>() ; 
        while(rs.next()){
        Offre o = new Offre(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getFloat(5),rs.getFloat(6),rs.getDate(7).toLocalDate(),rs.getDate(8).toLocalDate(),rs.getString(9));
        list.add(o) ;
        }
        return list ;
    }
  
    */

}
