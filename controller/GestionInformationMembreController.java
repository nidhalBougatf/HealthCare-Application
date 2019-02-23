/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Rami
 */
public class GestionInformationMembreController implements Initializable {

    @FXML
    private TextField TxtNom;
    @FXML
    private TextField TxtPrenom;
    @FXML
    private TextField TxtPoids;
    @FXML
    private TextField TxtTaille;
    @FXML
    private RadioButton RadBtnHomme;
    @FXML
    private ToggleGroup gender;
    @FXML
    private RadioButton RadBtnFemme;
    @FXML
    private DatePicker DateNaissance;
    @FXML
    private VBox Hboxx;
    @FXML
    private TextField TxtCurrentPass;
    @FXML
    private TextField TxtNewPass;
    @FXML
    private TextField TxtNewPass2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      Hboxx.setVisible(false);
    }    

    @FXML
    private void SavingInformation(ActionEvent event) {
    }

    @FXML
    private void AfficherMotdepasse(ActionEvent event) {
        Hboxx.setVisible(true);
    }

    @FXML
    private void SavingPassword(ActionEvent event) {
    }
    
}
