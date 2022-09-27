package com.julex;

public class Sport extends Voiture {
    private int vitesseMax;

    public Sport() {
    }

    public Sport(String fabricant, int annee, String couleur, int nbPortes, int nbPlaces, int vitesseMax) {
        super(fabricant, annee, couleur, nbPortes, nbPlaces);
        this.vitesseMax = vitesseMax;
    }

    public int getVitesseMax() {
        return vitesseMax;
    }

    public void setVitesseMax(int vitesseMax) {
        this.vitesseMax = vitesseMax;
    }    
}
