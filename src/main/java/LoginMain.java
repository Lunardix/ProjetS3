/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

import com.vaadin.flow.component.login.LoginForm;

/**
 *
 * @author ACER
 */
public class LoginMain extends LoginForm {
    
    public LoginMain() {
        this.addLoginListener((event) -> {
            String nom = event.getUsername();
            String pass = event.getPassword();
        });
        
    }
    
    
}
