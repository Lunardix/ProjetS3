/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author lyoubi01
 */
public class Inscription {
    
    private int idInscription;


    public void setIdInscription(int idInscription) {
        this.idInscription = idInscription;
    }

    public void setIdPersonne(Connection con, int id, int idPersonne)throws SQLException {
        String sql = "UPDATE Inscription SET idPersonne = ? WHERE id = ?";
        try(PreparedStatement pst = con.prepareStatement(sql)){
            pst.setInt(1, idPersonne);
            pst.setInt(2, id);
            pst.executeUpdate();
        }
    }

    public void setIdModule(Connection con, int id, int idModule)throws SQLException {
        String sql = "UPDATE Inscription SET idModule = ? WHERE id = ?";
        try(PreparedStatement pst = con.prepareStatement(sql)){
            pst.setInt(1, idModule);
            pst.setInt(2, id);
            pst.executeUpdate();
        }
    }
    
    public void setIdSemestre(Connection con, int id, int idSemestre)throws SQLException {
        String sql = "UPDATE Inscription SET idSemestre = ? WHERE id = ?";
        try(PreparedStatement pst = con.prepareStatement(sql)){
            pst.setInt(1, idSemestre);
            pst.setInt(2, id);
            pst.executeUpdate();
        }
    }
   
   public Inscription(){
       this.idInscription = -1;
   }
   
   public void saveInscription(Connection con, int idModule, int idPersonne, int idSemestre) 
        throws SQLException {
       
        Inscription inscription = new Inscription();
        
        try (PreparedStatement pst = con.prepareStatement(
        "insert into Inscription (idModule, idPersonne, idSemestre)values (?,?,?)", 
                PreparedStatement.RETURN_GENERATED_KEYS)) {
        pst.setInt(1, idModule);
        pst.setInt(2, idPersonne);
        pst.setInt(3, idSemestre);
        pst.executeUpdate();
        ResultSet nouvellesCles = pst.getGeneratedKeys();
        nouvellesCles.next();
        
        this.setIdInscription(nouvellesCles.getInt(1));
        }
    }
}

