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

    public void setNg(int Ng) {
        this.Ng = Ng;
    }
    
    
    
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
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
    
    public void saveSemestre(Connection con, int annee, int numero, int Ng) 
        throws SQLException {
        Semestre semestre = new Semestre();
        try (PreparedStatement pst = con.prepareStatement(
        "insert into Semestre (annee,numero)values (?,?,?)", 
                PreparedStatement.RETURN_GENERATED_KEYS)) {
        pst.setInt(1, annee);
        pst.setInt(2, numero);
        pst.setInt(3, Ng);
        pst.executeUpdate();
        ResultSet nouvellesCles = pst.getGeneratedKeys();
        nouvellesCles.next();
        this.setIdSemestre(nouvellesCles.getInt(1));
        }
    }
}
