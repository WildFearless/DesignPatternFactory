package com.julex;

public class SUVFactory implements VoitureFactory{
    public Voiture fabriquerVoiture(){
        Voiture suv = new SUV("Dodge", 2022, "Noir", 4, 5, true);

        return suv;
    }
}
