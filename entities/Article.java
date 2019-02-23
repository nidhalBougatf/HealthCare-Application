/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author CePc
 */
public class Article {
    int ID_ARTICLE;
    int ID_UTILISATEUR;
    String TITRE_A;
    String DESCRIPTION_A;
    String EDITEUR_A;
    String TYPE_A;
    String Affiche_A;
    Date Date_a;

    public Article(int ID_ARTICLE, int ID_UTILISATEUR, String TITRE_A, String DESCRIPTION_A, String EDITEUR_A, String TYPE_A,String Affiche_A,Date Date_a) {
        this.ID_ARTICLE = ID_ARTICLE;
        this.ID_UTILISATEUR = ID_UTILISATEUR;
        this.TITRE_A = TITRE_A;
        this.DESCRIPTION_A = DESCRIPTION_A;
        this.EDITEUR_A = EDITEUR_A;
        this.TYPE_A = TYPE_A;
        this.Affiche_A=Affiche_A;
        this.Date_a=Date_a;
        
        
    }

    public Article( int ID_UTILISATEUR, String TITRE_A, String DESCRIPTION_A, String EDITEUR_A, String TYPE_A,String AFFICHE_A,Date Date_a) {
        
        this.ID_UTILISATEUR = ID_UTILISATEUR;
        this.TITRE_A = TITRE_A;
        this.DESCRIPTION_A = DESCRIPTION_A;
        this.EDITEUR_A = EDITEUR_A;
        this.TYPE_A = TYPE_A;
        this.Affiche_A=Affiche_A;
        this.Date_a=Date_a;
    }

    @Override
    public String toString() {
        return "Article{" + "ID_ARTICLE=" + ID_ARTICLE + ", ID_UTILISATEUR=" + ID_UTILISATEUR + ", TITRE_A=" + TITRE_A + ", DESCRIPTION_A=" + DESCRIPTION_A + ", EDITEUR_A=" + EDITEUR_A + ", TYPE_A=" + TYPE_A + ", Affiche_A=" + Affiche_A + ", Date_a=" + Date_a + '}';
    }

    public String getAFFICHE_A() {
        return Affiche_A;
    }

    public void setAFFICHE_A(String AFFICHE_A) {
        this.Affiche_A = AFFICHE_A;
    }

    public Date getDate_a() {
        return Date_a;
    }

    public void setDate_a(Date Date_a) {
        this.Date_a = Date_a;
    }

    public int getID_ARTICLE() {
        return ID_ARTICLE;
    }

    public void setID_ARTICLE(int ID_ARTICLE) {
        this.ID_ARTICLE = ID_ARTICLE;
    }

    public int getID_UTILISATEUR() {
        return ID_UTILISATEUR;
    }

    public void setID_UTILISATEUR(int ID_UTILISATEUR) {
        this.ID_UTILISATEUR = ID_UTILISATEUR;
    }

    public String getTITRE_A() {
        return TITRE_A;
    }

    public void setTITRE_A(String TITRE_A) {
        this.TITRE_A = TITRE_A;
    }

    public String getDESCRIPTION_A() {
        return DESCRIPTION_A;
    }

    public void setDESCRIPTION_A(String DESCRIPTION_A) {
        this.DESCRIPTION_A = DESCRIPTION_A;
    }

    public String getEDITEUR_A() {
        return EDITEUR_A;
    }

    public void setEDITEUR_A(String EDITEUR_A) {
        this.EDITEUR_A = EDITEUR_A;
    }

    public String getTYPE_A() {
        return TYPE_A;
    }

    public void setTYPE_A(String TYPE_A) {
        this.TYPE_A = TYPE_A;
    }
    
}
