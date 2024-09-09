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
import sio.paris2024.model.Sport;

/**
 *
 * @author mahdi.ashuri
 */
public class DaoSport {
    
    Connection cnx;
    static PreparedStatement requeteSql = null;
    static ResultSet resultatRequete = null;
    
    public static ArrayList<Sport> getLesSports(Connection cnx){
        
        ArrayList<Sport> lesSports = new ArrayList<Sport>();
        try{
            requeteSql = cnx.prepareStatement("SELECT sport.id AS s_id,"
                    + " sport.nom AS s_nom,"
                    + " FROM sport;");
            resultatRequete = requeteSql.executeQuery();
            
            while (resultatRequete.next()){
                
                Sport s = new Sport();
                    s.setId(resultatRequete.getInt("s_id"));
                    s.setNom(resultatRequete.getString("s_nom"));
                     
                lesSports.add(s);
            }
           
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println("La requête de getLesSports a généré une erreur");
        }
        return lesSports;
    }
    
}