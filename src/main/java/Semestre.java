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
public class Semestre {
    private int idSemestre;

    
    public static int Nc;
    
    
    public int getIdSemestre() {
        return idSemestre;
    }

    public int getAnnee() {
        return annee;
    }

    public int getNumero() {
        return numero;
    }

    public int getNg() {
        return Ng;
    }

    public void setNg(Connection con, int id, int Ng)throws SQLException {
        String sql = "UPDATE Semestre SET Ng = ? WHERE id = ?";
        try(PreparedStatement pst = con.prepareStatement(sql)){
            pst.setInt(1, Ng);
            pst.setInt(2, id);
            pst.executeUpdate();
        }
    }
    
    public void setNumero(Connection con, int id, boolean numero)throws SQLException {
        String sql = "UPDATE Semestre SET numero = ? WHERE id = ?";
        try(PreparedStatement pst = con.prepareStatement(sql)){
            pst.setBoolean(1, numero);
            pst.setInt(2, id);
            pst.executeUpdate();
        }
    }

    public void setAnnee(Connection con, int id, int annee)throws SQLException {
        String sql = "UPDATE Semestre SET annee = ? WHERE id = ?";
        try(PreparedStatement pst = con.prepareStatement(sql)){
            pst.setInt(1, annee);
            pst.setInt(2, id);
            pst.executeUpdate();
        }
    }

    public void setIdSemestre(int idSemestre) {
        this.idSemestre = idSemestre;
    }

    public Semestre(){
        this.idSemestre = -1;
    }
    
    public static Semestre demandeSemestre() {
        // demande annee, num mais pas l'id
        return null;
    }
    
    public void saveSemestre(Connection con, int annee, boolean numero, int Ng) 
        throws SQLException {
        Semestre semestre = new Semestre();
        try (PreparedStatement pst = con.prepareStatement(
        "insert into Semestre (annee,numero)values (?,?,?)", 
                PreparedStatement.RETURN_GENERATED_KEYS)) {
        pst.setInt(1, annee);
        pst.setBoolean(2, numero);
        pst.setInt(3, Ng);
        pst.executeUpdate();
        ResultSet nouvellesCles = pst.getGeneratedKeys();
        nouvellesCles.next();
        this.setIdSemestre(nouvellesCles.getInt(1));
        }
    }
}
