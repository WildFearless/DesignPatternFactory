package com.julex;

public class SportFactory implements VoitureFactory {
    public Voiture fabriquerVoiture() {
        Voiture sport = new Sport("Ferrari", 2022, "Rouge", 2, 2, 350);

        return sport;
    }
}
