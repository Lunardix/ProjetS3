/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Vues.MyVerticalLayout;
import Vues.MyHorizontalLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.sql.SQLException;
/**
 *
 * @author ACER
 */
@Route(value = "")
@PageTitle("Inscription aux enseignements electifs")
public class VuePrincipale extends MyVerticalLayout {
    

    private SessionInfo sessionInfo;

    private MyHorizontalLayout entete;
    private MyVerticalLayout mainContent;

    public void setEntete(Component c) {
        this.entete.removeAll();
        this.entete.add(c);
    }

    public void setMainContent(Component c) {
        this.mainContent.removeAll();
        this.mainContent.add(c);
    }

    public VuePrincipale() {
        this.sessionInfo = new SessionInfo();
        this.entete = new MyHorizontalLayout();
        this.entete.setWidthFull();
        this.add(this.entete);

        this.mainContent = new MyVerticalLayout();
        this.mainContent.setWidthFull();
        this.mainContent.setHeightFull();
        this.add(this.mainContent);
        try {
            this.sessionInfo.setConBdD(Aime.defautConnect());
            this.setEntete(new InitialLoginEntete(this));
            this.setMainContent(new BienvenueMainVue(this));
        } catch (ClassNotFoundException | SQLException ex) {
            this.setMainContent(new BdDNonAccessible(this));
        }

    }

    
    /**
     * @return the sessionInfo
     */
    public SessionInfo getSessionInfo() {
        return sessionInfo;
    }

    
}
