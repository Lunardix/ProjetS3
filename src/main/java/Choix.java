
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

public class Choix {
    private int idChoix;

    public String stringChoix = "";
    
    
    public int getIdChoix() {
        return idChoix;
    }

    public void setIdPersonne(Connection con, int id, int idPersonne)throws SQLException {
        String sql = "UPDATE Choix SET idPersonne = ? WHERE id = ?";
        try(PreparedStatement pst = con.prepareStatement(sql)){
            pst.setInt(1, idPersonne);
            pst.setInt(2, id);
            pst.executeUpdate();
        }
    }
    
    public void setIdModule3(Connection con, int id, int idModule3)throws SQLException {
        String sql = "UPDATE Choix SET idModule3 = ? WHERE id = ?";
        try(PreparedStatement pst = con.prepareStatement(sql)){
            pst.setInt(1, idModule3);
            pst.setInt(2, id);
            pst.executeUpdate();
        }
    }

    public void setIdModule2(Connection con, int id, int idModule2)throws SQLException {
        String sql = "UPDATE Choix SET idModule2 = ? WHERE id = ?";
        try(PreparedStatement pst = con.prepareStatement(sql)){
            pst.setInt(1, idModule2);
            pst.setInt(2, id);
            pst.executeUpdate();
        }
    }

    public void setIdModule1(Connection con, int id, int idModule1)throws SQLException {
        String sql = "UPDATE Choix SET idModule1 = ? WHERE id = ?";
        try(PreparedStatement pst = con.prepareStatement(sql)){
            pst.setInt(1, idModule1);
            pst.setInt(2, id);
            pst.executeUpdate();
        }
    }

    public void setIdChoix(int idChoix) {
        this.idChoix = idChoix;
    }

    public Choix (){
        this.idChoix = -1;
    }
    
    public void saveChoix(Connection con, int idPersonne, int idModule1, int idModule2, int idModule3, int idGroupeModule) 
        throws SQLException {
       
        Choix choix = new Choix();
        
        try (PreparedStatement pst = con.prepareStatement(
        "insert into Choix (idPersonne,idModule1, idModule2, idModule3, idGroupeModule)values (?,?,?,?,?)", 
                PreparedStatement.RETURN_GENERATED_KEYS)) {
        pst.setInt(1, idPersonne);
        pst.setInt(2, idModule1);
        pst.setInt(3, idModule2);
        pst.setInt(4, idModule3);
        pst.setInt(5, idGroupeModule);
        pst.executeUpdate();
        ResultSet nouvellesCles = pst.getGeneratedKeys();
        nouvellesCles.next();
        
        this.setIdChoix(nouvellesCles.getInt(1));
        }
    }
    
    public String getStringChoix(Connection con)throws SQLException{
        String query = "SELECT * FROM Choix";
        try(PreparedStatement pst = con.prepareStatement(query)){
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                stringChoix = stringChoix + rs.getString("idPersonne") +rs.getString("idModule1") + rs.getString("idModule2") + rs.getString("idModule3");
            }
        }
        return stringChoix;
    }
        
    }
}
