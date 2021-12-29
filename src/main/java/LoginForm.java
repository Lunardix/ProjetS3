/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

/**
 *
 * @author ACER
 */

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import Test.VuePrincipale;
import Classes.Inscription;
import Classes.Personne;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

/**
 *
 * @author francois
 */
public class LoginForm extends MyVerticalLayout{
    
    private VuePrincipale main;
    
    private TextField vusername;
    private PasswordField vpass;
    private Button vbLogin;
    
    public LoginForm(VuePrincipale main) {
        this.main = main;
        this.vusername = new TextField("username");
        this.vpass = new PasswordField("mot de passe");
        this.vbLogin = new Button("login");
        this.add(this.vusername,this.vpass,this.vbLogin);
        this.vbLogin.addClickListener((event) -> {
            this.doLogin();
        });
    }
    
    public void doLogin() {
        String nom = this.vusername.getValue();
        String pass = this.vpass.getValue();
        try {
            Connection con = this.main.getSessionInfo().getConBdD();
            Optional<Personne> user = Inscription.login(con, username, pass);
            if(user.isEmpty()) {
                Notification.show("Utilisateur ou mot de passe invalide");
            } else {
                this.main.getSessionInfo().setCurUser(user);
                this.main.setEntete(new EnteteAfterLogin(this.main));
                this.main.setMainContent(new MainAfterLogin(this.main));
            }
        } catch (SQLException ex) {
            Notification.show("Problème interne : " + ex.getLocalizedMessage());
        }        
    }
    
}
