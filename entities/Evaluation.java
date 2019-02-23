/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author LENOVO
 */
public class Evaluation {

    private int ID_EVALUATION;
    private int ID_UTILISATEUR;
    private int NOTES_EV;
    private String COMMENTAIRE_EV;
    private String OBJET_EV;
    private String TYPE_EV;
    private int ID_O;
    private Date DATE_EVALUATION;

    public Evaluation(int ID_EVALUATION, int ID_UTILISATEUR, int NOTES_EV, String COMMENTAIRE_EV, String OBJET_EV, String TYPE_EV, int ID_O, Date DATE_EVALUATION) {
        this.ID_EVALUATION = ID_EVALUATION;
        this.ID_UTILISATEUR = ID_UTILISATEUR;
        this.NOTES_EV = NOTES_EV;
        this.COMMENTAIRE_EV = COMMENTAIRE_EV;
        this.OBJET_EV = OBJET_EV;
        this.TYPE_EV = TYPE_EV;
        this.ID_O = ID_O;
        this.DATE_EVALUATION=DATE_EVALUATION;
    }

    public Evaluation() {
    }

    public Date getDATE_EVALUATION() {
        return DATE_EVALUATION;
    }

    public void setDATE_EVALUATION(Date DATE_EVALUATION) {
        this.DATE_EVALUATION = DATE_EVALUATION;
    }

    public int getID_EVALUATION() {
        return ID_EVALUATION;
    }

    public void setID_EVALUATION(int ID_EVALUATION) {
        this.ID_EVALUATION = ID_EVALUATION;
    }

    public int getID_UTILISATEUR() {
        return ID_UTILISATEUR;
    }

    public void setID_UTILISATEUR(int ID_UTILISATEUR) {
        this.ID_UTILISATEUR = ID_UTILISATEUR;
    }

    public int getNOTES_EV() {
        return NOTES_EV;
    }

    public void setNOTES_EV(int NOTES_EV) {
        this.NOTES_EV = NOTES_EV;
    }

    public String getCOMMENTAIRE_EV() {
        return COMMENTAIRE_EV;
    }

    public void setCOMMENTAIRE_EV(String COMMENTAIRE_EV) {
        this.COMMENTAIRE_EV = COMMENTAIRE_EV;
    }

    public String getOBJET_EV() {
        return OBJET_EV;
    }

    public void setOBJET_EV(String OBJET_EV) {
        this.OBJET_EV = OBJET_EV;
    }

    public String getTYPE_EV() {
        return TYPE_EV;
    }

    public void setTYPE_EV(String TYPE_EV) {
        this.TYPE_EV = TYPE_EV;
    }

    public int getID_O() {
        return ID_O;
    }

    public void setID_O(int ID_O) {
        this.ID_O = ID_O;
    }

    @Override
    public String toString() {
        return "Evaluation{" + "ID_EVALUATION=" + ID_EVALUATION + ", ID_UTILISATEUR=" + ID_UTILISATEUR + ", NOTES_EV=" + NOTES_EV + ", COMMENTAIRE_EV=" + COMMENTAIRE_EV + ", OBJET_EV=" + OBJET_EV + ", TYPE_EV=" + TYPE_EV + ", ID_O=" + ID_O + ", DATE_EVALUATION=" + DATE_EVALUATION + '}';
    }

   
}
