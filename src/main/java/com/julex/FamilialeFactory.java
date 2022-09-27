package com.julex;

public class FamilialeFactory implements VoitureFactory {
    public Voiture fabriquerVoiture() {
        Voiture familiale = new Familiale("Ford", 2022, "Brun", 3, 8, true);

        return familiale;
    }
    
}
