/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import Services.CrudUtilisateur;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rami
 */
public class Reclamation {
    int ID_RECLAMATION ; 
    int ID_UTILISATEUR ;
    String TYPE_R ;
    String COMMENTAIRE_R ;
    String ETAT_R ;
    Date DATE_R ;
    String NomPrenomUtilisateur ;

    public Reclamation(int ID_RECLAMATION, int ID_UTILISATEUR, String TYPE_R, String COMMENTAIRE_R, String ETAT_R, Date DATE_R) {
        this.ID_RECLAMATION = ID_RECLAMATION;
        this.ID_UTILISATEUR = ID_UTILISATEUR;
        this.TYPE_R = TYPE_R;
        this.COMMENTAIRE_R = COMMENTAIRE_R;
        this.ETAT_R = ETAT_R;
        this.DATE_R = DATE_R;
    }

    public int getID_RECLAMATION() {
        return ID_RECLAMATION;
    }

    public void setID_RECLAMATION(int ID_RECLAMATION) {
        this.ID_RECLAMATION = ID_RECLAMATION;
    }

    public int getID_UTILISATEUR() {
        return ID_UTILISATEUR;
    }

    public void setID_UTILISATEUR(int ID_UTILISATEUR) {
        this.ID_UTILISATEUR = ID_UTILISATEUR;
    }

    public String getTYPE_R() {
        return TYPE_R;
    }

    public void setTYPE_R(String TYPE_R) {
        this.TYPE_R = TYPE_R;
    }

    public String getCOMMENTAIRE_R() {
        return COMMENTAIRE_R;
    }

    public void setCOMMENTAIRE_R(String COMMENTAIRE_R) {
        this.COMMENTAIRE_R = COMMENTAIRE_R;
    }

    public String getETAT_R() {
        return ETAT_R;
    }

    public void setETAT_R(String ETAT_R) {
        this.ETAT_R = ETAT_R;
    }

    public Date getDATE_R() {
        return DATE_R;
    }

    public void setDATE_R(Date DATE_R) {
        this.DATE_R = DATE_R;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "ID_RECLAMATION=" + ID_RECLAMATION + ", ID_UTILISATEUR=" + ID_UTILISATEUR + ", TYPE_R=" + TYPE_R + ", COMMENTAIRE_R=" + COMMENTAIRE_R + ", ETAT_R=" + ETAT_R + ", DATE_R=" + DATE_R + '}';
    }
    public void NameUser(){
        CrudUtilisateur c = new  CrudUtilisateur() ; 
        try {
            Utilisateur a = c.FindUserById(ID_UTILISATEUR) ;
            NomPrenomUtilisateur=a.getNom()+" "+a.getPrenom() ;
        } catch (SQLException ex) {
                ex.printStackTrace();
                
        }
        
    }
}
