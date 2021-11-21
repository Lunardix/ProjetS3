
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

 
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Integer> getListeModules() {
        return listeModules;
    }
    
   public int getSize(){
       return this.listeModules.size();
   }

    public void setListeModules(ArrayList<Integer> listeModules) {
        this.listeModules = listeModules;
    }
    
    // ici on utilisera une fct changeIdModule(numéroDuModuleAChanger, idNouveauModule) 
    //
    //
    //
    //
    ///////////////////////////////////////////////////////////////////////////////////
    
    
    
    public void ajouterModule(int idModule){
        if (this.getSize() == nbrModulesMax){
            return;
            // affichage de l'erreur (groupe trop grand)
        }
        this.listeModules.add(idModule);
    }
    
    public void retirerModule(int idModule){
        this.listeModules.remove(idModule);
    }

    public int getIdGroupeModule() {
        return idGroupeModule;
    }

    public void setIdGroupeModule(int idGroupeModule) {
        this.idGroupeModule = idGroupeModule;
    }
    
    public void saveGroupeModule(Connection con, String nom, ArrayList<Integer> listeModules) 
        throws SQLException {
        
        GroupeModule groupeModule = new GroupeModule();
        String statement = "insert into GroupeModule (nom,";
        String questionMarks = "(?,";
        for (int i = 0; i< this.getSize(); i++){
            if (i != (this.getSize()-1)){
                statement = statement + "module"+ String.valueOf(i+1) +",";
                questionMarks = questionMarks + "?,";
            }
            else {
                statement = statement + "module"+ (i+1) +")";
                questionMarks = questionMarks + "?)";
            }
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
