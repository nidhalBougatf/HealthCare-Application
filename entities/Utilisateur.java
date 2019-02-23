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
public class Utilisateur {
    int Id ; 
    String Nom ; 
    String Prenom ; 
    Date date_naissance ;
    String email;
    int STATUS ;
    int num_tel ;
    float taille ; 
    float poids ;
    String Gender;
    String avatar ;
    String mot_passe ;
    String type ;
    String StatusToString ; 

    public Utilisateur(int Id, String Nom, String Prenom, Date date_naissance,String Gender, String email, int STATUS, int num_tel, float taille, float poids,  String avatar, String mot_passe, String type) {
        this.Id = Id;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.date_naissance = date_naissance;
        this.email = email;
        this.STATUS = STATUS;
        this.num_tel = num_tel;
        this.taille = taille;
        this.poids = poids;
        this.Gender = Gender;
        this.avatar = avatar;
        this.mot_passe = mot_passe;
        this.type = type;
        StatToString() ;
    }

   

    public Utilisateur(int Id, String Nom, String Prenom, Date date_naissance,String Gender, String email,  String mot_passe) {
       
        this.Id = Id;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.date_naissance = date_naissance;
        this.email = email;
        this.Gender = Gender;
        this.mot_passe = mot_passe;
        StatToString() ;
         
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    
    

    
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public float getPoids() {
        return poids;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

    public float getTaille() {
        return taille;
    }

    public void setTaille(float taille) {
        this.taille = taille;
    }

    public String getMot_passe() {
        return mot_passe;
    }

    public void setMot_passe(String mot_passe) {
        this.mot_passe = mot_passe;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(int STATUS) {
        this.STATUS = STATUS;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "Id=" + Id + ", Nom=" + Nom + ", Prenom=" + Prenom + ", date_naissance=" + date_naissance + ", email=" + email + ", STATUS=" + STATUS + ", num_tel=" + num_tel + ", taille=" + taille + ", poids=" + poids + ", Gender=" + Gender + ", avatar=" + avatar + ", mot_passe=" + mot_passe + ", type=" + type + '}';
    }

   
            
    public void StatToString (){
        switch (STATUS) {
            case 1:
                StatusToString="Compte activé" ;
                break;
            case 2:
                StatusToString="Membre doit activer son compte par mail " ;
                break;
            case 3:
                StatusToString="Compte Coach attendant la confirmation par l'admin" ; 
                break;
            case 4:
                StatusToString="Compte verouillé par admin" ;
                break;
            default:
                break;
        }
    
    }
   
        public String getStatusToString() {
        return StatusToString;
    }

}
