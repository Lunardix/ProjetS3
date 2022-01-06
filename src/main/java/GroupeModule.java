
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
 * @author 57lun
 */
public class GroupeModule {
    

    private int idGroupeModule;
    
    private int nbrModules;
    
    public int[] tableau = new int[10];

    public int idModule = -1;
    
    public void setNbrModules(int nbr){
        nbrModules = nbr;
    }
    
    public GroupeModule() {
        this.idGroupeModule = -1;
    }
    
    public void setIdModule (Connection con, int idGroupeModule, int idModule, int position)throws SQLException{
        String sql = "UPDATE GroupeModule SET idModule "+ (position) +" = ? WHERE id = ?";
        try(PreparedStatement pst = con.prepareStatement(sql)){
            pst.setInt(1, idModule);
            pst.setInt(2, idGroupeModule);
            pst.executeUpdate();
        }
    }
    
    public int getIdModule (Connection con, int idGroupeModule, int position)throws SQLException{
        String query = "SELECT idModule "+ (position) +" FROM GroupeModule WHERE id = ?";
        try(PreparedStatement pst = con.prepareStatement(query)){
            pst.setInt(1, idGroupeModule);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                idModule = rs.getInt("idModule"+position);
            }
        }
        return idModule;
    }

    public void setNom(Connection con, int id, String nom)throws SQLException {
        String sql = "UPDATE GroupeModule SET nom = ? WHERE id = ?";
        try(PreparedStatement pst = con.prepareStatement(sql)){
            pst.setString(1, nom);
            pst.setInt(2, id);
            pst.executeUpdate();
        }
    }

    // ici on utilisera une méthode changeIdModule(numéroDuModuleAChanger, idNouveauModule) 
    //
    //
    //
    //
    ///////////////////////////////////////////////////////////////////////////////////

    public int getNbrModules(Connection con, int idGroupeModule)throws SQLException {
        String query = "SELECT NbrModules FROM GroupeModule WHERE id = ?";
        try(PreparedStatement pst = con.prepareStatement(query)){
            pst.setInt(1, idGroupeModule);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                return rs.getInt("NbrModules");
            }
            else {return 0;}
        }
    }
    
    
    public void ajouterModule(Connection con, int idGroupeModule, int idModule)throws SQLException{
        int position = getNbrModules(con, idGroupeModule) + 1;
        if (position == 11){
            return;
        }
        else if (this.getIdModule(position) == -1 ){
            setIdModule(con, idGroupeModule, idModule, position);
            return;
        }
        else{
            setIdModule(con, idGroupeModule, idModule, (position-1));
        }
    }
    
    public void retirerModule(Connection con, int idGroupeModule, int idModule)throws SQLException{
        int position = trouvePosition(con ,idModule);
        String sql = "UPDATE GroupeModule SET idModule "+(position)+" = -1 WHERE idGroupeModule = ?";
        try(PreparedStatement pst = con.prepareStatement(sql)){
            pst.setInt(1, idGroupeModule);
            pst.executeUpdate();
        }
    }

    public int getIdGroupeModule() {
        return idGroupeModule;
    }

    public void setIdGroupeModule(int idGroupeModule) {
        this.idGroupeModule = idGroupeModule;
    }
    
    public void saveGroupeModule(Connection con, String nom, int[] idModules, int nbrModules) 
        throws SQLException {
        GroupeModule groupeModule = new GroupeModule();
        String statement = "insert into GroupeModule (nom,";
        String questionMarks = "(?,";
        for (int i = 0; i < 10; i++){
            statement = statement + "idModule"+ String.valueOf(i+1) +",";
            questionMarks = questionMarks + "?,";
        }
            statement = statement + "nbrModules)";
            questionMarks = questionMarks + "?)";
        statement = statement + "values " + questionMarks;
        try (PreparedStatement pst = con.prepareStatement(statement, 
                PreparedStatement.RETURN_GENERATED_KEYS)) {
        pst.setString(1, nom);
        for (int i = 0; i < nbrModules; i++){
            pst.setInt((i+2), idModules[i]);
        }
        pst.executeUpdate();
        ResultSet nouvellesCles = pst.getGeneratedKeys();
        nouvellesCles.next();
        this.setIdGroupeModule(nouvellesCles.getInt(1));
        }
    }
    
    
}
