

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lyoubi01
 */
public class Module {
    
    private int idModule;


    public void setIntitule(Connection con, int id, String intitule)throws SQLException {
        String sql = "UPDATE Module SET intitule = ? WHERE id = ?";
        try(PreparedStatement pst = con.prepareStatement(sql)){
            pst.setString(1, intitule);
            pst.setInt(2, id);
            pst.executeUpdate();
        }
    }

    public void setDescription(Connection con, int id, String description)throws SQLException {
        String sql = "UPDATE Module SET description = ? WHERE id = ?";
        try(PreparedStatement pst = con.prepareStatement(sql)){
            pst.setString(1, description);
            pst.setInt(2, id);
            pst.executeUpdate();
        }
    }

    public int getIdModule() {
        return idModule;
    }

    public void setIdModule(int idModule) {
        this.idModule = idModule;
    }


    public void setIdSemestre(Connection con, int id, int idSemestre)throws SQLException {
        String sql = "UPDATE Module SET idSemestre = ? WHERE id = ?";
        try(PreparedStatement pst = con.prepareStatement(sql)){
            pst.setInt(1, idSemestre);
            pst.setInt(2, id);
            pst.executeUpdate();
        }
    }

    public void setAnnee(Connection con, int id, int annee)throws SQLException {
        String sql = "UPDATE Module SET annee = ? WHERE id = ?";
        try(PreparedStatement pst = con.prepareStatement(sql)){
            pst.setInt(1, annee);
            pst.setInt(2, id);
            pst.executeUpdate();
        }
    }

    public void setNbrDePlaces(Connection con, int id, int nbrDePlaces)throws SQLException {
        String sql = "UPDATE Module SET nbrDePlaces = ? WHERE id = ?";
        try(PreparedStatement pst = con.prepareStatement(sql)){
            pst.setInt(1, nbrDePlaces);
            pst.setInt(2, id);
            pst.executeUpdate();
        }
    }


    public void setIdResponsable(Connection con, int id, int idResponsable)throws SQLException {
        String sql = "UPDATE Module SET idResponsable = ? WHERE id = ?";
        try(PreparedStatement pst = con.prepareStatement(sql)){
            pst.setInt(1, idResponsable);
            pst.setInt(2, id);
            pst.executeUpdate();
        }
    }
    
    public Module(){
        this.idModule = -1;
    }
    
    public void saveModule(Connection con, String intitule, String description, int idSemestre, int idAnnee,int nbrDePlaces, int idResponsable) 
        throws SQLException {
        
        Module module = new Module();
        
        try (PreparedStatement pst = con.prepareStatement(
        "insert into Module (intitule,description,idSemestre,idAnnee,nbrDePlaces,idResponsable)values (?,?,?,?,?,?)", 
                PreparedStatement.RETURN_GENERATED_KEYS)) {
        pst.setString(1, intitule);
        pst.setString(2, description);
        pst.setInt(3, idSemestre);
        pst.setInt(4, idAnnee);
        pst.setInt(5, nbrDePlaces);
        pst.setInt(6, idResponsable);
        pst.executeUpdate();
        ResultSet nouvellesCles = pst.getGeneratedKeys();
        nouvellesCles.next();
        
        module.setIdModule(nouvellesCles.getInt(1));
        }
    }
}
