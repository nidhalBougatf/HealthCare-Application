/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import static java.lang.Math.round;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Nidhal Bougatf
 */
public class Offre {
    
    
    int ID_OFFRE;
    int ID_ESPACE;
    String DESCRIPTION_O ;
    String TITRE_O;
    Double ANCIEN_PRIX;
    Double NOUVEAU_PRIX;
    LocalDate DATEDEBUT_O;
    LocalDate DATEFIN_O;
    String AFFICHE_O;
    String nom_es;

    @Override
    public String toString() {
        return "Offre{" + "ID_OFFRE=" + ID_OFFRE + ", ID_ESPACE=" + ID_ESPACE + ", DESCRIPTION_O=" + DESCRIPTION_O + ", TITRE_O=" + TITRE_O + ", ANCIEN_PRIX=" + ANCIEN_PRIX + ", NOUVEAU_PRIX=" + NOUVEAU_PRIX + ", DATEDEBUT_O=" + DATEDEBUT_O + ", DATEFIN_O=" + DATEFIN_O + ", AFFICHE_O=" + AFFICHE_O + ", nom_es=" + nom_es + '}';
    }

    public String toSMS() {
        return "Cher client ,  une nouvelle offre vient d'etre ajouté dans notre espace  ! \nVenez profiter vite !\n\nOffre : " + TITRE_O +"\nDescription : " + DESCRIPTION_O +"\nNom Espace : " + nom_es +"\nPRIX : " + round(NOUVEAU_PRIX) +"DT \nDate expiration : " + DATEFIN_O+"\n\nHealthCare Support Team" ;
    }    
    

    public int getID_OFFRE() {
        return ID_OFFRE;
    }

    public void setID_OFFRE(int ID_OFFRE) {
        this.ID_OFFRE = ID_OFFRE;
    }

    public int getID_ESPACE() {
        return ID_ESPACE;
    }

    public void setID_ESPACE(int ID_ESPACE) {
        this.ID_ESPACE = ID_ESPACE;
    }

    public String getDESCRIPTION_O() {
        return DESCRIPTION_O;
    }

    public void setDESCRIPTION_O(String DESCRIPTION_O) {
        this.DESCRIPTION_O = DESCRIPTION_O;
    }

    public String getTITRE_O() {
        return TITRE_O;
    }

    public void setTITRE_O(String TITRE_O) {
        this.TITRE_O = TITRE_O;
    }

    public double getANCIEN_PRIX() {
        return ANCIEN_PRIX;
    }

    public void setANCIEN_PRIX(double ANCIEN_PRIX) {
        this.ANCIEN_PRIX = ANCIEN_PRIX;
    }

    public double getNOUVEAU_PRIX() {
        return NOUVEAU_PRIX;
    }

    public void setNOUVEAU_PRIX(double NOUVEAU_PRIX) {
        this.NOUVEAU_PRIX = NOUVEAU_PRIX;
    }

    public LocalDate getDATEDEBUT_O() {
        return DATEDEBUT_O;
    }

    public void setDATEDEBUT_O(LocalDate DATEDEBUT_O) {
        this.DATEDEBUT_O = DATEDEBUT_O;
    }

    public LocalDate getDATEFIN_O() {
        return DATEFIN_O;
    }

    public void setDATEFIN_O(LocalDate DATEFIN_O) {
        this.DATEFIN_O = DATEFIN_O;
    }

    public String getAFFICHE_O() {
        return AFFICHE_O;
    }

    public void setAFFICHE_O(String AFFICHE_O) {
        this.AFFICHE_O = AFFICHE_O;
    }


    public String getNom_es() {
        return nom_es;
    }

    public void setNom_es(String nom_es) {
        this.nom_es = nom_es;
    }

    public Offre(int ID_OFFRE, int ID_ESPACE, String DESCRIPTION_O, String TITRE_O, double ANCIEN_PRIX, double NOUVEAU_PRIX, LocalDate DATEDEBUT_O, LocalDate DATEFIN_O, String AFFICHE_O) {
        this.ID_OFFRE = ID_OFFRE;
        this.ID_ESPACE = ID_ESPACE;
        this.DESCRIPTION_O = DESCRIPTION_O;
        this.TITRE_O = TITRE_O;
        this.ANCIEN_PRIX = ANCIEN_PRIX;
        this.NOUVEAU_PRIX = NOUVEAU_PRIX;
        this.DATEDEBUT_O = DATEDEBUT_O;
        this.DATEFIN_O = DATEFIN_O;
        this.AFFICHE_O = AFFICHE_O;
    }

    public Offre(String nom_es, String DESCRIPTION_O, String TITRE_O, double ANCIEN_PRIX, double NOUVEAU_PRIX, LocalDate DATEDEBUT_O, LocalDate DATEFIN_O, String AFFICHE_O) {
        this.DESCRIPTION_O = DESCRIPTION_O;
        this.TITRE_O = TITRE_O;
        this.ANCIEN_PRIX = ANCIEN_PRIX;
        this.NOUVEAU_PRIX = NOUVEAU_PRIX;
        this.DATEDEBUT_O = DATEDEBUT_O;
        this.DATEFIN_O = DATEFIN_O;
        this.AFFICHE_O = AFFICHE_O;
        this.nom_es = nom_es;
    }

    public Offre( String nom_es,int ID_OFFRE, String DESCRIPTION_O, String TITRE_O, double ANCIEN_PRIX, double NOUVEAU_PRIX, LocalDate DATEDEBUT_O, LocalDate DATEFIN_O, String AFFICHE_O) {
        this.ID_OFFRE = ID_OFFRE;
        this.DESCRIPTION_O = DESCRIPTION_O;
        this.TITRE_O = TITRE_O;
        this.ANCIEN_PRIX = ANCIEN_PRIX;
        this.NOUVEAU_PRIX = NOUVEAU_PRIX;
        this.DATEDEBUT_O = DATEDEBUT_O;
        this.DATEFIN_O = DATEFIN_O;
        this.AFFICHE_O = AFFICHE_O;
        this.nom_es = nom_es;
    }
  
}
