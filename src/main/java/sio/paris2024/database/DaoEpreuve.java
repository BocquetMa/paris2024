package sio.paris2024.database;

/**
 *
 * @author ts1sio
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import sio.paris2024.model.Epreuve;

/**
 *
 * @author mahdi.ashuri
 */
public class DaoEpreuve {
    
    Connection cnx;
    static PreparedStatement requeteSql = null;
    static ResultSet resultatRequete = null;
    
    public static ArrayList<Epreuve> getLesEpreuves(Connection cnx){
        
        ArrayList<Epreuve> lesEpreuves = new ArrayList<Epreuve>();
        try{
            requeteSql = cnx.prepareStatement("SELECT epreuve.id AS e_id,"
                    + " epreuve.nom AS e_nom,"
                    + " FROM epreuve;");
            resultatRequete = requeteSql.executeQuery();
            
            while (resultatRequete.next()){
                
                Epreuve e = new Epreuve();
                    e.setId(resultatRequete.getInt("e_id"));
                    e.setNom(resultatRequete.getString("e_nom"));
                     
                lesEpreuves.add(e);
            }
           
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println("La requête de getLesEpreuves a généré une erreur");
        }
        return lesEpreuves;
    }
    
}