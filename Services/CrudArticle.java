/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import connexion.DataSource;
import entities.Article;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author CePc
 */
public class CrudArticle {
Connection con= DataSource.getInstance().getConnection() ;    
Statement ste ; 
PreparedStatement pst ; 
    ResultSet rs ;
    
     public void insertSt(Article a) throws SQLException{
         
        String requete = "INSERT INTO Article (ID_UTILISATEUR,TITRE_A,DESCRIPTION_A,EDITEUR_A,TYPE_A,AFFICHE_A,Date_a) values"
                + "('"+a.getID_UTILISATEUR()+"','"+a.getTITRE_A()+"','"+a.getDESCRIPTION_A()+"','"+a.getEDITEUR_A()+"','"+a.getTYPE_A()+"','"+a.getAFFICHE_A()+"','"+a.getDate_a()+"')" ;
                
        ste=con.createStatement() ;
        ste.executeUpdate(requete ); 
        
    }
    
    
     public void DeleteSt(int id) throws SQLException{
        String requete = "DELETE FROM Article WHERE id_ARTICLE="+id ;
        ste=con.createStatement() ;
        ste.executeUpdate(requete); 
                }
      public void UpdateArticle(Article a) throws SQLException{
        String requete="UPDATE Article SET TITRE_A=?, DESCRIPTION_A=?, EDITEUR_A=?, TYPE_A=?,AFFICHE_A=?,Date_a=? WHERE ID_ARTICLE=?" ;
       
        pst=con.prepareStatement(requete) ;
        
        pst.setString(1, a.getTITRE_A());
        pst.setString(2, a.getDESCRIPTION_A());
        pst.setString(3, a.getEDITEUR_A());
        pst.setString(4, a.getTYPE_A());
        pst.setString(5, a.getAFFICHE_A());
        pst.setDate(6, a.getDate_a());
        pst.setInt(7, a.getID_ARTICLE());
        
         
        
        pst.executeUpdate() ; 
       
    }
      
      
       public ObservableList<Article> displayAllArticle() throws SQLException{
        String requete="SELECT * FROM Article" ;
        ste=con.createStatement() ;
        rs=ste.executeQuery(requete);
        ObservableList<Article> list =  FXCollections.observableArrayList() ; 
        while(rs.next()){
        Article a = new Article(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7), rs.getDate(8));
        list.add(a) ;
        }
        return list ;
    }
      
     
     
     
     
     public ObservableList<Article> searchByNameArticle(String e) throws SQLException{
        String requete="SELECT * FROM article where TITRE_A LIKE '%"+e+"%'" ;
         System.out.println(requete);
        ste=con.createStatement() ;
        rs=ste.executeQuery(requete);
        ObservableList<Article>  list = FXCollections.observableArrayList()  ; 
        while(rs.next()){
        Article a = new Article(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getDate(8));     
        list.add(a) ;
        
        }
         

        return list ;
    }
    

    
     public Article searchByID_Article(int e) throws SQLException{
        String requete="SELECT * FROM article where ID_ARTICLE = "+e+"" ;
         System.out.println(requete);
        ste=con.createStatement() ;
        rs=ste.executeQuery(requete);

        Article a = null;     

        while(rs.next()){
         a = new Article(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getDate(8));     
        
        
        }
        

        return a ;
    }
   
    
    public ObservableList<Article> searchByType(String type)throws SQLException
    {
       
        
        String requete="SELECT * FROM article where TYPE_A =  "+type+" " ;
        System.out.println(requete);
        ste=con.createStatement() ;
        rs=ste.executeQuery(requete);
         ObservableList<Article>  list = FXCollections.observableArrayList() ;
        while(rs.next()){
        Article a = new Article(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getDate(8));       
        list.add(a) ;
        }
        return list ;
    }
    
    
   
      
   
}
