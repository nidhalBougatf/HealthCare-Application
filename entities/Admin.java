/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.sql.Date;

/**
 *
 * @author Rami
 */
public class Admin extends Utilisateur {

    public Admin(int Id, String Nom, String Prenom, Date date_naissance, String Gender, String email, int STATUS, int num_tel, float taille, float poids, String avatar, String mot_passe, String type) {
        super(Id, Nom, Prenom, date_naissance, Gender, email, STATUS, num_tel, taille, poids, avatar, mot_passe, type);
    }

    

    public Admin(int Id, String Nom, String Prenom, Date date_naissance, String email, String Gender, String mot_passe) {
        super(Id, Nom, Prenom, date_naissance, email, Gender, mot_passe);
    }

   

   

    @Override
    public String toString() {
        return super.toString()+"Admin{" + '}';
    }

  

    
    
            
    
    

    
}
