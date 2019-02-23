/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services;

import connexion.DataSource;
import entities.Coach;
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

public class CrudCoach {
Connection cnx= DataSource.getInstance().getConnection() ;    
Statement ste ; 
PreparedStatement pst ; 
    ResultSet rs ; 
    
   
    public List<Coach>displayAllCoachs() throws SQLException{
        String requete="SELECT * FROM utilisateur where type='coach'" ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        List<Coach> list = new ArrayList<>() ; 
        while(rs.next()){
        Coach c = new Coach(rs.getInt("ID_UTILISATEUR"),rs.getString("NOM"),rs.getString("PRENOM"),rs.getDate("DATENAISSANCE"),rs.getString("EMAIL"),rs.getString("EMAIL"),rs.getInt("STATUS"),rs.getInt("NUMTEL"),rs.getFloat("TAILLE"),rs.getFloat("POIDS"),rs.getString("AVATAR"),rs.getString("MDP"),"coach",rs.getString("NIVEAU_COACH"),rs.getString("CERTIF_COACH"),rs.getInt("NOTE_COACH"));
        list.add(c) ;
        }
        return list ;
    }
    public void UpdateCoach(Coach c) throws SQLException{
        String requete="UPDATE utilisateur SET NOM=?, PRENOM=? ,DATENAISSANCE=? , GENDER=? ,EMAIL=? ,STATUS=?, NUMTEL=?,TAILLE=?,POIDS=?,AVATAR=?,MDP=? , NIVEAU_COACH=? , CERTIF_COACH=?, NOTE_COACH=? WHERE id=?" ;
        pst=cnx.prepareStatement(requete) ; 
        pst.setString(1, c.getNom());
        pst.setString(2, c.getPrenom());
        pst.setDate(3, c.getDate_naissance());
        pst.setString(4, c.getGender());
        pst.setString(5, c.getEmail());
        pst.setInt(6, c.getSTATUS());
        pst.setInt(7, c.getNum_tel());
        pst.setFloat(8, c.getTaille());
        pst.setFloat(9, c.getPoids());
        pst.setString(10, c.getAvatar());
        pst.setString(11, c.getMot_passe());
        pst.setString(12, c.getNIVEAU_COACH());
        pst.setString(13, c.getCERTIF_COACH());
        pst.setInt(14, c.getNOTE_COACH());
        pst.setInt(15, c.getId());

        pst.executeUpdate() ; 
       
    }
    public void ApprouverCoach(Coach c) throws SQLException{
        String requete="UPDATE utilisateur SET STATUS=1 WHERE ID_UTILISATEUR="+c.getId() ;
        pst=cnx.prepareStatement(requete) ; 
        pst.executeUpdate() ;    
    }
    public void InsertCoach(Coach m) throws SQLException{
        String requete="Insert into utilisateur (NOM , PRENOM , DATENAISSANCE, GENDER , EMAIL , STATUS , NUMTEL , TAILLE , POIDS , AVATAR , MDP,TYPE,NIVEAU_COACH,CERTIF_COACH,NOTE_COACH) values (?, ? , ?,? ,? ,2, ?,?,?,?,?,'coach',?,?,?) " ;
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
        pst.setString(11, m.getNIVEAU_COACH());
        pst.setString(12, m.getCERTIF_COACH());
        pst.setInt(13, m.getNOTE_COACH());

        pst.executeUpdate() ; 
        MailService ms = new MailService() ; 
        CrudUtilisateur cr = new CrudUtilisateur() ;
    try {
        ms.SendEmailCoach(cr.FindByEmail(m.getEmail()));
    } catch (MessagingException ex) {
        ex.printStackTrace();
    }
      
    }
     public Coach FindCoachById(int id) throws SQLException{
        String requete="SELECT * FROM utilisateur where ID_UTILISATEUR="+id  ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
      
        while(rs.next()){
        
        Coach c = new Coach(rs.getInt("ID_UTILISATEUR"),rs.getString("NOM"),rs.getString("PRENOM"),rs.getDate("DATENAISSANCE"),rs.getString("GENDER"),rs.getString("EMAIL"),rs.getInt("STATUS"),rs.getInt("NUMTEL"),rs.getFloat("TAILLE"),rs.getFloat("POIDS"),rs.getString("AVATAR"),rs.getString("MDP"),"coach",rs.getString("CERTIF_COACH"),rs.getString("NIVEAU_COACH"),rs.getInt("NOTE_COACH"));
        
        return c ;
        }
        return null ;
    }
        public List<Coach> FindCoachByNameOrLastname(String name) throws SQLException{
        String requete="SELECT * FROM utilisateur where (NOM='"+name+"' OR PRENOM='"+name+"') AND Type='coach'" ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        List<Coach> list = new ArrayList<>() ; 
        while(rs.next()){
        
        Coach c = new Coach(rs.getInt("ID_UTILISATEUR"),rs.getString("NOM"),rs.getString("PRENOM"),rs.getDate("DATENAISSANCE"),rs.getString("GENDER"),rs.getString("EMAIL"),rs.getInt("STATUS"),rs.getInt("NUMTEL"),rs.getFloat("TAILLE"),rs.getFloat("POIDS"),rs.getString("AVATAR"),rs.getString("MDP"),"coach",rs.getString("NIVEAU_COACH"),rs.getString("CERTIF_COACH"),rs.getInt("NOTE_COACH"));
        
        list.add(c) ;
        }
        return list ;
    }
    
}
