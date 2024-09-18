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
import sio.paris2024. model.Sport;
import sio.paris2024. model.Epreuve;

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
                    + " sport.nom AS s_nom"
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
    
  

    public static ArrayList<Epreuve> getLesEpreuvesBySport(Connection cnx, int idEpreuve){
        
        ArrayList<Epreuve> lesEpreuves = new ArrayList<Epreuve>();
        Epreuve e = null;
        
        try{
            requeteSql = cnx.prepareStatement ("SELECT epreuve.id AS e_id, epreuve.nom AS e_nom FROM epreuve INNER JOIN sport ON sport.id = epreuve.sport_id WHERE epreuve.sport_id = ?;");
            requeteSql.setInt(1, idEpreuve);
            resultatRequete = requeteSql.executeQuery();
        
        while (resultatRequete.next()){
            
            e = new Epreuve();
            e.setId(resultatRequete.getInt("e_id"));
            e.setNom(resultatRequete.getString("e_nom"));
           
            lesEpreuves.add(e);
            }
        }
        
        
        catch (SQLException es){
            System.out.println("La requête de getLesEpreuvesBySite a généré une erreur");
        }
        return lesEpreuves;
    }
    
    public static Sport getSportById(Connection cnx, int idSport){
        
        Sport s = null ;
        try{
            requeteSql = cnx.prepareStatement("SELECT sport.id AS s_id,"
                + " sport.nom AS s_nom"
                + " FROM sport"
                + " WHERE sport.id = ?;");
            requeteSql.setInt(1, idSport);
            resultatRequete = requeteSql.executeQuery();
            
            if (resultatRequete.next()){
                
                    s = new Sport();
                    s.setId(resultatRequete.getInt("s_id"));
                    s.setNom(resultatRequete.getString("s_nom"));
                    
                    ArrayList<Epreuve> lesEpreuves = DaoSport.getLesEpreuvesBySport(cnx, idSport);
                    s.setLesEpreuves(lesEpreuves);
                    
                    
            }
           
        }
        catch (SQLException e){
            System.out.println("La requête de getSiteById a généré une erreur");
        }
        return s ;
    }
}
    
   