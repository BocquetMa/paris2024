/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sio.paris2024.model;

import java.util.ArrayList;

/**
 *
 * @author zakina
 */
public class Site {
    private int id;
    private String nom ;
    private String ville ;

    private String image ;

    private ArrayList<Sport> lesSports ;
    private ArrayList<Epreuve> lesEpreuves ;

    public Site() {
    }

    public Site(int id, String nom, String ville, String image) {
        this.id = id;
        this.nom = nom;
        this.ville = ville;
        this.image = image;
    }

    public Site(int id) {
        this.id = id;
    }
    
    public String getImage(){
        return image;
    }
    
    public void setImage(String image){
        this.image = image;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public ArrayList<Sport> getLesSports() {
        return lesSports;
    }

    public void setLesSports(ArrayList<Sport> lesSports) {
        this.lesSports = lesSports;
    }
    
    public void addSport(Sport s){
        
        if (lesSports == null){
            lesSports = new ArrayList<Sport>();
        }
        lesSports.add(s);
    }
    
    public ArrayList<Epreuve> getLesEpreuves() {
        return lesEpreuves;
    }

    public void setLesEpreuves(ArrayList<Epreuve> lesEpreuves) {
        this.lesEpreuves = lesEpreuves;
    }
    
    public void addEpreuve(Epreuve e){
        
        if (lesEpreuves == null){
            lesEpreuves = new ArrayList<Epreuve>();
        }
        lesEpreuves.add(e);
    }
    
}
