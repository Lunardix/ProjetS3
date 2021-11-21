/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author 57lun
 */
public class initialisationBaseDeDonnees {
    public void initialisation(){
        
        //Initialisation du SGBD
        SGBD postgres = new SGBD.Postgresql("localhost",
            5439, "postgres", "pass", "postgres");
                try (Connection con = postgres.connect()) {
            System.out.println("bien connecte");
            } catch (Exception ex) {
                throw new Error(ex);
            }
        try (Connection con = postgres.connect()) {
        initialisationTables(con);
        }
        catch (Exception ex) {
                throw new Error(ex);
            }
        }
    
    //méthode qui crée toutes les tables qui vont être utilisées
    public void initialisationTables(Connection con)throws SQLException{
        //initialisation table Personne
        try (Statement st = con.createStatement()) {
        st.executeUpdate(
        "create table Personne(id integer primary key generated always as identity,nom varchar(50) not null,prenom varchar(50) not null,dateNaissance date)");
        }
            
        //initialisation table Inscirption
        try (Statement st = con.createStatement()) {
        st.executeUpdate(
        "create table Inscription(id integer primary key generated always as identity,idModule int,idSemestre int,idPersonne int)");
        }
        
        //initialisation table Choix
        try (Statement st = con.createStatement()) {
        st.executeUpdate(
        "create table Choix(id integer primary key generated always as identity,idModule1 int,idModule2 int,idModule3 int,idPersonne int)");
        }
        
        //initialisation table Semestre
        try (Statement st = con.createStatement()) {
        st.executeUpdate(
        "create table Semestre(id integer primary key generated always as identity,annee int,numero int)");
        }
        
        //initialisation table Ouvert
        try (Statement st = con.createStatement()) {
        st.executeUpdate(
        "create table Ouvert(id integer primary key generated always as identity,idModule int,idSemestre int)");
        }
        
        //initialisation table Module
        try (Statement st = con.createStatement()) {
        st.executeUpdate(
        "create table Module(id integer primary key generated always as identity,intitule varchar(50),description text,idModule int,idSemestre int,idAnnee int,nbrDePlaces int,idResponsable int)");
        }
    }
}
