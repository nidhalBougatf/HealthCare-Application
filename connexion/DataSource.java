/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package connexion;


import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;


public class DataSource {
    
    private static DataSource data;
    private Connection con;
    String url="jdbc:mysql://127.0.0.1:3306/healthcare?zeroDateTimeBehavior=convertToNull";
    String login="root";
    String password="";
    
    private DataSource()
    {
        try
        {
            con = DriverManager.getConnection(url,login,password);
            System.out.println("connexion");
        }catch (SQLException ex)
        {
            System.out.println(ex);
        }
    }
    
    public Connection getConnection()
    {
        return con;
    }
    
    public static DataSource getInstance()
    {
        if(data == null)
            data = new DataSource();
        return data;
    }

   
        
    }
    
 

