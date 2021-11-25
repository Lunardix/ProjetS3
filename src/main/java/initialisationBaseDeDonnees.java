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
    
    //methode qui cree toutes les tables qui vont etre utilisees
    public void initialisationTables(Connection con)throws SQLException{
        //initialisation table Personne
        try (Statement st = con.createStatement()) {
        st.executeUpdate(
        "create table Personne(id integer primary key generated always as identity,dateNaissance date,prenom varchar(50) not null,nom varchar(50) not null,email varchar(50),randomString varchar(50),username varchar(50),hashMdp varchar(64)");
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
        "create table Semestre(id integer primary key generated always as identity,annee int,numero int,Ng int)");
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
        
        //initialisation table GroupeModule
        try(Statement st = con.createStatement()){
            st.executeUpdate(
            "create table GroupeModule (id integer primary key generated always as identity, nom varchar(40),idModule1 int default -1,idModule2 int default -1,idModule3 int default -1,idModule4 int default -1,idModule5 int default -1,idModule6 int default -1,idModule7 int default -1,idModule8 int default -1,idModule9 int default -1,idModule10 int default -1");
        }
    }
}
