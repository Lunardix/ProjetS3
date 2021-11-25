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

    
    public String getRandomString(Connection con, String username)throws SQLException {
        String query = "SELECT * FROM Personne WHERE username = ?";
        try(PreparedStatement pst = con.prepareStatement(query)){
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                return rs.getString("randomString");
            }
            else {return null;}
        }
    }
    
    public String getNom(Connection con, String username)throws SQLException {
        String query = "SELECT * FROM Personne WHERE username = ?";
        try(PreparedStatement pst = con.prepareStatement(query)){
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                return rs.getString("nom");
            }
            else {return null;}
        }
    }

    public String getPrenom(Connection con, String username)throws SQLException {
        String query = "SELECT * FROM Personne WHERE username = ?";
        try(PreparedStatement pst = con.prepareStatement(query)){
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                return rs.getString("prenom");
            }
            else {return null;}
        }
    }
    
   
    public Date getDate(Connection con, String username)throws SQLException {
        String query = "SELECT * FROM Personne WHERE username = ?";
        try(PreparedStatement pst = con.prepareStatement(query)){
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                return rs.getDate("date");
            }
            else {return null;}
        }
    }

    public String getEmail(Connection con, String username)throws SQLException {
        String query = "SELECT * FROM Personne WHERE username = ?";
        try(PreparedStatement pst = con.prepareStatement(query)){
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                return rs.getString("email");
            }
            else {return null;}
        }
    }

    public String getMdp(Connection con, String username)throws SQLException {
        String query = "SELECT * FROM Personne WHERE username = ?";
        try(PreparedStatement pst = con.prepareStatement(query)){
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                return rs.getString("hashMdp");
            }
            else {return null;}
        }
    }

    public void setMdp(Connection con, String username, String mdp)throws SQLException {
        String sql = "UPDATE Personne SET mdp = ? WHERE username = ?";
        try(PreparedStatement pst = con.prepareStatement(sql)){
            pst.setString(1, mdp);
            pst.setString(2, username);
            pst.executeUpdate();
        }
    }
    
    public void setEmail(Connection con, String username, String email)throws SQLException {
        String sql = "UPDATE Personne SET email = ? WHERE username = ?";
        try(PreparedStatement pst = con.prepareStatement(sql)){
            pst.setString(1, email);
            pst.setString(2, username);
            pst.executeUpdate();
        }
    }
    
    public void setDate(Connection con, String username, java.sql.Date date)throws SQLException {
        String sql = "UPDATE Personne SET date = ? WHERE username = ?";
        try(PreparedStatement pst = con.prepareStatement(sql)){
            pst.setDate(1, date);
            pst.setString(2, username);
            pst.executeUpdate();
        }
    }

    public void setPrenom(Connection con, String username, String prenom)throws SQLException {
        String sql = "UPDATE Personne SET prenom = ? WHERE username = ?";
        try(PreparedStatement pst = con.prepareStatement(sql)){
            pst.setString(1, prenom);
            pst.setString(2, username);
            pst.executeUpdate();
        }
    }

    public void setNom(Connection con, String username, String nom)throws SQLException {
        String sql = "UPDATE Personne SET nom = ? WHERE username = ?";
        try(PreparedStatement pst = con.prepareStatement(sql)){
            pst.setString(1, nom);
            pst.setString(2, username);
            pst.executeUpdate();
        }
    }

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }
    
    public int getNombreUtilisateursMemeNom(Connection con, String prenom, String nom)throws SQLException{
        int nombre = 0;
        String query = "SELECT COUNT (*) AS compte FROM Personne WHERE prenom = ? AND nom = ?";
        try(PreparedStatement pst = con.prepareStatement(query)){
            pst.setString(1, prenom);
            pst.setString(2, nom);
            ResultSet rs = pst.executeQuery();
                nombre = rs.getInt("compte");
        }
        return nombre;
    }
    
    public int userConnection (Connection con, String username, String mdp, int admin)throws SQLException{
        taille = (int) ((Math.random() * (30)) + 20);
        String randomString = getRandomString(con, username);
        String hashMdp = hash.hash(mdp, randomString);
        String compteUtilisateurEnregistre = "SELECT * FROM Personne WHERE username = ? AND hashMdp = ?";
        try(PreparedStatement checkUser = con.prepareStatement(compteUtilisateurEnregistre)) {
            checkUser.setString(1, username);
            checkUser.setString(2, hashMdp);
        ResultSet resultUser = checkUser.executeQuery();
        
        if(resultUser.next()){
            
            String compteAdministrateurEnregistre = "SELECT * FROM Personne WHERE username = ? AND hashMdp = ? AND admin = 1" ;
            try(PreparedStatement checkAdmin = con.prepareStatement(compteAdministrateurEnregistre)) {
                checkAdmin.setString(1, username);
                checkAdmin.setString(2, hashMdp);
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
        taille = (int) ((Math.random() * (30)) + 20);
        String randomString = hash.randomString(taille);
        String hashMdp = hash.hash(mdp, randomString);
        
        int numeroUser = getNombreUtilisateursMemeNom(con, prenom, nom) + 1;
        
        String username = prenom.charAt(0) + nom + String.format("%02d", numeroUser);
        
        try (PreparedStatement pst = con.prepareStatement(
        "insert into Personne (date,prenom,nom,email,randomString,username,hashMdp,admin)values (?,?,?,?,?,?,?)", 
                PreparedStatement.RETURN_GENERATED_KEYS)) {
        pst.setDate(1, date);
        pst.setString(2, prenom);
        pst.setString(3, nom);
        pst.setString(4, email);
        pst.setString(5, randomString);
        pst.setString(6, username);
        pst.setString(7, hashMdp);
        pst.setBoolean(8, admin);
        pst.executeUpdate();
        ResultSet nouvellesCles = pst.getGeneratedKeys();
        nouvellesCles.next();
        System.out.println("Le nom d'utilisateur de l'utilisateur enregistre est : " + username);
        this.setIdPersonne(nouvellesCles.getInt(1));
        }
    }
    public void deletePersonne(Connection con, String username) throws SQLException {
        
        try (PreparedStatement pst = con.prepareStatement("DELETE FROM Personne WHERE username = ?")) {
        pst.setString(1, username);
        pst.executeUpdate();
        System.out.println("L'utilisateur " + username + " a ete supprime.");
        }
    }  
}
