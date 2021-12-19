
import java.sql.Connection;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 57lun
 */
public class Controleur {
    private void personneLogin(Connection con, String username, String mdp) throws SQLException{
        switch(Personne.userConnection(con, username, mdp)){
            case 1: 
                // accès admin
            break;
            case 0:
                //accès élève
            break;
            case -1:
                //accès refusé (utilisateur non reconnu)
            break;
        }
        
    }
}
