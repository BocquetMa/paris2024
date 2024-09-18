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
import sio.paris2024.model.Site;
import sio.paris2024. model.Sport;
import sio.paris2024. model.Epreuve;

/**
 *
 * @author mahdi.ashuri
 */
public class DaoSite {
    
    Connection cnx;
    static PreparedStatement requeteSql = null;
    static ResultSet resultatRequete = null;
    
    public static ArrayList<Site> getLesSites(Connection cnx){
        
        ArrayList<Site> lesSites = new ArrayList<Site>();
        try{
            requeteSql = cnx.prepareStatement("SELECT site.id AS s_id,"
                    + " site.nom AS s_nom,"
                    + " site.ville AS s_ville,"
                    + " site.image AS s_image"
                    + " FROM site;");
            resultatRequete = requeteSql.executeQuery();
            
            while (resultatRequete.next()){
                
                Site s = new Site();
                    s.setId(resultatRequete.getInt("s_id"));
                    s.setNom(resultatRequete.getString("s_nom"));
                    s.setVille(resultatRequete.getString("s_ville"));
                    s.setImage(resultatRequete.getString("s_image"));
                     
                lesSites.add(s);
            }
           
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println("La requête de getLesSites a généré une erreur");
        }
        return lesSites;
    }
    
   
    public static ArrayList<Sport> getLesSportsBySite(Connection cnx, int idSport){
        
        ArrayList<Sport> lesSports = new ArrayList<Sport>();
        Sport s = null;
        
        try{
            requeteSql = cnx.prepareStatement ("SELECT sport.id AS s_id, sport.nom AS s_nom FROM sport INNER JOIN site ON site.id = sport.site_id WHERE sport.site_id = ?;");
            requeteSql.setInt(1, idSport);
            resultatRequete = requeteSql.executeQuery();
        
        while (resultatRequete.next()){
            
            s = new Sport();
            s.setId(resultatRequete.getInt("s_id"));
            s.setNom(resultatRequete.getString("s_nom"));
           
            lesSports.add(s);
            }
        }
        
         
        catch (SQLException e){
            e.printStackTrace();
            System.out.println("La requête de getLesSportsBySite a généré une erreur");
        }
        return lesSports;
    }
    
    public static Site getSiteById(Connection cnx, int idSite){
        
        Site s = null ;
        try{
            requeteSql = cnx.prepareStatement("SELECT site.id AS s_id,"
                + " site.nom AS s_nom,"
                + " site.ville AS s_ville,"
                + " site.image AS s_image"
                + " FROM site"
                + " WHERE site.id = ?;");
            requeteSql.setInt(1, idSite);
            resultatRequete = requeteSql.executeQuery();
            
            if (resultatRequete.next()){
                
                    s = new Site();
                    s.setId(resultatRequete.getInt("s_id"));
                    s.setNom(resultatRequete.getString("s_nom"));
                    s.setVille(resultatRequete.getString("s_ville"));
                    s.setImage(resultatRequete.getString("s_image"));
                    
                    ArrayList<Sport> lesSports = DaoSite.getLesSportsBySite(cnx, idSite);
                    s.setLesSports(lesSports);
                    
                    
            }
           
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println("La requête de getSiteById a généré une erreur");
        }
        return s ;
    }
    
    public static Site addSite(Connection connection, Site sit) {      
        int idGenere = -1;
            try {
        // Préparation de la requête
            requeteSql = connection.prepareStatement("INSERT INTO site (nom, ville, image) VALUES (?, ?, ?)", requeteSql.RETURN_GENERATED_KEYS);
            requeteSql.setString(1, sit.getNom());      
            requeteSql.setString(2, sit.getVille());
            requeteSql.setString(3, sit.getImage());
        
        // Exécution de la requête
            requeteSql.executeUpdate();
        
        // Récupération de l'id auto-généré
            resultatRequete = requeteSql.getGeneratedKeys();
        if (resultatRequete.next()) {
            idGenere = resultatRequete.getInt(1);
            sit.setId(idGenere);
            sit = getSiteById(connection, sit.getId());
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return sit;    
}
    
}