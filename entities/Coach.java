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
public class Coach extends Utilisateur {
    
    
String CERTIF_COACH ;   
int NOTE_COACH ;
String NIVEAU_COACH ;

    public Coach( int Id, String Nom, String Prenom, Date date_naissance, String Gender, String email, int STATUS, int num_tel, float taille, float poids, String avatar, String mot_passe, String type,String CERTIF_COACH,  String NIVEAU_COACH ,int NOTE_COACH) {
        super(Id, Nom, Prenom, date_naissance, Gender, email, STATUS, num_tel, taille, poids, avatar, mot_passe, type);
        this.CERTIF_COACH = CERTIF_COACH;
        this.NOTE_COACH = NOTE_COACH;
        this.NIVEAU_COACH = NIVEAU_COACH;
    }

    
    public Coach(int Id, String Nom, String Prenom, Date date_naissance, String Gender,String email,  String mot_passe,String CERTIF_COACH, int NOTE_COACH, String NIVEAU_COACH ) {
        super(Id, Nom, Prenom, date_naissance,Gender, email,  mot_passe);
        this.CERTIF_COACH = CERTIF_COACH;
        this.NOTE_COACH = NOTE_COACH;
        this.NIVEAU_COACH = NIVEAU_COACH;
    }

    

    

  

    public String getCERTIF_COACH() {
        return CERTIF_COACH;
    }

    public void setCERTIF_COACH(String CERTIF_COACH) {
        this.CERTIF_COACH = CERTIF_COACH;
    }

    public int getNOTE_COACH() {
        return NOTE_COACH;
    }

    public void setNOTE_COACH(int NOTE_COACH) {
        this.NOTE_COACH = NOTE_COACH;
    }

    public String getNIVEAU_COACH() {
        return NIVEAU_COACH;
    }

    public void setNIVEAU_COACH(String NIVEAU_COACH) {
        this.NIVEAU_COACH = NIVEAU_COACH;
    }

    @Override
    public String toString() {
        return super.toString() +"Coach{" + "CERTIF_COACH=" + CERTIF_COACH + ", NOTE_COACH=" + NOTE_COACH + ", NIVEAU_COACH=" + NIVEAU_COACH + '}';
    }


            
    
    
}
