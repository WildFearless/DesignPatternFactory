package com.julex;


public class Main 
{
    public static void main( String[] args ) {

        FamilialeFactory factoryFamiliale = new FamilialeFactory();
        SportFactory factorySport = new SportFactory();
        SUVFactory factorySuv = new SUVFactory();

        Voiture voitureFamiliale = factoryFamiliale.fabriquerVoiture();
        Voiture voitureSport = factorySport.fabriquerVoiture();
        Voiture voitureSUV = factorySuv.fabriquerVoiture();

        System.out.println("============On affiche tout d'abord les toString provenant de la classe Voiture============");
        System.out.println(voitureFamiliale);
        System.out.println(voitureSport);
        System.out.println(voitureSUV);
        System.out.println("\n");

        System.out.println("============On affiche ensuite les toString provenant des classes dérivées=================");       
        System.out.println((Familiale) voitureFamiliale);
        System.out.println((Sport) voitureSport);
        System.out.println((SUV) voitureSUV);
    }
}