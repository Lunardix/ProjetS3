
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
    
    static int nbrModulesMax = 10;

    
    
    public void setNbrModulesMax(int nbr){
        nbrModulesMax = nbr;
    }
    
    public GroupeModule() {
        this.idGroupeModule = -1;
    }
    
    public void setIdModule (Connection con, int idGroupeModule, int idModule, int position)throws SQLException{
        String sql = "UPDATE GroupeModule SET idModule "+(position)+" = ? WHERE id = ?";
        try(PreparedStatement pst = con.prepareStatement(sql)){
            pst.setInt(1, idModule);
            pst.setInt(2, idGroupeModule);
            pst.executeUpdate();
        }
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
    
    public void setIdModule(Connection con, int idGroupeModule, int idAncienModule, int idNouveauModule)throws SQLException{
        String sql = "UPDATE GroupeModule SET nom = ? WHERE id = ? AND idModule%";
        try(PreparedStatement pst = con.prepareStatement(sql)){
            pst.setInt(1, idGroupeModule);
            pst.setInt(2, idGroupeModule);
            pst.setInt(3, idGroupeModule);
            pst.executeUpdate();
        }
    }
    
    
    
    public void ajouterModule(Connection con, int idGroupeModule, int idModule, int position){
        if (position == 11){
            return;
        }
        else if (this.getIdModule(position) == -1 ){
            setIdModule(con, idGroupeModule, idModule, position);
            return;
        }
        else{
            ajouterModule(con, idGroupeModule, idModule, (position-1));
        }
    }
    
    public void retirerModule(Connection con, int idGroupeModule, int idModule){
        int position = trouvePosition(Connection con, int idModule);
        String sql = "UPDATE GroupeModule SET idModule "+(position)+" = -1 WHERE idModule = ?";
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
    
    public void saveGroupeModule(Connection con, String nom, int idModule1,int idModule2,int idModule3,int idModule4,int idModule5,int idModule6,int idModule7,int idModule8,int idModule9,int idModule10) 
        throws SQLException {
        
        GroupeModule groupeModule = new GroupeModule();
        String statement = "insert into GroupeModule (nom,";
        String questionMarks = "(?,";
        for (int i = 0; i< 10; i++){
                statement = statement + "idModule"+ String.valueOf(i+1) +",";
                questionMarks = questionMarks + "?,";
                statement = statement + "module"+ (i+1) +")";
                questionMarks = questionMarks + "?)";
        }
        statement = statement + "values " + questionMarks;
        try (PreparedStatement pst = con.prepareStatement(statement, 
                PreparedStatement.RETURN_GENERATED_KEYS)) {
        pst.setString(1, nom);
        for (int i = 0; i< this.getSize(); i++){
            pst.setInt((i+2), listeModules.get(i));
        }
        pst.executeUpdate();
        ResultSet nouvellesCles = pst.getGeneratedKeys();
        nouvellesCles.next();
        this.setIdGroupeModule(nouvellesCles.getInt(1));
        }
    }
    
    
}
