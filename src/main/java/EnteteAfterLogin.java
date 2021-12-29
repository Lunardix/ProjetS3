/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

import Classes.Inscription;
import com.vaadin.flow.component.button.Button;
import VuePrincipale;
import Classes.Choix;
import java.util.Optional;

/**
 *
 * @author ACER
 */
public class EnteteAfterLogin extends MyHorizontalLayout {
    private VuePrincipale main;
    
    private Button vbLogout;
    private Button vbInscription;
    private Button vbChoix;
    
    
    public EnteteAfterLogin(VuePrincipale main) {
        this.main = main;
        
        this.vbLogout = new Button("logout");
        this.vbLogout.addClickListener((event) -> {
            this.doLogout();
        });
        this.vbInscription = new Button("Inscription");
        this.vbInscription.addClickListener((event) -> {
            this.main.setMainContent(new Inscription(this.main));
        });
        this.vbChoix = new Button ("Choix des modules");
        this.vbChoix.addClickListener((event) -> {
            this.main.setMainContent(new Choix(this.main));
        });
        
        this.add(this.vbLogout,this.vbInscription,this.vbChoix);
    }
    
    public void doLogout() {
        this.main.getSessionInfo().setCurUser(Optional.empty());
        this.main.setEntete(new InitialLoginEntete(this.main));
        this.main.setMainContent(new BienvenueMainVue(this.main));
    }
    
    
}
