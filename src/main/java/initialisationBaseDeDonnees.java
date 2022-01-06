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
        initialisationSchema(con);
        }
        catch (Exception ex) {
                throw new Error(ex);
            }
        }
    
    //methode qui cree toutes les tables qui vont etre utilisees
    public void initialisationSchema(Connection con)throws SQLException{
        //initialisation schema base de donnÃ©es
        try (Statement st = con.createStatement()) {
            st.executeUpdate(
            "create schema BaseDeDonnees");
        
            //initialisation table Personne
            st.executeUpdate(
            "create table Personne(dateNaissance date,prenom varchar(50) not null,nom varchar(50) not null,email varchar(50),randomString varchar(50),username varchar(50),hashMdp varchar(64)");
            
            //initialisation table Inscirption
            st.executeUpdate(
            "create table Inscription(id integer primary key generated always as identity,idModule int,idPersonne int,idSemestre int)");
        
        
            //initialisation table Choix
            st.executeUpdate(
            "create table Choix(id integer primary key generated always as identity,idPersonne int,idModule1 int,idModule2 int,idModule3 int,idGroupeModule int)");
        
            //initialisation table Semestre
            st.executeUpdate(
            "create table Semestre(id integer primary key generated always as identity,annee int,numero int,Ng int)");
        
            //initialisation table Ouvert
            st.executeUpdate(
            "create table Ouvert(id integer primary key generated always as identity,idModule int,idSemestre int)");
        
            //initialisation table Module
            st.executeUpdate(
            "create table Module(id integer primary key generated always as identity,intitule varchar(50),description text,idModule int,idSemestre int,idAnnee int,nbrDePlaces int,idResponsable int)");
        
            //initialisation table GroupeModule
            st.executeUpdate(
            "create table GroupeModule (id integer primary key generated always as identity, nom varchar(40),idModule1 int default -1,idModule2 int default -1,idModule3 int default -1,idModule4 int default -1,idModule5 int default -1,idModule6 int default -1,idModule7 int default -1,idModule8 int default -1,idModule9 int default -1,idModule10 int default -1, nbrModules int default 0");
        
            
            //Ajout du lien entre les tables, on spécifie que idPersonne est une clé étrangère dans la table inscription 
            st.executeUpdate("alter table Inscription add constraint fk_inscription_personne foreign key (idPersonne) references Personne (id)");
            
            //Ajout du lien entre les tables, on spécifie que idModule est une clé étrangère dans la table inscription
            st.executeUpdate("alter table Inscription add constraint fk_inscription_module foreign key (idModule) references Module (id)");
            
            //Ajout du lien entre les tables, on spécifie que idSemestre est une clé étrangère dans la table inscription 
            st.executeUpdate("alter table Inscription add constraint fk_inscription_semestre foreign key (idSemestre) references Semestre (id)");
            
            //Ajout du lien entre les tables, on spécifie que idPersonne est une clé étrangère dans la table choix 
            st.executeUpdate("alter table Choix add constraint fk_choix_personne foreign key (idPersonne) references Personne (id)");
            
            //Ajout du lien entre les tables, on spécifie que idModule1 est une clé étrangère dans la table choix 
            st.executeUpdate("alter table Choix add constraint fk_choix_module1 foreign key (idModule1) references Module (id)");
            //Ajout du lien entre les tables, on spécifie que idModule2 est une clé étrangère dans la table choix 
            st.executeUpdate("alter table Choix add constraint fk_choix_module2 foreign key (idModule2) references Module (id)");
            //Ajout du lien entre les tables, on spécifie que idModule3 est une clé étrangère dans la table choix 
            st.executeUpdate("alter table Choix add constraint fk_choix_module3 foreign key (idModule3) references Module (id)");
            
            //Ajout du lien entre les tables, on spécifie que idGroupeModule est une clé étrangère dans la table choix 
            st.executeUpdate("alter table Choix add constraint fk_choix_groupeModule foreign key (idGroupeModule) references GroupeModule (id)");
            
        }
    }
}
