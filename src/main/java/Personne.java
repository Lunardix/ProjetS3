/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author lyoubi01
 */
public class Personne {
    
    private int idPersonne;
    public int taille;
    
    Hash hash;

    
    
    public int getIdPersonne() {
        return idPersonne;
    }

    
    public String getRandomString(Connection con, String prenom, String nom)throws SQLException {
        String query = "SELECT * FROM Personne WHERE prenom = ? AND nom = ?";
        try(PreparedStatement pst = con.prepareStatement(query)){
            pst.setString(1, prenom);
            pst.setString(2, nom);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                return rs.getString("randomString");
            }
            else {return null;}
        }
    }
    
    public String getNom(Connection con, int id)throws SQLException {
        String query = "SELECT * FROM Personne WHERE id = ?";
        try(PreparedStatement pst = con.prepareStatement(query)){
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                return rs.getString("nom");
            }
            else {return null;}
        }
    }

    public String getPrenom(Connection con, int id)throws SQLException {
        String query = "SELECT * FROM Personne WHERE id = ?";
        try(PreparedStatement pst = con.prepareStatement(query)){
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                return rs.getString("prenom");
            }
            else {return null;}
        }
    }
    
   
    public Date getDate(Connection con, int id)throws SQLException {
        String query = "SELECT * FROM Personne WHERE id = ?";
        try(PreparedStatement pst = con.prepareStatement(query)){
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                return rs.getDate("date");
            }
            else {return null;}
        }
    }

    public String getEmail(Connection con, int id)throws SQLException {
        String query = "SELECT * FROM Personne WHERE id = ?";
        try(PreparedStatement pst = con.prepareStatement(query)){
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                return rs.getString("email");
            }
            else {return null;}
        }
    }

    public String getMdp(Connection con, int id)throws SQLException {
        String query = "SELECT * FROM Personne WHERE id = ?";
        try(PreparedStatement pst = con.prepareStatement(query)){
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                return rs.getString("mdp");
            }
            else {return null;}
        }
    }

    public void setMdp(Connection con, int id, String mdp)throws SQLException {
        String sql = "UPDATE Personne SET mdp = ? WHERE id = ?";
        try(PreparedStatement pst = con.prepareStatement(sql)){
            pst.setString(1, mdp);
            pst.setInt(2, id);
            pst.executeUpdate();
        }
    }
    
    public void setEmail(Connection con, int id, String email)throws SQLException {
        String sql = "UPDATE Personne SET email = ? WHERE id = ?";
        try(PreparedStatement pst = con.prepareStatement(sql)){
            pst.setString(1, email);
            pst.setInt(2, id);
            pst.executeUpdate();
        }
    }
    
    public void setDate(Connection con, int id, java.sql.Date date)throws SQLException {
        String sql = "UPDATE Personne SET date = ? WHERE id = ?";
        try(PreparedStatement pst = con.prepareStatement(sql)){
            pst.setDate(1, date);
            pst.setInt(2, id);
            pst.executeUpdate();
        }
    }

    public void setPrenom(Connection con, int id, String prenom)throws SQLException {
        String sql = "UPDATE Personne SET prenom = ? WHERE id = ?";
        try(PreparedStatement pst = con.prepareStatement(sql)){
            pst.setString(1, prenom);
            pst.setInt(2, id);
            pst.executeUpdate();
        }
    }

    public void setNom(Connection con, int id, String nom)throws SQLException {
        String sql = "UPDATE Personne SET nom = ? WHERE id = ?";
        try(PreparedStatement pst = con.prepareStatement(sql)){
            pst.setString(1, nom);
            pst.setInt(2, id);
            pst.executeUpdate();
        }
    }

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }
    
    public int userConnection (Connection con, String prenom, String nom, String mdp, int admin)throws SQLException{
        taille = (int) ((Math.random() * (30)) + 20);
        String randomString = getRandomString();
        String hashMdp = hash.hash(mdp, randomString);
        String compteUtilisateurEnregistre = "SELECT * FROM Personne WHERE prenom = "+ prenom +" AND nom = "+ nom+ " AND hashMdp = "+ hashMdp ;
        try(PreparedStatement checkUser = con.prepareStatement(compteUtilisateurEnregistre)) {
        ResultSet resultUser = checkUser.executeQuery();
        
        if(resultUser.next()){
            
            String compteAdministrateurEnregistre = "SELECT * FROM Personne WHERE prenom = "+ prenom +" AND nom = "+ nom+ " AND mdp = "+mdp +" AND admin = 1" ;
            try(PreparedStatement checkAdmin = con.prepareStatement(compteAdministrateurEnregistre)) {
            ResultSet resultAdmin = checkAdmin.executeQuery();
            if (resultAdmin.next()){
                return 1;
            }
            else {return 0;}
            }
        }
        
        else {
        return -1;
        }
        }
    }

    
    public Personne(){
        this.idPersonne = -1;
    }
    
    public void savePersonne(Connection con, java.sql.Date date, String prenom, String nom, String mdp, String email, boolean admin) 
        throws SQLException {
        
        Personne personne = new Personne();
        String randomString = hash.randomString();
        String hashMdp = Hash.hash(mdp, randomString);
        
        try (PreparedStatement pst = con.prepareStatement(
        "insert into Personne (date,prenom,nom,email,randomString,hashMdp,admin)values (?,?,?,?,?,?,?)", 
                PreparedStatement.RETURN_GENERATED_KEYS)) {
        pst.setDate(1, date);
        pst.setString(2, prenom);
        pst.setString(3, nom);
        pst.setString(4, email);
        pst.setString(5, randomString);
        pst.setString(6, hashMdp);
        pst.setBoolean(7, admin);
        pst.executeUpdate();
        ResultSet nouvellesCles = pst.getGeneratedKeys();
        nouvellesCles.next();
        this.setIdPersonne(nouvellesCles.getInt(1));
        }
    }
    
}
