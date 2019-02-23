/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services;

import connexion.DataSource;
import entities.Reclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rami
 */

public class CrudReclamation {
Connection cnx= DataSource.getInstance().getConnection() ;    
Statement ste ; 
PreparedStatement pst ; 
    ResultSet rs ; 
    
   
  
    public void ChangerStatusToTraite(Reclamation r) throws SQLException{
        String requete = "Update reclamation set ETAT_R='traite' where ID_RECLAMATION="+r.getID_RECLAMATION() ; ;
        ste=cnx.createStatement() ;
        ste.executeUpdate(requete); 
                }
     public void ChangerStatusToNonTraite(Reclamation r) throws SQLException{
        String requete = "Update reclamation set ETAT_R='nontraite' where ID_RECLAMATION="+r.getID_RECLAMATION() ;
        ste=cnx.createStatement() ;
        ste.executeUpdate(requete); 
                }
   
     public List<Reclamation>displayAllNonTraiter() throws SQLException{
        String requete="SELECT * FROM reclamation where ETAT_R='nontraite'" ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        List<Reclamation> list = new ArrayList<>() ; 
        while(rs.next()){
        Reclamation r = new Reclamation(rs.getInt("ID_RECLAMATION"),rs.getInt("ID_UTILISATEUR"),rs.getString("TYPE_R"),rs.getString("COMMENTAIRE_R"),rs.getString("ETAT_R"),rs.getDate("DATE_R"));
        list.add(r) ;
        }
        return list ;
    }
     public List<Reclamation>displayAll() throws SQLException{
        String requete="SELECT * FROM reclamation" ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        List<Reclamation> list = new ArrayList<>() ; 
        while(rs.next()){
        Reclamation r = new Reclamation(rs.getInt("ID_RECLAMATION"),rs.getInt("ID_UTILISATEUR"),rs.getString("TYPE_R"),rs.getString("COMMENTAIRE_R"),rs.getString("ETAT_R"),rs.getDate("DATE_R"));
        list.add(r) ;
        }
        return list ;
    }
    public Reclamation findById(int id) throws SQLException{
         String requete="SELECT * FROM reclamation where ID_RECLAMATION="+id ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        
        while(rs.next()){
        Reclamation r = new Reclamation(rs.getInt("ID_RECLAMATION"),rs.getInt("ID_UTILISATEUR"),rs.getString("TYPE_R"),rs.getString("COMMENTAIRE_R"),rs.getString("ETAT_R"),rs.getDate("DATE_R"));
        return r ;
        }
        return null ;
       
    }
    
    public List<Reclamation> SearchReclamation(String comment) throws SQLException{
        String requete="SELECT * FROM reclamation where COMMENTAIRE_R LIKE'%"+comment+"%'" ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        List<Reclamation> list = new ArrayList<>() ; 
        while(rs.next()){
        
        Reclamation r = new Reclamation(rs.getInt("ID_RECLAMATION"),rs.getInt("ID_UTILISATEUR"),rs.getString("TYPE_R"),rs.getString("COMMENTAIRE_R"),rs.getString("ETAT_R"),rs.getDate("DATE_R"));
        
        list.add(r) ;
        }
        return list ;
    }
    public List<Reclamation> SearchReclamationByStatus(String type) throws SQLException{
        String requete="SELECT * FROM reclamation where ETAT_R="+type+" " ;       
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        List<Reclamation> list = new ArrayList<>() ; 
        while(rs.next()){
        
        Reclamation r = new Reclamation(rs.getInt("ID_RECLAMATION"),rs.getInt("ID_UTILISATEUR"),rs.getString("TYPE_R"),rs.getString("COMMENTAIRE_R"),rs.getString("ETAT_R"),rs.getDate("DATE_R"));
        
        list.add(r) ;
        }
        return list ;
    }
   
}
