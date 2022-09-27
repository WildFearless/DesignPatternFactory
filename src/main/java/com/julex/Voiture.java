package com.julex;

public abstract class Voiture {
    private String fabricant;
    private int annee;
    private String couleur;
    private int nbPortes;
    private int nbPlaces;

    public Voiture() {
    }

    public Voiture(String fabricant, int annee, String couleur, int nbPortes, int nbPlaces) {
        this.fabricant = fabricant;
        this.annee = annee;
        this.couleur = couleur;
        this.nbPortes = nbPortes;
        this.nbPlaces = nbPlaces;
    }   

    public String getFabricant() {
        return fabricant;
    }

    public void setFabricant(String fabricant) {
        this.fabricant = fabricant;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public int getNbPortes() {
        return nbPortes;
    }

    public void setNbPortes(int nbPortes) {
        this.nbPortes = nbPortes;
    }

    public int getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    @Override
    public String toString() {
        return "Voiture [annee=" + annee + ", couleur=" + couleur + ", fabricant=" + fabricant + ", nbPlaces="
                + nbPlaces + ", nbPortes=" + nbPortes + "]";
    } 

}
