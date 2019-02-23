/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author koussai
 */
public class Evenement {
  int  ID_EVENEMENT ; 
  int ID_UTILISATEUR ; 
  String  TITRE_E ; 
  String DESCRIPTION_E ; 
  String AFFICHE_E ; 
  Date DATEDEBUT_E ; 
  Date DATEFIN_E ; 
  String LIEU_E	;
  int  DUREE_E ; 
  float FRAIS_E ; 
  String ORGANISATEUR_E ; 
  String CONTACT_E ; 
  String TYPE_E; 

    public Evenement() {
    }

  
  
    public Evenement(int ID_EVENEMENT, int ID_UTILISATEUR, String TITRE_E, String DESCRIPTION_E, String AFFICHE_E, Date DATEDEBUT_E, Date DATEFIN_E, String LIEU_E, int DUREE_E, float FRAIS_E, String ORGANISATEUR_E, String CONTACT_E, String TYPE_E) {
        this.ID_EVENEMENT = ID_EVENEMENT;
        this.ID_UTILISATEUR = ID_UTILISATEUR;
        this.TITRE_E = TITRE_E;
        this.DESCRIPTION_E = DESCRIPTION_E;
        this.AFFICHE_E = AFFICHE_E;
        this.DATEDEBUT_E = DATEDEBUT_E;
        this.DATEFIN_E = DATEFIN_E;
        this.LIEU_E = LIEU_E;
        this.DUREE_E = DUREE_E;
        this.FRAIS_E = FRAIS_E;
        this.ORGANISATEUR_E = ORGANISATEUR_E;
        this.CONTACT_E = CONTACT_E;
        this.TYPE_E = TYPE_E;
    }

    public Evenement(String TITRE_E, String DESCRIPTION_E, String AFFICHE_E, Date DATEDEBUT_E, Date DATEFIN_E, String LIEU_E, int DUREE_E, float FRAIS_E, String ORGANISATEUR_E, String CONTACT_E, String TYPE_E) {
        
        
        this.TITRE_E = TITRE_E;
        this.DESCRIPTION_E = DESCRIPTION_E;
        this.AFFICHE_E = AFFICHE_E;
        this.DATEDEBUT_E = DATEDEBUT_E;
        this.DATEFIN_E = DATEFIN_E;
        this.LIEU_E = LIEU_E;
        this.DUREE_E = DUREE_E;
        this.FRAIS_E = FRAIS_E;
        this.ORGANISATEUR_E = ORGANISATEUR_E;
        this.CONTACT_E = CONTACT_E;
        this.TYPE_E = TYPE_E;
    }
    
    public int getID_EVENEMENT() {
        return ID_EVENEMENT;
    }

    public int getID_UTILISATEUR() {
        return ID_UTILISATEUR;
    }

    public String getTITRE_E() {
        return TITRE_E;
    }

    public String getDESCRIPTION_E() {
        return DESCRIPTION_E;
    }

    public String getAFFICHE_E() {
        return AFFICHE_E;
    }

    public Date getDATEDEBUT_E() {
        return DATEDEBUT_E;
    }

    public Date getDATEFIN_E() {
        return DATEFIN_E;
    }

    public String getLIEU_E() {
        return LIEU_E;
    }

    public int getDUREE_E() {
        return DUREE_E;
    }

    public float getFRAIS_E() {
        return FRAIS_E;
    }

    public String getORGANISATEUR_E() {
        return ORGANISATEUR_E;
    }

    public String getCONTACT_E() {
        return CONTACT_E;
    }

    public String getTYPE_E() {
        return TYPE_E;
    }

    public void setID_EVENEMENT(int ID_EVENEMENT) {
        this.ID_EVENEMENT = ID_EVENEMENT;
    }

    public void setID_UTILISATEUR(int ID_UTILISATEUR) {
        this.ID_UTILISATEUR = ID_UTILISATEUR;
    }

    public void setTITRE_E(String TITRE_E) {
        this.TITRE_E = TITRE_E;
    }

    public void setDESCRIPTION_E(String DESCRIPTION_E) {
        this.DESCRIPTION_E = DESCRIPTION_E;
    }

    public void setAFFICHE_E(String AFFICHE_E) {
        this.AFFICHE_E = AFFICHE_E;
    }

    public void setDATEDEBUT_E(Date DATEDEBUT_E) {
        this.DATEDEBUT_E = DATEDEBUT_E;
    }

    public void setDATEFIN_E(Date DATEFIN_E) {
        this.DATEFIN_E = DATEFIN_E;
    }

    public void setLIEU_E(String LIEU_E) {
        this.LIEU_E = LIEU_E;
    }

    public void setDUREE_E(int DUREE_E) {
        this.DUREE_E = DUREE_E;
    }

    public void setFRAIS_E(float FRAIS_E) {
        this.FRAIS_E = FRAIS_E;
    }

    public void setORGANISATEUR_E(String ORGANISATEUR_E) {
        this.ORGANISATEUR_E = ORGANISATEUR_E;
    }

    public void setCONTACT_E(String CONTACT_E) {
        this.CONTACT_E = CONTACT_E;
    }

    @Override
    public String toString() {
        return "Evenement{" + "ID_EVENEMENT=" + ID_EVENEMENT + ", ID_UTILISATEUR=" + ID_UTILISATEUR + ", TITRE_E=" + TITRE_E + ", DESCRIPTION_E=" + DESCRIPTION_E + ", AFFICHE_E=" + AFFICHE_E + ", DATEDEBUT_E=" + DATEDEBUT_E + ", DATEFIN_E=" + DATEFIN_E + ", LIEU_E=" + LIEU_E + ", DUREE_E=" + DUREE_E + ", FRAIS_E=" + FRAIS_E + ", ORGANISATEUR_E=" + ORGANISATEUR_E + ", CONTACT_E=" + CONTACT_E + '}';
    }

    public void setTYPE_E(String TYPE_E) {
        this.TYPE_E = TYPE_E;
    }
  
  
  
  
           
}
