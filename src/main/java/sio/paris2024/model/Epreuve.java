/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sio.paris2024.model;


/**
 *
 * @author zakina
 */
public class Epreuve {
    private int id;
    private String nom ;



    public Epreuve() {
    }

    public Epreuve(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Epreuve(int id) {
        this.id = id;
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
   
    
}
