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
public class Ouvert {
    
    private int idModule;
    private int idSemestre;
    private int idOuvert;
    
    public int getIdModule() {
        return idModule;
    }
    public void setIdOuvert(int idOuvert){
        this.idOuvert = idOuvert;
    }
    
    public int getIdSemestre() {
        return idSemestre;
    }

    public void setIdSemestre(Connection con, int id, int idSemestre)throws SQLException {
        String sql = "UPDATE Ouvert SET idSemestre = ? WHERE id = ?";
        try(PreparedStatement pst = con.prepareStatement(sql)){
            pst.setInt(1, idSemestre);
            pst.setInt(2, id);
            pst.executeUpdate();
        }
    }

    public void setIdModule(Connection con, int id, int idModule)throws SQLException {
        String sql = "UPDATE Ouvert SET idModule = ? WHERE id = ?";
        try(PreparedStatement pst = con.prepareStatement(sql)){
            pst.setInt(1, idModule);
            pst.setInt(2, id);
            pst.executeUpdate();
        }
    }
    
    public Ouvert(int idModule, int idSemestre){
        this.idModule = idModule;
        this.idSemestre = idSemestre;
        this.idOuvert = -1;
    }
    
    public void saveOuvert(Connection con, int idModule, int idSemestre) 
           throws SQLException {
    
        Ouvert ouvert = new Ouvert(idModule, idSemestre);
        
        try (PreparedStatement pst = con.prepareStatement(
        "insert into Ouvert (idModule,idSemestre)values (?,?)", 
                PreparedStatement.RETURN_GENERATED_KEYS)) {
        pst.setInt(1, idModule);
        pst.setInt(2, idSemestre);
        pst.executeUpdate();
        ResultSet nouvellesCles = pst.getGeneratedKeys();
        nouvellesCles.next();
        
        this.setIdOuvert(nouvellesCles.getInt(1));
        
        }
    }
}
