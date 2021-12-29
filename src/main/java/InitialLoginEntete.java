/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;


import com.vaadin.flow.component.button.Button;
import VuePrincipale;
import Classes.Personne;
import Vues.LoginForm;

/**
 *
 * @author ACER
 */
public class InitialLoginEntete extends MyHorizontalLayout{
    
    private VuePrincipale main;
    
    private Button vbLogin;
    private Button vbNouvelUtilisateur;
   
 public InitialLoginEntete(VuePrincipale main){
     this.main = main;
     
     this.vbLogin = new Button("Login");
     this.vbLogin.addClickListener((event) -> {
     this.main.setMainContent(new LoginForm(this.main));
     });
     this.vbNouvelUtilisateur = new Button("Nouvel utilisateur");
     this.vbLogin.addClickListener((event) -> {
     this.main.setMainContent(new Personne(this.main));
     });
 }
    
    
    
    
    
}
