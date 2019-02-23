/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services;

import connexion.DataSource;
import entities.Membre;
import entities.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rami
 */

public class CrudUtilisateur {
Connection cnx= DataSource.getInstance().getConnection() ;    
Statement ste ; 
PreparedStatement pst ; 
    ResultSet rs ; 
    
   
  
    public void BanUser(Utilisateur m) throws SQLException{
        String requete = "Update utilisateur set status=4 where ID_UTILISATEUR="+m.getId() ;
        ste=cnx.createStatement() ;
        ste.executeUpdate(requete); 
                }
    public void UnBanUser(Utilisateur m) throws SQLException{
        String requete = "Update utilisateur set status=1 where ID_UTILISATEUR="+m.getId() ;
        ste=cnx.createStatement() ;
        ste.executeUpdate(requete); 
                }
    public List<Utilisateur>displayAllUsers() throws SQLException{
        String requete="SELECT * FROM utilisateur" ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        List<Utilisateur> list = new ArrayList<>() ; 
        while(rs.next()){
        Utilisateur m = new Utilisateur(rs.getInt("ID_UTILISATEUR"),rs.getString("NOM"),rs.getString("PRENOM"),rs.getDate("DATENAISSANCE"),rs.getString("GENDER"),rs.getString("EMAIL"),rs.getInt("STATUS"),rs.getInt("NUMTEL"),rs.getFloat("TAILLE"),rs.getFloat("POIDS"),rs.getString("AVATAR"),rs.getString("MDP"),rs.getString("TYPE"));
        list.add(m) ;
        }
        return list ;
    }
   
        public List<Utilisateur> FindUserByNameOrLastname(String name) throws SQLException{
        String requete="SELECT * FROM utilisateur where (NOM LIKE '%"+name+"%' OR PRENOM LIKE '%"+name+"%')" ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        List<Utilisateur> list = new ArrayList<>() ; 
        while(rs.next()){
        
        Utilisateur m = new Utilisateur(rs.getInt("ID_UTILISATEUR"),rs.getString("NOM"),rs.getString("PRENOM"),rs.getDate("DATENAISSANCE"),rs.getString("GENDER"),rs.getString("EMAIL"),rs.getInt("STATUS"),rs.getInt("NUMTEL"),rs.getFloat("TAILLE"),rs.getFloat("POIDS"),rs.getString("AVATAR"),rs.getString("MDP"),rs.getString("TYPE"));
        
        list.add(m) ;
        }
        return list ;
    }
        public  Utilisateur Authentification(String email , String password) throws SQLException{
        String requete="SELECT * FROM utilisateur where EMAIL='"+email+"' AND MDP='"+password+"'" ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
      
        while(rs.next()){
        
        Utilisateur m = new Utilisateur(rs.getInt("ID_UTILISATEUR"),rs.getString("NOM"),rs.getString("PRENOM"),rs.getDate("DATENAISSANCE"),rs.getString("GENDER"),rs.getString("EMAIL"),rs.getInt("STATUS"),rs.getInt("NUMTEL"),rs.getFloat("TAILLE"),rs.getFloat("POIDS"),rs.getString("AVATAR"),rs.getString("MDP"),rs.getString("TYPE"));
        
        return m ;
        }
        return null ;
    
    }
     
     public Utilisateur FindByEmail(String email) throws SQLException{
        String requete="SELECT * FROM utilisateur where EMAIL='"+email+"'" ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        
        while(rs.next()){
        
        Utilisateur m = new Utilisateur(rs.getInt("ID_UTILISATEUR"),rs.getString("NOM"),rs.getString("PRENOM"),rs.getDate("DATENAISSANCE"),rs.getString("GENDER"),rs.getString("EMAIL"),rs.getInt("STATUS"),rs.getInt("NUMTEL"),rs.getFloat("TAILLE"),rs.getFloat("POIDS"),rs.getString("AVATAR"),rs.getString("MDP"),rs.getString("TYPE"));
        return m ;
        }
        return null ;
       
    }
      public Utilisateur FindUserById(int id) throws SQLException{
        String requete="SELECT * FROM utilisateur where ID_UTILISATEUR="+id  ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
      
        while(rs.next()){
        
        Utilisateur c = new Utilisateur(rs.getInt("ID_UTILISATEUR"),rs.getString("NOM"),rs.getString("PRENOM"),rs.getDate("DATENAISSANCE"),rs.getString("GENDER"),rs.getString("EMAIL"),rs.getInt("STATUS"),rs.getInt("NUMTEL"),rs.getFloat("TAILLE"),rs.getFloat("POIDS"),rs.getString("AVATAR"),rs.getString("MDP"),rs.getString("TYPE"));
        
        return c ;
        }
        return null ;
    }
      public List<Utilisateur>displayUsersByType(String type) throws SQLException{
        String requete="SELECT * FROM utilisateur where type= "+type+" " ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
        List<Utilisateur> list = new ArrayList<>() ; 
        while(rs.next()){
        Utilisateur m = new Utilisateur(rs.getInt("ID_UTILISATEUR"),rs.getString("NOM"),rs.getString("PRENOM"),rs.getDate("DATENAISSANCE"),rs.getString("GENDER"),rs.getString("EMAIL"),rs.getInt("STATUS"),rs.getInt("NUMTEL"),rs.getFloat("TAILLE"),rs.getFloat("POIDS"),rs.getString("AVATAR"),rs.getString("MDP"),rs.getString("TYPE"));
        list.add(m) ;
        }
        return list ;
    }
       public void UpdateMembreToModerateur(Utilisateur m) throws SQLException{
        String requete="UPDATE utilisateur SET type='moderateur' WHERE ID_UTILISATEUR=?" ;
        pst=cnx.prepareStatement(requete) ;  
        pst.setInt(1, m.getId());
        pst.executeUpdate() ; 
       
    }
        public void ApprouverCoach(Utilisateur m) throws SQLException{
        String requete="UPDATE utilisateur SET STATUS=1 WHERE ID_UTILISATEUR=?" ;
        pst=cnx.prepareStatement(requete) ;  
        pst.setInt(1, m.getId());
        pst.executeUpdate() ; 
       
    }
}
