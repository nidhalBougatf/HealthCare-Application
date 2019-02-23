/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services;

import connexion.DataSource;
import entities.Membre;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.mail.MessagingException;

/**
 *
 * @author Rami
 */

public class CrudMembre {
Connection cnx= DataSource.getInstance().getConnection() ;    
Statement ste ; 
PreparedStatement pst ; 
    ResultSet rs ; 
    
   
  
  
    public List<Membre>displayAll() throws SQLException{
        String requete="SELECT * FROM utilisateur where type='membre'" ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        List<Membre> list = new ArrayList<>() ; 
        while(rs.next()){
        Membre c = new Membre(rs.getInt("ID_UTILISATEUR"),rs.getString("NOM"),rs.getString("PRENOM"),rs.getDate("DATENAISSANCE"),rs.getString("EMAIL"),rs.getString("EMAIL"),rs.getInt("STATUS"),rs.getInt("NUMTEL"),rs.getFloat("TAILLE"),rs.getFloat("POIDS"),rs.getString("AVATAR"),rs.getString("MDP"),"membre");
        list.add(c) ;
        }
        return list ;
    }
    public void UpdateMembre(Membre m) throws SQLException{
        String requete="UPDATE utilisateur SET NOM=?, PRENOM=? ,DATENAISSANCE=?, GENDER=? ,EMAIL=? ,STATUS=?, NUMTEL=?,TAILLE=?,POIDS=?,AVATAR=?,MDP=? WHERE id=?" ;
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
    public Membre FindMembreById(int id) throws SQLException{
        String requete="SELECT * FROM utilisateur where ID_UTILISATEUR="+id  ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
      
        while(rs.next()){
        
        Membre c = new Membre(rs.getInt("ID_UTILISATEUR"),rs.getString("NOM"),rs.getString("PRENOM"),rs.getDate("DATENAISSANCE"),rs.getString("GENDER"),rs.getString("EMAIL"),rs.getInt("STATUS"),rs.getInt("NUMTEL"),rs.getFloat("TAILLE"),rs.getFloat("POIDS"),rs.getString("AVATAR"),rs.getString("MDP"),"membre");
        
        return c ;
        }
        return null ;
    }
        public List<Membre> FindMembreByNameOrLastname(String name) throws SQLException{
        String requete="SELECT * FROM utilisateur where (NOM='"+name+"' OR PRENOM='"+name+"') AND Type='membre'" ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        List<Membre> list = new ArrayList<>() ; 
        while(rs.next()){
        
        Membre c = new Membre(rs.getInt("ID_UTILISATEUR"),rs.getString("NOM"),rs.getString("PRENOM"),rs.getDate("DATENAISSANCE"),rs.getString("GENDER"),rs.getString("EMAIL"),rs.getInt("STATUS"),rs.getInt("NUMTEL"),rs.getFloat("TAILLE"),rs.getFloat("POIDS"),rs.getString("AVATAR"),rs.getString("MDP"),"membre");
        
        list.add(c) ;
        }
        return list ;
    }
        
     public List<Membre> filtre(List<Membre> list  , int i){
        return list.stream().filter(e->e.getSTATUS()==i).collect(Collectors.toList()) ;
    }
     
      public void InsertMembre(Membre m) throws SQLException{
        String requete="Insert into utilisateur (NOM , PRENOM , DATENAISSANCE , EMAIL , STATUS , NUMTEL , TAILLE , POIDS , AVATAR , MDP,TYPE , GENDER) values (?, ?,?,? ,2, ?,?,?,?,?,'membre',?) " ;
        pst=cnx.prepareStatement(requete) ; 
        pst.setString(1, m.getNom());
        pst.setString(2, m.getPrenom());
        pst.setDate(3, m.getDate_naissance());
        
        pst.setString(4, m.getEmail());
        pst.setInt(5, m.getNum_tel());
        pst.setFloat(6, m.getTaille());
        pst.setFloat(7, m.getPoids());
        pst.setString(8, m.getAvatar());
        pst.setString(9, m.getMot_passe());
        pst.setString(10, m.getGender());
        pst.executeUpdate() ; 
        MailService ms = new MailService() ;
        CrudUtilisateur c = new CrudUtilisateur() ;
    try {
        System.out.println( c.FindByEmail(m.getEmail()).getId()) ;
        ms.SendEmailModOrMembre(c.FindByEmail(m.getEmail()));
    } catch (MessagingException ex) {
        ex.printStackTrace();
    }
       
    }
     
}
