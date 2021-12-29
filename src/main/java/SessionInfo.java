/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Classes.Personne;
import java.sql.Connection;
import java.util.Optional;

/**
 *
 * @author francois
 */
public class SessionInfo {

    private Optional<Personne> curUser;
    private Connection conBdD;

    public SessionInfo() {
        this.curUser = Optional.empty();
        this.conBdD = null;
    }

    public boolean userConnected() {
        return this.curUser.isPresent();
    }

    public Optional<Personne> getCurUser() {
        return this.curUser;
    }

    public void setCurUser(Optional<Personne> curUser) {
        this.curUser = curUser;
    }

    public int getUserID() {
        return this.getId();
    }

    /**
     * @return the conBdD
     */
    public Connection getConBdD() {
        return conBdD;
    }

    /**
     * @param conBdD the conBdD to set
     */
    public void setConBdD(Connection conBdD) {
        this.conBdD = conBdD;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return this.getNom();
    }
  

}
