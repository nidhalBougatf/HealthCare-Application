/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services;

import connexion.DataSource;
import entities.Moderateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;

/**
 *
 * @author Rami
 */

public class CrudModerateur {
Connection cnx= DataSource.getInstance().getConnection() ;    
Statement ste ; 
PreparedStatement pst ; 
    ResultSet rs ; 
    
   
  

    public List<Moderateur>displayAllModerateur() throws SQLException{
        String requete="SELECT * FROM utilisateur where type='moderateur'" ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        List<Moderateur> list = new ArrayList<>() ; 
        while(rs.next()){
        Moderateur c = new Moderateur(rs.getInt("ID_UTILISATEUR"),rs.getString("NOM"),rs.getString("PRENOM"),rs.getDate("DATENAISSANCE"),rs.getString("EMAIL"),rs.getString("EMAIL"),rs.getInt("STATUS"),rs.getInt("NUMTEL"),rs.getFloat("TAILLE"),rs.getFloat("POIDS"),rs.getString("AVATAR"),rs.getString("MDP"),"moderateur");
        list.add(c) ;
        }
        return list ;
    }
    public void UpdateModerateur(Moderateur m) throws SQLException{
        String requete="UPDATE utilisateur SET NOM=?, PRENOM=? ,DATENAISSANCE=? , GENDER=? ,EMAIL=? ,STATUS=?, NUMTEL=?,TAILLE=?,POIDS=?,AVATAR=?,MDP=? WHERE id=?" ;
        pst=cnx.prepareStatement(requete) ; 
        pst.setString(1, m.getNom());
        pst.setString(2, m.getPrenom());
        pst.setDate(3, m.getDate_naissance());
        pst.setString(4, m.getGender());
        pst.setString(5, m.getEmail());
        pst.setInt(6, m.getSTATUS());
        pst.setInt(7, m.getNum_tel());
        pst.setFloat(8, m.getTaille());
        pst.setFloat(9, m.getPoids());
        pst.setString(10, m.getAvatar());
        pst.setString(11, m.getMot_passe());
        pst.setInt(12, m.getId());
        pst.executeUpdate() ; 
       
    }
   
     public void InsertModerateur(Moderateur m) throws SQLException{
        String requete="Insert into utilisateur (NOM , PRENOM , DATENAISSANCE ,GENDER, EMAIL , STATUS , NUMTEL , TAILLE , POIDS , AVATAR , MDP,TYPE) values (?, ?,?,? ,? ,2, ?,?,?,?,?,'moderateur') " ;
        pst=cnx.prepareStatement(requete) ; 
        pst.setString(1, m.getNom());
        pst.setString(2, m.getPrenom());
        pst.setDate(3, m.getDate_naissance());
        pst.setString(4, m.getGender());
        pst.setString(5, m.getEmail());
        pst.setInt(6, m.getNum_tel());
        pst.setFloat(7, m.getTaille());
        pst.setFloat(8, m.getPoids());
        pst.setString(9, m.getAvatar());
        pst.setString(10, m.getMot_passe());
       

        pst.executeUpdate() ; 
        MailService ms = new MailService() ; 
    try {
        ms.SendEmailModOrMembre(m);
    } catch (MessagingException ex) {
        ex.printStackTrace();
    }
     
    }
     public Moderateur FindModerateurById(int id) throws SQLException{
        String requete="SELECT * FROM utilisateur where ID_UTILISATEUR="+id  ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
      
        while(rs.next()){
        
        Moderateur c = new Moderateur(rs.getInt("ID_UTILISATEUR"),rs.getString("NOM"),rs.getString("PRENOM"),rs.getDate("DATENAISSANCE"),rs.getString("EMAIL"),rs.getString("EMAIL"),rs.getInt("STATUS"),rs.getInt("NUMTEL"),rs.getFloat("TAILLE"),rs.getFloat("POIDS"),rs.getString("AVATAR"),rs.getString("MDP"),"membre");
        
        return c ;
        }
        return null ;
    }
        public List<Moderateur> FindModerateurNameOrLastname(String name) throws SQLException{
        String requete="SELECT * FROM utilisateur where (NOM='"+name+"' OR PRENOM='"+name+"') AND Type='moderateur'" ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        List<Moderateur> list = new ArrayList<>() ; 
        while(rs.next()){
        
        Moderateur c = new Moderateur(rs.getInt("ID_UTILISATEUR"),rs.getString("NOM"),rs.getString("PRENOM"),rs.getDate("DATENAISSANCE"),rs.getString("EMAIL"),rs.getString("EMAIL"),rs.getInt("STATUS"),rs.getInt("NUMTEL"),rs.getFloat("TAILLE"),rs.getFloat("POIDS"),rs.getString("AVATAR"),rs.getString("MDP"),"membre");
        
        list.add(c) ;
        }
        return list ;
    }
}
