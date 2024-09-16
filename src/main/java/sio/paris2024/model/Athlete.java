/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sio.paris2024.model;

import java.time.LocalDate;

/**
 *
 * @author zakina
 */
public class Athlete {
    
    private int id;
    private String nom ;
    private String prenom ;
    private String image;
    private LocalDate dateNaiss ;
    private Pays pays ;
    
    public Athlete() {
    }

    public Athlete(int id, String nom, String prenom, String image, LocalDate dateNaiss) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.image = image;
        this.dateNaiss = dateNaiss;
    }

    public int getId() {
        return id;
    }
    
    public String getImage(){
        return image;
    }

    public void setImage(String image){
        this.image = image;
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

    public String getPrenom(){
        return prenom;
    }
    
    public void setPrenom(String prenom){
        this.prenom = prenom;
    }
    
    public LocalDate getDateNaiss(){
        return dateNaiss;
    }
    
    public void setDateNaiss(LocalDate dateNaiss){
        this.dateNaiss = dateNaiss;
    }
    
    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }
    
    
}
