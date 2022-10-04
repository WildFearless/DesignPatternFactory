package com.julex;

public class Familiale extends Voiture {
    private boolean contientTelevision;

    public Familiale() {

    }

    public Familiale(String fabricant, int annee, String couleur, int nbPortes, int nbPlaces,
            boolean contientTelevision) {
        super(fabricant, annee, couleur, nbPortes, nbPlaces);
        this.contientTelevision = contientTelevision;
    }

    public boolean isContientTelevision() {
        return this.contientTelevision;
    }

    public boolean getContientTelevision() {
        return this.contientTelevision;
    }

    public void setContientTelevision(boolean contientTelevision) {
        this.contientTelevision = contientTelevision;
    }
    
    /** Dans cette méthode toString() de la classe dérivée de Voiture on affiche tout d'abord les attributs relatifs
     *  à la superclasse ensuite ceux de la classe dérivée.
     */
    @Override
    public String toString() {
        return super.toString() + " -- Familiale [contientTelevision=" + contientTelevision + "]";
    }

    
}
