package com.julex;

public class SUV extends Voiture {
    private boolean estElectrique;

    public SUV() {
    }    

    public SUV(String fabricant, int annee, String couleur, int nbPortes, int nbPlaces, boolean estElectrique) {
        super(fabricant, annee, couleur, nbPortes, nbPortes);
        this.estElectrique = estElectrique;
    }

    public boolean isEstElectrique() {
        return estElectrique;
    }

    public void setEstElectrique(boolean estElectrique) {
        this.estElectrique = estElectrique;
    }

    /** Dans cette méthode toString() de la classe dérivée de Voiture on affiche tout d'abord les attributs relatifs
     *  à la superclasse ensuite ceux de la classe dérivée.
     */
    @Override
    public String toString() {
        return super.toString() + " -- SUV [estElectrique=" + estElectrique + "]";
    }
    
}
